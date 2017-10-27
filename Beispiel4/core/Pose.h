#pragma once

#include "defs.h"

class Pose
{
public:
	Pose();
	Pose(const double x, const double y, const double angle, const double width, const double height);
	Pose(const cv::Point p, const double angle, const double width, const double height);
	Pose(const Pose& p);
	~Pose();

	Pose& operator=( const Pose& rhs );	 // Zuweisung(Copy)

	void setXPos(const double x){xPos = x;}
	void setYPos(const double y){yPos = y;}
	void setRectifiedXPos(const double rx){rectified_xPos = rx;}
	void setRectifiedYPos(const double ry){rectified_yPos = ry;}
	void setAngle(const double an){angle = an;}
	void setWidth(const double w){width = w;}
	void setHeight(const double h){height = h;}
	
	double getXPos(){return xPos;}
	double getYPos(){return yPos;}	
	double getRectifiedXpos(){return rectified_xPos;}
	double getRectifiedYPos(){return rectified_yPos;}
	double getRadiantAngle(){return angle;}
	double getDegreeAngle() {return angle*180/CV_PI;}
	double getWidth(){return width;}
	double getHeight(){return height;}

	void setFrameNumber(int number){_frameNumber = number;}
	int getFrameNumber(){return _frameNumber;}
	void setPredictionFlag(bool flag){_predicted = flag;}
	bool isPredicted(){return _predicted;}

	cv::Point getTargetPoint(int scaleFactor);	
	std::string toString(bool rectified = false);	

private:
	// pose elements
	double	xPos;
	double	yPos;
	double	rectified_xPos;
	double	rectified_yPos;
	double	angle;		// in radiant
	double  width;
	double  height;

	int		_frameNumber;
	bool	_predicted;
};
