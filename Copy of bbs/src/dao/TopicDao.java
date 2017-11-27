package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import dto.Topic;



public class TopicDao extends Topic {
	    private ResultSet primarykeysave; 
	
	public void save(Connection conn, Topic topic) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO tbl_topic(title, body, uid,bid,replyCount,readCount) values (?, ?, ?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, topic.getTitle());
		ps.setString(2, topic.getBody());
		ps.setLong(3, topic.getUid());
		ps.setLong(4, topic.getBid());
		ps.setLong(5, topic.getReplyCount());
		ps.setLong(6, topic.getReadcount());
		ps.execute();
		primarykeysave=ps.getGeneratedKeys();
	}
    
	public void updatereadcount(Connection conn, Topic topic) throws SQLException {
		String updateSql="UPDATE tbl_topic SET  readCount=?  WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql,Statement.RETURN_GENERATED_KEYS);
		ps.setLong(1, topic.getReadcount());
		ps.setLong(2, topic.getId());
		ps.execute();
		primarykeysave=ps.getGeneratedKeys();
	}
	
	
	
	public void update(Connection conn, Long id, Topic topic) throws SQLException {
		
		
		String updateSql = "UPDATE tbl_topic SET title = ?, body = ?, uid = ?, bid = ?, replyCount = ?, readCount=?  WHERE id = ? ";
		
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, topic.getTitle());
		ps.setString(2, topic.getBody());
		ps.setLong(3, topic.getUid());
		ps.setLong(4, topic.getBid());
		ps.setLong(5, topic.getReplyCount());
		ps.setLong(6, topic.getReadcount());
		ps.setLong(7, id);
		ps.execute();
	}

	public void delete(Connection conn,  Topic topic) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM tbl_topic WHERE id = ?");
		ps.setLong(1, topic.getId());
		ps.execute();
	}
	/*
	 * 查询所有帖子数据保存结果集
	 */
	public ResultSet getresult(Connection conn ) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_topic;");
		return ps.executeQuery();
	}
	
	public ResultSet getresultbyID(Connection conn ,long id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_topic where id = ?");
		ps.setLong(1, id);
		return ps.executeQuery();
	}

	public ResultSet getPrimarykeysave() {
		return primarykeysave;
	}

	public void setPrimarykeysave(ResultSet primarykeysave) {
		this.primarykeysave = primarykeysave;
	}
}
