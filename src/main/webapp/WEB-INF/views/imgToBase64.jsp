<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/12/28
  Time: 10:30 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <img src="../../resources/images/user.jpg"/>
</body>

<script>
    var img = "../../resources/images/user.jpg";//imgurl 就是你的图片路径

    function getBase64Image(img) {
        var canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, img.width, img.height);
        var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();
        var dataURL = canvas.toDataURL("image/"+ext);
        return dataURL;
    }

    var image = new Image();
    image.src = img;
    image.onload = function(){
        var base64 = getBase64Image(image);
        console.log(base64);
    }
</script>
</html>
