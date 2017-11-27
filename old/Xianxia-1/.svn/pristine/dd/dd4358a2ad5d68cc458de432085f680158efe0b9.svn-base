package dao;

import model.User;
/**
 * 用户的数据库操作类
 * @author Admin
 *
 */
public class UserDao {
	
	//存放当前用户，方便获取
	public static User user;

	/**
	 * 存放用户的”数据库“
	 */
	private static User[] users = new User[10];
	
	/**
	 * 添加注册用户
	 * @param u
	 */
	public static void add(User u){
	
		for(int i = 0;i < users.length;i++){
			if(users[i] == null){
				users[i] = u;
				break;
			}
		}
	}
	
	/**
	 * 查询用户的方法
	 * @param u
	 */
	public static User get(String username,String password){
	
		for(int i = 0;i < users.length;i++){
			
			User u = users[i];
			
			if(u != null){
				
				if(username.equals(u.getUsername())){
					//告诉用户 账户不存在
					if(password.equals(u.getPassword())){
						return u;
					}
				}
			}
		}
		
		return null;
	}

	
	
	
}
