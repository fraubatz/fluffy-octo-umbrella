#include "FishIndividual.h"
#include "FishTank.h"


FishIndividual::FishIndividual(Pose p, Vektor v)
{
	++individuals;
	id = individuals;

	pose = p;
	lastPose = p;
	
	trajectory.clear();
	trajectory.push_back(pose);

	speedHistory.clear();
	speedHistory.push_back(v);

	meanBlobArea = 0;
	meanSpeed = 0;

	predicted = false;
	predictionTime = 0;
}

FishIndividual::~FishIndividual()
{
	//--individuals;
}

void FishIndividual::setPose(const Pose p)
{   
	if(!predicted)
		predictionTime = 0;

	// aktuelle Pose...
	pose = p;
	//...schliesslich wird er zur Trajectorie hinzugefuegt
	trajectory.push_back(pose);	
}

void FishIndividual::setPrediction()
{
	predicted = true;
	predictionTime++;
	//double angle = calculateMeanAngle();
	double angle = pose.getRadiantAngle();
	// the estimated vector to the next point
	Vektor predict(pose.getXPos(), 
				   pose.getYPos(), 
				   angle, 
				   calculateMeanSumVektor().getLength()/VELOCITY_RING_BUFFER_SIZE);
	// the coordinates of the next point
	double x = predict.getX2();
	double y = predict.getY2();
	// the new pose
	Pose p(x, y, angle, pose.getWidth(), pose.getHeight());
	p.setFrameNumber(FishTank::_framesStates);
	p.setPredictionFlag(true);
	setPose(p);
	setSpeed(predict);
	setBlobArea(this->getBlobArea());
	predicted = false;
}

void FishIndividual::setSpeed(const Vektor v)
{
	// copy currentspeed from const parameter
	if(addSpeedVektor(v))
		// calculate the sum of speeds over last 10 frames
		calculateMeanSpeed();
	// set prediction Vektor to the last speed	
}

void FishIndividual::setBlobArea(const double area)
{
	if(addBlobArea(area))
		calculateMeanBlobArea();
}

bool FishIndividual::addSpeedVektor(Vektor v)
{
	// add vektor to meanSpeed deque
	if(speedMean.size() <= VELOCITY_RING_BUFFER_SIZE)
	{
		speedMean.push_back(v);
	}
	else
	{
		speedMean.pop_front();
		speedMean.push_back(v);
	}

	// add vektor to history vektor
	speedHistory.push_back(v);
	
	return true;
}

bool FishIndividual::addBlobArea(double area)
{
	// add vektor to meanSpeed deque
	if(blobAreaBuffer.size() <= BLOB_AREA_BUFFER_SIZE)
	{
		blobAreaBuffer.push_back(area);
	}
	else
	{
		blobAreaBuffer.pop_front();
		blobAreaBuffer.push_back(area);
	}

	return true;
}

Vektor FishIndividual::calculateMeanSumVektor()
{
	if(speedMean.size() < 1)
		return Vektor();
	
	std::deque<Vektor>::iterator it = speedMean.begin();	
	Vektor sumVektor = *it;
	for(it; it != speedMean.end(); ++it)
		sumVektor = sumVektor + *it;
	return sumVektor;
}

double FishIndividual::calcSpeedHistory()
{
	if(speedHistory.size() < 1)
		return 0;

	std::vector<Vektor>::iterator it = speedHistory.begin();
	double sumspeed = (*it).getLength();
	for(it; it != speedHistory.end(); ++it)
		sumspeed = sumspeed + (*it).getLength();
	return sumspeed;
}

void FishIndividual::calculateMeanSpeed()
{
	double sumSpeed = 0;
	std::deque<Vektor>::iterator it = speedMean.begin();
	for(it; it != speedMean.end(); ++it)
		sumSpeed += (*it).getLength();
	meanSpeed = (sumSpeed / speedMean.size());
}

void FishIndividual::calculateMeanBlobArea()
{
	double sumblobArea = 0;
	std::deque<double>::iterator it = blobAreaBuffer.begin();
	for(it; it != blobAreaBuffer.end(); ++it)
		sumblobArea += (*it);
	meanBlobArea = (sumblobArea / blobAreaBuffer.size());
}

double FishIndividual::calculateMeanAngle()
{
	double sumAngle = 0;
	std::vector<Pose>::reverse_iterator rit = trajectory.rbegin();
	int index = 0;
	for(rit; rit != trajectory.rend(); ++rit)
	{
		sumAngle = sumAngle + (*rit).getRadiantAngle();
		index++;
		if(index == (30-1))
			break;
	}
	return sumAngle/30;
}

Pose& FishIndividual::findPoseForMotionPrediction()
{
	// return pose that fits the conditions
	std::vector<Vektor>::reverse_iterator rit = speedHistory.rbegin();
	std::vector<Pose>::reverse_iterator rit_pose = trajectory.rbegin();
	Vektor MotionPrediction = *rit;
	Vektor MotionReference = calculateMeanSumVektor();
	for(rit; rit != speedHistory.rend(); ++rit)
	{		
		++rit_pose;
		MotionPrediction = MotionPrediction + *rit;		

		double distance = (MotionPrediction - MotionReference) - 1.8;
		if(distance < 1.5 && MotionPrediction.getLength() >= 5.5 && rit_pose != trajectory.rend())
		{
			lastPose = *rit_pose;
			return *rit_pose;
		}
		
	}	
	return lastPose;
}

Pose& FishIndividual::getPose(bool current)
{
	if(current)
		return pose;

	return findPoseForMotionPrediction();
}

Pose* FishIndividual::getPoseByFrame(int frameNumber)
{
	Pose* out;
	for (int i=0;i<trajectory.size();++i)
	{
		int n = trajectory.at(i).getFrameNumber();
		if(n == frameNumber)
		{
			out = &trajectory.at(i);
			return out;
		}
	}
	return NULL;
}

int FishIndividual::getPos(int pos, int teiler, int val)
{
	if(trajectory.at(pos).getFrameNumber() == val)
		return pos;
	if(teiler == 0)
		return -1;

	if(trajectory.at(pos).getFrameNumber() > val)
		getPos(pos - teiler, teiler/2, val);
	if(trajectory.at(pos).getFrameNumber() < val)
		getPos(pos + teiler, pos/2, val);	
}



cv::Point* FishIndividual::createTrajectoryPoints(int& size, const int limit)
{
	size = 0;

	if(limit == 0)
		size = trajectory.size();
	else
		size = limit;

	cv::Point* trajectoryPoints = new cv::Point[size];
		
	std::vector<Pose>::reverse_iterator rit = trajectory.rbegin();
	int index = 0;
	for(rit; rit != trajectory.rend(); ++rit)
	{
		trajectoryPoints[index] = cv::Point((*rit).getXPos(), (*rit).getYPos());
		index++;
		if(index == (size-1))
			break;
	}

	return trajectoryPoints;
}