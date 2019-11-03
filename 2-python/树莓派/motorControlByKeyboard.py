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


# set up BCM GPIO numbering
RPi.GPIO.setmode(RPi.GPIO.BCM)

# set pins for Motor Diver Input Pin to output mode
RPi.GPIO.setup(In1_Motor, RPi.GPIO.OUT)
RPi.GPIO.setup(In2_Motor, RPi.GPIO.OUT)

## GPIOs Initial
RPi.GPIO.output(In1_Motor, False)
RPi.GPIO.output(In2_Motor, False)

try:
    print "Reading form keybord:"
    print "A=正方向 W=ブレーキ　S=ストップ D=逆方向　Q=Quit"
    time.sleep(0.1)
# Main loop start here
    while  True:
#get events of the controller
    fd=sys.stdin.fileno()
        old_settings=termios.tcgetattr(fd)
        try:
            tty.setraw(fd)
            ch=sys.stdin.read(1)
        finally:
            termios.tcsetattr(fd, termios.TCSADRAIN, old_settings)  
            #print 'error'
        if ch=='a':
            print '----------------'
            print '【move forward】'
            print '----------------'
            RPi.GPIO.output(In1_Motor, True)
            RPi.GPIO.output(In2_Motor, False)
            print (RPi.GPIO.input(In1_Motor))
            print (RPi.GPIO.input(In2_Motor))
            time.sleep(1) #此处等待为防止同时按下多个按钮
        elif ch=='w':
            print '----------------'
            print '【Break】'
            print '----------------'
            RPi.GPIO.output(In1_Motor, True)
            RPi.GPIO.output(In2_Motor, True)
            time.sleep(0.2)
        elif ch=='s':
            print '----------------'
            print '【Stop】'
            print '----------------'
            RPi.GPIO.output(In1_Motor, False)
            RPi.GPIO.output(In2_Motor, False)
            time.sleep(0.2)
        elif ch=='d':
            print '----------------'
            print "【move backward】"
            print '----------------'
            RPi.GPIO.output(In1_Motor, False)
            RPi.GPIO.output(In2_Motor, True)
            time.sleep(1)
        elif ch=='q':
            print '----------------'
            print "【shutdown!】"
            print '----------------'
            break
        elif ord(ch)==0x3: #这个是ctrl c
            
            print "shutdown"
            break
        print "Reading form keybord:"
        print "A=正方向 W=ブレーキ　S=ストップ D=逆方向　Q=Quit"

finally:                   # this block will run no matter how the try block exits
    RPi.GPIO.cleanup()    # clean up after yourself
