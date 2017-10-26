
from multiprocessing import Process, Queue
import YCM_factory.modelgenerator

## class description here...
#
# @author Stefan Forgo
# @date 23.05.2016
#

class Task(Process, object):

    ## parameter constructor
    def __init__(self, solver, model, id, range):
        super(Task, self).__init__()
        self.__processQueue = Queue()
        self.__id = id
        self.__range = range
        self.__solver = solver
        self.__model = model

    @property
    def processQueue(self):
        return self.__processQueue

    @property
    def id(self):
        return self.__id

    @property
    def model(self):
        return self.__model

    @property
    def solver(self):
        return self.__solver

    @property
    def range(self):
        return self.__range

    @range.setter
    def range(self, range):
        self.__range = range

    def run(self):
        self.__solver.range = self.range
        result, info, trange = self.model.solve()
        self.__processQueue.put(result)
        self.__processQueue.put(info)
        self.__processQueue.put(trange)
        #print "task {0} for range {1} - {2} with value {3}".format(self.id, self.range[0], self.range[1], result)


    def execute(self):
        self.__solver.execute()
