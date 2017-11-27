package Lesson9;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Sample11 extends JFrame {
        private JPanel pn;
        private JList<String> ls;
        private JScrollPane sp;
        private JButton bt;
        
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Sample11 sm =new Sample11();
      }
	public Sample11(){
		super("Sample11");
		
		File fl=new File("./src/Lesson9");
		File[] fls=fl.listFiles(new MyFileFilter());
		String[] st=new String[fls.length];
		for(int i=0;i<fls.length;i++){
			st[i]=fls[i].getName();
		}
		
		ls=new JList<String>(st);
		sp=new JScrollPane(ls);
		bt=new JButton("Æð„Ó");
		pn=new JPanel();
		
		pn.add(bt);
		 
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
			try {
				if(e.getSource()==bt){
					Desktop dp=Desktop.getDesktop();
					dp.open(new File((String)ls.getSelectedValue()));
				}
			} catch ( IOException e2) {
				// TODO: handle exception
				System.out.println("Æð„Ó¤Ç¤­¤Þ¤»¤ó¤Ç¤·¤¿");
			}
		}
	}
	 class SampleWindowListener extends WindowAdapter{
	 	   public void windowClosing(WindowEvent e){
	 		   System.exit(0);
	 	   }
	    }
	 class MyFileFilter implements FileFilter{

		@Override
		public boolean accept(File pathname) {
			// TODO Auto-generated method stub
			if(pathname.isFile()){
				return true;
			}
			return false;
		}
		 
	 }
}
