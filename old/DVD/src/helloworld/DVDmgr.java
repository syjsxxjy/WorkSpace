package helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class DVDmgr {
	//DVD初始化
	public static void ini() {
		DVDset dvd = new DVDset();
		dvd.name[0] = "罗马假日";
		dvd.state[0] = "借出";
		dvd.date[0] = "7.1";

		dvd.name[1] = "风声鹤唳";
		dvd.state[1] = "在库";
		dvd.date[1] = null;

		dvd.name[2] = "浪漫满屋";
		dvd.state[2] = "在库";
		dvd.date[2] = null;
	}
   //返回主菜单方法
	public static void returnMain() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				for (;;){
				System.out.println("输入0返回主菜单");
				String line = reader.readLine();
				int l=Integer.parseInt(line) ;
				if (l== 0) break;
							}
				startMenu();	
			}catch (IOException e) {
				System.out.println(e);
			}
	}

	
	
 //开始菜单
	public static void startMenu() {
		System.out.println("欢迎使用迷你DVD管理器  ");
		System.out.println("-----------------------------------------");
		System.out.println("1.新增DVD");
		System.out.println("2.查看DVD");
		System.out.println("3.删除DVD");
		System.out.println("4.借出DVD");
		System.out.println("5.归还DVD");
		System.out.println("6.退出DVD");
		System.out.println("-----------------------------------------");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = reader.readLine();
			switch (line) {
			case "1":
				System.out.println("增加一张DVD");
				DVDdo.add();
				returnMain();
				break;
			case "2":
				System.out.println("查看所有DVD");
				DVDdo.search();
				returnMain();
				break;
			//报错
			case "3":
				System.out.println("删除一张DVD");
				DVDdo.delete();
				returnMain();
				break;
			case "4":
				System.out.println("借出一张DVD");
                DVDdo.lend();
				returnMain();
				break;
			case "5":
				System.out.println("归还一张DVD");
				DVDdo.returnDVD();
				returnMain();
				break;
			case "6":
				System.out.println("已退出，谢谢使用♂");

				returnMain();
				break;
			default:
				System.out.println("请输入1-6的数字,输入0返回主菜单♂");
				returnMain();

			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public DVDmgr() {
		// TODO Auto-generated constructor stub

	}

}
