#ifndef SUSHI_H
#define SUSHI_H

#include <QtGui/QMainWindow>
#include "ui_sushi.h"
#include "core/TcpListener.h"

class Tracking;
class GraphicInterface;

class sushi : public QMainWindow
{
	Q_OBJECT

public:
	sushi(Tracking* t, QWidget *parent = 0, Qt::WFlags flags = 0);
	~sushi();
	void initSushi();

	GraphicInterface* getGraphicInterface(){return ui.mappingGraphicsView;}

public slots:
	void browseVideo();
	void browseRecordingLocation();
	void startRecording(bool flag);
	void runVideo();
	void stopVideo();
	void stepVideo();
	void resumeVideo();
	void runTracking();
	void stopTracking();
	void drawImage();
	void drawObservationRegion(int w, int h, int t, int l, int r, int b);

	void fileVideo(bool flag);
	void streamVideo(bool flag);

	void showLabel(bool flag);
	void showShape(bool flag);
	void showblobSize(bool flag);
	void showVelocity(bool flag);
	void showCenterPoint(bool flag);
	void showPredictionPoint(bool flag);
	void showOrientation(bool flag);
	void showROI(bool flag);
	void showTrajectory(bool flag);

	void setDelayTime(int frames);
	void updateThreshold(int val);
	void updateErode(int val);
	void updateDilate(int val);
	void updateBlobSize(int val);

	void showAlpha();
	void showBinary();
	void showErode();
	void showDilate();
	void showOriginal();

	void switchProcessView(int val);
	void changeFishId(int newId, cv::Point p);
	void blobMarginsChanged(dragType type, int val);

	void browseOutputLocation();
	void writeOutput();
	void network(bool flag);



signals:


private:
	Ui::SushiGui ui;
	Tracking* _t;

	//helpers: old dial positions
	int _old_threshold;
	int _old_erode;
	int _old_dilate;
	int _old_alpha;
	int _old_BlobSize;

	TcpListener _listener;
};

#endif // SUSHI_H
