<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/6
  Time: 12:04 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>课程列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet" />
    <link href="${ctx}/resources/css/teacher/courseList.css" rel="stylesheet" />
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx}';
        var teacher = "${teacher}";
        var teacherId = "${teacher.teacherId}";
    </script>
</head>
<body>
    <header class="mui-bar mui-bar-nav mui-badge-primary">
        <span class="mui-icon mui-icon-back" onclick="history.back()"></span>
        <h1 class="mui-title">我的课程</h1>
    </header>

    <div class="mui-content">
        <ul class="mui-table-view" id="commentDetail" >

        </ul>
        <div id="loading" style="display: none;">
            <button type="button" class="mui-btn mui-btn-outlined">加载</button>
        </div>
    </div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/teacher/courseList.js" type="application/javascript"></script>
</html>
