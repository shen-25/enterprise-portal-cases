package com.imooc.servlet;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class FirstServlet  extends HttpServlet{
	
	public FirstServlet() {
		System.out.println("正在创建FirstServlet对象");
	}

	

	@Override
	public void init(ServletConfig config) throws ServletException {
	  System.out.println("正在初始化FirstSevlet对象");
	}



	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		arg1.getWriter().print("nihao");
		System.out.println("FirstServlet工程你好啊");
	}



	@Override
	public void destroy() {
	   System.out.println("正在销毁FirstServlet对象");
	}
      
}
