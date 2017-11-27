package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Reply;


public class ReplyDao extends dto.Reply{
	
	public void save(Connection conn, Reply reply) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO tbl_reply(title, body, uid,tid) values (?, ?, ?,?)");
		ps.setString(1, reply.getTitle());
		ps.setString(2, reply.getBody());
		ps.setLong(3, reply.getUid());
		ps.setLong(4, reply.getTid());
		ps.execute();
	}

	public void update(Connection conn, Long id,Reply reply) throws SQLException {
		String updateSql = "UPDATE tbl_reply SET title = ?, body = ?, uid = ?, tid = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1,  reply.getTitle());
		ps.setString(2, reply.getBody());
		ps.setLong(3,  reply.getUid());
		ps.setLong(4,  reply.getTid());
		ps.setLong(5, id);
		ps.execute();
	}

	public void delete(Connection conn, Reply reply) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM tbl_reply WHERE id = ?");
		ps.setLong(1, reply.getId());
		ps.execute();
	}
	
	public void deletebytid(Connection conn, Reply reply) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM tbl_reply WHERE tid = ?");
		ps.setLong(1, reply.getTid() );
		ps.execute();
	}
	
	public ResultSet getresultbyID(Connection conn ,long id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_reply where id = ?");
		ps.setLong(1, id);
		return ps.executeQuery();
	}
	/*
	 * 查询回复数据库并保存结果集
	 */
	public ResultSet getResult(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_reply");
		return ps.executeQuery();
	}

}
