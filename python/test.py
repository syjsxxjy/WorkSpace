import RPi.GPIO
import time
import datetime

#setup input pin
InputA=16
InputB=12
TimeA=0
TimeB=0

RPi.GPIO.setmode(RPi.GPIO.BCM)

#pull up pins to high as default
RPi.GPIO.setup(InputA, RPi.GPIO.IN, pull_up_down=RPi.GPIO.PUD_UP) 
RPi.GPIO.setup(InputB, RPi.GPIO.IN, pull_up_down=RPi.GPIO.PUD_UP) 

# Define a threaded callback function to run in another thread when events are detected  
def writeTimeA():  
	if RPi.GPIO.input(InputA):
                TimeA= time.time() #record time A   
                print ("TimeA= %s"%(TimeA))  
                return TimeA
    

def writeTimeB():  
	if RPi.GPIO.input(InputB)==1:
    		TimeB= time.time() #record time B   
        	print ("TimeB= %s"%(TimeB))  
        	return TimeB

#detect rising edge
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING, callback=writeTimeA)
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING, callback=writeTimeB) 

try:  
    while True:
            time.sleep(0.1)
            s = writeTimeB()-writeTimeA()
            if s !=0:
            	print ("TimeB-TimeA= %d")%(s)
            	print ("-----------------")
            
  
finally:                   # this block will run no matter how the try block exits  
    RPi.GPIO.cleanup()         # clean up after yourself  
