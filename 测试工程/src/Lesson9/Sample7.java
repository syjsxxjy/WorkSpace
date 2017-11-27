package Lesson9;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Sample7 extends JFrame {
	
    private JLabel[] lb;
    private Icon ic;
    private JScrollPane[] sp;
    private JTabbedPane tp;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Sample7 sm=new Sample7();
                
	}
   
	public Sample7(){
		super("Sample7");
		
		File fl=new File("./src/Lesson9/");
		File[] fls=fl.listFiles(new MyFileFilter());
		
		lb=new JLabel[fls.length];
		sp=new JScrollPane[fls.length];
		tp=new JTabbedPane();
		
		for(int i =0;i<fls.length;i++){
			ic=new ImageIcon("./src/Lesson9/"+fls[i].getName());
			lb[i]=new JLabel(ic);
			sp[i]=new JScrollPane(lb[i]);
		}
		
		for(int i=0;i<fls.length;i++){
			tp.add(fls[i].getName(), sp[i]);
		}
		add(tp,BorderLayout.CENTER);
		
		addWindowListener(new SampleWindowListener());
		
		setSize(300, 200);
		setVisible(true);
	}
	    class MyFileFilter implements FilenameFilter{

			@Override
			public boolean accept(File dir, String n) {
				// TODO Auto-generated method stub
				if(n.toLowerCase().endsWith(".jpg")){
					return true;
				}
				return false;
			}
	    }
	    
	    class SampleWindowListener extends WindowAdapter{
	    	   public void windowClosing(WindowEvent e){
	    		   System.exit(0);
	    	   }
	       }
}
