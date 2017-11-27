package Lesson9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.glass.events.WindowEvent;


public class Sample3 extends JFrame {
   private JPanel pn1,pn2;
   private JLabel lb1,lb2,lb3,lb4;
   private JButton bt;
   
   public static void main(String args[]){
	   Sample3 sm=new Sample3();
   }
    
   public Sample3(){
	   super("•µ•Û•◊•Î");
	   
	   lb1=new JLabel("•’•°•§•Î§Úﬂxík§∑§∆§Ø§¿§µ§§");
	   lb2=new JLabel();
	   lb3=new JLabel();
	   lb4=new JLabel();
	   
	   pn1=new JPanel();
	   pn2=new JPanel();
	   bt=new JButton("ﬂxík");
	   
	   pn1.setLayout(new GridLayout(3, 1));
	   
	   pn1.add(lb2);
	   pn1.add(lb3);
	   pn1.add(lb4);
	   pn2.add(bt);
	   
	   add(lb1,BorderLayout.NORTH);
	   add(pn1,BorderLayout.CENTER);
	   add(pn2,BorderLayout.SOUTH);
	   
	   bt.addActionListener(new SampleActionListener());
	   addWindowListener(new SampleWindowListener());
	   
	   setSize(300,300);
	   setVisible(true);
   }
   
   class SampleActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		java.awt.Container cnt=getContentPane();
		JFileChooser fc =new JFileChooser();
		int res=fc.showOpenDialog(cnt);
		
		if (res==JFileChooser.APPROVE_OPTION){
			File fl=fc.getSelectedFile();
			lb2.setText("•’•°•§•Î√˚§œ"+fl.getName()+"§«§π");
			lb3.setText("Ω~åù•—•π§œ"+fl.getAbsolutePath()+"§«§π");
			lb4.setText("•µ•§•∫§œ"+fl.length()+"•–•§•»§«§π");
			
		}
	}
   }
   class SampleWindowListener extends WindowAdapter{
	   public void windowClosing(WindowEvent e){
		   System.exit(0);
	   }
   }
}
