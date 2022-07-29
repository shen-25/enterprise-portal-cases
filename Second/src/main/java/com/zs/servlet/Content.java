package com.zs.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class Content
 */


public class Content extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Content() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getContentType("text/html; charset=utf-8");
		response.setContentType("text/html; charset=utf-8");
		//response.getWriter().println("<b style='color:red'>你好ajax</b><br>");
		String type = request.getParameter("t");
		List list = new ArrayList();
		//好奇怪，这里的类要实现set和get方法。一定要
		if(type != null && type.equals("pypl")) {
			list.add(new News("PYPL", "2019年编程语言排行榜"));
			list.add(new News("PYPL", "2020年编程语言排行榜"));
			list.add(new News("PYPL", "2021年编程语言排行榜"));
			list.add(new News("PYPL", "2022年编程语言排行榜"));
		} else if(type == null || type.equals("tiobe")) {
			list.add(new News("TIOBE9", "2019年编程语言排行榜"));
			list.add(new News("TIOBE20", "2020年编程语言排行榜"));
			list.add(new News("TIOBE21", "2021年编程语言排行榜"));
			list.add(new News("TIOBE22", "2022年编程语言排行榜"));
		}
		String json = JSON.toJSONString((list));
//		System.out.println(json);
	    response.getWriter().println(json);
	    
//	    Employee employee = new Employee();
		
	}

}
