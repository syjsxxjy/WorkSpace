package lesson6;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sampe6
 */
@WebServlet("/Sample6")
public class Sample6 extends HttpServlet {
	Object ServletContext;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String carname= request.getParameter("cars");
			 
			ServletContext sc = getServletContext();
			
			if(carname.length()!=0){
				sc.getRequestDispatcher("/Sample2").forward(request, response);
			}else{
				sc.getRequestDispatcher("/error.html").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
