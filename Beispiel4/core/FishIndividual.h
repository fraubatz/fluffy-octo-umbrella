#pragma once

#include "defs.h"
#include "FishComponent.h"
#include "Pose.h"
#include "Vektor.h"



class FishIndividual :	public FishComponent
{
public:
	FishIndividual(Pose p, Vektor v);
	virtual ~FishIndividual();

	void setPose(const Pose p);
	void setSpeed(const Vektor v);
	Pose& getPose(bool current = true);
	Pose* getPoseByFrame(int frameNumber);
	Vektor getMotionVektor(){return calculateMeanSumVektor();}

	double getBlobArea(){return meanBlobArea;}
	void setBlobArea(const double area);

	int getId(){return id;}
	void setId(int id){this->id = id;}

	double getSpeed(){return meanSpeed;}
	int getLifeTime(){return trajectory.size();}
	cv::Point* createTrajectoryPoints(int& size, const int limit = 0);

	void setPrediction();
	bool isPredicted(){return predicted;}
	int getPredictionTime(){return predictionTime;}
	std::string toString();
	
private:
	// helpers
	bool addSpeedVektor(Vektor v);
	bool addBlobArea(double area);
	Vektor calculateMeanSumVektor();
	double calcSpeedHistory();
	void calculateMeanSpeed();
	void calculateMeanBlobArea();
	double calculateMeanAngle();
	Pose& findPoseForMotionPrediction();
	int getPos(int pos, int teiler, int val);
	
	int					id;
	std::vector<Pose>	trajectory;
	std::deque<Vektor>  speedMean;
	std::vector<Vektor> speedHistory;
	double				meanSpeed;	
	Pose				pose;	
	cv::Size			ellipseSize;
	Vektor				predictionStep;

	Pose				lastPose;
	bool				predicted;
	int					predictionTime;
};
