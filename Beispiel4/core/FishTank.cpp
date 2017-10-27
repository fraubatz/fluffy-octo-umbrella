#include "FishTank.h"
#include "FishComponent.h"
#include "Pose.h"
#include "Vektor.h"

#include <fstream>

int FishTank::_framesStates = 0;	

FishTank::FishTank()
{
	fishes.clear();
	fishes.reserve(50);
	fishmask.reset();
}

FishTank::~FishTank()
{
	removeAllFishes();
}

void FishTank::addFish(FishComponent* fc)
{
	// if size < capacity O(1), else O(n+1) -> n = Anzahl der Elemente
	fishes.push_back(fc);
}

FishComponent* FishTank::removeFish(const int id)
{
	// max O(m+1) -> m = Anzahl der kopierten Elemente an die zu löschende Position.
	FishComponent* removedFish = NULL;
	std::vector<FishComponent*>::iterator it = fishes.begin();
	for(it; it != fishes.end(); ++it)
	{
		if((*it)->getId() == id)
		{
			removedFish = *it;
			fishes.erase(it);
			return removedFish;
		}
	}
	return NULL;
}

void FishTank::removeFish(const int id, const double blobArea)
{
	double area = fishes.at(id-1)->getBlobArea();
	if(area > 2*blobArea)
		removeFish(id);
}

FishComponent* FishTank::findFish(Pose &p, int connectivity, const double blobArea, bool& merged)
{
	cv::Rect roi(p.getXPos()-connectivity/2, 
				 p.getYPos()-connectivity/2,
				 connectivity,
				 connectivity);

	std::vector<FishComponent*>::iterator it = fishes.begin();
	std::vector<FishComponent*> foundfishes;
	foundfishes.clear();

	for(it; it != fishes.end(); ++it)
	{
		cv::Point pt((*it)->getPose().getXPos(), (*it)->getPose().getYPos());
 		if(roi.contains(pt))
			foundfishes.push_back(*it);
	}
	
	FishComponent* fish;
	switch(foundfishes.size())
	{
	case 0:
			fish = NULL;
			break;
	case 1:
			fish = foundfishes.at(0);
			break;
	default:
			fish = getNearestNeighbour(p, foundfishes);
	}

	if(fish)
	{
		// blob merged
		if(blobArea > 2*fish->getBlobArea())
		{
			merged = true;
			fish = NULL;
		}
		else
			fishmask.set(fish->getId()-1);
	}
	return fish;
}

FishComponent* FishTank::findFish(cv::Point p, cv::Rect rect)
{
	std::vector<FishComponent*>::iterator it = fishes.begin();
	for(it; it != fishes.end(); ++it)
	{
		if(rect.contains(cv::Point((*it)->getPose().getXPos(), (*it)->getPose().getYPos())))
			return (*it);
	}
	return NULL;
}

FishComponent* FishTank::getNearestNeighbour(Pose& p, const std::vector<FishComponent*>& fishes)
{
	if(fishes.size() == 0)
		return NULL;

	double score_d = DBL_MAX;
	double score_a = DBL_MAX;
	double score_s = DBL_MAX;
	double score   = DBL_MAX;

	std::vector<double> distances;
	std::vector<double> alphas;
	std::vector<double> sizes;
	std::vector<double> scores;
	std::vector<FishComponent*>::const_iterator it = fishes.begin();

	for(it; it != fishes.end(); ++it)
	{
		// check euklid distance
		Vektor v(cv::Point(p.getXPos(), p.getYPos()), cv::Point((*it)->getPose().getXPos(), (*it)->getPose().getYPos()));
		score_d = v.getLength();

		// check dimensions
		double width_ref = p.getWidth();
		double height_ref = p.getHeight();
		double width_score = (abs(width_ref-(*it)->getPose().getWidth())/width_ref);
		score_s += width_score;
		double height_score = (abs(height_ref-(*it)->getPose().getHeight())/height_ref);
		score_s = score_s*height_score;

		// check orientation
		Vektor v_ref(p.getXPos(), p.getYPos(), p.getRadiantAngle(), 1);
		//Vektor v_ref2(p.getXPos(), p.getYPos(), p.getRadiantAngle() + CV_PI , 1);
		Vektor v_score((*it)->getPose().getXPos(), (*it)->getPose().getYPos(), (*it)->getPose().getRadiantAngle(), 1);
		//double angle_distance1 = v_ref1 - v_score;
		//double angle_distance2 = v_ref2 - v_score;
		//score_a += std::min(angle_distance1, angle_distance2);
		score_a = v_ref - v_score;
		
		distances.push_back(score_d);	
		alphas.push_back(score_a);
		sizes.push_back(score_s);

		score = 0.2*score_a + 0.1*score_d;// + score_s;
		scores.push_back(score);
	}

	int minDistance = std::min_element(scores.begin(), scores.end()) - scores.begin();
	//if(alphas.at(minDistance) > 1)
	//FishComponent* fc = fishes.at(minDistance);
	return fishes.at(minDistance);
}

FishComponent* FishTank::getFishById(int id)
{
	std::vector<FishComponent*>::const_iterator cit = fishes.begin();	
	int index = 0;
	for(cit; cit != fishes.end(); ++cit)
	{
		if((*cit)->getId() == id)
			return *cit;
	}

	return NULL;
}

FishComponent* FishTank::findFish(const std::vector<Pose> &trajectory)
{
	return NULL;
}

void FishTank::MotionPredictionOfLostFishes()
{
	std::vector<FishComponent*>::iterator it = fishes.begin();
	
	int index = 0;
	for(it; it != fishes.end(); ++it)
	{
		std::cout << "prediction time of " << (*it)->getId() << " is " << (*it)->getPredictionTime() << std::endl;
		if((*it)->getPredictionTime() >= MAX_PREDICTION_TIME )
		{
			//delete (*it);
			//it = fishes.erase(it);
			std::cout << "delete fish" << std::endl;
			if(it == fishes.end())
				break;
		}
		else if(fishmask[index] == 0)
			(*it)->setPrediction();
		index++;
	}
}

void FishTank::removeAllFishes()
{
	// O(n) -> n = Anzahl der Elemente
	std::vector<FishComponent*>::iterator it = fishes.begin();
	for(it; it != fishes.end(); ++it)
		delete(*it);
	fishes.clear();
	//reset fish counter
	FishComponent::individuals = 0;
}

void FishTank::writeContentToFile(std::ofstream& outFile)
{
	for(int i=1; i <= _framesStates ; i++)
	{
		outFile << ">F" << i << '\n';
		std::vector<FishComponent*>::iterator it = fishes.begin();
		for(it; it != fishes.end(); ++it)
		{
			Pose* p = (*it)->getPoseByFrame(i);
			if(p != NULL)
				outFile << (*it)->getId() << '\t' << p->toString(true) << '\n';
		}
	}
}

