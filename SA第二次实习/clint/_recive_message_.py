
from notebook.notebookapp import raw_input
import tkinter as Tk
import tkinter.messagebox
import socket

# HOST and PORT can be changed as you need.
class SEND:
    def Send(x,y,z):
        HOST = '47.97.184.36'
        PORT = 22
        BUFSIZE = 1024
        ADDR = (HOST, PORT)
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect(ADDR)
        data = str(x)+str(y)+str(z)
        if not data:
            Tk.messagebox("ss","不能为空")
        s.send(bytes(data, 'utf-8'))
        data = s.recv(BUFSIZE)
        print(data.decode('utf-8'))
        s.close()