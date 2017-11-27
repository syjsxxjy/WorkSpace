package Lesson4;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import java.awt.*;
public class Sample7 extends JApplet{
	private JLabel[] lb =new JLabel[3];
	private Icon ic;
	public void init(){
		for(int i =0;i<lb.length;i++){
			lb[i] = new JLabel("Ü‡"+i+"¤Ï¤¤¤«¤¬¤Ç¤¹¤«?");
		}
		ic = new ImageIcon(getImage(getDocumentBase(),"Lesson4/car.jpg"));
		
		lb[0].setIcon(ic);
		lb[1].setIcon(ic);
		lb[2].setIcon(ic);
		
		lb[0].setHorizontalAlignment(JLabel.LEFT);
		lb[1].setHorizontalAlignment(JLabel.CENTER);
		lb[2].setHorizontalAlignment(JLabel.RIGHT);
		
		lb[0].setVerticalAlignment(JLabel.TOP);
		lb[1].setVerticalAlignment(JLabel.CENTER);
		lb[2].setVerticalAlignment(JLabel.BOTTOM);
		
		setLayout(new GridLayout(3,1));
		
		for (int i =0;i<lb.length;i++){
			add(lb[i]);
		}
	}
}
