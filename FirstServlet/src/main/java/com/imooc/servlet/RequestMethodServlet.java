package com.imooc.servlet;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestMethodServlet extends HttpServlet{
	//处理get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		try {
			response.getWriter().println("<h1 style='color:green'>" + name + "</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   //处理post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name");
		try {
			response.getWriter().println("<h1 style='color:red'>" + name +"</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
