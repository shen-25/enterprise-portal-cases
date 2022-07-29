package com.imooc.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeviceAdapterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	   HttpServletRequest req = (HttpServletRequest)request;
	   HttpServletResponse res = (HttpServletResponse)response;
	   
	   String uri = req.getRequestURI();
	   System.out.println(uri);
	   
	   if(uri.startsWith("/desktop") || uri.startsWith("/mobile")) {
		   chain.doFilter(request, response);
		   
	   }
	   
	   String targetURI = "";
	   String userAgent = req.getHeader("user-agent").toLowerCase();
	   if(userAgent.indexOf("android") != -1 || userAgent.indexOf("iphone") != -1) {
		    targetURI = "/mobile" + uri;
		    //注释掉，出错了
		     //res.sendRedirect(targetURI);
	   } else {
		   targetURI = "/desktop" + uri;
		   //res.sendRedirect(targetURI);
	   }
	}

}
