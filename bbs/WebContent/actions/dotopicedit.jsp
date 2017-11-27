<%@page import="java.util.ArrayList"%>
<%@page import="dao.TopicDao,model.*,dto.*,java.sql.Connection,dao.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
User user = (User) request.getSession().getAttribute("user");
int boardpagenum =Integer.parseInt( request.getParameter("boardpagenum")); //板块帖子页码
if (user != null) {

   String topictitle =new String( request.getParameter("topictitle").getBytes("iso-8859-1"),"utf-8");
   String topicbody = new String( request.getParameter("topicbody").getBytes("iso-8859-1"),"utf-8");
   String s =request.getParameter("topicuid");
   long topicuid=Long.parseLong(s);
   String s4=request.getParameter("tid");
   long tid=(long)Integer.parseInt(s4);
   System.out.println("dotopicedit获取topicuid "+topicuid);
   System.out.println("dotopicedit获取topicbody "+topicbody);
   System.out.println("dotopicedit获取topictitle "+topictitle); 
   
   
   Topic topic =new Topic();
   if (IsEmpty.Checkisempty(topictitle)==true|| IsEmpty.Checkisempty(topicbody )==true || s==null) {
		request.setAttribute("msg", "标题或者内容为空");
		request.getRequestDispatcher("/error.jsp").forward(request,response);
	} else {
		
		
		topic.setTitle(topictitle);
		topic.setBody(topicbody);
		topic.setUid(topicuid);
		topic.setReplyCount(0);
		topic.setBid(1);
		
		 Connection conn =  ConnectionFactory.getInstance().makeConnection();
		 TopicDao topicdao=new TopicDao();
		 topicdao.update(conn, tid, topic);
	
		 response.sendRedirect("/bbs/topicpage.jsp?tid="+tid+"&pn="+boardpagenum);
     
		 
		

	}
   
}else{
	System.out.println("dopost检测到未登录  ");
request.setAttribute("msg", "请先登录");	
System.out.println("用户未登录" );
request.getRequestDispatcher("/error.jsp").forward(request,response);
} 
    










%>