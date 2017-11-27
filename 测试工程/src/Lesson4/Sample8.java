package Lesson4;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;

public class Sample8 extends JApplet {
	private JLabel[] lb =new JLabel[3];
	private Icon ic;
	
	public void init(){
		for(int i =0;i<lb.length;i++){
			lb[i] = new JLabel("This is a car.");
		}
		ic = new ImageIcon(getImage(getDocumentBase(),"Lesson4/car.jpg"));
		
		lb[0].setIcon(ic);
		lb[1].setIcon(ic);
		lb[2].setIcon(ic);
		
		lb[0].setFont(new Font("SansSerif", Font.ITALIC,12));
		lb[1].setFont(new Font("Helvetica", Font.BOLD,14));
		lb[2].setFont(new Font("Century", Font.BOLD,16));
		
		setLayout(new GridLayout(3,1));
		
		for (int i =0;i<lb.length;i++){
			add(lb[i]);
		}
	}
	
	
}
