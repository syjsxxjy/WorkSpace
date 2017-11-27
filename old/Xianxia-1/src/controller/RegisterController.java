package controller;

import java.util.Scanner;

import dao.UserDao;
import model.Map;
import model.User;
import model.UserInfo;
import view.RegisterView;

/**
 * 注册类，专做注册逻辑
 * @author Admin
 *
 */
public class RegisterController extends BaseController{
	
	public static void register(){
		
		RegisterView.show1();
		
		//iuput 对象 通过 继承 basecontroller 类 获得
		String username  = input.next();
		
		RegisterView.show2();
		
		String passwored  = input.next();
		
		//接受了 用户输入的账号跟密码外，需要把账号密码保存记录
		
		User user = new User();
		user.setPassword(passwored);
		user.setUsername(username);
		
		registerInit(user);

		UserDao.add(user);
		
	}
	
	/**
	 * 角色初始化操作
	 * @param user
	 */
	private  static void registerInit(User user){
		//初始化角色信息
		
		UserInfo userInfo = new UserInfo();
		userInfo.setBlood(100);
		userInfo.setName("李寻忆");
		
		user.setUserInfo(userInfo);
		
		//初始化位置信息
		Map map = new Map("盟重城");

		//把地图信息跟角色关联起来
		userInfo.setMap(map);
		
		//初始化角色位于地图中的位置
		int x = 10;
		int y = 10;
		map.setUser(y, x);
		
		//记录下 这个用户当前的位置
		userInfo.setX(x);
		userInfo.setY(y);
						
	}
}
