package com.baidu.servlet;

import java.io.IOException;

import javax.sound.midi.Soundbank;

import org.apache.catalina.connector.Response;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Third extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("wo shi di san gedd顶顶顶顶顶顶顶d ");
		System.out.println("我是第三个服务器啊");
	}
	

}
