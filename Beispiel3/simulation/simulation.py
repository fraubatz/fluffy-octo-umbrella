
import gc
import time
import pickle

from PyQt4.QtCore import *

import writer
from YCM_factory import modelgenerator
from YCM_simulation.consolidation.mediator import Mediator
from YCM_simulation.consolidation.meanMediator import MeanMediator
from YCM_simulation.consolidation.maxMediator import MaxMediator
#from YCM_simulation.plotcontrol import Plotcontrol
from task import Task
from trajectory import Trajectory
from datatypes import *

## class description here...
#
# @author Stefan Forgo
# @date 23.05.2016
#

class SimulationData(object):

    ## parameter constructor
    def __init__(self, start, end, step):
        """
        :param start:
        :param end:
        :param step:
        """
        self.__start = start
        self.__end = end
        self.__step = step
        self.__rateCount = 0
        self.__recalcRateCount()

    @property
    def start(self):
        return self.__start

    @start.setter
    def start(self, s):
        self.__start = s

    @property
    def end(self):
        return self.__end

    @end.setter
    def end(self, e):
        self.__end = e

    @property
    def step(self):
        return self.__step

    @step.setter
    def step(self, newstepsize):
        self.__step = newstepsize
        # rate count has changed in this case
        self.__recalcRateCount()

    @property
    def rateCount(self):
        """
        :return:
        """
        return self.__rateCount

    def range(self,  index):
        """
        :param index:
        :return:
        """
        return [self.__start + index*self.__step, self.__start + ((index+1)*self.__step)]

    def __recalcRateCount(self):
        self.__rateCount =  int((self.end-self.start)/self.step)


class Simulation(QObject, object):

    sendmessage = pyqtSignal(str, name = 'sendmessage')
    finished = pyqtSignal(name= 'finished')

    def __init__(self, dbconnection):
        super(Simulation, self).__init__()

        self.__name = "new Simulation"  # string
        self.__listOfObservers = []     # list of function handles
        self.__listOfTasks = []         # list of task objects
        self.__listOfModels = {}
        self.__trajectories = {}
        self.__mediator = None         # reference to mediator object
        #self.__plotControl = Plotcontrol(self.__trajectories)
        self.__simData = None           # referende to simulationData Object
        self.__dbconnection = dbconnection
        self.__running = True
        self.__needsTermination = False
        self.__currentRangeIndex = 0


    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, name):
        self.__name = name

    @property
    def listOfObservers(self):
        return self.__listOfObservers

    @listOfObservers.setter
    def listOfObservers(self, l):
        self.__listOfObservers = l

    @property
    def simData(self):
        """
        :return simulation data obect:
        :rtype: simData
        """
        return self.__simData

    @simData.setter
    def simData(self, simdata):
        self.__simData = simdata

    @property
    def dbconnection(self):
        return self.__dbconnection

    @dbconnection.setter
    def dbconnection(self, con):
        self.__dbconnection = con

    @property
    def listOfTasks(self):
        return self.__listOfTasks

    @property
    def listOfModels(self):
        return self.__listOfModels

    @property
    def mediator(self):
        return self.__mediator

    @mediator.setter
    def mediator(self, med):
        self.__mediator = med

    #@property
    #def plotControl(self):
    #    return self.__plotControl

    #@plotControl.setter
    #def plotControl(self, pc):
    #    self.__plotControl = pc

    @property
    def trajectories(self):
        return self.__trajectories

    @trajectories.setter
    def trajectories(self, trajectories):
        self.__trajectories = trajectories

    @property
    def running(self):
        return self.__running

    @running.setter
    def running(self, running):
        self.__running = running

    @property
    def needsTermination(self):
        return self.__needsTermination

    @needsTermination.setter
    def needsTermination(self, bval):
        self.__needsTermination = bval

    @property
    def currentRangeIndex(self):
        return self.__currentRangeIndex

    @currentRangeIndex.setter
    def currentRangeIndex(self, i):
        self.__currentRangeIndex = i

    def subscribe(self, observer):
        """
        :param observer: a view (plot) that wants to be informed if data has changed
        :type observer: function handle
        """
        #self.plotControl.addObserver(observer, arrangement)
        self.listOfObservers.append(observer)

    def unsubscribe(self, observer):
        #self.plotControl.removeObserver(observer, arrangement)
        self.listOfObservers.remove(observer)

    def updateObserver(self, data=True):
        """
        """
        if data:
            for notifyNewMessage in self.listOfObservers:
                notifyNewMessage(self.trajectories)
        else:
            nodata = {}
            for notifyNewMessage in self.listOfObservers:
                notifyNewMessage(nodata)

    def prepareSolver(self):
        # prepare solver data for all models in list
        for n in self.listOfModels:
            self.listOfModels[n]['model'].prepareSolver()

    def setSimData(self, start, end, step):
        """
        :param start: simulation start time
        :type start: double
        :param end: simulation end time
        :type end: double
        :param step: consolidation steps
        :type step: double
        """
        self.currentRangeIndex = 0
        self.simData = SimulationData(start, end, step)
        for t in self.trajectories:
            self.trajectories[t].minValue = start
            self.trajectories[t].maxValue = end
        self.trajectories = {}
        for n in self.listOfModels:
            m = self.listOfModels[n]['model']
            self.trajectories[n] = Trajectory(start,end)
            self.trajectories[m.name].units = m.listOfUnits
            self.trajectories[m.name].lastState = m.state

    def setMediator(self, type):
        if type == MEAN_MEDIATOR:
            #todo: create list of common species here, parse the model objects for that. No Db connection required!
            #todo: call self.mediator = MeanMediator(listOfCommonSpecies)
            self.mediator = MeanMediator(self.listOfModels.keys(), self.dbconnection)
        elif type == MAX_MEDIATOR:
            self.mediator = MaxMediator(self.listOfModels.keys(), self.dbconnection)
        else:
            self.mediator = MaxMediator(self.listOfModels.keys(), self.dbconnection)
        # bring all common states to equal values
        self.mediator.equalize(self.listOfModels)

    def __resetTasks(self):
        while len(self.listOfTasks) != 0:
            self.listOfTasks.pop()

    def __resetModels(self, key):
        while len(self.listOfModels) != 0:
            self.listOfModels.pop()

    def pauseSimulation(self):
        self.running = False
        writer.Writer().broadcast("simulation", "simulation paused!")

    def continueSimulation(self):
        self.running = True
        writer.Writer().broadcast("simulation", "simulation continued!")

    def terminateSimulation(self):
        #self.running = False

        for t in self.listOfTasks:
            t.terminate()
            print 'TERMINATED:', t, t.is_alive()

        for t in self.listOfTasks:
            t.join()
            print 'JOINED:', t, t.is_alive()

        self.running = True
        self.needsTermination = True
        writer.Writer().broadcast("simulation", "simulation terminated!")

    def run(self):

        rc = self.simData.rateCount
        if self.mediator:
            self.mediator.reset()

        while self.currentRangeIndex < self.simData.rateCount:
        #for self.currentRangeIndex in xrange(self.simData.rateCount):
            i = self.currentRangeIndex
            #print 'curren range {0}'.format(self.simData.range(i))
            for j in self.listOfModels:
                t = Task(self.listOfModels[j]['solver'], self.listOfModels[j]['model'], i, self.simData.range(i))
                self.listOfTasks.append(t)
                t.model.state = self.trajectories[t.model.name].lastState

            for t in self.listOfTasks:
                t.start()

            for t in self.listOfTasks:
                # first empty the queue...
                result = t.processQueue.get()
                info = t.processQueue.get()
                t_range = t.processQueue.get()

                self.trajectories[t.model.name].addTrace(result, t_range)
                #self.plotControl.trajectories = self.trajectories

                message = 'model {0}: solver step {1} to {2}: {3}'.format(t.model.name, t_range[0], t_range[-1], info['message'])
                self.sendmessage.emit(message)

                #...then stop all processes, when they finished
                t.join()

            self.currentRangeIndex += 1
            message = "consolidate..."
            self.sendmessage.emit(message)

            # todo: consolidate results
            #for m in self.listOfTasks:
            #    print 'state of {0}: {1}'.format(m.model.name, m.model.state)
            if self.mediator:
                #print self.mediator
                stepsize = self.mediator.consolidate(self.listOfModels, self.simData.step)
                if stepsize < self.simData.step:
                    changeFactor = int(self.simData.step/stepsize)
                    if stepsize < ODEINT_GRIDSIZE:
                        self.needsTermination = True
                    self.simData.step = stepsize
                    self.currentRangeIndex = (i-1) * changeFactor
                    print 'stepsize after consolidation {0}'.format(stepsize)

            while not self.running:
                time.sleep(0.1)

            self.__resetTasks()

            if self.needsTermination == True:
                break

            self.updateObserver()

        self.finished.emit()
        #self.trajectories = {}

        #self.updateObserver()
        self.updateObserver(False)

        #print gc.garbage

    def addModel(self, model):
        """
        :param modelNames:
        :param solverType:
        """

        modelfactory = None
        if model.type == 'ode':
            modelfactory = modelgenerator.OdeModelGenerator(self.dbconnection, model.models, model.solver)
        elif model.type == 'stochastic':
            modelfactory = modelgenerator.StochasticModelGenerator(self.dbconnection, model.models)
        elif model.type == 'boolean':
            modelfactory = modelgenerator.BooleanModelGenerator(self.dbconnection, model.models)
        else:
            message = 'model type {0} is not supported'.format(model.name)
            writer.Writer().broadcast("add model", message)
            return None

        if modelfactory == None:
            return None

        s = modelfactory.generateSolver()

        name = ''.join(model.models)
        if name in self.listOfModels:
            mod = self.listOfModels[name]['model']
            if mod.solver.type == model.solver:
                # return loaded model in case of no solver changes
                return mod
            else:
                mod.solver = s
                self.listOfModels[name]['solver'] = s
                return mod

        realmodel = modelfactory.generateModel(s)
        self.listOfModels[model.name] = {'solver':s, 'model':realmodel}

        return realmodel

    def addExistingModel(self, model, solver):
        self.listOfModels[model.name] = {'solver': solver, 'model': model}

    def removeModel(self, name):
        """
        :param taskId:
        """
        self.listOfModels.pop(name, None)

    def exportModel(self, name, location, newName, target):
        if target == MODELDICT:
            self.listOfModels[name]['model'].toDict(location, newName)
        elif target == SBML:
            self.listOfModels[name]['model'].toSBML(location)

    def safeModel(self):
        self.listOfModels[name]['model'].toDB()

    def writeModel(self, modelnames, location, newName):
        for model in modelnames:
            mod = self.listOfModels[model]['model']
            mod.name = newName.split('.')[0]

            #save structures which are not supported by pickle. These are imports and Connections
            md = mod.dataSource
            mod.dataSource = None

            if hasattr(mod.solver, 'f'):
                solverf = mod.solver.f
                mod.solver.f = None

            # serialize model objects
            with open("{0}/{1}".format(location, newName), 'wb') as f:
                # Pickle the 'data' dictionary using the highest protocol available.
                pickle.dump(mod, f)
                f.close()

            # re-assign saved structures
            mod.dataSource = md
            if hasattr(mod.solver, 'f'):
                mod.solver.f = solverf

        return True

    def readModel(self, location):
        m = None
        with open(location, 'r') as f:
            m = pickle.load(f)
            f.close()

        return m
