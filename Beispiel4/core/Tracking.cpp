
#include "Tracking.h"
#include <fstream>

using namespace cv;
using namespace std;

CvRNG Tracking::rng(0xffffffff);

Tracking::Tracking()
{
	video = NULL;
	firstFrame = true;
	frameNumber = 0;
	_g = NULL;

	
	//parameters for image processing
	_alpha = 0.0005;
	_motionThresh = 12;
	_threshold = 17;
	_erodeSize = 1;
	_dilateSize= 3;
	_blobsize = 12;

	//blob conditions
	_blob_top_margin = 	75;
	_blob_bottom_margin = 60;
	_blob_right_margin = 110;
	_blob_left_margin = 212;


	// flags for image processing
	_bTracking = false;
	_bBlobbing = true;
	_bStartRecording = false;
	_bPaused = false;
	_bOneStep = false;
	_bShowDirection = false;
	_bShowLabel = true;
	_bShowAngle = true;
	_bShowSpeed = false;
	_bShowCenter = false;
	_bShowBlob = false;
	_bShowBox = false;
	_bShowEllipse = true;
	_bShowTrajectory = false;
	_bShowPredictionPoint = false;
	_bShowMotionVector = false;
	_bShowLastPose = false;
	_bHomographie = false;
	_delayTime = 33;
	_trajectorySize = 0;

	//resources video
	int _cameraNr = 0;
	_videopath = "";
	_recordingpath = ".";
	_outputpath = ".";
	_vtype = VIDEO_FROM_FILE;
	_wtype = WINDOW_ORIGINAL;
	_fps = 30;
	_fourcc = CV_FOURCC('I','4','2','0');
	_xRes = 0;
	_yRes = 0;

	//homography
	_arena.clear();
	_rectifiedArenaCoordinates.clear();
	//_H; 	//Transformation Matrix
	//_q;
	_vertical_ratio = 1000;
	_horizontal_ratio = 1000;
}

void Tracking::initTrackingArea()
{
	//delete old video, if exist
	if(video)
		delete video;

	frameNumber = 0;

	//set first frame for background substraction
	firstFrame = true;

	//remove all fishes from previous tracking
	tank.removeAllFishes();

	_arena = *(_g->getArena());

	_rectifiedArenaCoordinates.push_back(cv::Point2f(0.0,0.0));
	_rectifiedArenaCoordinates.push_back(cv::Point2f(_horizontal_ratio, 0.0));
	_rectifiedArenaCoordinates.push_back(cv::Point2f(_horizontal_ratio,_vertical_ratio));
	_rectifiedArenaCoordinates.push_back(cv::Point2f(0.0, _vertical_ratio));


	if(_arena.size() == 4)
	{
		_H = cv::findHomography(_arena, _rectifiedArenaCoordinates);
		_bHomographie = true;
	}	
}

cv::Point Tracking::getRectifiedPoint(cv::Point p) 
{
	if(!_bHomographie)
		return cv::Point(0,0);

	cv::Mat v = (cv::Mat_<double>(3,1) << (double) p.x, (double) p.y, 1);
	gemm(_H, v, 1, 0, 0, _q);
	const double* qx = _q.ptr<double>(0);
	const double* qy = _q.ptr<double>(1);
	const double* qz = _q.ptr<double>(2);

	return cv::Point2f(*qx / *qz,*qy / *qz);
}

cv::Point Tracking::getShoalCenter()
{
	//______________________________________________
	//preparations

	int fishes = tank.getFishes().size()+1;
	cv::Point center(0,0);
	FishComponent* fc = NULL;
	int fishId;
	cv::Point bestmodel;
	std::vector<cv::Point> bestHits;

	if(FishComponent::individuals == 0 || fishes == 0)
		return center;

	//_______________________________________________
	//ransac loop

	for(int i=0; i<ITERATIONS; i++)
	{
		//generate 1st random fish id
		fishId = cvRandInt(&rng)%FishComponent::individuals;
		fc = tank.getFishById(fishId);
		if(fc == NULL)
		{
			std::cout << "fish with id " << fishId << " not found!" << std::endl;
			//return center;
			continue;
		}
		cv::Point model(fc->getPose().getXPos(), fc->getPose().getYPos());
		std::vector<cv::Point> hits;

		//looking for points in Epsilon environment
		std::vector<FishComponent*>::const_iterator cit_fishes = tank.getFishes().begin();
		for(cit_fishes; cit_fishes != tank.getFishes().end(); ++cit_fishes)
		{
			cv::Point p((*cit_fishes)->getPose().getXPos(), (*cit_fishes)->getPose().getYPos());
			Vektor distance(model, p);
			if(distance.getLength() <= EPSILON && (*cit_fishes)->getId() != 2 )
			{
				hits.push_back(p);				
			}
		}

		//compare model with bestmodel
		if(hits.size() > bestHits.size())
		{
			bestmodel = model;
			bestHits = hits;
		}	
	}

	if(bestHits.size() == 0)
		return center;

	std::cout << "best model: " 
			  << bestmodel.x << ", "
			  << bestmodel.y << ", "
			  << "points to model: " << bestHits.size() << std::endl;

	//cv::line(colorFrame, cv::Point(bestmodel.getX1(), bestmodel.getY1()), cv::Point(bestmodel.getX2(), bestmodel.getY2()), CV_RGB(255,0,0));
	
	
	std::vector<cv::Point>::const_iterator cit_hits = bestHits.begin();
	double meanX = 0;
	double meanY = 0;
	std::vector<double> xValues;
	std::vector<double> yValues;
	for(cit_hits; cit_hits != bestHits.end(); ++cit_hits)
	{
		meanX += (*cit_hits).x;
		meanY += (*cit_hits).y;
		xValues.push_back((*cit_hits).x);
		yValues.push_back((*cit_hits).y);
		circle(colorFrame, (*cit_hits), 1, CV_RGB(123,255,0), 2);
	}
	center.x = meanX/bestHits.size();
	center.y = meanY/bestHits.size();
	
	int minX = std::min_element(xValues.begin(), xValues.end()) - xValues.begin();
	int maxX = std::max_element(xValues.begin(), xValues.end()) - xValues.begin();
	int minY = std::min_element(yValues.begin(), yValues.end()) - yValues.begin();
	int maxY = std::max_element(yValues.begin(), yValues.end()) - yValues.begin();
	double shoalWidth = xValues.at(maxX) - xValues.at(minX);
	double shoalHeight = yValues.at(maxY) - yValues.at(minY);

	int radius = (shoalWidth/2) > SHOAL_RADIUS_MAX?SHOAL_RADIUS_MAX:(shoalWidth/2);

	circle(colorFrame, center, radius, CV_RGB(255,255,255),1);
	circle(colorFrame, center, 3, CV_RGB(255,255,255), 3);
	
	return center;
}

/*cv::Point Tracking::getShoalCenter()
{
	//______________________________________________
	//preparations

	int fishes = tank.getFishes().size()+1;
	cv::Point center(0,0);
	FishComponent* fc = NULL;
	int fishId;
	Vektor bestmodel;
	std::vector<cv::Point> bestHits;

	if(FishComponent::individuals == 0 || fishes == 0)
		return center;

	//_______________________________________________
	//ransac loop

	for(int i=0; i<ITERATIONS; i++)
	{
		//generate 1st random fish id
		fishId = cvRandInt(&rng)%FishComponent::individuals;
		fc = tank.getFishById(fishId);
		if(fc == NULL)
		{
			std::cout << "fish with id " << fishId << " not found!" << std::endl;
			//return center;
			continue;
		}
		cv::Point p1(fc->getPose().getXPos(), fc->getPose().getYPos());

		//generate 2nd random fish id
		fishId = cvRandInt(&rng)%fishes;
		fc = tank.getFishById(fishId);
		if(fc == NULL)
		{
			std::cout << "fish with id " << fishId << " not found!" << std::endl;
			//return center;
			continue;
		}
		cv::Point p2(fc->getPose().getXPos(), fc->getPose().getYPos());

		//model is a line between 2 points	
		Vektor model;
		model.setPoints(p1, p2);
		model.makeLine();
		std::vector<cv::Point> hits;

		//looking for points in Epsilon environment
		std::vector<FishComponent*>::const_iterator cit_fishes = tank.getFishes().begin();
		for(cit_fishes; cit_fishes != tank.getFishes().end(); ++cit_fishes)
		{
			cv::Point p0((*cit_fishes)->getPose().getXPos(), (*cit_fishes)->getPose().getYPos());
			if((model - p0) <= EPSILON)
			{
				hits.push_back(p0);				
			}
		}

		//compare model with bestmodel
		if(hits.size() > bestHits.size())
		{
			bestmodel = model;
			bestHits = hits;
		}	
	}

	if(bestHits.size() == 0)
		return center;

	std::cout << "best model: " 
			  << bestmodel.getX1() << ", "
			  << bestmodel.getY1() << ", "
			  << bestmodel.getX2() << ", "
			  << bestmodel.getY2() << ", "
			  << "points to model: " << bestHits.size() << std::endl;

	//cv::line(colorFrame, cv::Point(bestmodel.getX1(), bestmodel.getY1()), cv::Point(bestmodel.getX2(), bestmodel.getY2()), CV_RGB(255,0,0));
	
	
	std::vector<cv::Point>::const_iterator cit_hits = bestHits.begin();
	double meanX = 0;
	double meanY = 0;
	std::vector<double> xValues;
	std::vector<double> yValues;
	for(cit_hits; cit_hits != bestHits.end(); ++cit_hits)
	{
		meanX += (*cit_hits).x;
		meanY += (*cit_hits).y;
		xValues.push_back((*cit_hits).x);
		yValues.push_back((*cit_hits).y);
		circle(colorFrame, (*cit_hits), 1, CV_RGB(123,255,0), 2);
	}
	center.x = meanX/bestHits.size();
	center.y = meanY/bestHits.size();
	
	int minX = std::min_element(xValues.begin(), xValues.end()) - xValues.begin();
	int maxX = std::max_element(xValues.begin(), xValues.end()) - xValues.begin();
	int minY = std::min_element(yValues.begin(), yValues.end()) - yValues.begin();
	int maxY = std::max_element(yValues.begin(), yValues.end()) - yValues.begin();
	double shoalWidth = xValues.at(maxX) - xValues.at(minX);
	double shoalHeight = yValues.at(maxY) - yValues.at(minY);

	int radius = (shoalWidth/2) > SHOAL_RADIUS_MAX?SHOAL_RADIUS_MAX:(shoalWidth/2);

	circle(colorFrame, center, radius, CV_RGB(255,255,255),1);
	circle(colorFrame, center, 3, CV_RGB(255,255,255), 3);
	return center;
}*/

Tracking::~Tracking()
{

}

//_____________________________________________
// drawing all the windows
cv::Mat* Tracking::getProcessedImage()
{
	switch(_wtype)
	{
	case WINDOW_ORIGINAL:
		if(colorFrame.data)
			colorFrame.copyTo(outputFrame);
		break;
	case WINDOW_ALPHA:
		if(backgroundFrame.data)
			cvtColor(backgroundFrame, outputFrame, CV_GRAY2BGR);
		break;
	case WINDOW_BINARY:
		if(binaryFrame.data)
			cvtColor(binaryFrame, outputFrame, CV_GRAY2BGR);
		break;
	case WINDOW_EROTION:
		if(erodeFrame.data)
			cvtColor(erodeFrame, outputFrame, CV_GRAY2BGR);
		break;
	case WINDOW_DILATION:
		if(dilateFrame.data)
			cvtColor(dilateFrame, outputFrame, CV_GRAY2BGR);
		break;
	}
	
	return &outputFrame;
}

//_____________________________________________
void Tracking::doThreshold()
{
	int max = 255;
	if(diffFrame.data)
		cv::threshold(diffFrame, binaryFrame, _threshold, max, THRESH_BINARY);
}

//_____________________________________________
void Tracking::doErotion()
{
	Mat erodeKernel = getStructuringElement(MORPH_CROSS, Size(_erodeSize,_erodeSize));	
	if(binaryFrame.data)
		erode(binaryFrame, erodeFrame, erodeKernel);
}

//_____________________________________________
void Tracking::doDilation()
{
	Mat dilateKernel = getStructuringElement(MORPH_RECT, Size(_dilateSize,_dilateSize));
	if(erodeFrame.data)
		dilate(erodeFrame, dilateFrame, dilateKernel);
}

void Tracking::filterBlobs(CBlobResult& blobs)
{
	if(!video)
		return;

	int w  = int(video->get(CV_CAP_PROP_FRAME_WIDTH));
    int h = int(video->get(CV_CAP_PROP_FRAME_HEIGHT));

	// blobs kleiner als blobsize
	blobs.Filter( blobs, B_INCLUDE, CBlobGetArea(), B_GREATER_OR_EQUAL, _blobsize);
	// blobs größe
	blobs.Filter( blobs, B_INCLUDE, CBlobGetArea(), B_LESS, 550);

	blobs.Filter( blobs, B_INCLUDE, CBlobGetMinX(), B_GREATER_OR_EQUAL, _blob_left_margin);
	blobs.Filter( blobs, B_EXCLUDE, CBlobGetMaxX(), B_GREATER_OR_EQUAL, w-_blob_right_margin);
	blobs.Filter( blobs, B_INCLUDE, CBlobGetMinY(), B_GREATER_OR_EQUAL, _blob_top_margin);
	blobs.Filter( blobs, B_EXCLUDE, CBlobGetMaxY(), B_GREATER_OR_EQUAL, h-_blob_bottom_margin);
	//blobs.Filter( blobs, B_INCLUDE, CBlobGetStdDev(), B_INSIDE, blobsize);

	line(colorFrame, cv::Point(_blob_left_margin, 0), cv::Point(_blob_left_margin, h), BLOB_NARGIN_COLOR,1);
	line(colorFrame, cv::Point(0, _blob_top_margin), cv::Point(w, _blob_top_margin), BLOB_NARGIN_COLOR,1);
	line(colorFrame, cv::Point(w-_blob_right_margin, 0), cv::Point(w-_blob_right_margin, h), BLOB_NARGIN_COLOR,1);
	line(colorFrame, cv::Point(0, h-_blob_bottom_margin), cv::Point(w, h-_blob_bottom_margin), BLOB_NARGIN_COLOR,1);	
}

//______________________________________________
void Tracking::doBlobbing()
{
	colorFrame.copyTo(modifiedColorFrame); //saved colorframe for label change
	// preparations, get Blobs
	CBlob currentBlob;
	IplImage blobImage(dilateFrame);
	CBlobResult blobs = CBlobResult(&blobImage, NULL, 0);

	filterBlobs(blobs);	
	
	int blobCount = blobs.GetNumBlobs();
	int bc = blobCount;
	int lastX, lastY, saveX, saveY;

	// start analysis for each detected blob
	for (int i = 0; i < blobCount; i++ )
	{
		if(!_bBlobbing)
			continue;

		currentBlob = blobs.GetBlob(i);				
		// get bounding box
		CvRect boundingBox = currentBlob.GetBoundingBox();
		// get angle, delivered by blob recognition class
		double angle = currentBlob.GetEllipse().angle;
		cv::Size ellipseSize(currentBlob.GetEllipse().size.width*DRAW_FISHBODY_FACTOR, currentBlob.GetEllipse().size.height*DRAW_FISHBODY_FACTOR);
		// calc blob area	
		double blobArea = currentBlob.GetEllipse().size.height*currentBlob.GetEllipse().size.width*CV_PI;
		// get center point coordinates
		int x = currentBlob.GetEllipse().center.x;
		int y = currentBlob.GetEllipse().center.y;
		Point currenCenterPoint(x,y);	


		//__________________________________________________________________________________________________
		// test contours

		/*Mat blobMat	= dilateFrame(currentBlob.GetBoundingBox());
		Mat detected_edges;
		Canny( detected_edges, detected_edges, threshold, threshold*3, 3 );

		vector<vector<Point> > contours;
		vector<Vec4i> hierarchy;
		/// Find contours
		findContours( blobMat, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0) );

		if(contours.size())
		{
			if(contours.at(0).size() > 1)
			{
				std::vector<Point>::iterator it = contours.at(0).begin()+1;
				double outline = 0;
				for (it; it != contours.at(0).end(); ++it)
				{
					Vektor v(*(it-1), *it);
					outline = outline + v.getLength();  
				}
				::cout << "blob " << i << ": " << outline << std::endl; 
			}
		}*/

		//__________________________________________________________________________________________________
		// contours test end

		Vektor LastMotionVektor; // for the speed
		Vektor SaveMotionVektor; // for the angle

		bool mergedblob = false;
		FishComponent* f = tank.findFish(Pose(currenCenterPoint, angle*(CV_PI/180), currentBlob.GetEllipse().size.width, currentBlob.GetEllipse().size.height), SEARCH_REGION, blobArea, mergedblob);
		if(f != NULL)
		{
			Pose& temp = f->getPose();
			lastX = temp.getXPos();
			lastY = temp.getYPos();

			// get Pose far away enough to determine orientation vector
			temp = f->getPose(false);
			saveX = temp.getXPos();
			saveY = temp.getYPos();
			
			// build motion vector
			SaveMotionVektor.setPoints(cv::Point(saveX, saveY), currenCenterPoint);
			//SaveMotionVektor = f->getMotionVektor();
			LastMotionVektor.setPoints(cv::Point(lastX, lastY), currenCenterPoint);
		}
		else
		{
			// no fish recocnized -> build default motion vector with angle = 0 and v = 0
			LastMotionVektor.setPoints(currenCenterPoint, currenCenterPoint);
			SaveMotionVektor.setPoints(currenCenterPoint, currenCenterPoint);
		}
			
		
		bool bfailure = false;
		if(angle < 0.000000000000001 && f != NULL)
		{
			angle = f->getPose().getDegreeAngle();
			bfailure = true;
		}
		// shows the ellipse
		if(_bShowEllipse)
		{
			if(bfailure)	
				ellipse(colorFrame, currentBlob.GetEllipse().center, ellipseSize, -angle, 0, 360, FISH_COLOR1, 1);
			if(mergedblob)
				ellipse(colorFrame, currentBlob.GetEllipse().center, ellipseSize, -angle, 0, 360, MERGED_BLOB_COLOR, 1);
			else
				ellipse(colorFrame, currentBlob.GetEllipse().center, ellipseSize, -angle, 0, 360, FISH_COLOR2, 1);
		}
		bfailure = false;
				
		double angle_Bogenmass = angle*(CV_PI/180);
		Vektor ellipseVektor1(x, y, angle_Bogenmass, ellipseSize.height*DRAW_FISHBODY_FACTOR+15);
		Vektor ellipseVektor2(x, y, angle_Bogenmass + CV_PI, ellipseSize.height*DRAW_FISHBODY_FACTOR+15);
		Vektor frontaldirectionVektor;
		Vektor caudalDirectionVektor;
		Pose realPose;
		
		if(SaveMotionVektor-ellipseVektor1 < SaveMotionVektor-ellipseVektor2)
		{
		 	frontaldirectionVektor = ellipseVektor1;
			caudalDirectionVektor = ellipseVektor2;
		}
		else
		{
		 	frontaldirectionVektor = ellipseVektor2;
			caudalDirectionVektor = ellipseVektor1;
		} 

		cv::Point2f rectifiedPoint = getRectifiedPoint(currenCenterPoint);
		realPose.setXPos(x);
		realPose.setYPos(y);
		realPose.setRectifiedXPos(rectifiedPoint.x);
		realPose.setRectifiedYPos(rectifiedPoint.y);
		realPose.setAngle(frontaldirectionVektor.getRadiantAngle());
		realPose.setWidth(currentBlob.GetEllipse().size.width);
		realPose.setHeight(currentBlob.GetEllipse().size.height);
		realPose.setFrameNumber(frameNumber);

		if(!_bTracking)
			continue;

		if(mergedblob)
			continue;

		if(f == NULL)
		{
			f = new FishIndividual(realPose, LastMotionVektor);
			tank.addFish(f);
			std::cout << "add fish !?" << std::endl;
		}
		else
		{
			// ein Objekt gefunden (Fisch oder Shoal) -> setze Pose
			if(_bShowLastPose)
			{
				cv::Point pu(f->getPose().getXPos(), f->getPose().getYPos());
				circle(colorFrame, pu, 2, CV_RGB(10, 20, 30), 2);
			}
			f->setPose(realPose);
			f->setSpeed(LastMotionVektor);
		}
		f->setBlobArea(blobArea);

		// homographie test
		cv::Point p1 = getRectifiedPoint(cv::Point(realPose.getXPos(), realPose.getYPos()));
		std::cout << f->getId() << ": " << p1.x << "," << p1.y << std::endl;
		// end homographie
		
		//________________________________________________________________________________________________________________
		// visualisation		

		//showTail(f->getId(), currenCenterPoint, ellipseSize.width, ellipseSize.height, caudalDirectionVektor.getRadiantAngle());		
		// shows direction line - toggled by 'd'
		if(_bShowDirection)
			line(colorFrame, Point(x,y), Point(frontaldirectionVektor.getX2(), frontaldirectionVektor.getY2()), CV_RGB(173, 190, 255), 2, CV_AA);
		// shows identifier label - toggled by 'l'
		//if(_bShowLabel)
			//showFishLabel(f->getId(), Point(frontaldirectionVektor.getX2(), frontaldirectionVektor.getY2()));
		// shows speed - toggled by 's'
		if(_bShowSpeed)
			showSpeed(f->getSpeed(), cv::Point(saveX, saveY));	
		// shows start point where prediction motion vector starts
		if(_bShowPredictionPoint)
			circle(colorFrame, cvPoint(saveX, saveY), 2, CV_RGB(0, 0, 255), 2);
		// shows blob size - toggled by 'z'
		if(_bShowBlob)
			showMeanBlobSize(f->getBlobArea(), Point(frontaldirectionVektor.getX2(), frontaldirectionVektor.getY2()));
		// shows center point - toggled by 'c'
		if(_bShowCenter)
			circle(colorFrame, cvPoint(x,y), 2, CV_RGB(255,0,0), 2);	
		// shows bounding box of blob - toggled by 'x'
		if(_bShowBox)
			showCorrespodenceRectangle(currenCenterPoint, SEARCH_REGION);
		// shows motion vector - toggled by 'm'
		if(_bShowMotionVector)
		{
			Vektor showMotionVector(cv::Point(saveX, saveY), SaveMotionVektor.getRadiantAngle(), 80);
			line(colorFrame, Point(saveX, saveY), 
							 Point(showMotionVector.getX2(), showMotionVector.getY2()), CV_RGB(30, 247, 198), 1, CV_AA);
		}		
	}

	getShoalCenter();

	showTails();

	// shows the trajectory history - toggled by 'h'
	if(_bShowTrajectory)
	{
		std::vector<FishComponent*>::const_iterator cit = tank.getFishes().begin();
		for(cit; cit != tank.getFishes().end(); ++cit)
		{
			int tCount = 0;
			cv::Point* pl = (*cit)->createTrajectoryPoints(tCount, _trajectorySize);
			showTrajectory(pl, tCount);
		}		
	}
	
	if(_bShowLabel)
		showLabels();

	if(_bShowAngle)
		//showAngles();

	//cout << endl;
	showBlobCount(bc);		
	std::cout << frameNumber << ": \t" << tank.getFishMask() << std::endl;
	tank.MotionPredictionOfLostFishes();
	tank.clearFishmask();
	FishTank::_framesStates = frameNumber;
}

//_____________________________________________
// callback function of alpha slider
void Tracking::on_alphaChanged()
{
	_wtype = WINDOW_ALPHA;
}

//_____________________________________________
// callback function of threshold slider
void Tracking::on_thresholdChanged()
{
	doThreshold();
	doErotion();
	doDilation();
	doBlobbing();
	_wtype = WINDOW_BINARY;
}

//_____________________________________________
// callback function of erode slider
void Tracking::on_erodeConnectivityChanged()
{
	doErotion();
	doDilation();
	doBlobbing();
	_wtype = WINDOW_EROTION;
}

//_____________________________________________
// callback function of dilate slider
void Tracking::on_dilateConnectivityChanged()
{
	doDilation();
	doBlobbing();	
	_wtype = WINDOW_DILATION;
}

//_____________________________________________
// callback function of dilate slider
void Tracking::on_blobSizeChanged()
{
	doBlobbing();	
	_wtype = WINDOW_ORIGINAL;
}

//_____________________________________________
// callback function of motion vector slider
void Tracking::on_motionThreshChanged() 
{
	//motionThresh = (double)motionThreshSlider *  MOTION_THRESH_STEPSIZE;
	_wtype = WINDOW_ORIGINAL;
}

bool Tracking::processImage()
{
	// convert current frame to grayscale
	Mat currentFrame;
	cvtColor(colorFrame, currentFrame, CV_BGR2GRAY);

	if(firstFrame)
	{
		// set background to current frame at the first pass
		backgroundFrame = currentFrame;
		firstFrame = false;
	}
	else
	{
		//________________________________________________________________________________________
		// 1. calculate frame difference -> save result in diffFrame
		diffFrame = backgroundFrame - currentFrame;

		//________________________________________________________________________________________
		// 2. analyse diffFrame with given threshold, 0 if diffFrame(x,y) < thr, else 1 -> save result in binaryFrame
		doThreshold();

		//________________________________________________________________________________________
		// 3. erode binaryFrame with 3x3 4-connectivity -> save result in binaryFrame
		doErotion();

		//________________________________________________________________________________________
		// 4. dilate binaryFrame with 6x6 8-connectivity -> save result in binaryFrame
		doDilation();
				
		//________________________________________________________________________________________
		// 5. running average. Re-calculate background with given alpha
		// note: accumulateWeighted() function doesn't work on grayscale images.
		backgroundFrame = (1-_alpha)*backgroundFrame + _alpha*currentFrame;

		//________________________________________________________________________________________
		// 6. image processing is finished. Re-convert to Color-Frame
		//cvtColor(binaryFrame, binaryFrame, CV_GRAY2BGR);
		
		//________________________________________________________________________________________
		// 7. start blob finding
		doBlobbing();
	}

    return true;	
}

void Tracking::showBlobCount(int blobcount)
{
	rectangle(colorFrame, cvPoint(3,7), cvPoint(145,52), CV_RGB(122, 128, 251), CV_FILLED);
	char message_blobCount[25];	
	sprintf(message_blobCount, "blobs:  %d", blobcount, frameNumber);
	putText(colorFrame, 
			message_blobCount,
			cvPoint(5,25), 
			FONT_HERSHEY_DUPLEX, 
			0.7, 
			CV_RGB(255, 243, 51), 
			1);
	sprintf(message_blobCount, "frame: %d", frameNumber);
	putText(colorFrame, 
			message_blobCount,
			cvPoint(5,45), 
			FONT_HERSHEY_DUPLEX, 
			0.7, 
			CV_RGB(255, 243, 51), 
			1);
}

void Tracking::showFishLabel(int id, cv::Point p)
{
	char message_labelNumber[2];	
	sprintf(message_labelNumber, "%d", id);
	putText(colorFrame, 
			message_labelNumber,
			p, 
			FONT_HERSHEY_SIMPLEX, 
			0.4, 
			CV_RGB(201, 197, 252), 
			1);
}

void Tracking::showFishAngle(double angle, cv::Point p)
{
	char message_labelNumber[2];	
	sprintf(message_labelNumber, "%f", angle);
	putText(colorFrame, 
			message_labelNumber,
			p, 
			FONT_HERSHEY_SIMPLEX, 
			0.4, 
			CV_RGB(46, 150, 32), 
			2);
}

void Tracking::showSpeed(double angle, cv::Point p)
{
	char message_labelAngle[4];	
	sprintf(message_labelAngle, "%2.2f", angle);
	putText(colorFrame, 
			message_labelAngle,
			p, 
			FONT_HERSHEY_DUPLEX, 
			0.5, 
			CV_RGB(255, 255, 0), 
			1);
}

void Tracking::showTrajectory(cv::Point* pointlist, int size)
{
	if(!pointlist)
		return;

	cv::Point startPoint = pointlist[0];
	for(int i=0; i<size-1;i++)
	{	
		if(i % 2 == 0)
		{
			if(pointlist[i].x != 0)
			{
				line(colorFrame, startPoint, pointlist[i], CV_RGB(255, 255, 0), 1, CV_AA);
				startPoint = pointlist[i];
			}
		}
	}
}

void Tracking::showLabels()
{
	std::vector<FishComponent*>::const_iterator cit = tank.getFishes().begin();
	for(cit; cit != tank.getFishes().end(); ++cit)
	{
		if(tank.isFishVisible((*cit)->getId()))
			showFishLabel((*cit)->getId(), (*cit)->getPose().getTargetPoint(20));
	}		
}

void Tracking::showAngles()
{
	std::vector<FishComponent*>::const_iterator cit = tank.getFishes().begin();
	for(cit; cit != tank.getFishes().end(); ++cit)
	{
		if(tank.isFishVisible((*cit)->getId()))
			showFishAngle((*cit)->getPose().getDegreeAngle(), (*cit)->getPose().getTargetPoint(20));
	}		
}

void Tracking::showTails()
{
	Scalar c = FISH_COLOR1;
	std::vector<FishComponent*>::const_iterator cit = tank.getFishes().begin();
	for(cit; cit != tank.getFishes().end(); ++cit)
	{
		if(tank.isFishVisible((*cit)->getId()))
		{
			if((*cit)->getId() == 2)
				c = CV_RGB(255, 67, 0);
			else
				c = FISH_COLOR2;

			showTail((*cit)->getId(), 
					  cv::Point((*cit)->getPose().getXPos(), (*cit)->getPose().getYPos()),
					  (*cit)->getPose().getWidth()*DRAW_FISHBODY_FACTOR, 
					  (*cit)->getPose().getHeight()*DRAW_FISHBODY_FACTOR,
					  (*cit)->getPose().getRadiantAngle()-CV_PI, c);
		}
	}		
}

void Tracking::showCorrespodenceRectangle(cv::Point p, int d)
{
	cv::Point p1(p.x-d/2, p.y-d/2);
	cv::Point p2(p.x+d/2, p.y+d/2);
	rectangle(colorFrame, p1, p2, CV_RGB(0, 255, 0));
}

void Tracking::showMeanBlobSize(double blobSize, cv::Point p)
{
	char message_labelBlobSize[4];	
	sprintf(message_labelBlobSize, "%2.2f", blobSize);
	putText(colorFrame, 
			message_labelBlobSize,
			p, 
			FONT_HERSHEY_DUPLEX, 
			0.5, 
			CV_RGB(255, 255, 0), 
			1);	
}

void Tracking::showTail(int id, cv::Point center, double ellipseWidth, double ellipseHeight, double angle, Scalar color)
{
	Vektor v1(center, angle, ellipseWidth);
	Vektor v2(cv::Point(v1.getX2(), v1.getY2()), angle + CV_PI/6, ellipseWidth*DRAW_FISHTAIL_FACTOR);
	Vektor v3(cv::Point(v1.getX2(), v1.getY2()), angle - CV_PI/6, ellipseWidth*DRAW_FISHTAIL_FACTOR);
	
	cv::Point p1(v1.getX2(), v1.getY2());
	cv::Point p2(v2.getX2(), v2.getY2());
	cv::Point p3(v3.getX2(), v3.getY2());
	cv::Point plist[] = {p1, p2, p3, p1};
	const int npts[] = {4};
	fillConvexPoly(colorFrame, plist, 4, color, CV_AA);
}

//void Tracking::setVideoData(int cameraNr, std::string videopath, videoType vtype)
//{
//	_cameraNr = cameraNr;
//	_videopath = videopath;
//	_vtype = vtype;
//}

void Tracking::setVideoDataFile(std::string videopath)
{
	_videopath = videopath;
}

void Tracking::setVideoDataStream(int cameraNr)
{
	_cameraNr = cameraNr;
}

void Tracking::run()
{
	initTrackingArea();	

	switch(_vtype)
	{
	case VIDEO_FROM_CAM:
		video = new VideoCapture(_cameraNr);
		break;
	case VIDEO_FROM_FILE:
		video = new VideoCapture(_videopath);
		break;
	}
	
	if(video == NULL) 
		return;	

	//------------- for writing output videos ---------------------------
	_fourcc = CV_FOURCC('I','4','2','0');
    _fps = video->get(CV_CAP_PROP_FPS);
    _xRes  = int(video->get(CV_CAP_PROP_FRAME_WIDTH));
    _yRes = int(video->get(CV_CAP_PROP_FRAME_HEIGHT));
	std::string path = _recordingpath+"\\videoOut.avi";
	CvVideoWriter* writer = cvCreateVideoWriter(path.c_str(),_fourcc,_fps,cvSize(_xRes, _yRes));
	//--------------------------------------------------------------------

	emit trackingRegionDefined(_xRes, _yRes, _blob_top_margin, _blob_left_margin, _blob_right_margin, _blob_bottom_margin);

	while(true)
	{
		// only sleep() if reading from video file, not when using a live video stream
		if (_vtype == VIDEO_FROM_FILE)
			_sleep(_delayTime);
		
		if(!video->read(colorFrame))
		{
			delete video;
			break;
		}

		processImage();
		emit trackingSequenceDone();

		while(_bPaused)
			_sleep(10);

		frameNumber++;

		//writer.write(colorFrame);
		//cvtColor(dilateFrame, dilateFrame, CV_GRAY2BGR);
		IplImage outImage(colorFrame);
		if(_bStartRecording)
			cvWriteToAVI(writer,&outImage);
		
		//_wtype = WINDOW_DILATION; // call show image from graphic scene
		//cvWaitKey(500);		

		if(_bOneStep)
			_bPaused = true;
	}

	cvReleaseVideoWriter(&writer);	
}

void Tracking::stop()
{
	_bPaused = true;
}

void Tracking::resume()
{
	_bPaused = false;
	_bOneStep = false;
}

void Tracking::step()
{
	_bPaused = false;
	_bOneStep = true;
}

std::string Tracking::toString()
{
	std::ostringstream out;
	out << ">H:" << '\n' << _videopath << '\n' << _fps << '\n' << _xRes << "," << _yRes << "\n\n";
	return out.str();
}

void Tracking::writeDataAsFASTA(std::string path)
{
	std::ofstream outFile;
	outFile.open(path.c_str());
	outFile << toString();
	tank.writeContentToFile(outFile);
	outFile << ">END";
	outFile.close();
}

void Tracking::writeDataAsCSV(std::string path)
{

}

void Tracking::writeOutput()
{
	std::ofstream outFile;
	std::string path = _outputpath + "\\out.txt";
	outFile.open(path.c_str());
	tank.writeContentToFile(outFile);
	outFile.close();
}

void Tracking::changeFishId(int newId, cv::Point pos)
{
	cv::Rect roi(pos.x-5, pos.y-5, 10, 10);
	FishComponent* f = tank.findFish(pos, roi);
	if(f != NULL)
	{
		std::cout << "exchange " << f->getId() << " with " << newId << std::endl;
		f->setId(newId);
		modifiedColorFrame.copyTo(colorFrame);
		//showLabels();
		doBlobbing();
		emit trackingSequenceDone();
	}
}

void Tracking::setMargin(dragType type, int val)
{
	switch(type)
	{
	case TOP:
		_blob_top_margin = val;
		break;
	case LEFT:
		_blob_left_margin = val;
		break;
	case RIGHT:
		_blob_right_margin = val;
		break;
	case BOTTOM:
		_blob_bottom_margin = val;
		break;
	}
}

const int Tracking::getMargin(dragType type)
{
	switch(type)
	{
	case TOP:
		return _blob_top_margin;
	case LEFT:
		return _blob_left_margin;
	case RIGHT:
		return _blob_right_margin;
	case BOTTOM:
		return _blob_bottom_margin;
	}
	return 0;
}