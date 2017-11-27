package controller;

import model.User;
import dao.UserDao;
import view.LoginView;
import view.RegisterView;

/**
 * 登录的操作
 * @author Admin
 *
 */
public class LoginController extends BaseController {
	
	public static void login(){
		
		LoginView.show1();
		
		//iuput 对象 通过 继承 basecontroller 类 获得
		String username  = input.next();
		
		LoginView.show2();
		
		String password  = input.next();
		
		//接受完用户输入后，要去数据仓库（dao）中，对事先存放的账号密码 进行查询
		
		User user = UserDao.get(username, password);
		
		//登录的时候，如果用户输入的账号密码 正好在数据库中存在，并且密码正确，那么返回这个用户对象
		if(user != null){
			//开始做登录操作，进入到游戏主页面]
			
			//把登陆后的用户信息，存入到整个程序可以访问到的全局变量
			UserDao.user = user;
			
			CenterController.show();
		}else{
			//账号密码错误，返回欢迎界面
		}
		
	}
}
