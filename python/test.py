
#-----------------------------------------------------------------#
# "#"<- this mark is comments, codes after "#" will not be run.   #
#-----------------------------------------------------------------#
#RPi.GPIO.wait_for_edge(InputA, RPi.GPIO.RISING,bouncetime=200)
#

#import libraries
import RPi.GPIO
import time
import datetime

#setup input pins,edit pin number here
InputA=16  #set GPIO16 as inputA
InputB=7   #set GPIO7 as inputB

#setup output pin for LED
LED=12

# set up BCM GPIO numbering 
RPi.GPIO.setmode(RPi.GPIO.BCM)

#set pins to input mode and pull up pins to high(if need) as default
RPi.GPIO.setup(InputA, RPi.GPIO.IN) 
RPi.GPIO.setup(InputB, RPi.GPIO.IN)

#set pin for LED to output mode
RPi.GPIO.setup(LED, RPi.GPIO.OUT)

#set up variables to save start and end time, initialize them to 0 as default
TimeA=0         
TimeB=0

# Define serveral threaded callbacks function to run in another thread when events are detected
# 
def writeTimeA():   
    if RPi.GPIO.input(InputA):
            global TimeA
            TimeA= time.time() #record time A   
            print ("TimeA in writeTimeA= %s"%(TimeA))

def writeTimeB():
    if RPi.GPIO.input(InputB):
            global TimeB
            TimeB= time.time() #record time B   
            print ("writeTimeB= %s"%(TimeB))           
            
def turnon():
        RPi.GPIO.output(LED, True)
        print ("Output = True")
        time.sleep(0.4)

def turnoff():
        RPi.GPIO.output(LED, False)

def CountA(channel):
        if RPi.GPIO.input(InputA):
            writeTimeA()
        if RPi.GPIO.event_detected(InputB):
            writeTimeB()
        global TimeB
        global TimeA
        s=TimeB*100-TimeA*100
        print ("s= %d")%(s)
        if 0<s<200:
           turnon()
           turnoff()       
        TimeA = -1
        TimeB = -1        
        
#detect rising edge
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING, callback=CountA,bouncetime=200 ) #bouncetime=200
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING,callback=CountA,bouncetime=200 )

try:  
    while True:
            time.sleep(0.2)
            print ("InputA= %s") %(RPi.GPIO.input(InputA))
            print ("InputB= %s") %(RPi.GPIO.input(InputB))
            print ("Main: TimeA= %s") %(TimeA)
            print ("Main: TimeB= %s") %(TimeB)
            #print ("TimeB-TimeA= %d")%(s)
            print ("-----------------")
            
  
finally:                   # this block will run no matter how the try block exits  
    RPi.GPIO.cleanup()         # clean up after yourself  
