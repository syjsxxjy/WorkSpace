package model;

import dao.UserDao;

/**
 * 角色信息
 * @author Admin
 *
 */
public class UserInfo {
	
	private String name;
	private int  blood;
	
	//当前所在地图
	private Map map;
	
	private int x;
	private int y;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}

	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 人移动的方法
	 */
	public void move(String direction){
		
		
		switch (direction) {
			case "w":
				//之所以在这里--y，无非就是想 这里一步就记录了角色移动后的位置
				map.execute(--y, x,direction);
				break;
			case "s":
				map.execute(++y, x,direction);
				break;
			case "a":
				map.execute(y, --x,direction);
				break;
			case "d":
				map.execute(y, ++x,direction);
				break;
			default:
				break;
		}
		
		
	}
	
	
}
