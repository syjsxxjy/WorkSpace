package Lesson9;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import Lesson9.Sample4.SampleActionListener;
import Lesson9.Sample4.SampleWindowListener;

public class Sample5 extends JFrame {
     
	private JLabel lb;
	private JPanel pn1,pn2;
	private JTextField tf[]=new JTextField[5];
	private JButton bt1,bt2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Sample5 sm=new Sample5();
	}
    public Sample5(){
    	super("Sample5");
    	
    	lb=new JLabel("整数を入力してください");
    	pn1=new JPanel();
    	pn2=new JPanel();
    	
    	for(int i=0;i<tf.length;i++){
    		String num=(new Integer(i)).toString();
    		tf[i]=new JTextField(num,5);
    	}
    	
    	bt1=new JButton("読み込み");
   	    bt2=new JButton("保存");
   	    
   	    for(int i=0;i<tf.length;i++){
   	    	pn1.add(tf[i]);
   	    }
   	    pn2.add(bt1);
   	    pn2.add(bt2);
   	    
   	 add(lb,BorderLayout.NORTH);
	 add(pn1,BorderLayout.CENTER);
	 add(pn2, BorderLayout.SOUTH);
	 
	 bt1.addActionListener((ActionListener) new SampleActionListener());
	 bt2.addActionListener((ActionListener) new SampleActionListener());
	 addWindowListener(new SampleWindowListener());
	 
	 pack();
	 setVisible(true);
    }
    
    class SampleActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			java.awt.Container cnt=getContentPane();
			JFileChooser fc =new JFileChooser();
			fc.setFileFilter(new MyFileFilter());
			try {
				if (e.getSource()==bt1){
					int res=fc.showOpenDialog(cnt);
					if (res==JFileChooser.APPROVE_OPTION){
						File fl=fc.getSelectedFile();
						BufferedInputStream bis=new BufferedInputStream(new FileInputStream(fl));
						for(int i =0;i<tf.length;i++){
							int num=bis.read();
							tf[i].setText((new Integer(num)).toString());
						}
						bis.close();
					}
				}else if(e.getSource()==bt2){
					int res=fc.showOpenDialog(cnt);
					if (res==JFileChooser.APPROVE_OPTION){
						File fl=fc.getSelectedFile();
						BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream(fl));
						for(int i = 0;i<tf.length;i++){
							int num=Integer.parseInt(tf[i].getText());
							bos.write(num);
						}
						bos.close();
					}
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
    	class MyFileFilter extends FileFilter{

			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if(f.isDirectory()){
					return true;
				}
				String fn=f.getName();
				if(fn.toLowerCase().endsWith(".bin")){
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "binary file";
			}
    		
    	}
    	
    }
    class SampleWindowListener extends WindowAdapter{
   	   public void windowClosing(WindowEvent e){
   		   System.exit(0);
   	   }
      }
}
