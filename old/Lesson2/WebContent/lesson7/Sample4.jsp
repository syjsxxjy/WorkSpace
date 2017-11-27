<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String tmp=request.getParameter("cars");
  String carname = new String(tmp.getBytes("iso-8859-1"),"utf-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=carname %></title>
</head>
<body>
<center>
<h2><%=carname %></h2>
<%=carname %>の買い上げありがとうございました。<br>
<br>
<jsp:include page="company.html" flush="true"></jsp:include>
</center>
</body>
</html>