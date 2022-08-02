<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="css/paint.css">

<title>油画商城</title>
</head>
<body>
   <c:if test="${param.c != null }">
    <c:set var="categoryParam" value="&c=${param.c }"></c:set>
   </c:if>
   <c:if test="${param.c == null }">
      <c:set var="categoryParam" value=""></c:set>
   </c:if>
	<div class="header">
		<div class="icon">
			<img src="image\Snipaste_2022-07-22_14-02-15.png" alt="">
		</div>
		<div class="meun" id="meun">
			<div class="sort" id="meun-title">
				<a href="#" id="color">内容分类</a>
			</div>
			<ul id="meun1">
			<a href="/mgallery3/page?c=1"><li>现实主义</li></a>
		    <a href="/mgallery3/page?c=2"><li>抽象主义</li></a>
				
			</ul>
		</div>
		<ul class="log">
			<li><a href="#">注册</a></li>
			<li><a href="#">登录</a></li>
		</ul>
	</div>
	<div class="content">
		<div class="content-img">
			<img src="image/Snipaste_2022-07-22_14-02-51.png" alt="">
		</div>
		<div class="img-content">
			<ul>
				<c:forEach items="${pageModel.pageData }" var="painting">
					<li><img  class ="img_paint" src="${painting.preview }" alt="">
						<div class="info">
							<h3>${painting.getpname()}</h3>
							<p>${painting.description }</p>
							<div class="btn">
								<div class="price">
									<fmt:formatNumber pattern="￥0.00">${painting.price }</fmt:formatNumber>
								</div>
								<div class="btn-car">
									<a href="#" class="cart"> 
									<img
										src="image\Snipaste_2022-07-19_14-23-33.png " alt="">
									</a>
								</div>
							</div>
						</div></li>
				</c:forEach>
			</ul>
		</div>
		<div class="paging">
			<ul>
				<li><a href="/mgallery3/page?page=1${categoryParam}">首页</a></li>
				<li><c:choose>
						<c:when test="${pageModel.hasPreviousPage }">
							<a href="/mgallery3/page?page=${pageModel.page - 1 }${categoryParam}">上一页</a>
						</c:when>
						<c:otherwise>
							<span>上一页</span>
						</c:otherwise>
					</c:choose></li>
				<c:forEach begin="1" end="${pageModel.totalPages }" var="pno">
					<li ${pno == pageModel.page?"class='show'":"" }><a
						href="/mgallery3/page?page=${pno }${categoryParam}"> ${pno } </a></li>
				</c:forEach>
				<li><c:choose>
						<c:when test="${pageModel.hasNextPage }">
							<a href="/mgallery3/page?page=${pageModel.page + 1}${categoryParam}">下一页</a>
						</c:when>
						<c:otherwise>
							<span>下一页</span>
						</c:otherwise>
				 </c:choose></li>
				<li><a href="/mgallery3/page?page=${pageModel.totalPages }${categoryParam}">尾页</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="footer">
		<div class="copyright">
			<p>
				<span>M_GALLARY</span> ©2012-2022 字节跳动 POWERED BY IMMOOC.INC 
				<a style="color:red" href="/mgallery3/management.html">管理后台</a>
			</p>
		</div>
	</div>

</body>
<script src="js/index.js"></script>

</html>