package controller;

import model.Map;
import model.UserInfo;
import dao.UserDao;
import view.MapView;
import view.MeanView;
import view.UserInfoView;

/**
 * 游戏主体控制操作
 * 移动等等
 * @author Admin
 *
 */
public class CenterController extends BaseController {
	
	public static void show(){
		
		UserInfo user = UserDao.user.getUserInfo();
		
		while(true){
			
			MapView.show();
			MeanView.show();
			
			//展示主页面后 就需要等待用户输入
			String k = input.next();
			
			switch (k) {
			case "w":
					user.move(k);
				break;
			case "s":
					user.move(k);
				break;
			case "a":
					user.move(k);
				break;
			case "d":
					user.move(k);
				break;
			case "1":
				UserInfoView.show();
				input.next();
			break;
			default:
				break;
			}
			
			
			MapView.show();
			MeanView.show();
		}

	}
}
