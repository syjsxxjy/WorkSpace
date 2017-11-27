package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import dao.ConnectionFactory;
import dao.UserDao;
import dto.User;

public class LoginUserCheck {
 private UserDao userdao=new UserDao();
 
 public boolean check(User user){
	Connection conn=null;
	try {
		conn =  ConnectionFactory.getInstance().makeConnection();
		conn.setAutoCommit(false);
		ResultSet result=userdao.get(conn,user);
		while(result.next()){
			return true;
		}		
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
}
