package Lesson4;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class Sample12 extends JApplet {
	private JLabel lb;
	private JPanel pn;
	private JRadioButton rb1,rb2,tmp;
	private ButtonGroup bg;
	
	public void init(){
		lb=new JLabel("中日仄扎中引六﹝");
		pn =	new JPanel();
		rb1=new JRadioButton("��",true);
		rb2=new JRadioButton("玄仿永弁",false);
		bg =new ButtonGroup();
		
		bg.add(rb1);
		bg.add(rb2);
		
		pn.add(rb1);
		pn.add(rb2);
		add(lb,BorderLayout.NORTH);
		add(pn,BorderLayout.SOUTH);
		
		rb1.addActionListener((ActionListener)new SampleActionListener());
		rb2.addActionListener((ActionListener)new SampleActionListener());
	}
	class SampleActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			tmp = (JRadioButton) e.getSource();
			lb.setText(tmp.getText()+"毛腢太引仄凶");
		}
	}
}
