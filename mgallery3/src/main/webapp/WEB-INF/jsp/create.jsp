<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/create.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/validation.js"></script>
    <title>新增作品</title>
</head>
<script type="text/javascript">
   function checkSubmit(){
	  // var result = true;
	   var r1 = checkEmpty('#pname', '#errorPname');
	   var r2 = checkCategory('#category', '#errorCategory');
	   var r3 = checkPrice('#price', '#errorPrice');
	   var r4 = checkFile('#preview', '#errorPreview');
	   var r5 = checkEmpty('#description', '#errorDesc');
	   if(r1 && r2 && r3 && r4 && r5){
		   return true;
	   } else{
	   return false;
	   }
   }
</script>
<body>

    <div class="container">
        <fieldset>
            <legend>新增油画</legend>
            <form action="/mgallery3/management?method=create" method="post" autocomplete="off" enctype="multipart/form-data"
             onsubmit="return checkSubmit()">
                <ul>
                    <li>
                        <span>油画名称</span>
                        <span id="errorPname"></span>
                        <input type="text" id="pname" name="pname" onblur="checkEmpty('#pname', '#errorPname')">
                    </li>

                    <li>
                        <span>油画类型</span>
                        <span id="errorCategory"></span>
                        <select name="category" id="category" onchange="checkCategory('#category', '#errorCategory')">
                            <option value="-1">请选择油画类型</option>
                            <option value="1">现实主义</option>
                            <option value="2">抽象主义</option>
                        </select>
                    </li>
                    <li><span>油画价格</span>
                        <span id="errorPrice"></span>
                        <input type="text" name="price" id="price" onblur="checkPrice('#price', '#errorPrice')">
                    </li>
                    <li><span>作品预览</span><span id="errorPreview"></span>
                        <!--<img src="image/Snipaste_2022-07-29_16-12-37.png" alt="">-->
                        <input type="file" name="preview" id="preview" onchange="checkFile('#preview', '#errorPreview')">
                    </li>
                    <li><span>详细描述</span><span id="errorDesc"></span>
                        <textarea name="description" id="description" cols="30" rows="10" onblur="checkEmpty('#description', '#errorDesc')"></textarea>
                    </li>
                    <li style="text-align: center;">
                        <input class="sub" type="submit" value="提交">
                    </li>
                </ul>
            </form>
        </fieldset>
    </div>