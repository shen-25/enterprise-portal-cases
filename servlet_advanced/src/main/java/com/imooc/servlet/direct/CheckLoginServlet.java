package com.imooc.servlet.direct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CheckLoginServlet
 */
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("用户登录成功");
		//对于默认get发送请求
		//System.out.println(request.getParameter("name"));
		//实现了请求转发的功能
		//这里的请求需要同一个请求才能发送属性。
		//响应charset:utf-8;
		response.setContentType("text/html;charset:utf-8");
		request.setAttribute("username", "admi顶顶顶到达付款了n");
		request.getRequestDispatcher("/direct/index").forward(request, response);
		 
		//重定向需要写工程名字，
//		request.setAttribute("username", "admin"); 
//		response.sendRedirect("/servlet_advanced/direct/index");
	}

}
