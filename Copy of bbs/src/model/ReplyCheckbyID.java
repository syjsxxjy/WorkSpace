package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.ReplyDao;

public class ReplyCheckbyID {
	private ArrayList<String> replytitlelist;
    private ArrayList<String> replybodylist;
    private ArrayList<Integer> replyuidlist;
    private ArrayList<Integer> replytidlist;
    private ArrayList<Integer> replyidlist;
    
	private ReplyDao replydao=new ReplyDao();
	
	 public boolean check(long id){//访问数据库，封装结果集
			Connection conn=null;
			ArrayList<String> titlelist=new ArrayList<String>(); titlelist.add("replytitle0");
			ArrayList<String> bodylist=new ArrayList<String>(); bodylist.add("replybody0");
			ArrayList<Integer> uidlist=new ArrayList<Integer>(); uidlist.add(0);
			ArrayList<Integer> tidlist=new ArrayList<Integer>(); tidlist.add(0);
			ArrayList<Integer> idlist=new ArrayList<Integer>(); idlist.add(0);
			
			try {
				conn =  ConnectionFactory.getInstance().makeConnection();
				conn.setAutoCommit(false);
				ResultSet result=replydao.getresultbyID(conn, id);
				while(result.next()){
					titlelist.add(result.getNString("title"));
					bodylist.add(result.getNString("body"));
					uidlist.add(result.getInt("uid"));
					tidlist.add(result.getInt("tid"));
					idlist.add(result.getInt("id"));
					
				}
				setReplytitlelist(titlelist);
				setReplybodylist(bodylist);
				setReplyuidlist(uidlist);
				setReplytidlist(tidlist);
				setReplyidlist(idlist);
								
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

	public ArrayList<String> getReplytitlelist() {
		return replytitlelist;
	}

	public void setReplytitlelist(ArrayList<String> replytitlelist) {
		this.replytitlelist = replytitlelist;
	}

	public ArrayList<String> getReplybodylist() {
		return replybodylist;
	}

	public void setReplybodylist(ArrayList<String> replybodylist) {
		this.replybodylist = replybodylist;
	}

	public ArrayList<Integer> getReplyuidlist() {
		return replyuidlist;
	}

	public void setReplyuidlist(ArrayList<Integer> replyuidlist) {
		this.replyuidlist = replyuidlist;
	}

	public ArrayList<Integer> getReplytidlist() {
		return replytidlist;
	}

	public void setReplytidlist(ArrayList<Integer> replytidlist) {
		this.replytidlist = replytidlist;
	}

	public ArrayList<Integer> getReplyidlist() {
		return replyidlist;
	}

	public void setReplyidlist(ArrayList<Integer> replyidlist) {
		this.replyidlist = replyidlist;
	}
}
