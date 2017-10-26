
import YCM_gui.frameworkUI
import writer
import time
from YCM_gui.translation import *
from YCM_gui.sessionData import SessionData
from datatypes import *

## class description here...
#
# @author Stefan Forgo
# @date 23.05.2016
#

class PageSim(QtGui.QWidget):

    newSimulationStarted = QtCore.pyqtSignal()
    observerAdded = QtCore.pyqtSignal(int, str)

    ## parameter constructor
    def __init__(self, parent = None):

        super(PageSim,self).__init__(parent)

        self.__parent = parent
        self.__type = SIM

        self.__simulationThread = QtCore.QThread()
        self.__simulationThread.setTerminationEnabled(True)
        self.__simlogic = YCM_gui.frameworkUI.FrameworkUi().logic
        self.__simlogic.moveToThread(self.__simulationThread)
        self.__simlogic.sendmessage.connect(self.receivemessage)
        self.__simulationThread.started.connect(self.__simlogic.run)
        self.__simlogic.finished.connect(self.__simulationThread.quit)
        self.__simulationThread.finished.connect(self.simulationFinished)
        #todo: begin working on branch simcontrol

        self.setupUI()
        self.retranslateUI()
        self.setupConnections()

    @property
    def logic(self):
        return self.__simlogic

    @logic.setter
    def logic(self, logic):
        self.__simlogic = logic

    @property
    def parent(self):
        return self.__parent

    @property
    def type(self):
        return self.__type

    @property
    def startTime(self):
        return self.__startTime

    @startTime.setter
    def startTime(self, time):
        self.__startTime = time

    @property
    def endTime(self):
        return self.__endTime

    @endTime.setter
    def endTime(self, time):
        self.__endTime = time

    @property
    def stepSize(self):
        return self.__stepSize

    @stepSize.setter
    def stepSize(self, size):
        self.__stepSize = size

    @property
    def oldStartTime(self):
        return self.__oldStartTime

    @oldStartTime.setter
    def oldStartTime(self, time):
        self.__oldStartTime = time

    @property
    def oldEndTime(self):
        return self.__oldEndTime

    @oldEndTime.setter
    def oldEndTime(self, time):
        self.__oldEndTime = time

    @property
    def oldStepSize(self):
        return self.__oldStepSize

    @oldStepSize.setter
    def oldStepSize(self, size):
        self.__oldStepSize = size

    def setupUI(self):

        self.page_sim = QtGui.QWidget()
        self.page_sim.setObjectName(fromUtf8("page_sim"))
        self.page_sim_layout = QtGui.QGridLayout(self)
        self.page_sim_layout.setObjectName(fromUtf8("page_sim_layout"))
        self.button_startsim = QtGui.QPushButton(self.page_sim)
        self.button_startsim.setObjectName(fromUtf8("button_startsim"))
        self.page_sim_layout.addWidget(self.button_startsim, 1, 0, 1, 1)
        self.group_sim = QtGui.QGroupBox(self.page_sim)
        self.group_sim.setObjectName(fromUtf8("group_sim"))
        self.group_sim_layout = QtGui.QGridLayout(self.group_sim)
        self.group_sim_layout.setObjectName(fromUtf8("group_sim_layout"))
        self.gridLayout_sim = QtGui.QGridLayout()
        self.gridLayout_sim.setObjectName(fromUtf8("gridLayout_sim"))
        self.dial_starttime = QtGui.QDial(self.group_sim)
        self.dial_starttime.setObjectName(fromUtf8("dial_starttime"))
        self.gridLayout_sim.addWidget(self.dial_starttime, 0, 0, 1, 1)
        self.verticalLayout_endtime = QtGui.QVBoxLayout()
        self.verticalLayout_endtime.setObjectName(fromUtf8("verticalLayout_endtime"))
        spacerItem = QtGui.QSpacerItem(20, 40, QtGui.QSizePolicy.Minimum, QtGui.QSizePolicy.Expanding)
        self.verticalLayout_endtime.addItem(spacerItem)
        self.label_endtime = QtGui.QLabel(self.group_sim)
        self.label_endtime.setObjectName(fromUtf8("label_endtime"))
        self.verticalLayout_endtime.addWidget(self.label_endtime)
        self.edit_endtime = QtGui.QLineEdit(self.group_sim)
        self.edit_endtime.setObjectName(fromUtf8("edit_endtime"))
        self.verticalLayout_endtime.addWidget(self.edit_endtime)
        spacerItem1 = QtGui.QSpacerItem(20, 40, QtGui.QSizePolicy.Minimum, QtGui.QSizePolicy.Expanding)
        self.verticalLayout_endtime.addItem(spacerItem1)
        self.gridLayout_sim.addLayout(self.verticalLayout_endtime, 1, 2, 1, 1)
        self.dial_endtime = QtGui.QDial(self.group_sim)
        self.dial_endtime.setObjectName(fromUtf8("dial_endtime"))
        self.gridLayout_sim.addWidget(self.dial_endtime, 1, 0, 1, 1)
        self.dial_stepsize = QtGui.QDial(self.group_sim)
        self.dial_stepsize.setObjectName(fromUtf8("dial_stepsize"))
        self.gridLayout_sim.addWidget(self.dial_stepsize, 2, 0, 1, 1)
        self.verticalLayout_starttime = QtGui.QVBoxLayout()
        self.verticalLayout_starttime.setObjectName(fromUtf8("verticalLayout_starttime"))
        spacerItem2 = QtGui.QSpacerItem(20, 40, QtGui.QSizePolicy.Minimum, QtGui.QSizePolicy.Expanding)
        self.verticalLayout_starttime.addItem(spacerItem2)
        self.label_starttime = QtGui.QLabel(self.group_sim)
        self.label_starttime.setObjectName(fromUtf8("label_starttime"))
        self.verticalLayout_starttime.addWidget(self.label_starttime)
        self.edit_starttime = QtGui.QLineEdit(self.group_sim)
        self.edit_starttime.setObjectName(fromUtf8("edit_starttime"))
        self.verticalLayout_starttime.addWidget(self.edit_starttime)
        spacerItem3 = QtGui.QSpacerItem(20, 40, QtGui.QSizePolicy.Minimum, QtGui.QSizePolicy.Expanding)
        self.verticalLayout_starttime.addItem(spacerItem3)
        self.gridLayout_sim.addLayout(self.verticalLayout_starttime, 0, 2, 1, 1)
        self.verticalLayout_stepsize = QtGui.QVBoxLayout()
        self.verticalLayout_stepsize.setObjectName(fromUtf8("verticalLayout_stepsize"))
        spacerItem4 = QtGui.QSpacerItem(20, 40, QtGui.QSizePolicy.Minimum, QtGui.QSizePolicy.Expanding)
        self.verticalLayout_stepsize.addItem(spacerItem4)
        self.label_stepsize = QtGui.QLabel(self.group_sim)
        self.label_stepsize.setObjectName(fromUtf8("label_stepsize"))
        self.verticalLayout_stepsize.addWidget(self.label_stepsize)
        self.edit_stepsize = QtGui.QLineEdit(self.group_sim)
        self.edit_stepsize.setObjectName(fromUtf8("edit_stepsize"))
        self.verticalLayout_stepsize.addWidget(self.edit_stepsize)
        spacerItem5 = QtGui.QSpacerItem(20, 40, QtGui.QSizePolicy.Minimum, QtGui.QSizePolicy.Expanding)
        self.verticalLayout_stepsize.addItem(spacerItem5)
        self.gridLayout_sim.addLayout(self.verticalLayout_stepsize, 2, 2, 1, 1)
        self.group_sim_layout.addLayout(self.gridLayout_sim, 0, 0, 1, 1)
        self.page_sim_layout.addWidget(self.group_sim, 0, 0, 1, 1)
        self.button_stopsim = QtGui.QPushButton(self.page_sim)
        self.button_stopsim.setObjectName(fromUtf8("button_stopsim"))
        self.page_sim_layout.addWidget(self.button_stopsim, 4, 0, 1, 1)
        self.button_continuesim = QtGui.QPushButton(self.page_sim)
        self.button_continuesim.setObjectName(fromUtf8("button_continuesim"))
        self.page_sim_layout.addWidget(self.button_continuesim, 3, 0, 1, 1)
        self.button_pausesim = QtGui.QPushButton(self.page_sim)
        self.button_pausesim.setObjectName(fromUtf8("button_pausesim"))
        self.page_sim_layout.addWidget(self.button_pausesim, 2, 0, 1, 1)

    def retranslateUI(self):

        self.button_startsim.setText(translate("MainWindow", "Start Simulation", None))
        self.button_continuesim.setText(translate("MainWindow", "Continue Simulation", None))
        self.button_continuesim.setDisabled(True)
        self.button_stopsim.setText(translate("MainWindow", "Stop Simulation", None))
        self.button_stopsim.setDisabled(True)
        self.button_pausesim.setText(translate("MainWindow", "Pause Simulation", None))
        self.button_pausesim.setDisabled(True)
        self.group_sim.setTitle(translate("MainWindow", "Simulation Data:", None))
        self.label_endtime.setText(translate("MainWindow", "end time", None))
        self.label_starttime.setText(translate("MainWindow", "start time", None))
        self.label_stepsize.setText(translate("MainWindow", "consolidation step size", None))
        try:
            self.edit_starttime.setText(str(SessionData().startTime))
            self.edit_endtime.setText(str(SessionData().endTime))
            self.edit_stepsize.setText(str(SessionData().stepSize))
        except: pass

        self.__startTime = SessionData().startTime
        self.__oldStartTime = self.dial_starttime.value()
        self.__endTime = SessionData().endTime
        self.__oldEndTime = self.dial_endtime.value()
        self.__stepSize = SessionData().stepSize
        self.__oldStepSize = self.dial_stepsize.value()

    def setupConnections(self):

        self.edit_stepsize.textChanged.connect(self.stepSizeChanged)
        self.edit_starttime.textChanged.connect(self.startTimeChanged)
        self.edit_endtime.textChanged.connect(self.endTimeChanged)
        self.dial_starttime.valueChanged.connect(self.startTimeSelected)
        self.dial_endtime.valueChanged.connect(self.endTimeSelected)
        self.dial_stepsize.valueChanged.connect(self.stepSizeSelected)
        self.button_startsim.clicked.connect(self.startSimulation)
        self.button_pausesim.clicked.connect(self.pauseSimulation)
        self.button_stopsim.clicked.connect(self.stopSimulation)
        self.button_continuesim.clicked.connect(self.continueSimulation)

        'simulation shortcuts'
        self.connect(QtGui.QShortcut(QtGui.QKeySequence(QtCore.Qt.Key_R), self), QtCore.SIGNAL('activated()'), self.startSimulation)
        self.connect(QtGui.QShortcut(QtGui.QKeySequence(QtCore.Qt.Key_P), self), QtCore.SIGNAL('activated()'), self.pauseSimulation)
        self.connect(QtGui.QShortcut(QtGui.QKeySequence(QtCore.Qt.Key_S), self), QtCore.SIGNAL('activated()'), self.stopSimulation)
        self.connect(QtGui.QShortcut(QtGui.QKeySequence(QtCore.Qt.Key_C), self), QtCore.SIGNAL('activated()'), self.continueSimulation)

    @QtCore.pyqtSlot()
    def restartSimulation(self):
        self.logic = YCM_gui.frameworkUI.FrameworkUi().logic
        self.logic.terminateSimulation()

    @QtCore.pyqtSlot()
    def startSimulation(self):

        if len(SessionData().models) == 0: return

        self.button_startsim.setDisabled(True)
        self.button_pausesim.setDisabled(False)
        self.button_continuesim.setDisabled(True)
        self.button_stopsim.setDisabled(True)

        self.newSimulationStarted.emit()

        self.logic.prepareSolver()
        self.logic.setSimData(SessionData().startTime, SessionData().endTime, SessionData().stepSize)

        for i in SessionData().models:
            self.observerAdded.emit(QT_DOCKING_TIMECOURSE, translate("MainWindow", i, None))

        self.__simulationThread.start()
        # activate this line if debbuging support in qt thread is required, otherwise comment this out
        #self.logic.run()

    @QtCore.pyqtSlot()
    def continueSimulation(self):
        self.__simlogic.continueSimulation()
        self.button_startsim.setDisabled(True)
        self.button_pausesim.setDisabled(False)
        self.button_continuesim.setDisabled(True)
        self.button_stopsim.setDisabled(True)

    @QtCore.pyqtSlot()
    def pauseSimulation(self):
        self.__simlogic.pauseSimulation()
        self.button_startsim.setDisabled(True)
        self.button_pausesim.setDisabled(True)
        self.button_continuesim.setDisabled(False)
        self.button_stopsim.setDisabled(False)

    @QtCore.pyqtSlot()
    def stopSimulation(self):
        self.__simlogic.terminateSimulation()
        self.button_startsim.setDisabled(False)
        self.button_pausesim.setDisabled(True)
        self.button_continuesim.setDisabled(True)
        self.button_stopsim.setDisabled(True)

    @QtCore.pyqtSlot(str)
    def receivemessage(self, message):
        writer.Writer().broadcast("database connector: ", message)

    @QtCore.pyqtSlot()
    def finishSimulation(self):
        self.__simulationThread.quit()
        writer.Writer().broadcast("simulation", "simulation finished")

    @QtCore.pyqtSlot()
    def simulationFinished(self):
        self.logic.needsTermination = False
        self.button_startsim.setDisabled(False)
        self.button_pausesim.setDisabled(True)
        self.button_continuesim.setDisabled(True)
        self.button_stopsim.setDisabled(True)
        writer.Writer().broadcast("simulation", "simulation thread finished")

    @QtCore.pyqtSlot()
    def startTimeChanged(self):
        self.startTime = float(self.edit_starttime.text())
        SessionData().startTime = float(self.edit_starttime.text())

    @QtCore.pyqtSlot()
    def endTimeChanged(self):
        self.endTime = float(self.edit_endtime.text())
        SessionData().endTime = float(self.edit_endtime.text())

    @QtCore.pyqtSlot()
    def stepSizeChanged(self):
        self.stepSize = float(self.edit_stepsize.text())
        SessionData().stepSize = float(self.edit_stepsize.text())

    @QtCore.pyqtSlot(int)
    def startTimeSelected(self, time):
        if time > self.oldStartTime:
            self.startTime = self.startTime + 0.001
        else:
            self.startTime = self.startTime - 0.001

        self.oldStartTime = time

        if (self.startTime < 0): self.startTime = 0
        self.edit_starttime.setText(str(self.startTime))

    @QtCore.pyqtSlot(int)
    def endTimeSelected(self, time):
        if time > self.__oldEndTime:
            self.endTime = self.endTime + 1.0
        else:
            self.endTime = self.endTime - 1.0

        self.oldEndTime = time

        if (self.__endTime < 0): self.endTime = 0
        self.edit_endtime.setText(str(self.endTime))

    @QtCore.pyqtSlot(int)
    def stepSizeSelected(self, size):
        if size > self.oldStepSize:
            self.stepSize = self.stepSize + 0.00001
        else:
            self.stepSize = self.stepSize - 0.00001

        self.oldStepSize = size

        if (self.stepSize < 0.00001): self.stepSize = 0.00001
        self.edit_stepsize.setText(str(self.stepSize))


