import RPi.GPIO
import time
import datetime

#setup input pin,edit here
InputA=16  #set GPIO16 as inputA
InputB=7  #set GPIO12 as inputB

# set up BCM GPIO numbering 
RPi.GPIO.setmode(RPi.GPIO.BCM)

#set pins to input mode and pull up pins to high(if need) as default
#RPi.GPIO.setup(InputA, RPi.GPIO.IN, pull_up_down=RPi.GPIO.PUD_UP)
RPi.GPIO.setup(InputA, RPi.GPIO.IN) 
RPi.GPIO.setup(InputB, RPi.GPIO.IN) 

#set up variables to save times
TimeA=0  #time of rising edge 
TimeB=0

# Define a threaded callback function to run in another thread when events are detected  
def writeTimeA(channel):   
        #TimeA=0
	if RPi.GPIO.input(InputA):
            global TimeA
            TimeA= time.time() #record time A   
            print ("TimeA= %s"%(TimeA))  
        return TimeA
    

def writeTimeB(channel):
        #TimeB=0
	if RPi.GPIO.input(InputB):
    	    TimeB= time.time() #record time B   
            print ("TimeB= %s"%(TimeB))  
        return TimeB

#detect rising edge
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING, callback=writeTimeA, bouncetime=100)
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING, callback=writeTimeB, bouncetime=100) 

try:  
    while True:
            time.sleep(0.2)
            print ("InputA= %s") %(RPi.GPIO.input(InputA))
            print ("Main: TimeA= %s") %(TimeA)
            #print ("writeTimeA= %s") %(writeTimeA(16))
            # s = writeTimeB(12)- writeTimeA(16)
            #if writeTimeB(12)!=0 and writeTimeA(16)!=0:
            #	print ("TimeB-TimeA= %d")%(s)
            #	print ("-----------------")
            
  
finally:                   # this block will run no matter how the try block exits  
    RPi.GPIO.cleanup()         # clean up after yourself  
