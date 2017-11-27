package Lesson5;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample8 extends JApplet {
      private JLabel lb;
      private JButton[] bt=new JButton[3];
      private Icon ic;
      private JToolBar tl;
      
      public void init(){
    	  lb=new JLabel("中日仄扎中引六﹝");
    	  tl= new JToolBar();
    	  ic = new ImageIcon(getImage(getDocumentBase(),"Lesson5/car.jpg"));
    	  
    	  for(int i=0;i<bt.length;i++){
    		  bt[i]=new JButton(ic);
    	  }
    	  
    	  tl.add(bt[0]);
    	  tl.add(bt[1]);
    	  tl.addSeparator();
    	  tl.add(bt[2]);
    	  
    	  add(tl,BorderLayout.NORTH);
    	  add(lb,BorderLayout.CENTER);
    	  
    	  for(int i= 0;i<bt.length;i++){
    		  bt[i].addActionListener(new SampleActionListener());
    		  bt[i].setToolTipText("This is a button");
    	  }
      }
     class SampleActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int num =0;
			JButton tmp =(JButton)e.getSource();
			if(tmp==bt[0])
				num = 1;
			else if(tmp==bt[1])
				num =2;
			else if(tmp==bt[2])
				num =3;
			
			lb.setText(num+"瘍��匹允友");
		}        	
        }
}
