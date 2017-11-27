<%@page import="dao.TopicDao"%>
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
<%User user = (User) request.getSession().getAttribute("user");
long tid=Long.parseLong(request.getParameter("tid"));    
int boardpagenum =Integer.parseInt( request.getParameter("pn"));
long uid=0;
if (user != null) {
	uid=user.getId();
	
	 TopicCheck tc =new TopicCheck();
	   tc.check();  
	   int topicuid=tc.getTopicuidlist() .get(tc.getTopicidlist().indexOf((int)tid));
	  System.out.println("topicedit检测topicUID="+topicuid+" topicedit检测当前登录用户ID="+user.getId()+" 当前登录用户名="+user.getName());
	System.out.println("topicedit获取tid="+tid);
	 if(user.getId()!=topicuid){
		   request.setAttribute("msg", "不能编辑,发帖ID不一致");
			request.getRequestDispatcher("/error.jsp").forward(request,response);
	 }
	
}else{
System.out.println("newtopic检测到未登录  ");
request.setAttribute("msg", "请先登录");	
System.out.println("用户未登录" );
request.getRequestDispatcher("/error.jsp").forward(request,response);
} 


     TopicDao topicdao=new TopicDao();
     TopicCheckbyID tcbyid= new TopicCheckbyID();
     tcbyid.check(tid);
   
%>

<div class="container pagebody-top ">
    <div class="row">
        <div class="col-md-12 ">
            <ul class="breadcrumb" contenteditable="false">
                <li><a href="index.jsp">Homepage</a> <span class="divider"></span></li>
                <li><a href="boardpage.jsp?pn=1">BoardName</a> <span class="divider"></span></li>
                <li class="active">Edit topic</li>
            </ul>


            <form class="form"  action="<%=request.getContextPath()%>/actions/dotopicedit.jsp?tid=<%=tid %>"  method="post">
                <fieldset>
                    <div id="legend" class="">
                        <legend class="">Edit Topic</legend>
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

                        <input name="topictitle" type="text" class="form-control " aria-label="..." placeholder="Input title..." style="width: 45%"
                        value="<%=tcbyid.getTopictitlelist().get(1) %>">
                        <div class="col-md-5 ">
                        </div>
                    </div>

                    <br>
                    <div class="form-group" >

                        <textarea name="topicbody"  class="form-control" rows="15"  placeholder="Input text..." style="width: 90%" ><%=tcbyid.getTopicbodylist().get(1) %></textarea>
                    </div>  
                     
                     <input type="hidden" name="topicuid" value=<%=uid %>>
                        <input type="hidden" name="boardpagenum" value=<%=boardpagenum %>>
                    <button type="submit" class="btn btn-warning">Submit</button>
                </fieldset>
            </form>

        </div>
    </div>

</div>




<div class="panel-footer text-muted" style="margin-top: 30px">
    <div class="text-center">
        <h4 >Copyright Here  </h4>
        <p><a>Link here</a></p>
        <p> Copyright text </p>

    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/dropdowns-enhancement.js"></script>
</div>

</body>
</html>