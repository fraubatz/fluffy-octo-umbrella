
from Queue import Queue
from datatypes import *
import sys

## class description here...
#
# @author Stefan Forgo
# @date 23.05.2016
#

class Trajectory(object):

    ## parameter constructor
    def __init__(self, xmin, xmax):
        self.__speciesTraces = {}
        self.__speciesTime = []
        self.__lastTrace = {}
        self.__lastTime = {}
        self.__lastState = {}
        self.__queue = Queue()
        self.__lowerTime = xmin
        self.__upperTime = xmax
        self.__minValue = sys.float_info.max
        self.__maxValue = 0.0
        self.__type = CONCENTRATION
        self.__units = {}

    @property
    def speciesTraces(self):
        return self.__speciesTraces

    @speciesTraces.setter
    def speciesTraces(self, matrix):
        self.__speciesTraces = matrix

    @property
    def speciesTime(self):
        return self.__speciesTime

    @speciesTime.setter
    def speciesTime(self, time):
        self.__speciesTime = time

    @property
    def lastTrace(self):
        return self.__lastTrace

    @lastTrace.setter
    def lastTrace(self, trace):
        self.__lastTrace = trace

    @property
    def lastTime(self):
        return self.__lastTime

    @lastTime.setter
    def lastTime(self, t):
        self.__lastTime = t

    @property
    def lastState(self):
        return self.__lastState

    @lastState.setter
    def lastState(self, trace):
        self.__lastState = trace

    @property
    def queue(self):
        return self.__queue

    @property
    def lowerTime(self):
        return self.__lowerTime

    @lowerTime.setter
    def lowerTime(self, time):
        self.__lowerTime = time

    @property
    def upperTime(self):
        return self.__upperTime

    @upperTime.setter
    def upperTime(self, time):
        self.__upperTime = time

    @property
    def minValue(self):
        return self.__minValue

    @minValue.setter
    def minValue(self, val):
        self.__minValue = val

    @property
    def maxValue(self):
        return self.__maxValue

    @maxValue.setter
    def maxValue(self, val):
        self.__maxValue = val

    @property
    def units(self):
        return self.__units

    @units.setter
    def units(self, units):
        self.__units = units

    def addTrace(self, timecourse, range):
        # solution matrix
        for spec in timecourse:
            val = list(timecourse[spec])
            self.calculateBounds(val)
            self.lastTrace[spec] = val
            if spec not in self.speciesTraces:
                self.speciesTraces[spec] = val
            else:
                for v in val:
                    self.speciesTraces[spec].append(v)
            self.lastState[spec] = val[-1]

        # time
        for t in range:
            self.speciesTime.append(t)
        self.lastTime = range

        # data queue for plotting
        self.queue.put(timecourse)
        self.queue.put(range)

    def consistency(self):
        #todo: compare solutionmatrix column with time length
        return True

    def calculateBounds(self, l):
        upperbound = max(l) + 1
        lowerbound = min(l) - 1
        if upperbound > self.maxValue:
            self.maxValue = upperbound
        if lowerbound < self.minValue:
            self.minValue = lowerbound

    def clear(self):
        self.lastState = {}
        self.speciesTraces = {}
        self.speciesTime = []
        while self.queue.empty() == False:
            self.queue.get()