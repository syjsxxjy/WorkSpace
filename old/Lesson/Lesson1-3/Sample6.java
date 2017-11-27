import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Sample6 extends JApplet
	{
		private JLabel lb;
		public void init(){
			lb = new JLabel("ようこそ");
			//lb = new JLabel("いらしゃいませ。");
			add (lb, BorderLayout.NORTH);
			addMouseListener(new SampleMouseListner());
	}
			class SampleMouseListner extends MouseAdapter{
				public void mouseEntered(MouseEvent e){
					lb.setText("いらしゃいませ。");
					
				}
				public void mouseExited(MouseEvent e){
					lb.setText("ようこそ");
				}
			}
		}
