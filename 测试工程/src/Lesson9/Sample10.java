package Lesson9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class Sample10 extends JFrame {
    private JPanel pn;
    private JLabel lb;
    private JTextArea ta;
    private JTextField tf;
    private JButton bt;
    private JScrollPane sp;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Sample10 sm=new Sample10();
	}
	
	public Sample10(){
		super("Sample10");
		
		lb=new JLabel("»Î¡¶§∑§∆§Ø§¿§µ§§");
		ta=new JTextArea();
		sp=new JScrollPane(ta);
		
		pn=new JPanel(new GridLayout(1, 2));
		bt=new JButton("ó À˜");
		tf=new JTextField();
		
		pn.add(tf);
		pn.add(bt);
		
		add(lb,BorderLayout.NORTH);
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
					Highlighter hl=ta.getHighlighter();
					hl.removeAllHighlights();
					Pattern pn=Pattern.compile(tf.getText());
					Matcher mt=pn.matcher(ta.getText());
					
					while(mt.find()){
						hl.addHighlight(mt.start(), mt.end(), new DefaultHighlighter.DefaultHighlightPainter(null));
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
