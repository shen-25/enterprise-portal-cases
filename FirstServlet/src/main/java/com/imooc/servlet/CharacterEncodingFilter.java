package com.imooc.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CharacterEncodingFilter implements Filter {
        
	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = filterConfig.getInitParameter("encoding");
		System.out.println(filterConfig.getInitParameter("key"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	       HttpServletRequest req = (HttpServletRequest)request;
	       req.setCharacterEncoding(encoding);
	       HttpServletResponse res = (HttpServletResponse) response;
	       res.setContentType("text/html; charset=" + encoding);
	       chain.doFilter(request, response);
	       System.out.println(encoding);
	       
	}

}
