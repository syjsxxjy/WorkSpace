package lesson6_2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;


/**
 * Servlet Filter implementation class SampleFilter
 */
//@WebFilter("/SampleFilter")
public class SampleFilter implements Filter {

    /**
     * Default constructor. 
     */
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter pw=response.getWriter();
		pw.println("<html>\n"+
		            "<head><title>SampleFilter</title></head>\n"+
				    "<body><center>\n"+
				    "<h2>こんにちわ</h2>"+
				    "<hr/>\n");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		pw.println("<hr/>ありがとうございました.\n"+
		        "</center></body>\n"+
				"</html>"
				);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig)  {
		// TODO Auto-generated method stub
	}

}
