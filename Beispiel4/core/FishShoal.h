#pragma once

#include "defs.h"
#include "FishComponent.h"
#include "Pose.h"
#include "Vektor.h"


class FishShoal : public FishComponent
{
public:
	FishShoal(Pose p);
	virtual ~FishShoal();

	void addFish(FishComponent* fc);
	
	int getId(){return id;}
	void setId(int id){}
	double getSpeed(){return 0;}
	Pose& getPose(bool current = true);
	Pose* getPoseByFrame(int frame){return new Pose();}
	double getBlobArea(){return meanBlobArea;}
	void setBlobArea(double area);
	void setPose(const Pose p);
	void setPredictedPose();
	bool isPredicted(){return false;}
	int getPredictionTime(){return 0;}
	void setSpeed(const Vektor v);
	Vektor getMotionVektor(){return Vektor();}
	
	int getLifeTime();
	cv::Point* createTrajectoryPoints(int& size, const int limit = 0);

private:
	int id;
	Pose				pose;	
	std::vector<FishComponent*> shoalFishes;
};
