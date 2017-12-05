
#----------------------------------------------------------------------------#
# codes after "#" will not be run.       
# This code page is for execution on RSPi, English comments only.       
# References:
# https://sourceforge.net/p/raspberry-gpio-python/wiki/Inputs/     
#----------------------------------------------------------------------------#

# import libraries
import RPi.GPIO
import time

# setup input pins,edit pin number here
InputA=16  #set GPIO16 as inputA
InputB=7   #set GPIO7 as inputB

# setup GPIO triggered
A_triggered= False
B_triggered= False
led = False
Input_Num_GPIO =0
count=0

# setup output pin for LED
LED=27

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
sA=0
sB=0

# Define serveral threaded callback functions to run in another thread when events are detected
# define a function to record the time A
def writeTimeA(channel):   
    if RPi.GPIO.input(InputA):
            global A_triggered
            global Input_Num_GPIO
            global count
            count = count +1
            A_triggered= True
            Input_Num_GPIO=1
            

            global TimeA
            TimeA= time.time() #record time A 
            # print ("--------------------------------------")  
            # print ("TimeA in writeTimeA= %s"%(TimeA))
            # print ("A_triggered= %s"%(A_triggered))
            # print ("--------------------------------------")  
            
# define a function to record the time B
def writeTimeB(channel):
    if RPi.GPIO.input(InputB):
            global B_triggered
            global Input_Num_GPIO
            global count
            count = count +1
            B_triggered= True
            Input_Num_GPIO=2

            global TimeB
            TimeB= time.time() #record time B  
            # print ("--------------------------------------")   # 
            # print ("TimeB in writeTimeB= %s"%(TimeB))
            # print ("B_triggered= %s"%(B_triggered))
            # print ("--------------------------------------")  
            
# define a function to turn on LED for 0.4 second, edit duration here             
def turnon():
        global led
        RPi.GPIO.output(LED, True)
        led = True
        print ("+++++++++++++++++++++++++")
        print ("+ Output LED = [[True]] +") 
        print ("+++++++++++++++++++++++++")
        time.sleep(0.4) #edit the float in brackets to change duration

# define a function to turn of LED
def turnoff():
        global led
        RPi.GPIO.output(LED, False)
        led = False
        print ("++++++++++++++++++++++++++")
        print ("+ Output LED = [[False]] +") 
        print ("++++++++++++++++++++++++++")
        
# detect rising edge
# when a rising edge is detected on pin,the callback functions will be run
RPi.GPIO.add_event_detect(InputA, RPi.GPIO.RISING,callback=writeTimeA ,bouncetime=100 ) # when use real button swtich to switch the input level,
RPi.GPIO.add_event_detect(InputB, RPi.GPIO.RISING,callback=writeTimeB,bouncetime=100 ) # bouncetime=[time] to debounce the switch.

try:  
    start = time.time()
    print ("loop start: Wait for gpio 1st input...") 
    break_flag=False
    while True:                                  ### main(1st) loop start,check for 1stInput
        time.sleep(0.01)
        if B_triggered and A_triggered==False:   ### when 1stinput is B
            print("1st input=<B>,waiting for 2nd input...")
            B_triggered=False
            A_triggered=False
            while True:                          ### 2nd loop,check for 2nd Input
                if B_triggered and A_triggered==False: 
                    print("2nd input=<B>,False,back to start")
                    print ("loop start: Wait for gpio 1st input...")
                    B_triggered=False
                    A_triggered=False
                    break
                elif A_triggered and B_triggered==False: ### when 2ndinput is A
                    print("2nd input=<A>,True,turnoff and back to start")
                    print ("loop start: Wait for gpio 1st input...")
                    B_triggered=False
                    A_triggered=False
                    turnoff()
                    break
                elif A_triggered==True and B_triggered==True:
                    print("2nd input==Wrong,back to start")
                    print ("loop start: Wait for gpio 1st input...")
                    B_triggered=False
                    A_triggered=False
                    break
                else:
                    continue

        elif A_triggered and B_triggered==False:
            print("1st input=<A>,waiting for 2nd input...")
            B_triggered=False
            A_triggered=False
            while True:     #2nd loop,check for 2nd Input
                if B_triggered and A_triggered==False:
                    print("2nd input=<B>,True,turnon and back to start")
                    print ("loop start: Wait for gpio 1st input...")
                    B_triggered=False
                    A_triggered=False
                    turnon()
                    break
                elif A_triggered and B_triggered==False:
                    print("2nd input=<A>,False,back to start")
                    print ("loop start: Wait for gpio 1st input...")
                    B_triggered=False
                    A_triggered=False
                    break
                elif A_triggered==True and B_triggered==True:
                    print("2nd input==Wrong,back to start")
                    print ("loop start: Wait for gpio 1st input...")
                    B_triggered=False
                    A_triggered=False
                    break
                else:
                    continue

        else:
            B_triggered=False
            A_triggered=False
            continue


            
            
            

        
        # print ("==================")
        # print ("A_triggered= %s") %(RPi.GPIO.input(A_triggered))
        # print ("B_triggered= %s") %(RPi.GPIO.input(B_triggered))
        # print ("Input_Num_GPIO= %s") %(Input_Num_GPIO)
        # print ("==================")
        # print ("+++++++++++++++++++++")
        # print ("+ Output LED = %s ") %(led)
        # print ("+++++++++++++++++++++")
        # print (" ")
            
  
finally:                   # this block will run no matter how the try block exits  
    RPi.GPIO.cleanup()         # clean up after yourself  
    
