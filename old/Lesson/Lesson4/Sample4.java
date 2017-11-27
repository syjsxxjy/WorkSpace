import javax.swing.*;
import java.awt.*;
public class Sample4 extends JApplet
	{
		private JButton[] bt =new JButton[6];
		public void init(){
			for(int i =0;i<bt.length;i++){
				bt[i] = new JButton(Integer.toString(i));
			}

			setLayout(new GridLayout(2,3));
			for(int i =0;i<bt.length;i++){
				add(bt[i]);
			}

	}
}