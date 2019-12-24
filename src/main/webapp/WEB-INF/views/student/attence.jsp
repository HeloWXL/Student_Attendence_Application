<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/29
  Time: 9:44 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>考勤</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link href="${ctx}/resources/css/student/attence.css" rel="stylesheet">
    <script>
        var ctx = '${ctx}';
        var student = "${studentsession}";
        var studentId = "${studentsession.studentId}";
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">考勤</h1>
</header>
<div class="mui-content">
    <div class="top" style="margin-top: 10px;">
        <ul class="layui-timeline">
            <li class="layui-timeline-item" id="start-time">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">上课签到</h4>
                </div>
            </li>
            <li class="layui-timeline-item" id="end-time" style="display: none">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">下课签退</h4>
                </div>
            </li>
        </ul>
    </div>
    <div class="c-center">
        <div class="center">
            <div class="mui-card" id="start" onclick="start_time(this)">
                <!--内容区-->
                <div class="mui-card-content">
                    上课签到
                    <p></p>
                </div>
            </div>
            <div class="mui-card" id="end" style="display: none" onclick="end_time(this)">
                <!--内容区-->
                <div class="mui-card-content">
                    下课签到
                    <p></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/student/attence.js" type="application/javascript"></script>
</html>
