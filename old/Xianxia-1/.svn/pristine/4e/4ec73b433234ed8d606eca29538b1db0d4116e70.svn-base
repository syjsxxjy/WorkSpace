package controller;

import java.util.Random;
import java.util.Scanner;

import view.LoginView;
import view.WelcomeView;


/**
 * 游戏开始界面
 * @author Admin
 *
 */
public class StartGame extends BaseController{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//打印欢迎界面
		
		while(true){
			
			WelcomeView.show();
			
			String s = input.next();
			if(s.equalsIgnoreCase("1")){
				//调用登陆方法
				LoginController.login();
			}else if(s.equalsIgnoreCase("2")){
				//进入注册方法
				RegisterController.register();
				
			}else{
				System.out.println("选项错误，程序结束");
			}
		}

	}

}
