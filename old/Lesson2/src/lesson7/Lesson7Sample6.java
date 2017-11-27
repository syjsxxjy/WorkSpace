package lesson7;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lesson7Sample6
 */
@WebServlet("/Lesson7Sample6")
public class Lesson7Sample6 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String tmp =request.getParameter("cars");
			String carname=new String(tmp.getBytes("iso-8859-1"),"utf-8");
			
			CarBean cb=new CarBean();
			cb.setCarname(carname);
			cb.makeCardata();
			
			request.setAttribute("cb", cb);
			
			ServletContext sc=getServletContext();
			
			if(carname.length()!=0){
				sc.getRequestDispatcher("/lesson7/Sample6.jsp").forward(request, response);
			}else{
				sc.getRequestDispatcher("/lesson7/error.html").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
