package Lesson5;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class Sample9 extends JApplet {
	private JLabel lb;
    private JButton bt;
    private JPanel pn;
    
    public void init(){
    	lb=new JLabel("いらしゃいませ。");
    	pn = new JPanel();
    	bt =new JButton("�澓");
    	
    	pn.add(bt);
    	add(lb,BorderLayout.NORTH);
    	add(pn,BorderLayout.SOUTH);
    	
    	bt.addActionListener(new SampleActionListener());
    }
    class SampleActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Container cnt =getContentPane();
			String title = "�澓";
			String msg="�澓ありがとうございました";
			int type= JOptionPane.INFORMATION_MESSAGE;
			 
			JOptionPane.showMessageDialog(cnt, msg,title,type);
		}
    }
}
