package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.*;

public class TopicCheck {
       private ArrayList<String> topictitlelist;
       private ArrayList<String> topicbodylist;
       private ArrayList<Integer> topicuidlist;
       private ArrayList<Integer> topicreplyCountlist;
       private ArrayList<Integer> topicidlist;
       private ArrayList<Integer> topicreadcountlist;

	private TopicDao topicdao=new TopicDao();
	
	 public boolean check(){//访问数据库，封装结果集
			Connection conn=null;
			ArrayList<String> titlelist=new ArrayList<String>(); titlelist.add("title0");
			ArrayList<String> bodylist=new ArrayList<String>(); bodylist.add("body0");
			ArrayList<Integer> uidlist=new ArrayList<Integer>(); uidlist.add(0);
			ArrayList<Integer> replyCountlist=new ArrayList<Integer>(); replyCountlist.add(0);
			ArrayList<Integer>idlist=new ArrayList<Integer>(); idlist.add(0);
			ArrayList<Integer>readcountlist=new ArrayList<Integer>(); readcountlist.add(0);
			
			try {
				conn =  ConnectionFactory.getInstance().makeConnection();
				conn.setAutoCommit(false);
				ResultSet result=topicdao.getresult(conn);		
				while(result.next()){
					titlelist.add(result.getNString("title"));
					bodylist.add(result.getNString("body"));
					uidlist.add(result.getInt("uid"));
					replyCountlist.add(result.getInt("replyCount"));
					idlist.add((result.getInt("id")));
					readcountlist.add(result.getInt("readCount"));
				}
				setTopictitlelist(titlelist);
				setTopicbodylist(bodylist);
				setTopicuidlist(uidlist);
				setTopicreplyCountlist(replyCountlist);
				setTopicidlist(idlist);
				setTopicreadcountlist(readcountlist);
				
				
				
								
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
	 
	 
	 public ArrayList<Integer> getTopicidlist() {
			return topicidlist;
		}

		public void setTopicidlist(ArrayList<Integer> topicidlist) {
			this.topicidlist = topicidlist;
		}
	 
	 public ArrayList<Integer> getTopicreplyCountlist() {
			return topicreplyCountlist;
		}

		public void setTopicreplyCountlist(ArrayList<Integer> topicreplyCountlist) {
			this.topicreplyCountlist = topicreplyCountlist;
		}

	public ArrayList<String> getTopictitlelist() {
		return topictitlelist;
	}
	public void setTopictitlelist(ArrayList<String> topictitlelist) {
		this.topictitlelist = topictitlelist;
	}
	public ArrayList<String> getTopicbodylist() {
		return topicbodylist;
	}
	public void setTopicbodylist(ArrayList<String> topicbodylist) {
		this.topicbodylist = topicbodylist;
	}

	public ArrayList<Integer> getTopicuidlist() {
		return topicuidlist;
	}

	public void setTopicuidlist(ArrayList<Integer> topicuidlist) {
		this.topicuidlist = topicuidlist;
	}


	public ArrayList<Integer> getTopicreadcountlist() {
		return topicreadcountlist;
	}


	public void setTopicreadcountlist(ArrayList<Integer> topicreadcount) {
		this.topicreadcountlist = topicreadcount;
	}


}
