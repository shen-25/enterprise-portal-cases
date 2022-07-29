package com.imooc.servlet.pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Pattern
 */
public class Pattern extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pattern() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       String url = (request.getRequestURL().toString());
	     
	       request.setAttribute("score",44);
	       String id = url.substring(url.lastIndexOf("/") + 1);
	       response.getWriter().println("<h1>"+ id +"</h1>");
//	       HttpSession session = request.getSession();
//	       Student stu = new Student("子墨","200001414021");
//	     
//	          session.setAttribute("student", stu);
           request.getRequestDispatcher("/core.jsp").forward(request, response);
	       
	}

}
