package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.BoardDao;
import dao.ConnectionFactory;
import dto.Board;

public class BoardSearch {
	private ArrayList boardnamelist;
	private int boardamount;
	
	public ArrayList getBoardnamelist() {
		return boardnamelist;
	}

	private void setBoardnamelist(ArrayList boardnamelist) {
		this.boardnamelist = boardnamelist;
	}
   
	public int getBoardamount() {
		return boardamount;
	}

	private void setBoardamount(int boardamount) {
		this.boardamount = boardamount;
	}

	
	
	
	private BoardDao boarddao=new BoardDao();
		 
	 public boolean check(){
		Connection conn=null;
		ArrayList list=new ArrayList();
		try {
			conn =  ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			ResultSet result=boarddao.getresult(conn);		
			while(result.next()){
				list.add(result.getNString("boardname"));
			}
			setBoardamount(list.size());
			setBoardnamelist(list);
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
