package lesson6;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sample1
 */
@WebServlet("/Sample1")
public class Sample1 extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			//•≥•Û•∆•Û•ƒ•ø•§•◊§Œ‘O∂®
			response.setContentType("text/html; charset=Shift_JIS");
			
			Date dt =new Date();
		    PrintWriter pw=response.getWriter();
		    pw.println("<html>\n"+"<head><title>•µ•Û•◊•Î</title></head>\n"+
		    "<body><center>\n"+
		    "<h2>§Ë§¶§≥§Ω</h2>"+
		    "<hr/>\n"+
		    "ΩÒ"+dt+"§«§π°£<br/>\n"+
		    "§™ﬂx§”§Ø§¿§µ§§<br/>\n"+
		    "<br/>\n"+
		    "<a href=\"..car1.html\">Å\”√‹á</a><br/>\n"+
		    "<a href=\"..car2.html\">•»•È•√•Ø</a><br/>\n"+
		    "<a href=\"..car3.html\">•™©`•◊•Û•´©`</a><br/>\n"+
		    "</center></body>\n"+
		    "</html>\n"
		    		);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
