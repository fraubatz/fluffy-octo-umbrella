#include "FishShoal.h"

FishShoal::FishShoal(Pose p)
{
	++individuals;
	id = individuals;

	pose = p;
	//lastPose = p;
}

FishShoal::~FishShoal()
{
}

void FishShoal::addFish(FishComponent* fc)
{
	shoalFishes.push_back(fc);
}

void FishShoal::setPose(const Pose p)
{
	pose = p;
	std::vector<FishComponent*>::iterator it = shoalFishes.begin();
	for(it; it != shoalFishes.end(); ++it)
		(*it)->setPrediction();
}

void FishShoal::setPredictedPose()
{

}

void FishShoal::setSpeed(const Vektor v)
{
	Vektor newSpeed = v;
	std::vector<FishComponent*>::iterator it = shoalFishes.begin();
	for(it; it != shoalFishes.end(); ++it)
		(*it)->setSpeed(newSpeed);
}

Pose& FishShoal::getPose(bool current)
{
	//if (shoalFishes.size() > 0)
	return shoalFishes.at(0)->getPose();
}

void FishShoal::setBlobArea(double area)
{
	//double alpha = 1/traj.size();
	//blobArea = (1-alpha)*blobArea + alpha*area;
}

int FishShoal::getLifeTime()
{
	int lifetime = 0;
	std::vector<FishComponent*>::iterator it = shoalFishes.begin();
	for(it; it != shoalFishes.end(); ++it)
		lifetime += (*it)->getLifeTime();
	return lifetime;
}

cv::Point* FishShoal::createTrajectoryPoints(int& size, const int limit)
{
	size = 0;
	if(limit == 0)
		size = getLifeTime();
	else
		size = shoalFishes.size()*limit;

	cv::Point* trajectoryPoints = new cv::Point[size];
	int index = 0;
	
	std::vector<FishComponent*>::iterator it = shoalFishes.begin();
	for(it; it != shoalFishes.end(); ++it)
	{
		int count = 0;
		cv::Point* temp = (*it)->createTrajectoryPoints(count, limit);
		for(int i = 0; i < count; ++i)
			trajectoryPoints[index+i] = temp[i];
		index = count;
		//size += count;
		delete [] temp;
	}
	
	return trajectoryPoints;
}
