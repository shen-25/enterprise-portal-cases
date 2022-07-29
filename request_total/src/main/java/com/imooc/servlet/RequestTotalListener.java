package com.imooc.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

public class RequestTotalListener implements ServletContextListener, ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		ServletRequestListener.super.requestDestroyed(sre);
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
		String url = request.getRequestURL().toString();
		if(url.endsWith("/rt") == true) {
			return;
		}
		List<String> timeList = (List)sre.getServletContext().getAttribute("timeList");
		List<Integer> valueList = (List)sre.getServletContext().getAttribute("valueList");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String time = sdf.format(date);
		if(timeList.indexOf(time) == -1) {
			timeList.add(time);		
			valueList.add(1);
			sre.getServletContext().setAttribute("timeList", timeList);
			sre.getServletContext().setAttribute("valueList", valueList);
		} else {
		   int index = timeList.indexOf(time);
		   int ans = valueList.get(index);
		   valueList.set(index, ans + 1);
		   sre.getServletContext().setAttribute("valueList", valueList);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		List<String> timeList = new ArrayList();
		List<Integer> valueList = new ArrayList();
		//初始化数据，一个保存时间，一个保存访问数值
		sce.getServletContext().setAttribute("timeList", timeList);
		sce.getServletContext().setAttribute("valueList", valueList);
		
	}

}
