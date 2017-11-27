package Lesson4;
/*
 * ラベル　Label
 */
import javax.swing.*;
import java.awt.*;
public class Sample6 extends JApplet
	{
		private JLabel[] lb =new JLabel[3];
		
		public void init(){
			for(int i =0;i<lb.length;i++){
				lb[i] = new JLabel("車"+i+"いかがですか");
			}
			//前景色を設定します
			lb[0].setForeground(Color.black);
			lb[1].setForeground(Color.black);
			lb[2].setForeground(Color.black);
			//背景色を設定します
			lb[0].setBackground(Color.white);
			lb[1].setBackground(Color.gray);
			lb[2].setBackground(Color.white);
			//透過を設定します
			lb[0].setOpaque(true);
			lb[1].setOpaque(true);
			lb[2].setOpaque(true);
			//水平方向の位置揃えを設定します
			lb[0].setHorizontalAlignment(JLabel.LEFT);
			lb[1].setHorizontalAlignment(JLabel.CENTER);
			lb[2].setHorizontalAlignment(JLabel.RIGHT);
			//垂直方向の位置揃えを設定します
			lb[0].setVerticalAlignment(JLabel.TOP);
			lb[1].setVerticalAlignment(JLabel.CENTER);
			lb[2].setVerticalAlignment(JLabel.BOTTOM);
			
			setLayout(new GridLayout(3, 1, 3, 3));
			
			for(int i =0;i<lb.length;i++){
				add(lb[i]);
			}
	}
}