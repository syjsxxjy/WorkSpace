package model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.*;

public class UserCheck {
          private ArrayList usernamelist;
          private ArrayList passwordlist;
          private ArrayList emaillist;
          private ArrayList<String> avatarurllist;
          private ArrayList<Long> useridlist=new ArrayList<Long>();

	
          
	    private UserDao userdao =new UserDao();
	    
	 public boolean check(){//访问数据库，封装结果集
			Connection conn=null;
			ArrayList<String> usernamelst=new ArrayList<String>(); usernamelst.add("username0");
			ArrayList<String> passwordlst=new ArrayList<String>(); passwordlst.add("password0");
			ArrayList<String> emaillst=new ArrayList<String>(); emaillst.add("email0");
			ArrayList<Long> useridlst=new ArrayList<Long>(); useridlst.add((long) 0);
			ArrayList<String> avatarurllst=new ArrayList<String>(); avatarurllst.add("uid0");
		
			
			try {
				conn =  ConnectionFactory.getInstance().makeConnection();
				conn.setAutoCommit(false);
				ResultSet result=userdao.getResult(conn);		
				while(result.next()){
					usernamelst.add(result.getNString("username"));
					passwordlst.add(result.getNString("password"));
					emaillst.add(result.getNString("email"));
					useridlst.add(result.getLong("id"));
					avatarurllst.add(result.getNString("avatarurl"));
				}
				
				setEmaillist(emaillst);
				setPasswordlist(passwordlst);
				setUsernamelist(usernamelst);
				setUseridlist(useridlst);
				setAvatarurllist(avatarurllst);
				
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			 	 return false;
			  }
	public ArrayList<String> getUsernamelist() {
		return usernamelist;
	}
	public void setUsernamelist(ArrayList<String> usernamelist) {
		this.usernamelist = usernamelist;
	}
	public ArrayList<String> getPasswordlist() {
		return passwordlist;
	}
	public void setPasswordlist(ArrayList<String> passwordlist) {
		this.passwordlist = passwordlist;
	}
	public ArrayList<String> getEmaillist() {
		return emaillist;
	}
	public void setEmaillist(ArrayList<String> emaillist) {
		this.emaillist = emaillist;
	}
	public ArrayList<Long> getUseridlist() {
		return useridlist;
	}
	public void setUseridlist(ArrayList<Long> useridlist) {
		this.useridlist = useridlist;
	}
	public ArrayList<String> getAvatarurllist() {
		return avatarurllist;
	}
	public void setAvatarurllist(ArrayList<String> avatarurllist) {
		this.avatarurllist = avatarurllist;
	}
		
}
