<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <jsp:useBean id="cb" class="lesson7.CarBean" scope="request"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sample6</title>
</head>
<body>
<center>
<h2>御礼</h2>
<jsp:getProperty name="cb" property="cardata"/>
の買い上げありがとうございました<br/>s
</center>
</body>
</html>