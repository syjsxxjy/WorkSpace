package Lesson9;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

//内部フレームを表示する
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Lesson9.Sample7.SampleWindowListener;

public class Sample8 extends JFrame {
    private JTextArea[] ta;
    private JScrollPane[] sp;
    private JPanel pn;
    private JDesktopPane dp;
    private JInternalFrame[] itf;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                   Sample8 sm=new Sample8();
	}
    public Sample8(){
    	super("Sample8");
    	
    	dp=new JDesktopPane();
    	
    	File fl=new File("./src/Lesson9");
    	File[] fls=fl.listFiles(new MyFileFilter());
    	
    	try {
			ta =new JTextArea[fls.length];
			sp=new JScrollPane[fls.length];
			itf=new JInternalFrame[fls.length];
			
			for(int i=0;i<fls.length;i++){
				ta[i]=new JTextArea();
				sp[i]=new JScrollPane(ta[i]);
				itf[i]=new JInternalFrame(fls[i].getName(), true,true, true,true);
				
				BufferedReader br = new BufferedReader(new FileReader(fls[i]));
				
				ta[i].read(br, null);
				br.close();
			}
			
			for(int i=0;i<fls.length;i++){
				itf[i].add(sp[i]);
				dp.add(itf[i]);
				itf[i].setLocation(i*10, i*10);
				itf[i].setSize(300, 200);
				itf[i].setVisible(true);
			}
			
			add(dp,BorderLayout.CENTER);
			
			addWindowListener(new SampleWindowListener());
			
			setSize(300, 200);
			setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    class MyFileFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String n) {
			// TODO Auto-generated method stub
			if(n.toLowerCase().endsWith(".java")){
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
