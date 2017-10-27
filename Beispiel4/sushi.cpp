#include "sushi.h"
#include "core/Tracking.h"
#include "core/TcpListener.h"
#include <QFileDialog>
#include <QString>

sushi::sushi(Tracking* t, QWidget *parent, Qt::WFlags flags) : QMainWindow(parent, flags), _listener(t)
{
	ui.setupUi(this);
	_t = t;

	//set mouse tracking for all child windows
	setMouseTracking(true);

	_old_threshold = 0;
	_old_erode = 0;
	_old_dilate = 0;
	_old_BlobSize = 0;

	initSushi();

	QObject::connect(ui.button_videoSearch, SIGNAL(clicked()), this, SLOT(browseVideo()));
	QObject::connect(ui.button_recordingLocation, SIGNAL(clicked()), this, SLOT(browseRecordingLocation()));
	QObject::connect(ui.groupBox_startRecording, SIGNAL(clicked(bool)), this, SLOT(startRecording(bool)));
	//video
	QObject::connect(ui.button_startVideo, SIGNAL(clicked()), this, SLOT(runVideo()));
	QObject::connect(ui.button_stopVideo, SIGNAL(clicked()), this, SLOT(stopVideo()));
	QObject::connect(ui.button_resumeVideo, SIGNAL(clicked()), this, SLOT(resumeVideo()));
	QObject::connect(ui.button_stepVideo, SIGNAL(clicked()), this, SLOT(stepVideo()));
	//video source
	QObject::connect(ui.groupBox_videoFile, SIGNAL(clicked(bool)), this, SLOT(fileVideo(bool)));
	QObject::connect(ui.groupBox_videoStream, SIGNAL(clicked(bool)), this, SLOT(streamVideo(bool)));
	//tracking
	QObject::connect(ui.button_startTracking, SIGNAL(clicked()), this, SLOT(runTracking()));
	QObject::connect(ui.button_endTracking, SIGNAL(clicked()), this, SLOT(stopTracking()));
	QObject::connect(_t, SIGNAL( trackingSequenceDone() ), this, SLOT( drawImage() ));
	QObject::connect(_t, SIGNAL( trackingRegionDefined(int, int, int, int, int, int) ), this, SLOT( drawObservationRegion(int, int, int, int, int, int) ));
	//view attributes
	QObject::connect(ui.checkBox_viewLabel, SIGNAL(clicked(bool)), this, SLOT(showLabel(bool)));
	QObject::connect(ui.checkBox_viewEllipse, SIGNAL(clicked(bool)), this, SLOT(showShape(bool)));
	QObject::connect(ui.checkBox_viewBlobSize, SIGNAL(clicked(bool)), this, SLOT(showblobSize(bool)));
	QObject::connect(ui.checkBox_viewVelocity, SIGNAL(clicked(bool)), this, SLOT(showVelocity(bool)));
	QObject::connect(ui.checkBox_viewCenterPoint, SIGNAL(clicked(bool)), this, SLOT(showCenterPoint(bool)));
	QObject::connect(ui.checkBox_viewPredictionPoint, SIGNAL(clicked(bool)), this, SLOT(showPredictionPoint(bool)));
	QObject::connect(ui.checkBox_viewOrientationVector, SIGNAL(clicked(bool)), this, SLOT(showOrientation(bool)));
	QObject::connect(ui.checkBox_viewROI, SIGNAL(clicked(bool)), this, SLOT(showROI(bool)));
	QObject::connect(ui.groupBox_viewTrajectory, SIGNAL(clicked(bool)),     this, SLOT(showTrajectory(bool)));
	//image processing
	QObject::connect(ui.dial_videoDelay, SIGNAL(valueChanged(int)), this, SLOT(setDelayTime(int)));
	//QObject::connect(ui.dial_threshold, SIGNAL(dialMoved(int)), this, SLOT(updateThreshold(int)));
	QObject::connect(ui.dial_threshold, SIGNAL(valueChanged(int)), this, SLOT(updateThreshold(int)));
	QObject::connect(ui.dial_erode, SIGNAL(valueChanged(int)), this, SLOT(updateErode(int)));
	QObject::connect(ui.dial_dilate, SIGNAL(valueChanged(int)), this, SLOT(updateDilate(int)));
	QObject::connect(ui.dial_minBlobSize, SIGNAL(valueChanged(int)), this, SLOT(updateBlobSize(int)));
	//view processed frames
	QObject::connect(ui.dial_processview, SIGNAL(valueChanged(int)), this, SLOT(switchProcessView(int)));
	//modify id
	QObject::connect(ui.mappingGraphicsView, SIGNAL(fishIdChanged(int, cv::Point)), this, SLOT(changeFishId(int, cv::Point)));
	QObject::connect(ui.button_outputSearch, SIGNAL(clicked()), this, SLOT(browseOutputLocation()));
	QObject::connect(ui.button_writeOutput, SIGNAL(clicked()), this, SLOT(writeOutput()));
	QObject::connect(ui.mappingGraphicsView, SIGNAL(blobMarginsChanged(dragType, int)), this, SLOT(blobMarginsChanged(dragType, int)));
	//network interface
	QObject::connect(ui.checkBox_network, SIGNAL(clicked(bool)), this, SLOT(toggleNetwork(bool)));
	QObject::connect(&_listener, SIGNAL(newConnection()), &_listener, SLOT(acceptConnection()));
	QObject::connect(_t, SIGNAL(trackingSequenceDone()), &_listener, SLOT(sendPositions()));
}

void sushi::initSushi()
{
	//_________________________________________
	//video tab
	int delaytime = _t->getDelayTime();
	ui.label_videoDelayValue->setNum(delaytime);
	ui.dial_videoDelay->setValue(delaytime);
	//_________________________________________
	//image processing tab
	int threshold = _t->getThreshold();
	_old_threshold = threshold%10;
	int dilate = _t->getDilation();
	_old_dilate = _t->getDilation();
	int erode = _t->getErotion();
	_old_erode = _t->getErotion();
	int blobSize = _t->getBlobSize();
	_old_BlobSize = _t->getBlobSize();

	ui.label_thresholdValue->setNum(threshold);
	ui.label_dilateValue->setNum(dilate);
	ui.label_erodeValue->setNum(erode);
	ui.label_minBlobSizeValue->setNum(blobSize);

	ui.dial_dilate->setValue(dilate%10);
	ui.dial_threshold->setValue(threshold%10);
	ui.dial_erode->setValue(erode%10);
	ui.dial_minBlobSize->setValue(blobSize%10);
}

sushi::~sushi()
{

}


void sushi::fileVideo(bool flag)
{
	ui.groupBox_videoStream->setChecked(false);
	_t->setVideoType(VIDEO_FROM_FILE);
}

void sushi::streamVideo(bool flag)
{
	ui.groupBox_videoFile->setChecked(false);
	_t->setVideoType(VIDEO_FROM_CAM);
}

void sushi::browseVideo()
{
	QString fileName = QFileDialog::getOpenFileName(this, tr("Open video"), "", tr("video Files (*.avi)"));
	ui.edit_videoPath->setText(fileName);
}

void sushi::browseRecordingLocation()
{
	QString fileLocation = QFileDialog::getExistingDirectory(this, tr("navigate recording location"), "");
	ui.edit_recordingLocation->setText(fileLocation);
	_t->setRecordingOutput(fileLocation.toStdString());
}

void sushi::startRecording(bool flag)
{
	_t->startRecording(flag);
}

void sushi::runVideo()
{
	ui.mappingGraphicsView->setEnabled(true);
	//terminate old thread
	_t->terminate();
	//get new video source (or keep old)
	switch (_t->getVideoType())
	{
		case VIDEO_FROM_FILE:
			_t->setVideoDataFile(ui.edit_videoPath->text().toStdString());
			break;
		case VIDEO_FROM_CAM:
			_t->setVideoDataStream(CV_CAP_ANY);
			break;
	}
	//start new thread
	_t->start();
}

void sushi::stopVideo()
{
	_t->stop();
}

void sushi::resumeVideo()
{
	_t->resume();
}

void sushi::stepVideo()
{
	_t->step();
}

void sushi::runTracking()
{
	_t->startTracking();
}

void sushi::stopTracking()
{
	_t->stopTracking();
}

void sushi::drawImage()
{
	if(_t->getProcessedImage())
		ui.mappingGraphicsView->showImage(_t->getProcessedImage());
}

void sushi::drawObservationRegion(int w, int h, int t, int l, int r, int b)
{
	ui.mappingGraphicsView->showObservationRegion(w, h, t, l, r, b);
}

void sushi::showLabel(bool flag)
{
	_t->showLabel(flag);
}

void sushi::showShape(bool flag)
{
	_t->showShape(flag);
}

void sushi::showblobSize(bool flag)
{
	_t->showblobSize(flag);
}

void sushi::showVelocity(bool flag)
{
	_t->showVelocity(flag);
}

void sushi::showCenterPoint(bool flag)
{
	_t->showCenterPoint(flag);
}

void sushi::showPredictionPoint(bool flag)
{
	_t->showPredictionPoint(flag);
}

void sushi::showOrientation(bool flag)
{
	_t->showOrientation(flag);
}

void sushi::showROI(bool flag)
{
	_t->showROI(flag);
}

void sushi::showTrajectory(bool flag)
{
	_t->showTrajectory(flag);	
}

void sushi::setDelayTime(int frames)
{
	int delayTime = (int)1000/frames;
	_t->setDelayTime(delayTime);
}

void sushi::updateThreshold(int val)
{
	int offset = val%10 -_old_threshold;
	if(offset < 0 && abs(offset) > 4 )
		offset += 10;
	if(offset > 0 && abs(offset) > 4)
		offset -= 10;

	int oldValue = _t->getThreshold();
	int newThreshold = oldValue + offset;

	_t->setThreshold(newThreshold);
	ui.label_thresholdValue->setNum(newThreshold);

	_old_threshold = newThreshold%10;
}

void sushi::updateErode(int val)
{
	int offset = val%10 -_old_erode;
	if(offset < 0 && abs(offset) > 4 )
		offset += 10;
	if(offset > 0 && abs(offset) > 4)
		offset -= 10;

	int oldValue = _t->getErotion();
	int newErode = oldValue + offset;

	_t->setErotion(newErode);
	ui.label_erodeValue->setNum(newErode);

	_old_erode = newErode%10;
}

void sushi::updateDilate(int val)
{
	int offset = val%10 -_old_dilate;
	if(offset < 0 && abs(offset) > 4 )
		offset += 10;
	if(offset > 0 && abs(offset) > 4)
		offset -= 10;

	int oldValue = _t->getDilation();
	int newDilate = oldValue + offset;

	_t->setDilation(newDilate);
	ui.label_dilateValue->setNum(newDilate);

	_old_dilate = newDilate%10;
}

void sushi::updateBlobSize(int val)
{
	int offset = val%10 -_old_BlobSize;
	if(offset < 0 && abs(offset) > 4 )
		offset += 10;
	if(offset > 0 && abs(offset) > 4)
		offset -= 10;

	int oldValue = _t->getBlobSize();
	int newBlobSize = oldValue + offset;

	_t->setBlobSize(newBlobSize);
	ui.label_minBlobSizeValue->setNum(newBlobSize);

	_old_BlobSize = newBlobSize%10;
}

void sushi::showAlpha()
{
	_t->showAlpha();
}

void sushi::showBinary()
{
	_t->showBinary();
}

void sushi::showErode()
{
	_t->showErode();
}

void sushi::showDilate()
{
	_t->showDilate();
}

void sushi::showOriginal()
{
	_t->showOriginal();
}

void sushi::changeFishId(int newId, cv::Point p)
{
	std::cout << "new fish id : " << newId << " at " << p.x << "," << p.y << std::endl;
	_t->changeFishId(newId, p);
}

void sushi::blobMarginsChanged(dragType type, int val)
{
	_t->setMargin(type, val);
}

void sushi::switchProcessView(int viewType)
{
	switch(viewType)
	{
	case 1:
		_t->showErode();
		break;
	case 2:
		_t->showDilate();
		break;
	case 3:
		_t->showOriginal();
		break;
	case 4:
		_t->showAlpha();
		break;
	case 5:
		_t->showBinary();
		break;
	}
}

void sushi::browseOutputLocation()
{
	QString fileLocation = QFileDialog::getExistingDirectory(this, tr("navigate output location"), "");
	ui.edit_outputPath->setText(fileLocation);
	_t->setOutputLocation(fileLocation.toStdString());
}

void sushi::writeOutput()
{
	_t->writeOutput();
}

void sushi::network(bool flag)
{
	if (flag)
	{
		int port = ui.edit_tcpPort->text().toInt();
		
		if (!_listener.listen(QHostAddress::Any, port))
		{
			ui.edit_notification->append("network: FAILED to listen on [any]:" + QString::number(port)
				+ ", reason: " + _listener.errorString());
			return;
		}
		ui.edit_notification->append("network: now listening on [any]:" + QString::number(port));
	}
	else
	{
		_listener.close();
		ui.edit_notification->append("network: no longer listening (not closing open connections)");
	}
}