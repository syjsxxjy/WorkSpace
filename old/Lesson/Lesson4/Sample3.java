import javax.swing.*;
import java.awt.*;
public class Sample3 extends JApplet
	{
		private JButton[] bt =new JButton[5];
		public void init(){
			for(int i =0;i<bt.length;i++){
				bt[i] = new JButton(Integer.toString(i));
			}

			setLayout(new FlowLayout());
			for(int i =0;i<bt.length;i++){
				add(bt[i]);
			}

	}
}