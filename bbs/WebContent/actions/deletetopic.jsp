<%@page import="java.util.ArrayList"%>
<%@page import="dao.*,model.*,dto.*,java.sql.Connection,dao.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
User user = (User) request.getSession().getAttribute("user");
if (user != null) {

   int topicid =Integer.parseInt( request.getParameter("tid"));
   int boardpage =Integer.parseInt( request.getParameter("pn"));
   ReplyCheck rc=new ReplyCheck();
   TopicCheck tc =new TopicCheck();
   tc.check();  rc.check();
   int topicuid=tc.getTopicuidlist() .get(tc.getTopicidlist().indexOf(topicid));
   System.out.println("deletetopic检测topicUID="+topicuid+" deletetopic检测当前登录用户ID="+user.getId()+" 当前登录用户名="+user.getName());
   
   if(user.getId()!=topicuid){
	   request.setAttribute("msg", "删除失败,发帖ID不一致");
		request.getRequestDispatcher("/error.jsp").forward(request,response);
  }else{
	  Topic topic=new Topic();
	  topic.setId((long)topicid);
	  
	  Connection conn =  ConnectionFactory.getInstance().makeConnection();
	  TopicDao topicdao=new TopicDao();
	  topicdao.delete(conn, topic);
	  System.out.println("帖子删除成功");
	  
	  Reply reply =new Reply();
	  reply.setTid(topicid);
	  ReplyDao replydao=new ReplyDao();
	  replydao.deletebytid(conn, reply);
	  System.out.println("回复删除成功");
	  /* ArrayList<Long> thisreplyid=new ArrayList<Long>();
	  for(int i=1;i<rc.getReplytidlist().size();i++){
		  System.out.println("Replytidlist.size()="+rc.getReplytidlist().size());
		  System.out.println("i="+i);
		  if(rc.getReplytidlist().get(i)==topicid){
			  thisreplyid.add( (long)rc.getReplyidlist().get(i));
			  System.out.println("thisreplyid="+thisreplyid);
			  
		  }
	  }
	  
	  for(int i=0;i<thisreplyid.size();i++){
		  Reply reply =new Reply();
		  reply.setId(thisreplyid.get(i));
		  System.out.println(thisreplyid.get(i));
		  ReplyDao replydao=new ReplyDao();
		  replydao.delete(conn, reply);
		  System.out.println("该帖回复删除成功，回复ID="+thisreplyid);
	  } */
	  
	  response.sendRedirect("/bbs/boardpage.jsp?pn="+ boardpage);
	
  }
   
   }else{
	   request.setAttribute("msg", "删除失败，请先登录");
		request.getRequestDispatcher("/error.jsp").forward(request,response);
   }
   %>