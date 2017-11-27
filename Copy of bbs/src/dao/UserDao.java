package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UserDao extends dto.User{
	public void save(Connection conn, User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO tbl_user(username, password, email,avatarurl) values (?, ?, ?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getAvatarurl());
		ps.execute();
	}

	public void update(Connection conn, Long id, User user) throws SQLException {
		String updateSql = "UPDATE tbl_user SET name = ?, password = ?, email = ?, avatarurl=?,WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getAvatarurl());
		ps.setLong(4, id);
		ps.execute();
	}

	public void delete(Connection conn, User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM tbl_user WHERE id = ?");
		ps.setLong(1, user.getId());
		ps.execute();
	}
	/*
	 * 根据用户名和密码查询数据库
	 */
	public ResultSet get(Connection conn,  User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_user WHERE username = ? AND password = ?");
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		return ps.executeQuery();
	}
	
	/* 
	 * 根據用戶名查询数据库
	 *	 */
	public ResultSet getUsername(Connection conn,  User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_user WHERE username = ?");
		ps.setString(1, user.getName());
		return ps.executeQuery();
	}
	
	
	public ResultSet getResult(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_user ;");
		return ps.executeQuery();
	}
	
}
