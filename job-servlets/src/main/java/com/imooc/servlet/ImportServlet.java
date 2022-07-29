package com.imooc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class ImportServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		 System.out.println("正在导入数据");
	}
}
