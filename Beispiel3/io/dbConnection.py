
from abc import ABCMeta, abstractmethod

## interface for all datasource connections
#
# @author Stefan Forgo
# @date 20.05.2016
#
# Abstract interface for a db connection, that defines the abstract methods open(), close(), callQuery() and callProcedure() as the minimum requirement for a db connection class.
# The data source could be any relational database management system (RDBMS) or any file based database system. These details are defined in the derived classes.
# Derived classes have to implement open(), close(), callQuery() and callProcedure() and should take care of the their return types! If procedures are not supported by the database,
# the derived class has to resolve the queries inside the procedures and launch callQuery() from callProcedure() instead.

class DbConnection(object):


    __metaclass__ = ABCMeta

    ## standard constructor
    def __init__(self):
        self.__isEstablished = False

    ## simple property
    # @return [Boolean] connection status. True if connection is established, else False
    @property
    def isEstablished(self):
        return self.__isEstablished

    ## simple setter. This should be called by the derived class only!
    # @param connected The new connection status.
    # @return [void] nothing returned
    @isEstablished.setter
    def isEstablished(self, connected):
        self.__isEstablished = connected


    ## abstract method. Has to be overwritten by derived class. Function executes SQL-query and returns the result
    # @param query [string] the SQL-query
    # @return [Tuple] the query result stored in a dictionary where the keys are the column names in the query, wrapped by a tuple
    # @exception NotImplementedError this exception is thrown if function is not implemented by the derived class
    @abstractmethod
    def callQuery(self, query):
        raise NotImplementedError("function callQuery(query)")

    ## abstract method. Has to be overwritten by derived class. Function executes an internal procedure in the database.
    # @param query [string] the SQL-query
    # @return [void] nothing returned
    # @exception NotImplementedError this exception is thrown if function is not implemented by the derived class
    @abstractmethod
    def callProcedure(self, query):
        raise NotImplementedError("function callProcedure(query)")

    ## abstract method. Has to be overwritten by derived class. Function tries to establish connection to the database
    # @param user [string] User name.
    # @param host [string] Computer DNS name or IP-adress where the database is located
    # @param pwd [string] Passwort to the database
    # @param db [string] Name of the database
    # @return [Boolean] True if connection is established, else False
    # @exception NotImplementedError this exception is thrown if function is not implemented by the derived class
    @abstractmethod
    def open(self, user, host, pwd, db):
       raise NotImplementedError("function openConnection(user, host, pwd)")

    ## abstract method. Has to be overwritten by derived class. Function closes the connection to a database
    # @exception NotImplementedError this exception is thrown if function is not implemented by the derived class
    @abstractmethod
    def close(self):
        raise NotImplementedError("function closeConnection()")

    ## @var __isEstablished
    # [Boolean] hold the connection status, set by the derived classes. Init with False
    ## @var __metaclass__
    # python decorator class for defining abstract methods


    @abstractmethod
    def callSelect(self, columns):
        raise NotImplementedError("function callSelect()")

    @abstractmethod
    def callInsert(self, columns, values):
        raise NotImplementedError("function callInsert()")

    @abstractmethod
    def callUpdate(self, columns, values):
        raise NotImplementedError("function callUpdate()")

