#!/usr/bin/env python
# -*- coding: utf-8 -

# import libraries
import RPi.GPIO
import time
import pygame
from pygame.locals import *

# setup input pin for motor
In1_Motor=13   #GPIO13 
In2_Motor=11  #GPIO11 


# set up BCM GPIO numbering
RPi.GPIO.setmode(RPi.GPIO.BCM)

# set pins for LEDs to output mode
RPi.GPIO.setup(In1_Motor, RPi.GPIO.OUT)
RPi.GPIO.setup(In2_Motor, RPi.GPIO.OUT)

try:
    pygame.joystick.init()
    joystick0 = pygame.joystick.Joystick(0)
    joystick0.init()
    print 'joystick start'
    pygame.init()
    time.sleep(0.5)
    print 'Loop start,wait for 1st input...'
# Main loop start here
    while  True:
#get events of the controller
    eventlist = pygame.event.get()   
    for  e1 in eventlist:
        if e1.type == QUIT:
                    break
                elif e1.type == pygame.locals.JOYBUTTONDOWN:
                
            if e1.button==2:
                        print 'Button B pressed' 
                        print 'turn In1 to high'
                        RPi.GPIO.output(In1_Motor, True)
                
            elif e1.button==0:
                        print "Button A pressed"
                        print 'turn In2 to high'
                        RPi.GPIO.output(In2_Motor, True)
                
        elif e1.type == pygame.locals.JOYBUTTONUP:
                
            if e1.button==2:
                        print 'Button B released' 
                        print 'turn In1 to low'
                        RPi.GPIO.output(In1_Motor, False)
                    elif e1.button==0:
                        print "Button A released"
                        print 'turn In2 to low'
                        RPi.GPIO.output(In2_Motor, False)

except pygame.error:
    print 'cannot find Joy Stick'
finally:                   # this block will run no matter how the try block exits
    RPi.GPIO.cleanup()    # clean up after yourself
