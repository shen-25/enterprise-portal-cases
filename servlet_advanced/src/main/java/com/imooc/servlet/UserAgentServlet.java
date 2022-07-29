package com.imooc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserAgentServlet
 */

public class UserAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	       String userAgent = request.getHeader("User-Agent");
	       response.setContentType("text/html;charset=utf-8");
	       response.getWriter().println(userAgent);
	       if(userAgent.indexOf("iPhone") == -1) {
	    	   response.getWriter().println("<h1>这不是iPone客户端</h1>");
	       } else {
	    	   response.getWriter().println("<h1>这是iPone客户端</h1>");
	       }
	}

}
