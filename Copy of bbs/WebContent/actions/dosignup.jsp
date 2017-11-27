<%@page import="dao.UserDao,model.*"%>
<%@page import="model.SigninUserCheck,dto.User,java.sql.Connection,dao.ConnectionFactory"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
SigninUserCheck signinusrcheck =new SigninUserCheck();

String username = request.getParameter("username");
String password = request.getParameter("password");
String email =request.getParameter("email");

System.out.println("用户名 ==》 " + username);
System.out.println("密码 ==》 " + password);
System.out.println("Email ==》 " + email);

String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+"localhost"+":"+request.getServerPort()+path; 

String redirecturl =request.getHeader("Referer");

if (IsEmpty.Checkisempty(username)==true || IsEmpty.Checkisempty(password)==true|| IsEmpty.Checkisempty(email)==true) {
	request.setAttribute("msg", "用户名或密码或email为空");
	request.getRequestDispatcher("/error.jsp").forward(request,response);
} else {
	User user = new User();
	user.setName(username);
	user.setPassword(password);
	user.setEmail(email);
	user.setAvatarurl(basePath+"/img/defaultavatar.jpg");
	System.out.println(user.getAvatarurl());
	boolean bool = signinusrcheck.check(user);

	if (bool) {
		request.setAttribute("msg", "该用户名已存在");	
		System.out.println("用户名已存在" );
		request.getRequestDispatcher("/error.jsp").forward(request,response);
		
	} else {
		Connection conn =  ConnectionFactory.getInstance().makeConnection();
		UserDao userdao=new UserDao();
		userdao.save(conn, user);
		
		UserCheck uc=new UserCheck();
		uc.check();
		long id =uc.getUseridlist().get( uc.getUsernamelist().indexOf(user.getName()));
		user.setId(id);      System.out.println("当前注册完毕后登录用户 UserID= "+user.getId());   
		request.getSession().setAttribute("user", user);
		System.out.println("注册成功" );
		 response.sendRedirect(redirecturl);
		
	}


}

%>