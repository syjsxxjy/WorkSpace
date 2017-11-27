package Lesson9;//置換を行う
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.*;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class Sample9 extends JFrame {
    
	private JPanel pn;
	private JLabel lb1,lb2,lb3;
	private JTextArea ta;
	private JTextField tf1,tf2;
	private JButton bt;
	private JScrollPane sp;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Sample9 sm=new Sample9();
	}
    
	public Sample9(){
		super("Sample9");
		
		lb1=new JLabel("入力してください");
		lb2=new JLabel("置換前");
		lb3=new JLabel("置換後");
		ta= new JTextArea();
		sp=new JScrollPane(ta);
		
		pn=new JPanel();
		bt=new JButton("置換");
		tf1=new JTextField();
		tf2=new JTextField();
		
		pn.setLayout(new GridLayout(1, 5));
		
		pn.add(lb2);
		pn.add(tf1);
		pn.add(lb3);
		pn.add(tf2);
		pn.add(bt);
		
		add(lb1,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		add(pn,BorderLayout.SOUTH);
		
		bt.addActionListener(new SampleActionListener());
		addWindowListener(new SampleWindowListener());
		
		setSize(300, 200);
		setVisible(true);
	}
	
	class SampleActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==bt){
				Pattern pn=Pattern.compile(tf1.getText());
				Matcher mt=pn.matcher(ta.getText());
				ta.setText(mt.replaceAll(tf2.getText()));
			}
		}
	}
	 class SampleWindowListener extends WindowAdapter{
	 	   public void windowClosing(WindowEvent e){
	 		   System.exit(0);
	 	   }
	    }
}
