<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
 <h1>姓名：${sessionScope.student.name }</h1>
 <h1>学号：${sessionScope.student.id}</h1>
 <h1>等级：${ sessionScope.grade}</h1>
 <h1>学生信息：${sessionScope.student }</h1>
  <h1>浏览器输入的值：${param.name }</h1>
</body>
</html>