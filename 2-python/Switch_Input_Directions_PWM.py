#!/usr/bin/env python
# -*- coding: utf-8 -*-

#-----------------------------------------------------------------------------------------#
# PROJECT   :  RasPi Python :: Switch Input Directions Test
# FILENAME  :  Switch_Input_Directions_PWM.py
# Author    :  https://github.com/syjsxxjy
#
# Codes after "#" will not be run.
# This code shows how to input in sequences and determine if they are in the right sequences.
# 
# Turn on   Turn off   Pwm up   Pwm Down
#   AB        BA         DC       CD
#   AC        BC         DB       CB
#   AD        BD         DA       CA
#   
#  点灯      消灯      调光Up   调光Down
#   AB        BA         DC       CD
#   AC        BC         DB       CB
#   AD        BD         DA       CA
#
# You can find this code here:
# https://github.com/syjsxxjy/WorkSpace/tree/master/python
#
# References(参考文献):
# https://sourceforge.net/p/raspberry-gpio-python/wiki/Inputs/
# https://sourceforge.net/p/raspberry-gpio-python/wiki/PWM/
#-----------------------------------------------------------------------------------------#

# import libraries
import RPi.GPIO
import time

# 设置全局变量 setup global variables at first
# setup input pins,edit pin number here
InputA=16  #GPIO16 为A输入
InputB=7   #GPIO7 为B输入
InputC=8   #GPIO8 为C输入
InputD=15  #GPIO15 as inputD

# setup output pin for LED
LED=13   #GPIO13 for LED
LED1=11  #GPIO11 for LED1
LED2=9
LED3=10

# setup GPIO triggered  记录按钮输入状态（是否按下）
A_triggered= False
B_triggered= False
C_triggered= False
D_triggered= False

# 树莓派基本设定
# set up BCM GPIO numbering
RPi.GPIO.setmode(RPi.GPIO.BCM)

# set pins to input mode and pull up to high level(if necessary).
RPi.GPIO.setup(InputA, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)
RPi.GPIO.setup(InputB, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)
RPi.GPIO.setup(InputC, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)
RPi.GPIO.setup(InputD, RPi.GPIO.IN,pull_up_down=RPi.GPIO.PUD_DOWN)

# set pins for LEDs to output mode
RPi.GPIO.setup(LED, RPi.GPIO.OUT)
RPi.GPIO.setup(LED1, RPi.GPIO.OUT)
RPi.GPIO.setup(LED2, RPi.GPIO.OUT)
RPi.GPIO.setup(LED3, RPi.GPIO.OUT)

# set up variables to save start and end time, initialize them to 0 as default
#TimeA=0.00
#TimeB=0.00

#set pwm



# Define serveral threaded callback functions to run in another thread when events are detected
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

def outputIconTrasnfer(output):
    outputIcon=0
    if output==True:
        return "【○ 】(True)"
    if output==False:   
        return "【× 】(False)"

# define a function to turn on LED
def turnon(LED_Num):
        RPi.GPIO.output(LED_Num, True)
        print ("-------------------------------")   #【○ 】
        print ("| LED[0]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED))))
        print ("-------------------------------")
        print ("| LED[1]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED1))))
        print ("-------------------------------")
        print ("| LED[2]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED2))))
        print ("-------------------------------")
        print ("| LED[3]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED3))))
        print ("-------------------------------")

# define a function to turn off LED
def turnoff(LED_Num):
        RPi.GPIO.output(LED_Num, False)
        print ("---------------------------------")   #【× 】
        print ("| LED[0]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED))))
        print ("-------------------------------")
        print ("| LED[1]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED1))))
        print ("-------------------------------")
        print ("| LED[2]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED2))))
        print ("-------------------------------")
        print ("| LED[3]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED3))))
        print ("-------------------------------")

## define a function to turn off all the LEDs
def turnoffall():
    RPi.GPIO.output(LED, False)
    RPi.GPIO.output(LED1, False)
    RPi.GPIO.output(LED2, False)
    RPi.GPIO.output(LED3, False)
    print ("---------------------------------")   #【× 】
    print ("| LED[0]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED))))
    print ("-------------------------------")
    print ("| LED[1]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED1))))
    print ("-------------------------------")
    print ("| LED[2]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED2))))
    print ("-------------------------------")
    print ("| LED[3]の出力 ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED3))))
    print ("-------------------------------")

# detect rising edge
# when a rising edge is detected on pin,the callback functions will be run
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING,callback=SwitchA,bouncetime=200 ) # when use real button swtich to switch the input level,
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING,callback=SwitchB,bouncetime=200 ) # bouncetime=[time] to debounce the switch.
RPi.GPIO.add_event_detect(InputC, RPi.GPIO.RISING,callback=SwitchC,bouncetime=200 )
RPi.GPIO.add_event_detect(InputD, RPi.GPIO.RISING,callback=SwitchD,bouncetime=200 )

try:
    start = time.time()
    print ("AltとCを押すと,プログラムを中止する。")
    time.sleep(0.5)
    print ("ループ開始：１番目の入力を待つ... Wait for 1st input...")
    #print ("Press 'Alt+C' to quit ")

    ### main(1st) loop start,check for 1stInput
    while True:
        time.sleep(0.1)

    ### when 1stinput is B
        if B_triggered and A_triggered==False:
            #TriggeredAllFalse()
            print("１番目の入力 == [Button B],２番目の入力を待つ...")

    ### 2nd loop,check for 2nd Input
            while True:
                time.sleep(0.1)
                if A_triggered : ### when 2ndinput is A
                    B_triggered=False
                    A_triggered=False
                    turnoffall()
                    print("２番目の入力==[Button A],キー==[BA](右),LED0を消す。最初に戻る。")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print("2nd input=[Button A],encryption key==True,turnoff and back to the start")
                    #print ("loop start: Wait for 1st input...")
                    break

                elif C_triggered:
                    B_triggered=False
                    C_triggered=False
                    turnoffall()
                    print("２番目の入力==[Button C],キー==[BC](右),LED0を消す。最初に戻る。")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print("2nd input=[Button C],Key==False,back to start")
                    #print ("Wait for 1st input...")
                    break
                elif D_triggered:
                    B_triggered=False
                    D_triggered=False
                    turnoffall()
                    print("２番目の入力==[Button D],キー==[BD](右),LED0を消す。最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break
                else:
                    continue

    ### when 1stinput is A
        elif A_triggered and B_triggered==False:
            #TriggeredAllFalse()
            print("１番目の入力 == [Button A],２番目の入力を待つ...")
            #print("1st input=[Button A],waiting for 2nd input...")

    ### 2nd loop,check for 2nd Input
            while True:
                time.sleep(0.1)
                if B_triggered :          ### when 2ndinput is B
                    B_triggered=False
                    A_triggered=False
                    turnon(LED)
                    print("２番目の入力==[Button B],キー==[AB](左),LED0をつける。最初に戻る。")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print("2nd input=[Button B],True,turnon the LED and back to start")
                    #print ("loop start: Wait for 1st input...")
                    break
                                                                ### if 2nd input is A,the loop will continue
                elif C_triggered:
                    C_triggered=False
                    A_triggered=False
                    turnon(LED)
                    print("２番目の入力==[Button C],キー==[AC](左),LED0をつける。最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break

                elif D_triggered:
                    D_triggered=False
                    A_triggered=False
                    turnon(LED)
                    print("２番目の入力==[Button D],キー==[AD](左),LED0をつける。最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break
                else:
                    continue

    ### when 1stinput is C
        elif C_triggered and D_triggered==False:
            print("１番目の入力 == [Button C],２番目の入力を待つ...")
            #print("1st input=[Button C],waiting for 2nd input...")

    ### 2nd loop,check for 2nd Input
            while True:
                time.sleep(0.1)

                if A_triggered :      ### when 2ndinput is A
                    C_triggered=False
                    A_triggered=False
                    pwm = RPi.GPIO.PWM(LED, 100)  
                    pwm.start(100)
                    pause_time = 0.02 
                    print ("２番目の入力==[Button A],キー==[CA](调光->Down),LED0が暗くなる。")
                    for i in range(100,-1,-1):        # from 100 to zero in steps of -1  
                        pwm.ChangeDutyCycle(i)  
                        time.sleep(pause_time)  
                    pwm.stop()
                    time.sleep(0.005)   
                    turnoff(LED)
                    print("最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break

                elif B_triggered :      ### when 2ndinput is B
                    C_triggered=False
                    B_triggered=False
                    pwm = RPi.GPIO.PWM(LED, 100)  
                    pwm.start(100)
                    pause_time = 0.02 
                    print ("２番目の入力==[Button B],キー==[CB](调光->Down),LED0が暗くなる。")
                    for i in range(100,-1,-1):        # from 100 to zero in steps of -1  
                        pwm.ChangeDutyCycle(i)  
                        time.sleep(pause_time)  
                    pwm.stop()
                    time.sleep(0.005)   
                    turnoff(LED)
                    print("最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break

                elif D_triggered:
                    C_triggered=False
                    D_triggered=False
                    pwm = RPi.GPIO.PWM(LED, 100)  
                    pwm.start(100)
                    pause_time = 0.02 
                    print ("２番目の入力==[Button D],キー==[CD](调光->Down),LED0が暗くなる。")
                    for i in range(100,-1,-1):        # from 100 to zero in steps of -1  
                        pwm.ChangeDutyCycle(i)  
                        time.sleep(pause_time)  
                    pwm.stop()
                    time.sleep(0.005)   
                    turnoff(LED)
                    print("最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break
                else:
                    continue
                pass

    ### when 1stinput is D
        elif D_triggered and C_triggered==False :        
            print("１番目の入力==[Button D],２番目の入力を待つ...")
            time.sleep(0.3)
            # print("1st input=[Button D],False,back to start")
            # print("loop start: Wait for 1st input...")
            
    ### 2nd loop,check for 2nd Input
            while True:
                time.sleep(0.1)
                if A_triggered : ### when 2ndinput is A
                    D_triggered=False
                    A_triggered=False
                    pwm = RPi.GPIO.PWM(LED, 100)  
                    pwm.start(0)
                    pause_time = 0.02 
                    print ("２番目の入力==[Button A],キー==[DA](调光->Up),LED0が明るくなる。")
                    for i in range(0,101,1):      
                        pwm.ChangeDutyCycle(i)  
                        time.sleep(pause_time)  
                    pwm.stop()
                    time.sleep(0.005)   
                    turnon(LED)
                    print("最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    break

                elif C_triggered:
                    D_triggered=False
                    C_triggered=False
                    pwm = RPi.GPIO.PWM(LED, 100)  
                    pwm.start(0)
                    pause_time = 0.02 
                    print ("２番目の入力==[Button C],キー==[DC](调光->Up),LED0が明るくなる。")
                    for i in range(0,101,1):        # from 100 to zero in steps of -1  
                        pwm.ChangeDutyCycle(i)  
                        time.sleep(pause_time)  
                    pwm.stop()
                    time.sleep(0.005)   
                    turnon(LED)
                    print("最初に戻る。")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break

                elif B_triggered:
                    D_triggered=False
                    B_triggered=False
                    pwm = RPi.GPIO.PWM(LED, 100)  
                    pwm.start(0)
                    pause_time = 0.02 
                    print ("２番目の入力==[Button B],キー==[DB](调光->Up),LED0が明るくなる。")
                    for i in range(0,101,1):        # from 100 to zero in steps of -1  
                        pwm.ChangeDutyCycle(i)  
                        time.sleep(pause_time)  
                    pwm.stop()
                    time.sleep(0.006)   
                    turnon(LED)
                    print("最初に戻る。")
                        # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                    time.sleep(0.3)
                    print ("１番目の入力を待つ...")
                    #print ("loop start: Wait for 1st input...")
                    break
                else:
                    continue
        else:
            TriggeredAllFalse()
            continue

finally:                   # this block will run no matter how the try block exits
    RPi.GPIO.cleanup()         # clean up after yourself
