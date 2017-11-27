package lesson6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sample2
 */
@WebServlet("/Sample2")
public class Sample2 extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try {
			/**
			 * 终于搞出来这个编码的问题
			 */
			String tmp=request.getParameter("cars");
			String carname=new String (tmp.getBytes("iso-8859-1"),"utf-8");
			
			response.setContentType("text/html;charset=UTF-8");
		
			 PrintWriter pw=response.getWriter();
			 pw.println("<html>\n"+"<head><title>\n"+carname+"</title></head>\n"+
					    "<body><center>\n"+
					    "<h2>\n"+carname+"</h2>\n"+
					    carname+
					    "のお買い上げありがとうございました<br/>\n"+
					    "</center></body>\n"+
					    "</html>\n"
					    		);
		}catch(Exception e){
			e.printStackTrace();
		}
	}



}
