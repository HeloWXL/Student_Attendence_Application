<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/5
  Time: 11:55 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>个人信息</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/css/teacher/person.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx}';
        var teacher = "${teacher}";
    </script>

</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <h1 class="mui-title">我的信息</h1>
    <button type="button" class="mui-btn" id="back">退出</button>
</header>

<div class="mui-content">
    <ul class="mui-table-view">
        <li class="mui-table-view-cell">

            <div class="mui-input-row">
                <label>姓名</label>
                <input type="text" class="mui-input-clear" name="name">
            </div>

            <div class="mui-input-row">
                <label>工号</label>
                <input type="text" class="mui-input-clear" name="tno">
            </div>
            <div class="mui-input-row">
                <label>年龄</label>
                <input type="text" class="mui-input-clear" name="age">
            </div>
            <div class="mui-input-row">
                <label>性别</label>
                <input type="text" class="mui-input-clear" name="sex">
            </div>
            <div class="mui-input-row">
                <label>职称</label>
                <input type="text" class="mui-input-clear" name="job_title">
            </div>

            <div class="mui-input-row">
                <label>专业</label>
                <input type="text" class="mui-input-clear" name="profession">
            </div>
            <div class="mui-input-row">
                <label>学院</label>
                <input type="text" class="mui-input-clear" name="apartment">
            </div>
            <div class="mui-input-row">
                <label>学校</label>
                <input type="text" class="mui-input-clear" name="school">
            </div>
    </ul>
</div>
<nav class="mui-bar mui-bar-tab" id="home">
    <a class="mui-tab-item">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="mui-tab-item">
        <span class="mui-icon mui-icon-chatbubble"></span>
        <span class="mui-tab-label">消息</span>
    </a>
    <a class="mui-tab-item mui-active" id="myinfo">
        <span class="mui-icon mui-icon-person"></span>
        <span class="mui-tab-label">我的信息</span>
    </a>
</nav>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script type="text/javascript" src="${ctx}/resources/js/teacher/person.js"></script>
</html>
