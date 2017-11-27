package lesson6;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Sample4")
public class Sample4 extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		
		try {
			HttpSession hs =request.getSession(true);
			Integer cn =(Integer) hs.getAttribute("count");
			Date dt=(Date) hs.getAttribute("date");
			
			String str1,str2;
			
			if(cn==null){
				cn = new Integer(1);
				dt=new Date();
				str1="はじめてのおこしですね";
				str2="";
				}else{
					cn = new Integer(cn.intValue()+1);
					str1=cn+"回目のおこしですね";
					str2="（前回:"+dt+"）";
					dt=new Date();
				}
			
			hs.setAttribute("count", cn);
			hs.setAttribute("date", dt);
		
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter pw=response.getWriter();
			pw.println("<html>\n"+
			            "<head><title>Sample4</title></head>\n"+
					    "<body><center>\n"+
					    "<h2>ようこそ</h2>"+
					    "<hr/>\n"+
					    str1+"<br/>\n"+
					    str2+"<br/>\n"+
					    "お選びください<br/>\n"+
					    "<br/>\n"+
					    "<a href=\"../car1.html\">乗用車</a><br/>\n"+
					    "<a href=\"../car2.html\">トラック</a><br/>\n"+
					    "<a href=\"../car3.html\">オープンカー</a><br/>\n"+
					    "</center></body>\n"+
					    "</html>\n"
					    		);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
