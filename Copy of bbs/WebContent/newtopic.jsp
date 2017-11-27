<%@ page import="dto.*,java.util.*,model.*"%>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>New Topic -BBS</title>
<jsp:include page="head.jsp"></jsp:include>
<%
User user = (User) request.getSession().getAttribute("user");
int boardpagenum =Integer.parseInt( request.getParameter("pn"));
ArrayList<String> topictitlelist = new ArrayList<String>();
TopicCheck tc =new TopicCheck();
tc.check();
topictitlelist=tc.getTopictitlelist();
ArrayList<Integer> topicidlist=tc.getTopicidlist();
long topicuid=0;
if (user != null) {
	        topicuid=user.getId();
	        System.out.println("newtopid获取发帖uid   "+topicuid);
}else{
	System.out.println("newtopic检测到未登录  ");
	request.setAttribute("msg", "请先登录");	
	System.out.println("用户未登录" );
	request.getRequestDispatcher("/error.jsp").forward(request,response);
} 
%>

    <div class="container pagebody-top ">
        <div class="row">
            <div class="col-md-12">
                <ul class="breadcrumb" contenteditable="false">
                    <li><a href="index.jsp">Homepage</a> <span class="divider"></span></li>
                    <li><a href="boardpage.jsp?pn=1">BoardName</a> <span class="divider"></span></li>
                    <li class="active">New topic</li>
                </ul>


                <form class="form" action="<%=request.getContextPath()%>/actions/dopost.jsp" method="post">
                    <fieldset>
                        <div id="legend" class="">
                            <legend class="">New Topic</legend>
                        </div>

                      <div class="input-group">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Category <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><input type="radio" id="ID" name="NAME" value="VALUE"><label for="ID">Category</label></li>
                                <li><input type="radio" id="ID2" name="NAME" value="VALUE"><label for="ID2">Category2</label></li>
                                <li><input type="radio" id="ID3" name="NAME" value="VALUE"><label for="ID3">Category3</label></li>
                            </ul>
                        </div><!-- /btn-group -->

                        <input name="topictitle" type="text" class="form-control " aria-label="..." placeholder="New topic..." style="width: 45%">
                        <div class="col-md-5 ">
                        </div>
                    </div>
                        <br>
                        <div class="form-group" >

                                <textarea name="topicbody" class="form-control" rows="15"  placeholder="Input text here" style="width: 90%"></textarea>
                         </div>
                        
                        <input type="hidden" name="topicuid" value=<%=topicuid %>>
                        <input type="hidden" name="boardpagenum" value=<%=boardpagenum %>>
                        <input type="submit" class="btn btn-warning" value="Post"></button>
                        
                    </fieldset>
                </form>

            </div>
        </div>
    </div>


<jsp:include page="foot.jsp"></jsp:include>