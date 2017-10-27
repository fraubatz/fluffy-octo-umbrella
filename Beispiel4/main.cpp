#include "sushi.h"
#include <QtGui/QApplication>

#include "core/Tracking.h"
#include "TrackingInterface.h"
#include "GraphicInterface.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	a.setStyleSheet("QLineEdit	{ background-color: yellow }");	

	Tracking* t = new Tracking();
	sushi w(t);
	t->setView(w.getGraphicInterface());
	w.show();
	return a.exec();
}
