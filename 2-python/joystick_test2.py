#!/usr/bin/python
# -*- coding: utf-8 -*-
import sys
import pygame
from pygame import locals

# 初期化
pygame.init()
pygame.joystick.init()

# ゲームパッドを列挙する（複数つなぐ場合があるので）
joysticks = [pygame.joystick.Joystick(x) for x in range(pygame.joystick.get_count())]

# ゲームパッドを認識しているか？
if pygame.joystick.get_count() == 0:
	print "ゲームパッドがありません。"
	sys.exit("終了")
else:
	print "ゲームパッドが" + str(len(joysticks)) + "個見つかりました。"

# ゲームパッドを初期化
print "ゲームパッドを初期化します..."
joysticks[0].init()
print "名称:%s" % (joysticks[0].get_name())

# ゲームパッドに軸はいくつあるの？ => 6軸
n_axis = joysticks[0].get_numaxes()
print "軸の数(axis):" + str(n_axis)

# ゲームパッドにボタンはいくつあるの？ => 11個
n_button = joysticks[0].get_numbuttons()
print "ボタンの数(buttons):" + str(n_button)