package com.zs.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Second  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String html = "<h1 style='color:red'>" + name + "</h1>";
		PrintWriter out = response.getWriter();
		out.println(html);
		System.out.println("你好服务器");
	}
	

}
