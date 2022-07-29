package com.imooc.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName="ZhuJieFilter", urlPatterns = "/*",
        initParams = {
        		@WebInitParam(name = "encoding", value = "UTF-8"),
        		@WebInitParam(name = "p1", value = "v1")
        }
		)

public class ZhuJieFilter  implements Filter {
     
	private String encoding;
	private String key;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		encoding = filterConfig.getInitParameter("encoding");
		key = filterConfig.getInitParameter("p1");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		   System.out.println(key);
		   System.out.println(encoding);
		 chain.doFilter(request, response);
	}
	

}
