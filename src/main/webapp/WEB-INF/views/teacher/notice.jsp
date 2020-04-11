<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>通知</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/css/student/index.css" rel="stylesheet"/>
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
    <h1 class="mui-title">通知</h1>
</header>
<div class="mui-content">
    <ul class="mui-table-view">
        <li class="mui-table-view-cell">请假通知
            <a href="${ctx}/leaveNotice/noticeTeaList/${teacher.teacherId}" style="position: unset;">
                <span class="mui-badge">${leaveNotice}</span>
            </a>
        </li>
    </ul>
</div>
<nav class="mui-bar mui-bar-tab">
    <a class="mui-tab-item " id="home">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="mui-tab-item mui-active" id="notice">
        <span class="mui-icon mui-icon-chatbubble"></span>
        <span class="mui-tab-label">通知</span>
    </a>
    <a class="mui-tab-item" id="myinfo">
        <span class="mui-icon mui-icon-person"></span>
        <span class="mui-tab-label">我的信息</span>
    </a>
</nav>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script>
    $(function () {
        // 判断教师是否登录，未登录直接跳转到登录界面
        if (teacher == null || teacher == '') {
            location.href = ctx + "/teacherApi/toLogin"
        }
        //我的信息
        $("#myinfo").click(function () {
            location.href = ctx + '/teacherApi/toPerson';
        });
        //首页
        $("#home").click(function () {
            location.href = ctx + '/teacherApi/toIndex';
        });
        //通知
        $("#notice").click(function () {
            location.href = ctx + '/teacherApi/notice';
        });
    })
</script>
</html>
