
#ifndef TRACKING_H_
#define TRACKING_H_

#include "Pose.h"
#include "Vektor.h"
#include "cvBlobs\blob.h"
#include "cvBlobs\BlobResult.h"
#include "FishTank.h"
#include "FishIndividual.h"
#include "FishShoal.h"

#include <iostream>
#include <QThread>

#include "GraphicInterface.h"
#include "TrackingInterface.h"


class Tracking : public QThread
{
	Q_OBJECT

protected:
	// the matrices for the image processing steps
	cv::Mat colorFrame;			// the original
	cv::Mat modifiedColorFrame; // the saved copy for labeling
	cv::Mat backgroundFrame;	// the background substraction
	cv::Mat erodeFrame;			// after erotion
	cv::Mat dilateFrame;		// after dilation
	cv::Mat diffFrame;			// frame difference after background substraction
	cv::Mat binaryFrame;		// the binary frame after threshold
	cv::Mat blobFrame;			// the frame showing the blobs
	cv::Mat outputFrame;		// this frame hold the current output depending on _wtype

	
	cv::VideoCapture* video;
	bool firstFrame;
	int frameNumber;
	FishTank tank;
	GraphicInterface* _g;

	//parameters for image processing
	double _alpha;
	double _motionThresh;
	int _threshold;
	int _erodeSize;
	int _dilateSize;
	int _blobsize;

	//blob conditions
	int _blob_top_margin;
	int _blob_bottom_margin;
	int _blob_right_margin;
	int _blob_left_margin;

	// flags for image processing
	bool _bTracking;
	bool _bBlobbing;
	bool _bStartRecording;
	bool _bPaused;
	bool _bOneStep;
	bool _bShowDirection;
	bool _bShowLabel;
	bool _bShowAngle;
	bool _bShowSpeed;
	bool _bShowCenter;
	bool _bShowBlob;
	bool _bShowBox;
	bool _bShowEllipse;
	bool _bShowTrajectory;
	bool _bShowPredictionPoint;
	bool _bShowMotionVector;
	bool _bShowLastPose;
	bool _bHomographie;
	int  _delayTime;
	int  _trajectorySize;

	//resources video
	int _cameraNr;
	std::string _videopath;
	std::string _recordingpath;
	std::string _outputpath;
	videoType _vtype;
	windowType _wtype;
	int _fps;
	int _fourcc;
	int _xRes;
	int _yRes;

	//homography
	std::vector<cv::Point2f> _arena;
	std::vector<cv::Point2f> _rectifiedArenaCoordinates;
	cv::Mat _H; 	//Transformation Matrix
	cv::Mat _q;
	double _vertical_ratio;
	double _horizontal_ratio;
	
	//shoal center



public:

	Tracking();
	~Tracking();

	//random generator
	static CvRNG rng;

	//overloads
	void setView(GraphicInterface* g){_g = g;}

	void initTrackingArea();
	cv::Point getRectifiedPoint(cv::Point p);
	cv::Point getShoalCenter();

	cv::Mat* getProcessedImage();
	//void setVideoData(int cameraNr, std::string videopath, videoType vtype);
	void setVideoDataFile(std::string videopath);
	void setVideoDataStream(int cameraNr);
	void setRecordingOutput(std::string recordingpath){_recordingpath = recordingpath;}
	void setOutputLocation(std::string outputpath){_outputpath = outputpath;}
	void startTracking(){_bTracking = true;}
	void stopTracking(){_bTracking = false; tank.removeAllFishes();}

	void run();
	void stop();
	void resume();
	void step();
	bool processImage();

	void doThreshold();
	void doErotion();
	void doDilation();
	void doBlobbing();

	void setMargin(dragType type, int val);
	const int getMargin(dragType type);

	void setAlpha(double alpha){this->_alpha = alpha;}
	const double getAlpha(){return this->_alpha;}
	void setThreshold(int thresh){this->_threshold = thresh;}
	const int getThreshold(){return this->_threshold;}
	void setErotion(int erode){this->_erodeSize = erode;}
	const int getErotion(){return this->_erodeSize;}
	void setDilation(int dilate){this->_dilateSize = dilate;}
	const int getDilation(){return this->_dilateSize;}	
	void setDelayTime(int time){this->_delayTime = time;}
	const int getDelayTime(){return this->_delayTime;}
	void setBlobSize(int blobSize){this->_blobsize = blobSize;}
	const int getBlobSize(){return _blobsize;}
	void setVideoType(videoType vType) { this->_vtype = vType; }
	const videoType getVideoType() { return this->_vtype; }

	void showLabel(bool show){_bShowLabel = show;}
	void showAngle(bool show){_bShowAngle = show;}
	void showShape(bool show){_bShowEllipse = show;}
	void showblobSize(bool show){_bShowBlob = show;}
	void showVelocity(bool show){_bShowSpeed = show;}
	void showCenterPoint(bool show){_bShowCenter = show;}
	void showPredictionPoint(bool show){_bShowPredictionPoint = show;}
	void showOrientation(bool show){_bShowMotionVector = show;}
	void showROI(bool show){_bShowBox = show;}
	void showTrajectory(bool show){_bShowTrajectory = show;}

	void showAlpha(){_wtype = WINDOW_ALPHA;emit trackingSequenceDone();}
	void showBinary(){_wtype = WINDOW_BINARY;emit trackingSequenceDone();}
	void showErode(){_wtype = WINDOW_EROTION;emit trackingSequenceDone();}
	void showDilate(){_wtype = WINDOW_DILATION;emit trackingSequenceDone();}
	void showOriginal(){_wtype = WINDOW_ORIGINAL;emit trackingSequenceDone();}

	void changeFishId(int newId, cv::Point pos);
	void writeDataAsFASTA(std::string path);
	void writeDataAsCSV(std::string path);
	void writeOutput();
	void startRecording(bool recording){_bStartRecording = recording;}

	FishTank *getTank() { return &tank; }

signals:
	void trackingSequenceDone();
	void trackingRegionDefined(int width, int height, int top, int left, int right, int bottom);

	//helper
private:
	void filterBlobs(CBlobResult&);
	void showBlobCount(int);
	void showFishLabel(int, cv::Point);
	void showFishAngle(double, cv::Point);
	void showLabels();
	void showAngles();
	void showSpeed(double, cv::Point);
	void showCorrespodenceRectangle(cv::Point, int);
	void showMeanBlobSize(double, cv::Point);
	void showTrajectory(cv::Point*, int);
	void showTail(int, cv::Point, double, double, double, cv::Scalar);
	void showTails();

	void on_alphaChanged();
	void on_thresholdChanged();
	void on_erodeConnectivityChanged();
	void on_dilateConnectivityChanged();
	void on_blobSizeChanged();
	void on_motionThreshChanged();

	// stream data header
	std::string toString();	
};

#endif /* TRACKING_H_ */
