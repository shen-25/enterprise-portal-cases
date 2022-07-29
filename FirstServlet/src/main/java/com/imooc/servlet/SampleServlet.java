package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet{

	    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	PrintWriter out = response.getWriter();
	    	out.println("<a href='https://www.baidu.com/'>baidu</a>");
	    	out.println(request.getMethod());
	    	out.println("<div style='background-color: red height:220px'> </div>");
	    	String name = request.getParameter("name");
	    	out.println("<h1> name: "+ name +"</h1>");
	    	String phone = request.getParameter("phone");
	    	out.println("<h1> Phone:" + phone + "</h1>");
	    	String sex = request.getParameter("sex");
	    	out.println("<h1>sex:" + sex +"</h1>");
	    	String[] sepcs = request.getParameterValues("spec");
	    	for(String str : sepcs) {
	    	out.println("<h1> spec:" + str + "</h1>");  	
	    	}
	    }
}
