package lesson6;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Sample5")
public class Sample5 extends HttpServlet {
	Object ServletContext;

	/**
	 * @param  
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String carname= request.getParameter("cars");
			 
			ServletContext sc = getServletContext();
			
			if(carname.length()!=0){
				sc.getRequestDispatcher("/thanks.html").forward(request, response);
			}else{
				sc.getRequestDispatcher("/error.html").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
