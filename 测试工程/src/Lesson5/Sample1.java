package Lesson5;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sample1 extends JApplet{
    private JLabel lb;
    private JList<String> lst;
    private JScrollPane sp;
    
    public void init(){
    	String[] str={
    			"乗用車","トラック","オープンカー","タクシー","スポーツカー","ミニカー","自転車","三輪車","バイク","飛行機",
    			"ヘリコプター","ロケット"};
    	lb=new JLabel("いらしゃいませ。");
    	lst =new JList<String>(str);
    	sp =new JScrollPane(lst);
    	
    	add(lb,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		
		lst.addListSelectionListener((ListSelectionListener) new SampleListSelectionListener());
    }
    class SampleListSelectionListener implements ListSelectionListener{
    	public void valueChanged(ListSelectionEvent e){
    	JList tmp=(JList)e.getSource();
    	String str =(String)tmp.getSelectedValue();
    	lb.setText(str+"ですね");
    	}
    }
}
