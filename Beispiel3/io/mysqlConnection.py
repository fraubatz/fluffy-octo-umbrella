import writer
import MySQLdb, MySQLdb.cursors
from dbConnection import DbConnection

## Wrapper for mysql database connections
#
#  @author Stefan Forgo
#  @date 20.05.2016
#


class MysqlConnection(DbConnection):

    ## standard constructor
    def __init__(self):
        super(MysqlConnection, self).__init__()
        self.__connection = None

    def callQuery(self, query):
        """
        execute a query on ycm_model database.

        :param query: Sql statement
        :type query: string
        :return: the query result
        :rtype: dictionary {column: value}
        """
        try:
            cur = self.__connection.cursor()
            cur.execute(query)
            resultset = cur.fetchall()
            self.__connection.commit()
            cur.close()
            return resultset

        except MySQLdb.Error, e:
            writer.Writer().broadcast("mysql connector", "db query: " + query + 'failed: ' + e.__str__())
            return None

    def callProcedure(self, procname, argList):
        """
        call a stored procedure on mysql server.

        :param name: the name of the stored procedure
        :param argList: list of arguments passed with the stored procedure
        :return: the query result
        :rtype: dictionary {column: value}
        """
        try:
            cur = self.__connection.cursor()
            cur.callproc(procname, args=argList)
            resultset = cur.fetchall()
            cur.close()
            #writer.Writer().broadcast("mysql connector", "procedure call success")
            return resultset

        except MySQLdb.Error, e:
            writer.Writer().broadcast("mysql connector", "procedure call failed: " + e.__str__())
            return None

    #@listener
    def open(self, user, host, pwd, db):
        """establish a connection to the mysql server.

        :param db: name of database
        :type db: string
        :param user: user name
        :type user: string
        :param host: db host name
        :type host: string
        :param pwd: password
        :type pwd: string
        :raises MySQLdb.Error: if connection failed
        """
        try:
            self.__connection =  MySQLdb.connect(host, user, pwd, db, use_unicode = True, charset = 'utf8', cursorclass = MySQLdb.cursors.DictCursor)
            #self.__connection = MySQLdb.connect(host, user, pwd, db, cursorclass=MySQLdb.cursors.DictCursor)
            self.isEstablished = True
            writer.Writer().broadcast("mysql connector", "connection succeeded")

        except MySQLdb.Error, e:
            self.__connection = None
            self.isEstablished = False
            print e
            writer.Writer().broadcast("mysql connector", "connection failed: " + e.__str__())

    def close(self):
        """
        close connection to Mysql-Server
        """
        self.__connection.close()
        self.isEstablished = False

    def callSelect(self, columns):
        pass

    def callInsert(self, columns, values):
        pass

    def callUpdate(self, columns, values):
        pass

    ## @var __connection
    # [MysqlDb._mysql] Open Database Connectivity (ODBC)-connector to the Mysql database. Init with None




