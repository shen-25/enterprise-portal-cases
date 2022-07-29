package com.imooc.servlet;

import java.util.ArrayList;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/anno")
public class AnnotationServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println("I'm annotation servlet!提示");
		response.getWriter().println(request.getServletContext().getInitParameter("copyright"));
		
	}
	
   
	
}
