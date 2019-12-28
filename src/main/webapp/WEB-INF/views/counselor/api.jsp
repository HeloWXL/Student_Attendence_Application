<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/10/19
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>FaceApi管理</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/resources/ico/logo.ico"  type=”image/x-icon”>
    <script>
        var ctx = '${ctx}'
         var counselorId = '${counselor.counselorId}';
    </script>
</head>
<body>
<div class="layui-row">
    <div class="layui-col-xs12">
        <div class="grid-demo grid-demo-bg1">
            <!-- 模块名 -->
            <blockquote class="layui-elem-quote">FaceApi管理</blockquote>
            <%--操作--%>
            <div class="top">

            </div>
            <%--表格--%>
            <div class="center">
                <table id="demo" lay-filter="apifilter"></table>
            </div>
        </div>
    </div>
</div>
<!-- 表格标签工具栏 -->

<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/counselor/counselor_api.js" type="application/javascript"></script>
</body>
</html>
