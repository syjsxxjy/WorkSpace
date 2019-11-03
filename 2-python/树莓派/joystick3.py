#!/usr/bin/env python
# -*- coding: utf-8 -*-
import RPi.GPIO
import time
import pygame
from pygame.locals import *

#初始化Joystick
pygame.joystick.init()

#创建Joystick对象
joysticks = [pygame.joystick.Joystick(x) for x in range(pygame.joystick.get_count())]

print(Joystick数量：)
	print(pygame.joystick.get_count())
	print(Joystick ID：)
	print(pygame.joystick.get_id)
	print(Joystick名称：)
	print(pygame.joystick.get_name)

#主循环
while True:
	flag=0

	if flag==1
		break