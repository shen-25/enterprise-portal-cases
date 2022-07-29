package com.imooc.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class RequestTotalServlet
 */
public class RequestTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestTotalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   ServletContext context =  request.getServletContext();
	   List timeList = (List)context.getAttribute("timeList");
	   List valueList = (List)context.getAttribute("valueList");
	   PrintWriter out = response.getWriter();
	   response.setContentType("text/html; charset=UTF-8");
	   Map<String, List> map = new HashMap<>();
	   map.put("timeList", timeList);
	   map.put("valueList", valueList);
	   String json = JSON.toJSONString(map); 
//	   out.println(timeList);
//	   out.println(valueList);
	   out.println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
