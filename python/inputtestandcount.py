import RPi.GPIO
import time
import datetime

RPi.GPIO.setmode(RPi.GPIO.BCM)
RPi.GPIO.setup(12, RPi.GPIO.IN, pull_up_down=RPi.GPIO.PUD_UP) #A
#RPi.GPIO.setup(16, RPi.GPIO.IN)
RPi.GPIO.setup(16, RPi.GPIO.IN, pull_up_down=RPi.GPIO.PUD_UP) #B

count0=0
count1=1

try:

        while True:
                time.sleep(0.2)
                #print ("GPIO12= %s")%(RPi.GPIO.input(12))
                print ("GPIO16= %s")%(RPi.GPIO.input(16))
                print ("over")
                print ("time now: %s")%(datetime.datetime.now())
                if (RPi.GPIO.input(16)== 0):
                        count0=count0+1
                if (RPi.GPIO.input(16)== 1):
                        count1=count1+1
                print("Count0= %s"%(count0))
                print("Count1= %s"%(count1)) 
except KeyboardInterrupt:
        pass

RPi.GPIO.cleanup()
