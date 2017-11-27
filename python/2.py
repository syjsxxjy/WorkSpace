import threading
import time
import RPi.GPIO as GPIO
import datetime

#begin = datetime.datetime.now().microsecond
#print datetime.datetime.now().microsecond
#time.sleep(0.1)
#end = datetime.datetime.now().microsecond
#print datetime.datetime.now().microsecond
#k = end - begin
#print k

GPIO.setmode(GPIO.BCM)
GPIO.setup(23,GPIO.OUT) #LED
GPIO.setup(24,GPIO.IN)  #A
GPIO.setup(25,GPIO.IN)  #B

#初始化
Status_A=0
Status_B=0

try:
#线程A，记录传感器A的输入情况：输入=1, 未检测到=0
	def sA():
        while True:
           Status_A = GPIO.input(24):   #储存A的变量
           print ("[sA]Status_A= %s" % (Status_A))
           Status_APre=Status_A  #将A的值保存到另一个变量
	       d= Status_APre - Status_A  #计算差，若差=-1，则开始输入。若差=1，则输入结束     
                 #print datetime.datetime.now().microsecond
           if d == -1
               At1=datetime.datetime.now().microsecond
        #   if d == 1
         #      t2=datetime.datetime.now().microsecond
                 
	def sB():
    	while True:
        	Status_B = GPIO.input(25):   #储存B的变量
            print ("[sA]Status_A= %s" % (Status_A))
            Status_BPre=Status_B  #将B的值保存到另一个变量
	        d= Status_BPre - Status_B  #计算差，若差=-1，则开始输入。若差=1，则输入结束     
                 #print datetime.datetime.now().microsecond
            if d == -1
               Bt1=datetime.datetime.now().microsecond


	thread1 = threading.Thread(target = sA, args = ())
#thread2 = threading.Thread(target = sB, args = ())

	thread1.start()
#thread2.start()

#主线程
	while True:
	print ("[main]Status_A= %s" % (Status_A)) 
	print ("[main]d= %s" % (d))
    
	





	while True:
    k = end - begin
    print k
    if 0<k<200440:
        GPIO.output(23, True)
    else:
        GPIO.output(23, False)

	print("\nThis is the main thread!")
	thread1.join()
	thread2.join() 

except KeyboardInterrupt:
	pass

RPi.GPIO.cleanup()