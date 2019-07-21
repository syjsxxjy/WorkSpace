#!/usr/bin/env python
# -*- coding: utf-8 -*-

# import the os module
import os
import sys
import subprocess
import tty, termios
import RPi.GPIO
import time
import threading

class myThread (threading.Thread):
    def __init__(self, threadID, name, key):
        threading.Thread.__init__(self)
        self.threadID = threadID
        self.name = name
        self.key = key
    def run(self):
        print "Starting " + self.name
        inputKey(key)
        print_time(self.name, self.key, 3)

#定义键盘输入方法 
def inputKey(key):
	print("waiting for input @%"%(key))
	InputKey=key
    while True:
        fd=sys.stdin.fileno()
        old_settings=termios.tcgetattr(fd)
        try:
            tty.setraw(fd)
            ch=sys.stdin.read(1)
        finally:
            termios.tcsetattr(fd, termios.TCSADRAIN, old_settings)  
            #print 'error'
        if ch==InputKey:
            print("Press key ==%s"%(InputKey))
            time.sleep(1) #此处等待为防止同时按下多个按钮

        elif ord(ch)==0x3: #这个是ctrl c  
          print "shutdown"
        break
        print "Reading form keybord:"

#创建线程数列
threads = []

# 创建新线程
thread1 = myThread(1, "inputB", "b")
thread2 = myThread(2, "inputQ", "q")

# 添加线程到线程列表
threads.append(thread1)
threads.append(thread2)

# 等待所有线程完成
for t in threads:
    t.join()
print "Exiting Main Thread"