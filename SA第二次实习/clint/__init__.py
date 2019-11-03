import tkinter
from tkinter import ttk

from matplotlib.backend_bases import key_press_handler

'''因为combobox在ttk'''
import tkinter.messagebox
import boto3
import clint._send_message_
import clint._recive_message_
import matplotlib.pyplot as plt

from matplotlib.figure import Figure
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg, NavigationToolbar2Tk

import numpy as np

'''
初始化客户端界面

'''
root = tkinter.Tk()
root.geometry('500x400')
root.title('客户端')


def ClickFeadback1():
    x = text1.get(0.0, "end")
    text4.insert('end', x)
    y = text2.get(0.0, "end")
    text4.insert('end', y)
    z = text3.get(0.0, "end")
    text4.insert('end', z)
    s =_send_message_.connect(x,y,z)
    r = _recive_message_.send(x,y,z)
    tkinter.messagebox.showinfo("提醒", "查询消息成功")
def ClickFeadback2():
    tkinter.messagebox.showinfo("提醒", "消息接收成功")
def paint():
    def on_key_event(event):
        """键盘事件处理"""
        print("你按了%s" % event.key)

        key_press_handler(event, canvas, toolbar)

    def _quit_():
        quit()

    son = tkinter.Tk()
    son.title('曲线')
    f = Figure(figsize=(5, 4), dpi=100)
    a = f.add_subplot(111)  # 添加子图:1行1列第1个
    x = np.linspace(-1, 1, 50)  # 从(-1,1)均匀取50个点
    y = 2 * x
    a.plot(x, y)
    button4 = tkinter.Button(son, text='quit', command=_quit_)
    button4.pack(side=tkinter.BOTTOM)
    canvas = FigureCanvasTkAgg(f, master=son)
    canvas.draw()
    canvas.get_tk_widget().pack(side=tkinter.TOP,
                                fill=tkinter.BOTH,
                                expand=tkinter.YES)
    toolbar = NavigationToolbar2Tk(canvas, son)
    toolbar.update()
    canvas._tkcanvas.pack(side=tkinter.TOP,  # get_tk_widget()得到的就是_tkcanvas
                          fill=tkinter.BOTH,
                          expand=tkinter.YES)
    son.mainloop()


label1 = tkinter.Label(root, text='->')
label2 = tkinter.Label(root, bd='2', text='SELECT')
label3 = tkinter.Label(root, text='FROM')
label4 = tkinter.Label(root, text='WHERE')
button1 = tkinter.Button(root, text='查询消息', command=ClickFeadback1)
button2 = tkinter.Button(root, text='接受消息', command=ClickFeadback2)
button3 = tkinter.Button(root, text='绘制', command=paint)
text1 = tkinter.Text(root, bg='pink', bd='2', width=10, height=1)
text2 = tkinter.Text(root, bg='yellow', bd='2', width=10, height=1)
text3 = tkinter.Text(root, bg='green', bd='2', width=10, height=1)
text4 = tkinter.Text(root, bg='yellow', bd='2', width=50, height=25)
combobox1 = ttk.Combobox(root, width=36, height=1)

label1.place(x=0, y=32)
label2.place(x=18, y=32)
label3.place(x=153, y=32)
label4.place(x=278, y=32)
button1.place(x=430, y=33)
button2.place(x=430, y=90)
button3.place(x=430, y=66)
text1.place(x=70, y=30)
text2.place(x=200, y=30)
text3.place(x=330, y=30)
text4.place(x=70, y=90)
combobox1.place(x=71, y=60)

root.mainloop()
