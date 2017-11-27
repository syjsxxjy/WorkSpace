<%@page import="java.util.ArrayList"%>
<%@page import="dao.*,model.*,dto.*,java.sql.Connection,dao.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
User user = (User) request.getSession().getAttribute("user");
if (user != null) {
	
	String topictitle =new String( request.getParameter("topictitle").getBytes("iso-8859-1"),"utf-8");
	   String replytitle="Reply: "+topictitle;
	   System.out.println("回复内容获取："+request.getParameter("replybody"));
	   String replybody = new String( request.getParameter("replybody").getBytes("iso-8859-1"),"utf-8");
			   int replyuid=user.getId().intValue();
			   String s2=request.getParameter("replytid");
			   int replytid=Integer.parseInt(s2);
			   String s3=request.getParameter("boardpagenum");
			   int boardpagenum=Integer.parseInt(s3);
			   System.out.println("doreply获取replytitle "+replytitle);
			   System.out.println("doreply获取replybody "+replybody);
			   System.out.println("doreply获取replyuid "+replyuid); 
			   System.out.println("doreply获取replytid "+replytid); 
			   
			  
			   
			   
			   
			   Reply reply =new Reply();  Topic topic=new Topic();
			   if (IsEmpty.Checkisempty(replybody)==true ) {
					request.setAttribute("msg", "请填写回复内容");
					request.getRequestDispatcher("/error.jsp").forward(request,response);
				} else {
										
					reply.setTitle(replytitle);
					reply.setBody(replybody);
					reply.setUid(replyuid);
					reply.setTid(replytid);		
					
					 Connection conn =  ConnectionFactory.getInstance().makeConnection();				
					 ReplyDao replydao=new ReplyDao();
					 replydao.save(conn, reply);	
					 
					 ReplyCheck rc =new ReplyCheck();  rc.check();
					 ArrayList<Integer> replytidlist=new ArrayList<Integer>();       
					 replytidlist=rc.getReplytidlist();
			          int replycount =0;  
			             for(int i=1;i<replytidlist.size();i++){        	  
			            	  if(replytidlist.get(i)==replytid){
			            		  replycount++;
			            	  }
			             }
			             
			             System.out.println("统计回复总数为"+replycount);
					
			             TopicCheck tc=new TopicCheck(); tc.check();    
					topic.setBid(0);
					topic.setBody(tc.getTopicbodylist().get(tc.getTopicidlist().indexOf( replytid)));
					topic.setReplyCount(replycount);
					topic.setTitle(tc.getTopictitlelist().get(tc.getTopicidlist().indexOf( replytid)));
					topic.setUid(tc.getTopicuidlist().get(tc.getTopicidlist().indexOf( replytid)));
					 System.out.println("Topic参数--------------------------------");
					 System.out.println("bid "+topic.getBid());
					 System.out.println("body "+topic.getBody());
					 System.out.println("replycount "+topic.getReplyCount());
					 System.out.println("title "+topic.getTitle());
					 System.out.println("uid "+topic.getUid());
					
						TopicDao topicdao=new TopicDao();
						topicdao.update(conn, (long)replytid, topic);
		
					 System.out.println("发表回复成功");
					 response.sendRedirect("/bbs/topicpage.jsp?tid="+replytid+"&pn="+boardpagenum)	;

				}
	
}else{
System.out.println("doreply检测到未登录  ");
request.setAttribute("msg", "请先登录");	
System.out.println("用户未登录" );
request.getRequestDispatcher("/error.jsp").forward(request,response);
} 

 

%>