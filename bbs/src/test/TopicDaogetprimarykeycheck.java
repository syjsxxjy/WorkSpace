package test;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.*;
import dto.*;

public class TopicDaogetprimarykeycheck {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Connection conn=null;
		Topic topic=new Topic();
              topic.setBid(0);
              topic.setBody("topicbodytest");
              topic.setReplyCount(233);
              topic.setTitle("Test topic title");
              topic.setUid(1);
              
          	

				conn =  ConnectionFactory.getInstance().makeConnection();				  
	              TopicDao topicdao=new TopicDao();
	              topicdao.save(conn, topic);
	            
	              if(topicdao.getPrimarykeysave().next()){  
	            	    System.out.println(topicdao.getPrimarykeysave().getInt(1));  
	            	    }  
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            
	}

}
