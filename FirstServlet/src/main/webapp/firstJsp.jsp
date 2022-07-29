<%@page import="java.util.List, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	%>

<%!boolean isPrime(int num) {
		boolean flag = true;
		for (int j = 2; j < num; j++) {
			if (num % j == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	List<Integer> list = new ArrayList<>();
	for (int i = 2; i <= 1000; i++) {
		boolean flag = isPrime(i);
		if (flag) {
		/* 	out.println("<h1>" + i + "</h1>"); */
		list.add(i);
		}
	}
	%>
	
	<% for(int p : list){ 
		//out.println("<h1>" + p + "是质数</h1>");
    %>
    <h1 style="color:red; font-size: 41px"><%=p%>是质数啊</h1>
   <% 
	}
	%>
</body>
</html>