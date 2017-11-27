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
			   String s4=request.getParameter("rid");
			   long rid=(long)Integer.parseInt(s4);
			   System.out.println("doreplyedit获取replytitle "+replytitle);
			   System.out.println("doreplyedit获取replybody "+replybody);
			   System.out.println("doreplyedit获取replyuid "+replyuid); 
			   System.out.println("doreplyedit获取replytid "+replytid); 
			   System.out.println("doreplyedit获取rid "+rid); 
			   
			  
			   
			   
			   
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
					 replydao.update(conn, rid, reply);
					 
					
					 response.sendRedirect("/bbs/topicpage.jsp?tid="+replytid+"&pn="+boardpagenum)	;

				}
	
}else{
System.out.println("doreply检测到未登录  ");
request.setAttribute("msg", "请先登录");	
System.out.println("用户未登录" );
request.getRequestDispatcher("/error.jsp").forward(request,response);
} 

 

%>