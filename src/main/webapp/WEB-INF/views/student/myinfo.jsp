<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/11/2
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>我的信息</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/css/student/myinfo.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
      var ctx = '${ctx }';
      var student = "${studentsession}";
    </script>
</head>
<body>
<%--头部内容--%>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <h1 class="mui-title">我的信息</h1>
    <button type="button" class="mui-btn mui-btn-outlined" id="back">退出</button>
</header>
<%--中间部分--%>
<div class="mui-content">
    <ul class="mui-table-view">
            <div class="mui-input-row">
                <label>头像</label>
                <img src="" name="userPic">
            </div>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>姓名</label>
                <input type="text" class="mui-input-clear" name="username">
            </div>
        </li>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>学号</label>
                <input type="text" class="mui-input-clear" name="sno">
            </div>
        </li>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>年龄</label>
                <input type="text" class="mui-input-clear" name="age">
            </div>
        </li>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>性别</label>
                <input type="text" class="mui-input-clear" name="sex">
            </div>
        </li>
        <%--<li class="mui-table-view-cell">--%>
            <%--<div class="mui-input-row">--%>
                <%--<label>QQ</label>--%>
                <%--<input type="text" class="mui-input-clear" name="qq">--%>
            <%--</div>--%>
    <%--</li>--%>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>邮箱</label>
                <input type="text" class="mui-input-clear" name="email">
            </div>
    </li>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>专业</label>
                <input type="text" class="mui-input-clear" name="profession">
            </div>
        </li>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>学院</label>
                <input type="text" class="mui-input-clear" name="apartment">
            </div>
        </li>
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>学校</label>
                <input type="text" class="mui-input-clear" name="school">
            </div>
        </li>
    </ul>
</div>
<%--底部导航栏--%>
<nav class="mui-bar mui-bar-tab">
    <a class="mui-tab-item" id="home">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="mui-tab-item">
        <span class="mui-icon mui-icon-chatbubble"></span>
        <span class="mui-tab-label">考勤统计</span>
    </a>
    <a class="mui-tab-item" id="myinfo">
        <span class="mui-icon mui-icon-person mui-active"></span>
        <span class="mui-tab-label">我的信息</span>
    </a>
</nav>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/student/myinfo.js" type="application/javascript" ></script>
</html>
