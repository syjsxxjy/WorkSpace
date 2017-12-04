<%@page import="dao.TopicDao"%>
<%@page import="org.apache.catalina.startup.UserConfig,java.util.*,java.sql.Connection,dao.ConnectionFactory"%>
<%@ page import="dto.*,model.*,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>Topic Page -BBS</title>
<jsp:include page="head.jsp"></jsp:include> 
<%int topicid=Integer.parseInt(request.getParameter("tid"));
     int boardpage =Integer.parseInt( request.getParameter("pn")); //板块帖子页码
     
  ArrayList topictitlelist=new ArrayList();
  ArrayList topicbodylist=new ArrayList();
  ArrayList<Integer> topicuidlist=new ArrayList<Integer>();
  ArrayList<String> usravatarlist=new ArrayList<String>();
  ArrayList<Integer> topicreadcountlist=new ArrayList<Integer>();
  
  TopicCheck tc =new TopicCheck();
  UserCheck uc =new UserCheck();
  tc.check(); uc.check();
  topictitlelist=tc.getTopictitlelist();
  topicbodylist=tc.getTopicbodylist();
  topicuidlist=tc.getTopicuidlist();
  topicreadcountlist=tc.getTopicreadcountlist();
  usravatarlist=uc.getAvatarurllist();
long topicuid=topicuidlist.get(tc.getTopicidlist().indexOf(topicid));
  System.out.println("topicpage获取topicuid "+topicuid);
  System.out.println("发帖人"+  uc.getUsernamelist().get(uc.getUseridlist().indexOf((long) topicuid)));
  User user = (User) request.getSession().getAttribute("user");
  if(user!=null){
	  System.out.println("当前登录用户 "+user.getName()+"  userID="+user.getId());
  }

 if(session.getAttribute("tidmap")==null){	 Map<Integer, String> tidmap = new HashMap<Integer, String>();
	 session.setAttribute("tidmap", tidmap);
 }
 Map<Integer, String> tidmap = (HashMap)session.getAttribute("tidmap");
if(tidmap.containsKey(topicid)==false){
	long readcount = (long)topicreadcountlist.get(tc.getTopicidlist().indexOf(topicid))+1;
	Topic topic =new Topic();  topic.setReadcount(readcount);    topic.setId((long)topicid);
	Connection conn =  ConnectionFactory.getInstance().makeConnection();
	TopicDao topicdao=new TopicDao();  topicdao.updatereadcount(conn, topic);

}
 tidmap.put(topicid, "exist");
 System.out.println("------------------------------访问人数="+topicreadcountlist.get(tc.getTopicidlist().indexOf(topicid))+"----------");
  
%>

<div class="container pagebody-top">
    <div class="row">
    <div class="col-md-12 ">
        <ul class="breadcrumb " contenteditable="false">
            <li><a href="index.jsp">Homepage</a> <span class="divider"></span></li>
            <li><a href="boardpage.jsp?pn=1">BoardName</a> <span class="divider"></span></li>
            <li class="active">Topic Name</li>
        </ul>



         <h2 ><a href="#" class="button button-rounded button-width-small button-icon-txt-large">Category</a> <%=topictitlelist.get(tc.getTopicidlist().indexOf(topicid)) %> 
                <small> At 2016-12-23 23:45</small>                                                                                                                   <% System.out.println("Topic title is "+topictitlelist.get(tc.getTopicidlist().indexOf(topicid))); %>
            </h2>

        <table class="table table-bordered table-responsive ">
            <thead>
            <tr>
                
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="col-md-2 " >

        <img src="<%=usravatarlist.get(uc.getUseridlist().indexOf(topicuid)) %>"  class="IMG-ROUNDED img-thumbnail center-block img-responsive" alt="Responsive image ">
                        <div style="margin-top: 10px    "></div><%System.out.println("Topic useravatarurl is "+usravatarlist.get(uc.getUseridlist().indexOf(topicuid)) ); %>
                        <a href="#" class="button   button-border button-small button-block button-rounded " style="font-size: large"><%= uc.getUsernamelist().get(uc.getUseridlist().indexOf(topicuid))%></a>

                        <div style="margin-top: 10px;" class="">
                            <button type="button" class="btn btn-primary btn-xs btn-label">Lv1</button>
                            <button type="button" class="btn btn-success btn-xs btn-label">Beginer <li class="icon-food"></li></button>
                            <button type="button" class="btn btn-danger btn-xs btn-label">Admin</button>
                            <button type="button" class="btn btn-info btn-xs btn-label">Exp:23</button>
                            <button type="button" class="btn btn-danger btn-xs btn-label">BoardMaster</button>
                            <button type="button" class="btn btn-success btn-xs btn-label">OtherBadges</button>
                        </div>            
                </td>
                <td>
                    <table  class="table table-responsive table-bordered table-condensed">
                            <tr>
                                <td>
                                    <button type="button" class="btn btn-inverse btn-xs">1st Floor</button>
                                    <%if (user!=null && user.getId()==topicuid){%>
                                    	
                                    	 <a href="/bbs/topicedit.jsp?tid=<%=topicid %>&pn=<%=boardpage %>" type="button" class="btn btn-default btn-xs" style="margin-left: 15px">Edit <span class="icon-pencil"></span></button>
                                    <a  href="/bbs/actions/deletetopic.jsp?tid=<%=topicid %>&pn=<%=boardpage %>" type="button" class="btn btn-default btn-xs">Delete <span class="icon-trash"></span></a>
                                    <button class="button button-border button-primary button-pill button-tiny ">Share <span class="icon-share-alt"></span></button>
                                    <%	}else{%>
                                                                       
                                    <button class="button button-border button-primary button-pill button-tiny"  style="margin-left: 15px ">Share <span class="icon-share-alt"></span></button>
                                      <%} %>
                                </td>
                            </tr>
                        </table>

                         <p>
                             <%=topicbodylist.get(tc.getTopicidlist().indexOf(topicid)) %>
                         </p>

                        <div style="margin-top: 200px;"></div>

                        <div class="signature">
                            Signature
                        </div>
                </td>
            </tr>
           </tbody>

            <%
             ReplyCheck rc =new ReplyCheck();  rc.check();
             ArrayList<String> replybodylist=new ArrayList<String>();
             ArrayList<Integer> replytidlist=new ArrayList<Integer>();
             ArrayList<Integer> replyuidlist=new ArrayList<Integer>();             
             replybodylist=rc.getReplybodylist();
             replyuidlist=rc.getReplyuidlist();
             replytidlist=rc.getReplytidlist();
             
             int replycount =0;  
             for(int i=1;i<replytidlist.size();i++){        	  
            	  if(replytidlist.get(i)==topicid){
            		  replycount++;
            	  }
             }
             
             System.out.println("当前帖子topicid="+topicid+" 回复数量获取值 "+replycount);
             
             if(replycount==0){%>
            	                     </table>
  
  <nav class="text-center">
                            <ul class="pagination  pagination-sm">
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">6</a></li>
                                <li><a href="#">7</a></li>
                                <li><a href="#">8</a></li>
                                <li><a href="#">9</a></li>
                                <li>
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
  
  
            <div >
                <div class="well">

                    <form class="" action="<%=request.getContextPath()%>/actions/doreply.jsp" method="post">
                        <fieldset>
                            <div class="form-group" >
                                <div>
                                    <textarea class="form-control " rows="5"  placeholder="Input text here" style="" name="replybody"></textarea>
                                </div>
                            </div>
                           <input type="hidden"  name="topictitle" value=<%=topictitlelist.get(tc.getTopicidlist().indexOf(topicid)) %>>
                           <input type="hidden"  name="replytid" value=<%=topicid %>>
                           <input type="hidden"  name="boardpagenum" value=<%=boardpage %>>
                            <button type="submit" class="btn btn-warning">Reply</button>
                        </fieldset>
                    </form>
                </div>
            </div>
    </div>
    </div>
    </div>
            	 
            <% }else{%>
            
            
                  
                	      <thead>
                <tr>
                    <th class=" text-right">
                        Replies：<%=replycount %>
                    </th>
                    <th class=""></th>
                </tr>
                </thead>
            
            
            <tbody>

            
                              
                   <%  int floorcount=1; 
                   for(int j=1;j<replytidlist.size();j++){    
            	             if(replytidlist.get(j)==topicid){ 
            	            	 floorcount++;
            		            System.out.println("replybodylistget="+replybodylist.get(j));  
            		            int thisreplyid=rc.getReplyidlist().get(j);
            		            %>
            		            <tr>
                <td>

                <img src="<%=uc.getAvatarurllist().get(uc.getUseridlist().indexOf((long) replyuidlist.get(j))) %>"  class="IMG-ROUNDED img-thumbnail center-block img-responsive" alt="Responsive image ">
                        <div style="margin-top: 10px    "></div>
                        <%System.out.println(uc.getAvatarurllist().get(uc.getUseridlist().indexOf((long) replyuidlist.get(j)))); %>
                        <a href="#" class="button   button-border button-small button-block button-rounded" style="font-size: large"><%=uc.getUsernamelist().get(uc.getUseridlist().indexOf((long) replyuidlist.get(j)))%></a>

                        <div style="margin-top: 10px;" class="">
                            <button type="button" class="btn btn-primary btn-xs btn-label">Lv1</button>
                            <button type="button" class="btn btn-success btn-xs btn-label">Beginer <li class="icon-food"></li></button>
                            <button type="button" class="btn btn-danger btn-xs btn-label">Admin</button>
                            <button type="button" class="btn btn-info btn-xs btn-label">Exp:23</button>
                            <button type="button" class="btn btn-danger btn-xs btn-label">BoardMaster</button>
                            <button type="button" class="btn btn-success btn-xs btn-label">OtherBadges</button>
                        </div>


                </td>
                <td>
             <table  class="table table-responsive table-bordered table-condensed">
                            <tr>
                                <td>
                                    <button type="button" class="btn btn-inverse btn-xs">Floor: <%=floorcount %></button>
                                    <%if (user!=null && user.getId()==(long) replyuidlist.get(j)){%>
                                    
  <a type="button"   href="/bbs/replyedit.jsp?rid=<%=thisreplyid %>&pn=<%=boardpage %>&tid=<%=topicid %>"  class="btn btn-default btn-xs" style="margin-left: 15px">Edit <span class="icon-pencil"></span></a>
                                    <button type="button" class="btn btn-default btn-xs">Delete <span class="icon-trash"></span></button>
                                    <button class="button button-border button-primary button-pill button-tiny ">Share <span class="icon-share-alt"></span></button>
                                    
                                        <%}  else{ %>
                                         <button class="button button-border button-primary button-pill button-tiny " style="margin-left: 15px">Share <span class="icon-share-alt"></span></button>            
                                         
                                         <%} %>
                                         </div>
                                </td>
                            </tr>
                        </table>


                    <p>
            		             <%=replybodylist.get(j)  %>
            		           
                    </p>

                    
                    <div style="margin-top: 200px;"></div>

                        <div class="signature">
                            Signature <button type="button" class="btn btn-default btn-xs  "   style="margin-left: 15px"> at 1230-23-23 23:34:23</button>
                        </div>

                </td>
            </tr>        
            	       <%    }
                      } %>

            </tbody>
        </table>
               
          
            
<nav class="text-center">
                            <ul class="pagination  pagination-sm">
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">6</a></li>
                                <li><a href="#">7</a></li>
                                <li><a href="#">8</a></li>
                                <li><a href="#">9</a></li>
                                <li>
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
  
            <div>
                <div class="well">
                    <form name="replyform" class="" action="<%=request.getContextPath()%>/actions/doreply.jsp" method="post">
                        <fieldset>
                            <div class="form-group" >
                                <div>
                                    <textarea   class="form-control " rows="5"  placeholder="Input text here" style="" name="replybody" ></textarea>
                                </div>
                            </div>
                           <input type="hidden"  name="topictitle" value=<%=topictitlelist.get(tc.getTopicidlist().indexOf(topicid)) %>>
                           <input type="hidden"  name="replytid" value=<%=topicid%>>
                           <input type="hidden"  name="boardpagenum" value=<%=boardpage %>>
                            <button type="submit" class="btn btn-warning">Reply</button>
                        </fieldset>
                    </form>
                </div>
            </div>

    </div>
    </div>
    </div>
            
           <%  }%>
             
                        
            


<jsp:include page="foot.jsp"></jsp:include>