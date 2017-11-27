<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
request.getSession().invalidate();
String refer=request.getHeader("Referer");
System.out.println("登出操作返回链接 "+refer);
response.sendRedirect(refer);

%>