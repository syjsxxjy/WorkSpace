package Lesson9;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class Sample4 extends JFrame{
     private JPanel pn;
     private JLabel lb;
     private JTextArea ta;
     private JScrollPane sp;
     private JButton bt1,bt2;
     
     public static void main(String[] args) {
		Sample4 sm=new Sample4();
	}
     public Sample4(){
    	 super("Sample4");
    	 
    	 lb=new JLabel("•’•°•§•Î§Úﬂxík§∑§∆§Ø§¿§µ§§");
    	 ta=new JTextArea();
    	 sp=new JScrollPane(ta);
    	 
    	 pn=new JPanel();
    	 bt1=new JButton("’i§ﬂﬁz§ﬂ");
    	 bt2=new JButton("±£¥Ê");
    	 
    	 pn.add(bt1);
    	 pn.add(bt2);
    	 
    	 add(lb,BorderLayout.NORTH);
    	 add(sp,BorderLayout.CENTER);
    	 add(pn, BorderLayout.SOUTH);
    	 
    	 bt1.addActionListener(new SampleActionListener());
    	 bt2.addActionListener(new SampleActionListener());
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
            try {
            	if (e.getSource()==bt1){
            		int res=fc.showOpenDialog(cnt);
            		if (res==JFileChooser.APPROVE_OPTION){
            			File fl=fc.getSelectedFile();
            			InputStreamReader isr = new InputStreamReader(new FileInputStream(fl), "utf-8");
            			BufferedReader br=new BufferedReader(isr);
            			ta.read(br, null);
            			br.close();
            		}    				
    			}else if(e.getSource()==bt2){
    				int res=fc.showOpenDialog(cnt);
    				if (res==JFileChooser.APPROVE_OPTION){
            			File fl=fc.getSelectedFile();
            			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(fl), "utf-8");
            			BufferedWriter bw=new BufferedWriter(osw);
            			
            			ta.write(bw);
            			bw.close();
            		}    				
    					
    				}
    			
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
			
		}
    	 
     }
     class SampleWindowListener extends WindowAdapter{
  	   public void windowClosing(WindowEvent e){
  		   System.exit(0);
  	   }
     }
}
