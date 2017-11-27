package lesson6;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Sample8
 */
@WebServlet("/Sample8")
public class Sample8 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try{
		 response.setContentType("text/html; charset=utf-8");
		 
		 PrintWriter pw = response.getWriter();
   	     pw.println(
   	    		"<html>\n"+
   			    "<head><title>Authorize</title></head>\n"+
   				"<body><center>\n"+
   			    "<h2>おめでとうございます</h2>"+
   				"<hr/>\n"+
   	            "�J�^に撹孔しました。<br/>\n"+
				    "お�xびください<br/>\n"+
				    "<br/>\n"+
				    "<a href=\"../car1.html\">�\喘��</a><br/>\n"+
				    "<a href=\"../car2.html\">トラック</a><br/>\n"+
				    "<a href=\"../car3.html\">オ�`プンカ�`</a><br/>\n"+
				    "</center></body>\n"+
					"</html>\n"
				    		);
		 	}catch(Exception e) {
		 		e.printStackTrace();
		 	}
		 		
		 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
