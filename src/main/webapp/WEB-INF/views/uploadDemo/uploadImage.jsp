<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/11/8
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>图片上传demo</title>
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

<div class="layui-row layui-col-space1" >
    <%--<div class="layui-col-xs3">--%>
        <%--<div class="grid-demo grid-demo-bg1">--%>
            <%--<div class="layui-upload" style="text-align: center">--%>
               <%--上传图片Demo01--%>
                <%--<div class="layui-upload-list">--%>
                    <%--<img class="layui-upload-img" id="demo1">--%>
                    <%--<p id="demoText"></p>--%>
                <%--</div>--%>
                <%--<button type="button" class="layui-btn" id="test1">上传图片</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="layui-col-xs3">
        <div class="grid-demo">
            <div class="layui-upload" style="text-align: center">
                上传图片Demo02
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo1">
                    <p id="demoText"></p>
                </div>
                <button type="button" class="layui-btn layui-btn-normal" id="selectADocument">选择文件</button>
                <button type="button" class="layui-btn" id="startUpload">开始上传</button>
            </div>

        </div>
    </div>
    <%--<div class="layui-col-xs3">--%>
        <%--<div class="grid-demo grid-demo-bg1">3/12</div>--%>
    <%--</div>--%>
    <%--<div class="layui-col-xs3">--%>
        <%--<div class="grid-demo">3/12</div>--%>
    <%--</div>--%>
</div>


</body>
<%--引入js文件--%>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<%--自定义js文件--%>
<script>
  layui.use('upload', function() {
    var $ = layui.jquery
        , upload = layui.upload;
    //普通图片上传
     upload.render({
       elem: '#test1'
      ,url: ctx+'/uploadApi/uploadImage'
      ,before: function(obj){
        //预读本地文件示例，不支持ie8
        obj.preview(function(index, file, result){
          $('#demo1').attr('src', result); //图片链接（base64）
        });
      }
      ,done: function(res){
        //如果上传失败
        if(res.code > 0){
          return layer.msg('上传失败',{icon:5,time:1500});
        }else{
          return layer.msg('上传成功',{icon:1,time:1500});
        }

      }
      ,error: function(){
        //演示失败状态，并实现重传
        var demoText = $('#demoText');
        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
        demoText.find('.demo-reload').on('click', function(){
          uploadInst.upload();
        });
      }
    });

    //选完文件后不自动上传
    upload.render({
      elem: '#selectADocument'
      ,url:  ctx+'/uploadApi/uploadImage'
      ,auto: false
      ,bindAction: '#startUpload'
      // 选择完文件后，回调
      ,choose: function(obj){
        //预读本地文件示例，不支持ie8
        obj.preview(function(index, file, result){
          $('#demo1').attr('src', result); //图片链接（base64）
        });
      }
      ,done: function(res){
        //如果上传失败
        if(res.code > 0 ){
          return layer.msg('上传失败',{icon:5,time:1500});
        }else{
          return layer.msg('上传成功',{icon:1,time:1500});

        }
      }
    });

  })
</script>
</html>
