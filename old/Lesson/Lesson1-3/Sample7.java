import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Sample7 extends JApplet
	{
		private JLabel lb1,lb2;
		public void init(){
			lb1 = new JLabel("矢印キーでお選びください");
			lb2 = new JLabel();
			//コンテナへの追加
			add (lb1, BorderLayout.NORTH);
			add (lb2, BorderLayout.SOUTH);
			addKeyListener(new SampleKeyListner());
			setFocusable(true);
	}

			class SampleKeyListner extends KeyAdapter{
				public void keyPressed(KeyEvent e){
					String str;
					int k = e.getKeyCode();
					switch(k){
						case KeyEvent.VK_UP:
						str = "上"; break;
						case KeyEvent.VK_DOWN:
						str = "下"; break;
						case KeyEvent.VK_LEFT:
						str = "左"; break;
						case KeyEvent.VK_RIGHT:
						str = "右"; break;
						default:
						str = "他のキー";
					}
			lb2.setText(str+"ですね。");
				}
			}
		}
