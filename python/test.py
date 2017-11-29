
#----------------------------------------------------------------------------#
# "#"<<<<this mark is a comment mark, codes after "#" will not be run.       
# This code page is for execution on RSPi, English comments only.            
#----------------------------------------------------------------------------#
#RPi.GPIO.wait_for_edge(InputA, RPi.GPIO.RISING,bouncetime=200)

# import libraries
import RPi.GPIO
import time

# setup input pins,edit pin number here
InputA=16  #set GPIO16 as inputA
InputB=7   #set GPIO7 as inputB
led = "False"

# setup output pin for LED
LED=22

# set up BCM GPIO numbering 
RPi.GPIO.setmode(RPi.GPIO.BCM)

# set pins to input mode and pull up to high level(if necessary) as default
RPi.GPIO.setup(InputA, RPi.GPIO.IN) 
RPi.GPIO.setup(InputB, RPi.GPIO.IN)

# set pin for LED to output mode
RPi.GPIO.setup(LED, RPi.GPIO.OUT)

# set up variables to save start and end time, initialize them to 0 as default
TimeA=0.00      
TimeB=0.00

# Define serveral threaded callback functions to run in another thread when events are detected
# define a function to record the time A
def writeTimeA(channel):   
    if RPi.GPIO.input(InputA):
            global TimeA
            TimeA= time.time() #record time A 
            print ("--------------------------------------")  
            print ("TimeA in writeTimeA= %s"%(TimeA))
            print ("--------------------------------------")  
            
# define a function to record the time B
def writeTimeB(channel):
    if RPi.GPIO.input(InputB):
            global TimeB
            TimeB= time.time() #record time B  
            print ("--------------------------------------")   # 
            print ("TimeB in writeTimeB= %s"%(TimeB))
            print ("--------------------------------------")  
            
# define a function to turn on LED for 0.4 second, edit duration here             
def turnon():
        global led
        RPi.GPIO.output(LED, True)
        led = "<<<<True>>>>"
        print ("+++++++++++++++++++++")
        print ("+ Output LED = [[True]] ") 
        print ("+++++++++++++++++++++")
        time.sleep(0.4)   #edit the float in brackets to change duration

# define a function to turn of LED
def turnoff():
        global led
        RPi.GPIO.output(LED, False)
        led = "False"
        
# detect rising edge
# when a rising edge is detected on pin,the callback functions will be run
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING,callback=writeTimeA ,bouncetime=200 ) # when use real button swtich to switch the input level,
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING,callback=writeTimeB,bouncetime=200 ) # bouncetime=[time] to debounce the switch.

# loop
try:  
    while True:
        time.sleep(0.1)
        if RPi.GPIO.event_detected(InputB):
            s=TimeB*100-TimeA*100   # calculation difference 
            print ("=====================")
            print ("Difference = %d")%(s)
            print ("=====================")
            if 1<s<200 :  #compare the difference with 1 and max time
                turnon()
                turnoff()
            TimeA = -1
            TimeB = -1

        
        
        print ("==================")
        print ("InputA= %s") %(RPi.GPIO.input(InputA))
        print ("InputB= %s") %(RPi.GPIO.input(InputB))
        print ("==================")
        print ("+++++++++++++++++++++")
        print ("+ Output LED = %s ") %(led)
        print ("+++++++++++++++++++++")
        print (" ")
            
  
finally:                   # this block will run no matter how the try block exits  
    RPi.GPIO.cleanup()         # clean up after yourself  
