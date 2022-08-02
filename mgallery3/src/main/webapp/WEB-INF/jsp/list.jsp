<%@page contentType="text/html;charset=utf-8"%>
<!-- 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/list.css">
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="js/sweetalert2.all.min.js"></script>
    <title>油画列表</title>
<script type="text/javascript">
 function showPreview(previewObj){
 	var preview = $(previewObj).attr("data-preview");
 	var pname = $(previewObj).attr("data-pname");
 	 Swal.fire({
 		title:pname,
 	
 		html:"<img src='"+preview+"' style='width:460px;height:350px'/>",
 		showCloseButton: true,
 		showConfirmButton:false
 	})
 }
 
 function del(delObj){
	 var id = $(delObj).attr("data-id");
	var pname = $(delObj).attr("data-pname");
	 var preview = $(delObj).attr("data-preview");
	 Swal.fire({
		 title: "你确定要删除["+ pname + "]油画吗？",
		 html: "<img src='" + preview + "' style='width:480px; height:260px'>",
		 showCancelButton: true,
		confirmButtonText: "是",
		cancelButtonText: "否"	 
    }).then(function(result){
    	if(result.value == true){
    	
    	$.ajax({
    		//别写错属性值了，刚才写错了success
    	  url: "/mgallery3/management?method=delete&id=" + id,
    	   type: "get",
    	   dataType: "json",
    	   success: function(json){
    		   if(json.result == "ok"){
    			   window.location.reload();			  
    		   } else{
    			   Swal.fire({
    				   title: json.result
    				  
    			   })
    		   }
    	   }
    	})
    	}
    	
    })
 }
 </script>
 
</head>
<body>
    <div class="container">
        <div class="show">油画列表</div>
        <ul>
            <li>
                <a href="/mgallery3/management?method=show_create">新增</a>
            </li>
            <li>
                <a href="">删除</a>
            </li>
        </ul>

    </div>
    <div class="content">
        <table cellspacing="0px">
            <thead>
                <tr>
                    <th style="width: 100px;">分类</th>
                    <th style="width: 150px;">名称</th>
                    <th style="width: 100px;">价格</th>
                    <th style="width: 400px;">描述</th>
                    <th style="width: 100px;">操作</th>
                </tr>
            </thead>
            <c:forEach items="${pageModel.pageData }" var="painting">
            <tr>
                <td style="border-left:1px solid #ccc ;">
                 ${painting.category }
                </td>
                <td>${painting.pname }</td>
                <td><fmt:formatNumber pattern="￥0.00" value="${painting.price}" ></fmt:formatNumber></td>
                <td class="descr">${painting.description }</td>
                <td>
                    <a class="oplink" href="javascript:void(0)" data-id="${painting.id }" data-pname="${painting.pname }" data-preview="${painting.preview }" onclick="showPreview(this)">预览</a>
                    <a class="oplink" href="/mgallery3/management?method=show_update&id=${painting.id }" >修改</a>
                    <a class="oplink" href="javascript:void(0)" data-id="${painting.id }" data-pname="${painting.pname }" data-preview="${painting.preview }" onclick="del(this)">删除</a>
                </td>
            </tr>
   </c:forEach>

        </table>
        <div class="fenye">
            <a href="/mgallery3/management?p=1&method=list">首页 </a>
            <c:choose>
             <c:when test="${ pageModel.hasPreviousPage}">
               <a href="/mgallery3/management?p=${pageModel.page - 1 }&method=list"> 上页</a></c:when>
              <c:otherwise> 
              <a href="#"> 上页</a>
              </c:otherwise>
            </c:choose>
            <c:forEach  begin="1" end="${ pageModel.totalPages}" step="1" var="pno">
            <a ${pno == pageModel.page? "class='show'": ""  } href="/mgallery3/management?p=${pno }&method=list">${pno }</a>
            </c:forEach>
            <c:choose>
            <c:when test="${ pageModel.hasNextPage}">
             <a  href="/mgallery3/management?p=${pageModel.page + 1 }&method=list">下页</a>
            </c:when>
            <c:otherwise>
              <a href="#">下页</a>
            </c:otherwise>
            </c:choose>
            <a href="/mgallery3/management?p=${pageModel.totalPages }&method=list">尾页</a>
        </div>
    </div>
</body>
</html>