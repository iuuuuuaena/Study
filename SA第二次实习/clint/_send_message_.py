import boto3
import sys
import pymysql
#from clint.__init__ import text1
# class Connect:
#     xx = "1"
#     yy = "2"
#     zz = "3"
#     def __init__(self,x,y,z):
#         xx = x
#         yy = y
#         zz = z
import pymysql
def connect(xx,yy,zz):
        # 连接database
    print("Serty")
    conn = pymysql.connect(host="mysql1.cxn970h1xyso.us-east-1.rds.amazonaws.com", user="A123456", password="12345678", database="mysql1", charset="utf8")
        # 得到一个可以执行SQL语句的光标对象
    cursor = conn.cursor()

    # SQL = """select *
    #          from text
    #          where changes < 2.0 """
    # 执行SQL语句 # 定义要执行的SQL语句
        #
        #         #x = text1.get(0.0,'end')
        #        # print('x:',x)clint/_send_message_.py:18
        #
    sql = """select  %(X)s 
             from  %(Y)s 
             where  %(Z)s """
    SQL = sql % dict(X = xx,Y = yy,Z = zz)
    ret = cursor.execute(SQL)
    data = cursor.fetchall()
    print("数据为",data)
    cursor.close()
    conn.close()

#
if __name__ == '__main__':
    # c = Connect(1,2,3)
    connect()

















