<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<div id="message">
操作失败.<br/>

错误提示：
<%
String redirecturl =request.getHeader("Referer");
	Object obj = request.getAttribute("msg");
	if(obj != null){
		out.println(obj.toString());
	}else{
		out.println("无");
	}
%>

返回上一个页面：<a href="<%=redirecturl%>"><%=redirecturl%></a>

</div>

</body>
</html>