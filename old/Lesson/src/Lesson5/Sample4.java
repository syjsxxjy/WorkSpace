package Lesson5;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class Sample4 extends JApplet {
	private JLabel lb;
	private JTable tb;
	private JScrollPane sp;
	
	public void init(){
   
    	lb=new JLabel("§§§È§∑§„§§§ﬁ§ª°£");
    	tb =new JTable(new MyTableModel());
    	sp =new JScrollPane(tb);
    	
    	add(lb,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
    }
	   class MyTableModel extends AbstractTableModel{
		   DateFormat df;
		   public MyTableModel(){
			   df =new SimpleDateFormat("yyyy/MM/dd");
		   }
		   public int getRowCount(){
			   return 50;
		   }
		   public int getColumnCount(){
			   return 2;
		   }
		   public Object getValueAt(int row,int column){
		    Calendar cl=Calendar.getInstance();
		    cl.setTime(new Date());
		    cl.add(Calendar.DATE, row);
		    
		    switch(column) {
		    	case 0:
		    		return (df.format(cl.getTime()));
		    	case 1:
		    		if (cl.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
		    			return "–›»’§«§π";
		    		}else{
		    				return "Ü”òI»’§«§π";
		    		}
		    	default:
		    		return "";	
		    }	    	
		    	}
		    public String getColumnName(int column){
		    	switch(column){
		    	case 0:
		    		return "»’∏∂";
		    	case 1:
		    		return "Ü”òI";
		    	default:
		    		return "";		
		    	
		    }
		   }
	   }
}
