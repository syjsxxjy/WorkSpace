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
			//コンテンツタイプの�O協
			response.setContentType("text/html; charset=Shift_JIS");
			
			Date dt =new Date();
		    PrintWriter pw=response.getWriter();
		    pw.println("<html>\n"+"<head><title>サンプル</title></head>\n"+
		    "<body><center>\n"+
		    "<h2>ようこそ</h2>"+
		    "<hr/>\n"+
		    "書"+dt+"です。<br/>\n"+
		    "お�xびください<br/>\n"+
		    "<br/>\n"+
		    "<a href=\"..car1.html\">�\喘��</a><br/>\n"+
		    "<a href=\"..car2.html\">トラック</a><br/>\n"+
		    "<a href=\"..car3.html\">オ�`プンカ�`</a><br/>\n"+
		    "</center></body>\n"+
		    "</html>\n"
		    		);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
