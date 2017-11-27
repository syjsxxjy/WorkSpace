package lesson6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sample3
 */
@WebServlet("/Sample3")
public class Sample3 extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String tmp = request.getParameter("cars");
			String carname=new String (tmp.getBytes("iso-8859-1"),"UTF-8");
			
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter pw=response.getWriter();
			if(carname.length()!=0){
				 pw.println(
						    "<html>\n"+
						    
						    "<head><title>\n"+carname+"</title></head>\n"+
						    "<body><center>\n"+
						    "<h2>\n"+carname+"</h2>\n"+
						    carname+
						    " have been bought ,Thank you for shopping.及惁中奻仆丐曰互午丹仍介中引仄凶<br/>\n"+
						    "</center></body>\n"+
						    "</html>\n"
						    		);
			}else{
				pw.println("<html>\n"+"<head><title>Error</title></head>\n"+
					    "<body><center>\n"+
					    "<h2>Error</h2>\n"+
					    "Please fill the blanks�蹅忖楔々砟壑竣�<br/>\n"+
					    "</center></body>\n"+
					    "</html>\n");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
