package com.imooc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class ChannelServlet
 */
public class ChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String level = request.getParameter("level");
		 String parent = request.getParameter("parent");
		 List<Channel> chlist = new ArrayList<>();
		 if(level.equals("1")) {
			 chlist.add(new Channel("ai", "前沿科技/区块链/人工智能"));
			 chlist.add(new Channel("web", "前端/小程序/js"));
		 } else if(level.equals("2")) {
			 if(parent.equals("ai")) {
				 chlist.add(new Channel("micro", "微服务"));
				 chlist.add(new Channel("blockchain", "区块链"));
				 chlist.add(new Channel("other", "....."));
			 } else if(parent.equals("web")) {
				 chlist.add(new Channel("html", "html"));
				 chlist.add(new Channel("css", "CSS"));
				 chlist.add(new Channel("other", "....."));
			 }
		 }
		 String json = JSON.toJSONString(chlist);
		 response.setContentType("text/html; charset=utf-8");
		 response.getWriter().println(json);
	}

}
