
#include "Pose.h"

Pose::Pose() : xPos(0), yPos(0), rectified_xPos(0), rectified_yPos(0), angle(-1), width(0), height(0)
{
	_frameNumber = 0;
	_predicted = false;
}

Pose::Pose(const double x, const double y, const double angle, const double width, const double height)
{
	this->xPos = x;
	this->yPos = y;
	this->rectified_xPos = 0;
	this->rectified_yPos = 0;
	this->angle = angle;
	this->width = width;
	this->height = height;
	this->_frameNumber = 0;
	this->_predicted = false;
}

Pose::Pose(const cv::Point p, const double angle, const double width, const double height)
{
	this->xPos = p.x;
	this->yPos = p.y;
	this->rectified_xPos = 0;
	this->rectified_yPos = 0;
	this->angle = angle;
	this->width = width;
	this->height = height;
	this->_frameNumber = 0;
	this->_predicted = false;
}

Pose::Pose(const Pose& p)
{
	*this = p;
}

Pose::~Pose()
{
}

Pose& Pose::operator =(const Pose& p)
{
	this->xPos = p.xPos;
	this->yPos = p.yPos;
	this->rectified_xPos = p.rectified_xPos;
	this->rectified_yPos = p.rectified_yPos;
	this->angle = p.angle;
	this->width = p.width;
	this->height = p.height;
	this->_frameNumber = p._frameNumber;
	this->_predicted = p._predicted;
	return *this;
}

cv::Point Pose::getTargetPoint(int scaleFactor)
{
	double y = yPos - scaleFactor * sin(angle);
	double x = xPos + scaleFactor * cos(angle);
	return cv::Point(x,y);
}

std::string Pose::toString(bool rectified)
{
	std::ostringstream out;
	int predictedFlag = _predicted ? 1 : 0;
	if(rectified == false)
		out << xPos << "," << yPos << "," << angle << "," << width << "," << height << "," << predictedFlag;
	else
		out << rectified_xPos << "," << rectified_yPos << "," << angle << "," << width << "," << height << "," << predictedFlag;
	return out.str();
}