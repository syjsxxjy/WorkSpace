[TOC]
## Buck降圧電源回路設計
### 目的
1．マイコンで電源の出力を制御できる回路を作る。
### Buckコンバーターの原理
1．コンデンサーの特性を利用して，電荷を蓄えたり、放出したりすることによって，負荷の両端に印加する電圧を変える。
http://tinyurl.com/y86eydqf   
![](https://syjsxxjy.github.io/myblog/assets/1/20180629-27a1d715.png)  

2．スイッチをONとOFFを繰り返して，電圧をV±5%維持する。  
![](https://syjsxxjy.github.io/myblog/assets/1/20180629-6d852fcc.png)  

3．スイッチをONとOFFをする瞬間，大きいな電流が発生することがあるので，インダクタンスを直列に入れる。  
http://tinyurl.com/ycdcedvk  
![](https://syjsxxjy.github.io/myblog/assets/1/20180629-e0c4669f.png)  

4．インダクタンスがあって，反対方向への電流を生じる。あとOFF状態のインダクタンスが電流を放出するので，ダイオードをつける。  
http://tinyurl.com/ybdm25zc  
![](https://syjsxxjy.github.io/myblog/assets/1/20180629-9355d7dc.png)  

5．最後にスイッチをするための素子をいれる。  
http://tinyurl.com/ydgzrzwn  
![](https://syjsxxjy.github.io/myblog/assets/1/20180629-9f975ee7.png)  

### 計算公式
#### DutyCyle
D=Vout/Vin  
Vout:負荷両端にかかる電圧  Vin：VCC電源の電圧  
#### インダクタンス
L=Vout(1-D)/Iout·ρ·f  
ρ：ripple voltage（電圧の偏差[%]）　f：周波数  



























## シミュレートの结果：
http://tinyurl.com/ydgzrzwn  
![](https://syjsxxjy.github.io/myblog/assets/1/20180629-1fd8ccbc.png)  
