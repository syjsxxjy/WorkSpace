package Lesson5;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
public class Sample10 extends JApplet{
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
			String title1 = "�_�J";
			String msg1="云輝に�澓しますか��";
			int type1 = JOptionPane.YES_NO_OPTION;
			
			String title2 = "�澓";
			String msg2="�澓ありがとうございました";
			int type2= JOptionPane.INFORMATION_MESSAGE;
			
			int res= JOptionPane.showConfirmDialog(cnt,msg1,title1,type1);
			if(res==JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(cnt,msg2,title2,type2);
			}

		}
    }
}
