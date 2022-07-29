package com.imooc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class CreateServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		 System.out.println("正在创建数据库");
	}

}
