#pragma once

#include "defs.h"

class Pose;
class Vektor;

class FishComponent
{
public:
	FishComponent(void);
	virtual ~FishComponent(void);

	virtual int getId() = 0;
	virtual void setId(int id) = 0;
	virtual double getSpeed() = 0;

	virtual Pose& getPose(bool current = true) = 0;
	virtual Pose* getPoseByFrame(int frame) = 0;
	virtual void setPose(const Pose p) = 0;
	virtual void setPrediction() = 0;
	virtual bool isPredicted() = 0;
	virtual int getPredictionTime() = 0;
	virtual void setSpeed(const Vektor v) = 0;
	virtual Vektor getMotionVektor() = 0;

	virtual double getBlobArea() = 0;
	virtual void setBlobArea(double blobArea) = 0;

	virtual int getLifeTime() = 0;
	virtual cv::Point* createTrajectoryPoints(int& size, const int limit = 0) = 0;
	static int			individuals;

protected:
	
	double				meanBlobArea;
	std::deque<double>  blobAreaBuffer;
	int					lifeCounter;
};
