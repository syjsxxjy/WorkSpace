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
   System.out.println("dopost获取topicuid "+topicuid);
   System.out.println("dopost获取topicbody "+topicbody);
   System.out.println("dopost获取topictitle "+topictitle); 
   
   
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
		 topicdao.save(conn, topic);
		 if(topicdao.getPrimarykeysave().next()){  
     	    System.out.println("获取插入数据库获取的主键（ID）值： "+topicdao.getPrimarykeysave().getInt(1));  
     	   int thistopicid=topicdao.getPrimarykeysave().getInt(1);
     	  System.out.println("获取新帖ID"+thistopicid);
     	 System.out.println("帖子发表成功");
		 response.sendRedirect("/bbs/topicpage.jsp?tid="+thistopicid+"&pn="+boardpagenum);
     	    }  
		 
		

	}
   
}else{
	System.out.println("dopost检测到未登录  ");
request.setAttribute("msg", "请先登录");	
System.out.println("用户未登录" );
request.getRequestDispatcher("/error.jsp").forward(request,response);
} 
    










%>