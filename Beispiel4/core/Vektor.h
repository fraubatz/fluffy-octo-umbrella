#pragma once

#include "defs.h"

class Vektor
{
public:
	Vektor();
	Vektor(const cv::Point p1, const cv::Point p2);
	Vektor(const double x, const double y, const double angle, double len);
	Vektor(const cv::Point p, const double angle, double len);
	Vektor(const Vektor& p);
	~Vektor();

	// operatoren
	double operator- (const Vektor& v);			// Winkeldistanz
	double operator- (const cv::Point& p);	// Distanz Vektor <-> Punkt
	Vektor&  operator+ (const Vektor& v);		// Vektoraddition 
	Vektor& operator=( const Vektor& rhs );		// Zuweisung(Copy)

	//interface
	void setX1(double const x){this->x1 = x;}
	void setY1(double const y){this->y1 = y;}
	void setX2(double const x){this->x2 = x;}
	void setY2(double const y){this->y2 = y;}
	void setAngle(double const an){this->angle = an;}
	void setPoints(const cv::Point p2, const cv::Point p1);
	void setLength(double len);
	void makeLine();


	double getX1(){return x1;}
	double getY1(){return y1;}
	double getX2(){return x2;}
	double getY2(){return y2;}

	double getLength(){return length;}
	double getRadiantAngle(){return angle;}
	double getDegreeAngle() {return angle*180/CV_PI;}
	
private:
	double x1;
	double y1;
	double x2;
	double y2;
	double angle;
	double length;

	//helper
	void calcVector();
};
