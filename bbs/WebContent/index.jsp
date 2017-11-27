<%@ page import="dto.*,model.BoardSearch,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>Index -BBS</title>
<jsp:include page="head.jsp"></jsp:include>

<!-- 导航条 -->
<div class="container pagebody-top">
     <div>
        <ul class="breadcrumb " contenteditable="false">
            <li class="active">Homepage</li>
        </ul>
     </div>


    <!--板块1-->
    <div class="row">
        <div class="col-md-12">
        <% 
          BoardSearch bs =new BoardSearch();
          ArrayList lst= new ArrayList();		
          bs.check();
          lst=bs.getBoardnamelist();
          for (int i=0;i<bs.getBoardamount();i++){%>   	  
        	  <div class="media well">
                <a href="boardpage.jsp?pn=1" class="pull-left"><span class="media-object icon-picture icon-3x" alt='' /></span></a>
                <div class="media-body">
                    <h4 class="media-heading">
                        <a href="boardpage.jsp?pn=1"><%=lst.get(i) %></a>
                    </h4> 板块简介板块简介板块简介板块简介板块简介板块简介板块简介板块简介板块简介板块简介板块简介板块简
                </div>
            </div>
         	  
          <%}%>
              </div>
    </div> 
    <!--板块1-->


<jsp:include page="foot.jsp"></jsp:include>
    
    
