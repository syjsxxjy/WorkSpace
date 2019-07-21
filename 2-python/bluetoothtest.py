#!/usr/bin/env python
# -*- coding: utf-8 -*-
import bluetooth 
import time  
print "In/Out Board"  
while True: 
  print "Checking " + time.strftime("%a, %d %b %Y %H:%M:%S")
  result = bluetooth.lookup_name('98:B6:E9:8A:74:CD', timeout=10) 
  if (result != None): 
    print "JoyCon is in" 
  else: 
    print "JoyCon is out"  