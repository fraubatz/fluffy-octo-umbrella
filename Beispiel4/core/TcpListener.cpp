#include "TcpListener.h"

TcpListener::TcpListener(Tracking *tracking, QObject *parent) : QTcpServer(parent)
{
	_tracking = tracking;
}

void TcpListener::acceptConnection()
{
	while (this->hasPendingConnections())
	{
		QTcpSocket *socket = this->nextPendingConnection();
		_sockets.push_back(socket);
		socket->write("Fish Tracking ready.\r\n");
	}
}

void TcpListener::sendPositions()
{
	char line[100];
	sprintf(line, "%d\t%d\r\n",
		_tracking->getShoalCenter().x,
		_tracking->getShoalCenter().y);

	std::vector<QTcpSocket *>::const_iterator sock_it = _sockets.begin();
	for (sock_it; sock_it != _sockets.end(); ++sock_it)
		(*sock_it)->write(line);
	
	FishTank *tank = _tracking->getTank();
	std::vector<FishComponent*> allFish = tank->getFishes();
	std::vector<FishComponent *>::const_iterator fish_it = allFish.begin();
	for (fish_it; fish_it != allFish.end(); ++fish_it)
	{
		sprintf(line, "%f\t%f\t%f\t%f\t%f\t%f\r\n",
			(*fish_it)->getPose().getXPos(),
			(*fish_it)->getPose().getYPos(),
			(*fish_it)->getPose().getRadiantAngle(),
			(*fish_it)->getPose().getDegreeAngle(),
			(*fish_it)->getPose().getWidth(),
			(*fish_it)->getPose().getHeight());
		for (sock_it = _sockets.begin(); sock_it != _sockets.end(); ++sock_it)
			(*sock_it)->write(line);
	}
	for (sock_it = _sockets.begin(); sock_it != _sockets.end(); ++sock_it)
		(*sock_it)->write("\r\n");
}

