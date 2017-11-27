package Lesson5;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


import java.awt.event.*;


public class Sample5 extends JApplet {
	private JList<String> lst;
	private JScrollPane sp;
	private JButton bt;
	private JPanel pn;
	
	public void init(){
		String[] str={
    			"乗用車","トラック","オープンカー","タクシー","スポーツカー","ミニカー","自転車","三輪車","バイク","飛行機",
    			"ヘリコプター","ロケット"};
		
		lst = new JList<String>(str);
		sp=new JScrollPane(lst);
		bt=new JButton("ビューの変更");
		pn= new JPanel();
		
		pn.add(bt);
		add(sp,BorderLayout.CENTER);
		add(pn,BorderLayout.SOUTH);
		
		bt.addActionListener(new SampleActionListener());
	}
    class SampleActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    	Container cnt =getContentPane();
    	try{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFell");
    		SwingUtilities.updateComponentTreeUI(cnt);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	}
    	}
}
