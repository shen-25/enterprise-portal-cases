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
    <title>更新作品</title>
    <script type="text/javascript">
   function checkSubmit(){
	  // var result = true;
	   var r1 = checkEmpty('#pname', '#errorPname');
	   var r2 = checkCategory('#category', '#errorCategory');
	   var r3 = checkPrice('#price', '#errorPrice');
	  // var r4 = checkFile('#preview', '#errorPreview');
	  if($("#isPreviewModified").val() == "1"){
		  r4 = checkFile('#preview', '#errorPreview');
		  console.log("有谱了");
	  } else{
		  r4 = true;
	  }
	  
	   var r5 = checkEmpty('#description', '#errorDesc');
	   if(r1 && r2 && r3 && r4 && r5){
		   return true;
	   } else{
	   return false;
	   }
   }
   $(function(){
	   $("#category").val(${painting.category});
   })
   
   function selectPreview(){
	   checkFile("#preview", "#errorPreview");
	   $("#painting").hide();
	   $('#isPreviewModified').val(1);
   }
</script>
</head>

<body>

    <div class="container">
        <fieldset>
            <legend>修改油画</legend>
            <!-- /mgallery3/management?method=update -->
            <form action="/mgallery3/management?method=update" method="post" autocomplete="off" enctype="multipart/form-data"
             onsubmit="return checkSubmit()">
                <ul>
                    <li>
                        <span>油画名称</span>
                        <span id="errorPname"></span>
                        <input type="text" id="pname" value = "${painting.pname }"  name="pname" onblur="checkEmpty('#pname', '#errorPname')">
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
                        <input type="text" name="price" value="${painting.price }" id="price" onblur="checkPrice('#price', '#errorPrice')">
                    </li>
                    <li>   
                    
                    <span>作品预览</span>
                     <input type="hidden" id="isPreviewModified" name="isPreviewModified" value="0">
        
                     <span id="errorPreview"></span>
                      <img src="${painting.preview }" id="painting" alt="" style="height: 250px; width: 400px; margin-left:120px">
                       <input type="file" name="preview" id="preview" onchange="selectPreview()">
                    </li>
                    <li><span>详细描述</span><span id="errorDesc"></span>
                        <textarea name="description" id="description"  cols="30" rows="10" onblur="checkEmpty('#description', '#errorDesc')">${painting.description }
                        </textarea>
                    </li>
                    <li style="text-align: center;">
                        <input type="hidden" id="id" name="id" value="${painting.id }">
                        <input class="sub" type="submit" value="提交">
                    </li>
                </ul>
            </form>
        </fieldset>
    </div>