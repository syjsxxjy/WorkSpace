#!/usr/bin/env python
# -*- coding: utf-8 -*-

#-----------------------------------------------------------------------------------------#
# PROJECT   :  RasPi Python :: Switch Input Directions Test
# FILENAME  :  Switch_Input_Directions.py
# Author    :  https://github.com/syjsxxjy
#
# Codes after "#" will not be run.
# This code shows how to input in sequences and determine if they are in the right sequences.
#
# 
# You can find this code here:
# https://github.com/syjsxxjy/WorkSpace/tree/master/python
#
# References(�ο�����):
# https://sourceforge.net/p/raspberry-gpio-python/wiki/Inputs/
#-----------------------------------------------------------------------------------------#

# import libraries
import RPi.GPIO
import time
import pygame
from pygame.locals import *

# ����ȫ�ֱ��� setup global variables at first
# setup input pins,edit pin number here
InputA=16  #GPIO16 ΪA����
InputB=7   #GPIO7 ΪB����
InputC=8   #GPIO8 ΪC����
InputD=15  #GPIO15 as inputD

# setup output pin for LED
LED=13   #GPIO13 for LED
LED1=11  #GPIO11 for LED1
LED2=9
LED3=10

# setup GPIO triggered  ��¼��ť����״̬���Ƿ��£�
A_triggered= False
B_triggered= False
C_triggered= False
D_triggered= False

# ��ݮ�ɻ����趨
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

# Define serveral threaded callback functions to run in another thread when events are detected
def SwitchA(channel):   #��ťA���������߳�
    #if RPi.GPIO.input(InputA):
            global A_triggered
            A_triggered= True

def SwitchB(channel):   #��ťB���������߳�
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

def TriggeredAllFalse():  #�����п�������״̬����Ϊ��
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
        return "���� ��(True)"
    if output==False:   
        return "���� ��(False)"

# define a function to turn on LED
def turnon(LED_Num):
        RPi.GPIO.output(LED_Num, True)
        print ("-------------------------------")   #���� ��
        print ("| LED[0]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED))))
        print ("-------------------------------")
        print ("| LED[1]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED1))))
        print ("-------------------------------")
        print ("| LED[2]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED2))))
        print ("-------------------------------")
        print ("| LED[3]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED3))))
        print ("-------------------------------")

# define a function to turn off LED
def turnoff(LED_Num):
        RPi.GPIO.output(LED_Num, False)
        print ("---------------------------------")   #���� ��
        print ("| LED[0]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED))))
        print ("-------------------------------")
        print ("| LED[1]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED1))))
        print ("-------------------------------")
        print ("| LED[2]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED2))))
        print ("-------------------------------")
        print ("| LED[3]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED3))))
        print ("-------------------------------")

## define a function to turn off all the LEDs
def turnoffall():
    RPi.GPIO.output(LED, False)
    RPi.GPIO.output(LED1, False)
    RPi.GPIO.output(LED2, False)
    RPi.GPIO.output(LED3, False)
    print ("---------------------------------")   #���� ��
    print ("| LED[0]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED))))
    print ("-------------------------------")
    print ("| LED[1]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED1))))
    print ("-------------------------------")
    print ("| LED[2]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED2))))
    print ("-------------------------------")
    print ("| LED[3]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED3))))
    print ("-------------------------------")

def turnoff123():
    RPi.GPIO.output(LED1, False)
    RPi.GPIO.output(LED2, False)
    RPi.GPIO.output(LED3, False)
    print ("---------------------------------")   #���� ��
    print ("| LED[0]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED))))
    print ("-------------------------------")
    print ("| LED[1]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED1))))
    print ("-------------------------------")
    print ("| LED[2]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED2))))
    print ("-------------------------------")
    print ("| LED[3]�γ��� ==%s |"%(outputIconTrasnfer(RPi.GPIO.input(LED3))))
    print ("-------------------------------")

# detect rising edge
# when a rising edge is detected on pin,the callback functions will be run
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING,callback=SwitchA,bouncetime=200 ) # when use real button swtich to switch the input level,
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING,callback=SwitchB,bouncetime=200 ) # bouncetime=[time] to debounce the switch.
RPi.GPIO.add_event_detect(InputC, RPi.GPIO.RISING,callback=SwitchC,bouncetime=200 )
RPi.GPIO.add_event_detect(InputD, RPi.GPIO.RISING,callback=SwitchD,bouncetime=200 )

try:
    pygame.joystick.init()
    joystick0 = pygame.joystick.Joystick(0)
    joystick0.init()
    print 'joystick start'
    pygame.init()
    turnoffall()
    print ("Alt��C��Ѻ����,�ץ�������ֹ���롣")
    time.sleep(0.5)
    print ("��`���_ʼ������Ŀ�����������... Wait for 1st input...")
    #print ("Press 'Alt+C' to quit ")

    ### main(1st) loop start,check for 1stInput
    while True:
        time.sleep(0.1)
        eventlist = pygame.event.get()
### 1st loop start      
        for e in eventlist:
            if e.type == QUIT:
                pass
                 #return
            elif e.type == pygame.locals.JOYBUTTONDOWN:
            
### if 1st button is B 
                if e.button==2:
                    print 'Button B pressed' 
                    while True:
                        flag=0
                        for e2 in pygame.event.get() : 
                            if e2.type == pygame.locals.JOYBUTTONDOWN and e2.button==1:
                                turnon(LED)
                                print 'Button B->X pressed'
                                print 'Turn on LED 0 ' 
                                time.sleep(0.3)
                                print "���ص���ʼ�����µȴ���һ�����롣"                               
        
                                flag=1
                        if flag==1:
                            break
                    break 

### if 1st button is X
                if e.button==1:
                    print 'Button X pressed' 
                    while True:
                        flag=0
                        for e2 in pygame.event.get() : 
                            if e2.type == pygame.locals.JOYBUTTONDOWN and e2.button==2:
                                turnoff(LED)
                                print 'Button X->B pressed' 
                                print "Turn off LED 0"
                                time.sleep(0.3)
                                print "���ص���ʼ�����µȴ���һ�����롣"
                                flag=1
                        if flag==1:
                            break
                    break 

### if 1st button is A
                if e.button==0:
                    print 'Button A pressed' 
                    while True:
                        flag=0
                        for e2 in pygame.event.get() : 
                            if e2.type == pygame.locals.JOYBUTTONDOWN and e2.button==3:
                                if RPi.GPIO.input(LED3):
                                    turnoff123()
                                    print("����Ŀ������==[Button Y],���`==[A->Y](��x4),LED123������������ˑ��롣")
                                    # print("2nd input=[Button D],True,turnoff all of the leds and back to start")
                                elif RPi.GPIO.input(LED2):
                                    turnon(LED3)
                                    print("����Ŀ������==[Button Y],���`==[A->Y](��x3),LED3��Ĥ��롣����ˑ��롣")
                                    # print("2nd input=[Button D],True,turn on LED 3 and back to start")
                                elif RPi.GPIO.input(LED1):
                                    turnon(LED2)
                                    print("����Ŀ������==[Button Y],���`==[A->Y](��x2),LED2��Ĥ��롣����ˑ��롣")
                                    # print("2nd input=[Button D],True,turn on LED 2 and back to start")
                                elif RPi.GPIO.input(LED1)==False:
                                    turnon(LED1)
                                    print("����Ŀ������==[Button Y],���`==[A->Y](��x1),LED1��Ĥ��롣����ˑ��롣")
                                    # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                                time.sleep(0.3)
                                print "���ص���ʼ�����µȴ���һ�����롣"

                                flag=1
                        if flag==1:
                            break
                    break 
### if 1st button is Y
                if e.button==3:
                    print 'Button Y pressed' 
                    while True:
                        flag=0
                        for e2 in pygame.event.get() : 
                            if e2.type == pygame.locals.JOYBUTTONDOWN and e2.button==0:
                                if RPi.GPIO.input(LED3):
                                    turnoff(LED3)
                                    print("����Ŀ������==[Button D],���`==[CD](��),LED3������������ˑ��롣")
                                    # print("2nd input=[Button D],True,turnoff all of the leds and back to start")
                                    pass
                                elif RPi.GPIO.input(LED2):
                                    turnoff(LED2)
                                    print("����Ŀ������==[Button D],���`==[CD](��),LED2������������ˑ��롣")
                                    # print("2nd input=[Button D],True,turn on LED 3 and back to start")
                                elif RPi.GPIO.input(LED1):
                                    turnoff(LED1)
                                    print("����Ŀ������==[Button D],���`==[CD](��),LED1������������ˑ��롣")
                                    # print("2nd input=[Button D],True,turn on LED 2 and back to start")
                                elif RPi.GPIO.input(LED1)==False:
                                    print("����Ŀ������==[Button D],���`==[CD](��),LED123��ȫ��������Ƥ���Τ�,�Τ⤷�ʤ���")
                                    print("����ˑ��롣")
                                    # print("2nd input=[Button D],True,turn on LED 1 and back to start")
                                time.sleep(0.3)
                                print "���ص���ʼ�����µȴ���һ�����롣"
                                flag=1
                        if flag==1:
                            break
                    break 

        time.sleep(0.1)

except pygame.error:
        print 'cannot find Joy Stick'
finally:                   # this block will run no matter how the try block exits
    RPi.GPIO.cleanup()         # clean up after yourself
