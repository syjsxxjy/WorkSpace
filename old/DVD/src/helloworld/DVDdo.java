package helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DVDdo {
	// DVD查询
	public static void search() {
		System.out.println("序号      状态              名称        借出日期");
		for (int i = 0; i < 50; i++) {
			if (DVDset.name[i] == null)
				continue;
			System.out.println((i + 1) + "          " + DVDset.name[i] + "        " + DVDset.state[i] + "        "
					+ DVDset.date[i] + "          ");

		}
	}

	// 增加
	public static void add() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("请输入DVD名称");
			String line = reader.readLine();
			for (int i = 0; i < DVDset.name.length; i++) {
				if (DVDset.name[i] == null) {
					Arrays.fill(DVDset.name, i, i + 1, line);
					Arrays.fill(DVDset.state, i, i + 1, "在库");
					Arrays.fill(DVDset.date, i, i + 1, null);
					break;
				}
			}
			System.out.println("添加一条信息完毕");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	// 删除（无法进行）
	public static void delete() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {// 把元素替换为null
			System.out.println("请输入要删除DVD编号");
			String line = reader.readLine();
			// int l=Integer.parseInt(line) ;
			// Arrays.sort(DVDset.name);
			int index = Arrays.binarySearch(DVDset.name, line);
			Arrays.fill(DVDset.name, index, index + 1, null);
		} catch (IOException e) {
			System.out.println(e);
		}
		// 将后面一个元素替换到前一个null元素的位置
		for (int i = 1; i < DVDset.name.length; i++) {
			for (int j = 0; j < DVDset.name.length - i; j++) {
				if (DVDset.name[j] == null) {
					DVDset.name[j] = DVDset.name[j + 1];
				}
			}
		}
		System.out.println("删除DVD信息完毕");
	}

	// 借出 （报错）
	public static void lend() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("请输入要借出的DVD名称");
			String line = reader.readLine();
			
	for (int i = 0; i < DVDset.name.length; i++) {
				String n = DVDset.name[i];
				String n2 =DVDset.name[i+1];
				String s = DVDset.state[i];
				String s2 = "在库";
				System.out.println("n="+n+"i="+i+"n2="+n2);
			  if (n.equals(line) && s.equals(s2)) {
					System.out.println("你借走了这本书");
					DVDset.state[i] = "借出";
					break;
				}
			  if (n.equals(line) && (s.equals(s2) == false)) {
					System.out.println("这张DVD已经被借走");
					break;
				
				}
			}
		
		} catch (

		IOException e)

		{
			System.out.println(e);
		}
	}

	// 归还
	public static void returnDVD() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("请输入要归还的DVD名称");
			String line = reader.readLine();
			int index = Arrays.binarySearch(DVDset.name, line);
			 DVDset.state[index]="在库";
			 DVDset.date[index]=null;
			System.out.println("你归还了DVD");

		} catch (

		IOException e)

		{
			System.out.println(e);
		}
	}
}