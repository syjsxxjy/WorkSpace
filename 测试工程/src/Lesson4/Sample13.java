package Lesson4;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Sample13 extends JApplet {
     private JLabel lb;
     private JTextField tf;
     
     public void init(){
    	 lb= new JLabel("云腢太仁分今中");
    	 tf=new JTextField();
    	 
 		add(lb,BorderLayout.NORTH);
 		add(tf,BorderLayout.SOUTH);
 		
 		tf.addActionListener((ActionListener)new SampleActionListener());
     }
     class SampleActionListener implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			JTextField tmp = (JTextField) e.getSource();
 			lb.setText(tmp.getText()+"匹允友");
 		}
 		}
}
