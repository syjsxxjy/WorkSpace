package Lesson4;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class Sample10 extends JApplet{
	private JLabel lb;
	private JPanel pn;
	private JButton bt;
	private Icon ic;
	
	public void init(){
		lb=new JLabel("いらしゃいませ。");
		pn =	new JPanel();
		bt = new JButton("�澓");
		ic = new ImageIcon(getImage(getDocumentBase(),"Lesson4/car.jpg"));
		//コンポ�`ネントの�O協
		bt.setIcon(ic);
		//コンテナへの弖紗
		pn.add(bt);
		add(lb,BorderLayout.NORTH);
		add(pn,BorderLayout.SOUTH);
		//リスナの鞠�h
		bt.addActionListener((ActionListener) new SampleActionListener());
	}
	
	class SampleActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			lb.setText("ご�澓ありかとうございます");
			bt.setEnabled(false);
		}
	}
}
