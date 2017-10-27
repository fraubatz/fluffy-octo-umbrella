#pragma once

#include "defs.h"

class FishComponent;
class Pose;

class FishTank
{
public:
	static int _framesStates;

	FishTank();
	~FishTank();

	void addFish(FishComponent* fc);
	FishComponent* removeFish(const int id);
	void removeFish(const int id, const double blobArea);
	void removeAllFishes();

	FishComponent* findFish(Pose &p, const int connectivity, const double blobSize, bool& merged);
	FishComponent* findFish(cv::Point p, cv::Rect rect);
	FishComponent* findFish(const std::vector<Pose> &trajectory);
	const std::vector<FishComponent*>& getFishes(){return fishes;}
	void MotionPredictionOfLostFishes();
	//FishComponent* findFish(Pose&p);

	FishComponent* getFishById(int id);
	FishComponent* getNearestNeighbour(Pose& p, const std::vector<FishComponent*>& fishes);
	const std::string getFishMask(){return fishmask.to_string();}
	void clearFishmask(){fishmask.reset();}

	bool isFishVisible(int id){return fishmask[id-1] == 0?false:true;}

	//out stream
	void writeContentToFile(std::ofstream& outFile);

	//void groupFishes();
	//void ungroupFishes();

private:
	std::vector<FishComponent*> fishes;
	std::bitset<500> fishmask;
	
};
