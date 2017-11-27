<%@page import="model.*,dto.User"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String redirecturl =request.getHeader("Referer");
User userNow = (User) request.getSession().getAttribute("user");
if (userNow != null) {
	request.setAttribute("msg", "您已登录，请不要重复登录");
	request.getRequestDispatcher("/error.jsp").forward(request,response);
}else{

LoginUserCheck loginusrcheck =new LoginUserCheck();

String username = request.getParameter("username");
String password = request.getParameter("password");


System.out.println("用户名 ==》 " + username);
System.out.println("密码 ==》 " + password);
System.out.println("重定向链接  " + redirecturl);



if (IsEmpty.Checkisempty(username)==true || IsEmpty.Checkisempty(password)==true) {
	request.setAttribute("msg", "用户名或密码为空");
	request.getRequestDispatcher("/error.jsp").forward(request,response);
} else {
	User user = new User();
	user.setName(username);
	user.setPassword(password);
	
	boolean bool = loginusrcheck.check(user);

	if (bool) {
		UserCheck uc=new UserCheck();
		uc.check();
		long id =uc.getUseridlist().get( uc.getUsernamelist().indexOf(user.getName()));
		
		for(int i=0;i<uc.getUseridlist().size();i++){
			System.out.println("遍历userID list "+uc.getUseridlist().get(i));
		}	
		
		for(int i=0;i<uc.getUsernamelist().size();i++){
			System.out.println("遍历username list "+uc.getUsernamelist().get(i));
		}	
		user.setId(id);    System.out.println("UserID= "+user.getId());   
		System.out.println("index of user.getname() at usernamelist: "+ uc.getUsernamelist().indexOf(user.getName()));
		 request.getSession().setAttribute("user", user);
		 System.out.println("登录成功");
		 response.sendRedirect(redirecturl);
		 System.out.println("返回链接"+redirecturl);
		 
		
	} else {
		request.setAttribute("msg", "用户名或密码错误");
		request.getRequestDispatcher("/error.jsp").forward(request,response);
		
	}
}
}

%>