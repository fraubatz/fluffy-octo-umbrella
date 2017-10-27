#pragma once

#include <QtCore/QThread>
#include <QtCore/QDebug>
#include <QtNetwork/QTcpServer>
#include <QtNetwork/QTcpSocket>
#include <QtNetwork/QNetworkInterface>

#include <vector>

#include "Tracking.h"

class TcpListener : public QTcpServer
{
	Q_OBJECT

public:
	TcpListener(QObject *parent = 0);
	TcpListener(Tracking *tracking, QObject *parent = 0);

public slots:
	void acceptConnection();
	void sendPositions();

private:
	Tracking *_tracking;
	std::vector<QTcpSocket *> _sockets;
};