<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/12/4
  Time: 8:57 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>上传图片到本地</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/resources/ico/logo.ico" type=”image/x-icon”>
    <script>
        var ctx = '${ctx}'
    </script>
    <style>
        #demo1{
            width:200px;
            height: 200px;
        }
        .grid-demo{
            background-color: #08acee;
        }
    </style>
</head>
<body>
    <div class="grid-demo">
        <div class="layui-upload" style="text-align: center">
            上传图片
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="demo1">
                <p id="demoText"></p>
            </div>
            <button type="button" class="layui-btn layui-btn-normal" id="selectADocument">选择文件</button>
            <button type="button" class="layui-btn" id="startUpload">开始上传</button>
        </div>
    </div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script>
    // $("#startUpload").on('click',function () {
    //     if()
    //
    //     $.ajax({
    //         url:ctx+'/uploadApi/uploadToLocal',
    //         type: 'post',
    //         data: formData,
    //         processData: false,
    //         dataType: 'json',
    //         contentType: false,
    //         success: function (data) {
    //             if (data.result == 'success') {
    //
    //             } else {
    //             }
    //         }
    //     });
    // })

</script>
<script>
    layui.use('upload', function() {
        var base64Img = '';
        var $ = layui.jquery
            , upload = layui.upload;
        //选完文件后不自动上传
        upload.render({
            elem: '#selectADocument'
            ,url:  ctx+'/uploadApi/uploadToLocal'
            ,auto: false
            ,bindAction: '#startUpload'
            // 选择完文件后，回调
            ,choose: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    base64Img=result;
                    console.log(base64Img)
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                if(res.code ==200 ){
                    return layer.msg('上传成功',{icon:1,time:1500});
                }else{
                    return layer.msg('上传失败',{icon:5,time:1500});
                }
            }
        });
    })

</script>
</html>
