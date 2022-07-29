package com.imooc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ContentTypeServlet
 */
public class ContentTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//浏览器以文件下载的方式解析
		//response.setContentType("application/x-msdownload;charset=utf-8"); 
		request.setCharacterEncoding("utf-8");
		String output = "<h1> <a href='https://www.baidu.com'><span>百度</span></a></h1>";
		 response.getWriter().println(output);
	}

}
