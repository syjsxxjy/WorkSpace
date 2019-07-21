#!/usr/bin/env python
# -*- coding: utf-8 -

# import libraries
import  os  
import  sys
import  tty, termios
import RPi.GPIO
import time

# setup input pin for motor
In1_Motor=13   #GPIOXX 
In2_Motor=19  #GPIOXX 

# LED1 指示灯 pin
outPutLED=6 # GPIO6 此灯亮表示程序启动完毕

# setup input pins,edit pin number here
InputA=16  #GPIO16 为A输入 键盘【A】 正转    
InputB=7   #GPIO7 为B输入 键盘【D】 反转
InputC=8   #GPIO8 为C输入 键盘【W】
InputD=15  #GPIO15 as inputD 键盘【S】 停止

# set up BCM GPIO numbering
RPi.GPIO.setmode(RPi.GPIO.BCM)

# set Motor Diver's Input Pin to output mode
RPi.GPIO.setup(In1_Motor, RPi.GPIO.OUT)
RPi.GPIO.setup(In2_Motor, RPi.GPIO.OUT)
RPi.GPIO.setup(outPutLED, RPi.GPIO.OUT)

## output GPIOs Initial
RPi.GPIO.output(In1_Motor, False)
RPi.GPIO.output(In2_Motor, False)
RPi.GPIO.output(outPutLED, False)

# setup GPIO triggered  记录按钮输入状态（是否按下）
A_triggered= False
B_triggered= False
C_triggered= False
D_triggered= False

# set pins to input mode and pull up to high level(if necessary).
RPi.GPIO.setup(InputA, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)
RPi.GPIO.setup(InputB, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)
RPi.GPIO.setup(InputC, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)
RPi.GPIO.setup(InputD, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)

#设置反馈线程
def SwitchA(channel):   #按钮A反馈方法线程
    #if RPi.GPIO.input(InputA):
            global A_triggered
            A_triggered= True

def SwitchB(channel):   #按钮B反馈方法线程
    #if RPi.GPIO.input(InputB):
            global B_triggered
            B_triggered= True

def SwitchC(channel):
    #if RPi.GPIO.input(InputC):
        global C_triggered
        C_triggered= True

def SwitchD(channel):
    #if RPi.GPIO.input(InputD):
        global D_triggered
        D_triggered= True

def TriggeredAllFalse():  #将所有开关输入状态设置为否
    global A_triggered
    global B_triggered
    global C_triggered
    global D_triggered
    A_triggered= False
    B_triggered= False
    C_triggered= False
    D_triggered= False

 #设置事件检测
 # when a rising edge is detected on pin,the callback functions will be run
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING,callback=SwitchA,bouncetime=200 ) # when use real Input swtich to switch the input level,
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING,callback=SwitchB,bouncetime=200 ) # bouncetime=[time] to debounce the switch.
RPi.GPIO.add_event_detect(InputC, RPi.GPIO.RISING,callback=SwitchC,bouncetime=200 )
RPi.GPIO.add_event_detect(InputD, RPi.GPIO.RISING,callback=SwitchD,bouncetime=200 )

try:
    print "Reading from keyboard or buttons:"
    print "A=正方向 W=ブレーキ　S=ストップ D=逆方向　Q=Quit"
    RPi.GPIO.output(outPutLED, True)
    
# Main loop start here
    while  True:
        time.sleep(0.1)
#get events of the keyboard
    # fd=sys.stdin.fileno()
    #     old_settings=termios.tcgetattr(fd)
    #     try:
    #         tty.setraw(fd)
    #         ch=sys.stdin.read(1)
    #     finally:
    #         termios.tcsetattr(fd, termios.TCSADRAIN, old_settings)  
    #         #print 'error'
    #     if ch=='a':
    #         print '----------------'
    #         print '【move forward】'
    #         print '----------------'
    #         RPi.GPIO.output(In1_Motor, True)
    #         RPi.GPIO.output(In2_Motor, False)
    #         print (RPi.GPIO.input(In1_Motor))
    #         print (RPi.GPIO.input(In2_Motor))
    #         time.sleep(1) #此处等待为防止同时按下多个按钮
    #     elif ch=='w':
    #         print '----------------'
    #         print '【Break】'
    #         print '----------------'
    #         RPi.GPIO.output(In1_Motor, True)
    #         RPi.GPIO.output(In2_Motor, True)
    #         time.sleep(0.2)
    #     elif ch=='s':
    #         print '----------------'
    #         print '【Stop】'
    #         print '----------------'
    #         RPi.GPIO.output(In1_Motor, False)
    #         RPi.GPIO.output(In2_Motor, False)
    #         time.sleep(0.2)
    #     elif ch=='d':
    #         print '----------------'
    #         print "【move backward】"
    #         print '----------------'
    #         RPi.GPIO.output(In1_Motor, False)
    #         RPi.GPIO.output(In2_Motor, True)
    #         time.sleep(1)
    #     elif ch=='q':
    #         print '----------------'
    #         print "【shutdown!】"
    #         print '----------------'
    #         break
    #     elif ord(ch)==0x3: #这个是ctrl c
            
    #         print "shutdown"
    #         break

# 按钮事件
    if A_triggered:
        print '----------------'
        print '【move forward】'
        print '----------------'
        RPi.GPIO.output(In1_Motor, True)
        RPi.GPIO.output(In2_Motor, False)
        print "In1_Motor 输出为："
        print (RPi.GPIO.input(In1_Motor))
        print "In2_Motor 输出为："
        print (RPi.GPIO.input(In2_Motor))
        A_triggered=False #把按钮A设为关闭
        time.sleep(1) #此处等待为防止同时按下多个按钮  

    elif B_triggered: #【D】
        print '----------------'
        print "【move backward】"
        print '----------------'
        RPi.GPIO.output(In1_Motor, False)
        RPi.GPIO.output(In2_Motor, True)
        B_triggered=False
        time.sleep(1)

    elif C_triggered: #【W】
        print '----------------'
        print '【Break】'
        print '----------------'
        RPi.GPIO.output(In1_Motor, True)
        RPi.GPIO.output(In2_Motor, True)
        C_triggered=False
        time.sleep(0.2)

    elif D_triggered: #【S】
        print '----------------'
        print '【Stop】'
        print '----------------'
        RPi.GPIO.output(In1_Motor, False)
        RPi.GPIO.output(In2_Motor, False)
        D_triggered=False
        time.sleep(0.2)

    print "Reading from keyboard or buttons:"
    print "A=正方向 W=ブレーキ　S=ストップ D=逆方向　Q=Quit"
finally:                   # this block will run no matter how the try block exits
    RPi.GPIO.cleanup()    # clean up after yourself
