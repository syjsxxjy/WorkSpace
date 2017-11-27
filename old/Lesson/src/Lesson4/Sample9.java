package Lesson4;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public class Sample9 extends JApplet {
	private JLabel[] lb =new JLabel[3];
	private Icon ic;
	
	public void init(){
		for(int i =0;i<lb.length;i++){
			lb[i] = new JLabel("Ü‡"+i+"¤¤¤«¤¬¤Ç¤¹¤«");
		}
		ic = new ImageIcon(getImage(getDocumentBase(),"Lesson4/car.jpg"));
		
		lb[0].setBorder(new BevelBorder(BevelBorder.RAISED));
		lb[1].setBorder(new EtchedBorder());
		lb[2].setBorder(new MatteBorder(5,5,5,5,ic));
		
		setLayout(new GridLayout(3,1,5,5));
		
		for (int i =0;i<lb.length;i++){
			add(lb[i]);
		}
	}
}
