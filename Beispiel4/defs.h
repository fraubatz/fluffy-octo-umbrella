#pragma once

// standard template library
// STL container
#include<list>
#include<vector>
#include<bitset>

// math operations
#include <math.h>
#include <float.h>

// openCV basic types
#include <opencv/cv.h>
#include <opencv/highgui.h>
#include <opencv/cxcore.h>

//drawing
#define FISH_COLOR1 CV_RGB(209, 87,175)
#define FISH_COLOR2	CV_RGB(191,188,224)
#define MERGED_BLOB_COLOR CV_RGB(255, 0, 0)
#define FISH_LABEL1 CV_RGB(255,255,0)
#define FISH_LABEL2 CV_RGB(0,0,0)
#define BLOB_NARGIN_COLOR CV_RGB(180, 180, 180)
#define DRAW_FISHBODY_FACTOR 2
#define DRAW_FISHTAIL_FACTOR 0.8

//parameters
#define SEARCH_REGION 30
#define VELOCITY_THRESH 5.5
#define VELOCITY_RING_BUFFER_SIZE 20
#define BLOB_AREA_BUFFER_SIZE 20
#define BLOBANGLE_THRESH 0.00000000000000001
#define DELAYTIME 0
#define MAX_PREDICTION_TIME 60
#define SHOAL_RADIUS_MAX 50

//ransac - shoal center
#define ITERATIONS	20
#define EPSILON 200

enum windowType{
	WINDOW_ORIGINAL,
	WINDOW_ALPHA,
	WINDOW_BINARY,
	WINDOW_EROTION,
	WINDOW_DILATION
};

enum videoType{
	VIDEO_FROM_FILE,
	VIDEO_FROM_CAM
};

enum dragType{
	OFF, 
	TOP, 
	LEFT, 
	RIGHT, 
	BOTTOM
};
