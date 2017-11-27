package view;

import model.Map;
import model.User;
import dao.UserDao;

/**
 * 动态展示地图
 * @author Admin
 *
 */
public class MapView {
	
	/**
	 * 默认展示当前角色所在地图
	 */
	public static void show(){
		//首先从数据仓库 中 取出 当前角色
		User user = UserDao.user;
		
		//拿到当前角色所在的地图
		Map map = user.getUserInfo().getMap();
		map.show();

	}
}
