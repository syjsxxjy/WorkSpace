package test;
import java.util.ArrayList;

import model.*;

public class TopicCheckTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		TopicCheck tc = new TopicCheck();
		tc.check();
		System.out.println("运行 ");
		list = tc.getTopicuidlist();
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
			System.out.println("列表内数据 " + list.get(i));
		}

	}

}
