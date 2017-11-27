package Lesson5;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Sample3 extends JApplet {
	private JLabel lb;
	private JTable tb;
	private JScrollPane sp;
	
	public void init(){
		String[] colname ={"車名","価格","月日"};
		String[][] data ={
				{"乗用車","1200","10-1"}	,
				{"トラック","2400","10-5"}	,	
				{"オープンカー","3600","10-6"}	,	
				{"タクシー","2500","10-7"}	,	
				{"スポーツカー","2600","10-8"}	,	
				{"ミニカー","300","10-9"}	,	
				{"自転車","800","10-10"}	,	
				{"三輪車","600","10-11"}	,	
		};
		
		lb=new JLabel("いらしゃいませ。");
		tb=new JTable(data,colname);
		sp=new JScrollPane(tb);
		
		add(lb,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
	}
}
