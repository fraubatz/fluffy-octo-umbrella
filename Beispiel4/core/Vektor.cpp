#include "Vektor.h"

Vektor::Vektor() : x1(0), y1(0), x2(0), y2(0), angle(0), length(0)
{
}

Vektor::Vektor(const cv::Point p1, const cv::Point p2)
{
	this->x1 = p1.x;
	this->y1 = p1.y;
	this->x2 = p2.x;
	this->y2 = p2.y;
	calcVector();
}

Vektor::Vektor(const double x, const double y, const double angle, const double len)
{
	this->x1 = x;
	this->y1 = y;
	this->angle = angle;
	this->length = len;
	this->x2 = x + len * cos(angle);
	this->y2 = y - len * sin(angle);	
}

Vektor::Vektor(const cv::Point p, const double angle, const double len)
{
	this->x1 = p.x;
	this->y1 = p.y;
	this->angle = angle;
	this->length = len;
	this->x2 = p.x + len * cos(angle);
	this->y2 = p.y - len * sin(angle);	
}

Vektor::Vektor(const Vektor& v)
{
	*this = v;
}

void Vektor::setPoints(const cv::Point p1, const cv::Point p2)
{
	this->x1 = p1.x;
	this->y1 = p1.y;
	this->x2 = p2.x;
	this->y2 = p2.y;
	calcVector();
}

void Vektor::setLength(double len)
{
	this->length = len;
	this->x2 = this->x1 + len * cos(angle);
	this->y2 = this->y1 - len * sin(angle);	
}

void Vektor::makeLine()
{
	this->length = 1000;

	this->x2 = this->x1 + this->length * cos(angle);
	this->y2 = this->y1 - this->length * sin(angle);	

	this->x1 = this->x1 + 2*this->length * cos(angle+CV_PI);
	this->y1 = this->y1 - 2*this->length * sin(angle+CV_PI);	
}

double Vektor::operator-(const Vektor& v)
{
	//this vector
	double cos_this = cos(this->angle);
	double sin_this = sin(this->angle);

	//parameter vector
	double cos_v = cos(v.angle);
	double sin_v = sin(v.angle);

	//subtractions vector
	double cos_sub = cos_v - cos_this;
	double sin_sub = sin_v - sin_this;

	// d = ||v2 - v1|| = sqrt(cos_sub^2 + sin_sub^2)
	double distance = sqrt( pow(cos_sub,2) + pow(sin_sub,2) );
	return distance;
}

double Vektor::operator-(const cv::Point& p)
{
	// Vektor points:     p1, p2
	// Point near Vektor: p0
	double x0 = p.x;
	double y0 = p.y;

	double d = abs((this->x2 - this->x1)*(this->y1 - y0)-(this->x1 - x0)*(this->y2 - this->y1))/
			   sqrt(pow((this->x2 - this->x1),2)+pow((this->y2 - this->y1),2));
	return d;
}

Vektor& Vektor::operator +(const Vektor& v)
{
	Vektor& result = *this; 
	result.x2 = v.x2;
	result.y2 = v.y2;
	result.calcVector();
    return result;
}

Vektor& Vektor::operator =(const Vektor& v)
{
	this->x1 = v.x1;
	this->y1 = v.y1;
	this->x2 = v.x2;
	this->y2 = v.y2;
	this->angle = v.angle;
	this->length = v.length;
	return *this;
}

Vektor::~Vektor(void)
{
}

void Vektor::calcVector()
{
	// calc vector length
	double dx = x2 - x1;
	double dy = y2 - y1;
	length = sqrt(pow(dx, 2)+pow(dy, 2));

	// calc direction angle
	if(dx == 0 && dy == 0)
	{
		// no motion!
  		angle = -1;
		return;
	}

	double OFFSET = 0.01;
	
	if(dx >= 0 && dy <= 0)		//1.Quadrant
	{
		if(dx == 0)
			angle = CV_PI/2;
		else
			angle = -atan(((double)(dy/dx))-OFFSET);;
	}
	else if(dx <= 0 && dy <= 0)	// 2. Quadrant
	{
		if(dx == 0)
			angle = CV_PI/2;
		else
			angle = -atan((double)(dy/dx)) + CV_PI;
	}
	else if(dx <= 0 && dy >= 0)	// 3. Quadrant
	{
		if(dx == 0)
			angle = (3*CV_PI)/2;
		else
			angle = -atan((double)(dy/dx)) + CV_PI;
	}
	else if(dx >= 0 && dy >= 0)	// 4. Quadrant
	{
		if(dx == 0)
			angle = (3*CV_PI)/2;
		else
			angle = -atan((double)(dy/dx)) + 2*CV_PI;		
	}
}
