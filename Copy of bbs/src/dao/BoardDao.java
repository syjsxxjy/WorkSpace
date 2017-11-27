package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Board;

public class BoardDao extends dto.Board{
	
	public void save(Connection conn, Board board) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO tbl_board(boardname, pid, ) values (1, 2)");
		ps.setString(1, board.getBoradname());
		ps.setString(2, board.getPid());
		ps.execute();
	}

	public void update(Connection conn, Long id, Board board) throws SQLException {
		String updateSql = "UPDATE tbl_board SET boardname = ?, pid = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, board.getBoradname());
		ps.setString(2, board.getPid());
		ps.setLong(3, id);
		ps.execute();
	}

	public void delete(Connection conn, Board board) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM tbl_board WHERE id = ?");
		ps.setLong(1, board.getId());
		ps.execute();
	}
	/*
	 * 查询板块表所有数据并保存结果集
	 */
	public ResultSet getresult(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_board; ");
		return ps.executeQuery();
	}

}
