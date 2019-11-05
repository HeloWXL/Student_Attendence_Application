<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/5
  Time: 11:37 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>教师登录</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet" />
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <link href="${ctx}/resources/css/teacher/login.css" rel="stylesheet"/>
    <script>
        var ctx = '${ctx}'
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <h1 class="mui-title">教师登录</h1>
</header>

<div class="mui-content">
    <div class="center">
        <img src="${ctx}/resources/images/default.png" id="icon"/>
    </div>

    <form id='login-form' class="mui-input-group">
        <div class="mui-input-row">
            <label>工号：</label>
            <input id='tno' type="text" class="mui-input-clear mui-input" placeholder="请输入教师工号">
        </div>
        <div class="mui-input-row">
            <label>密码</label>
            <input id='password' type="password" class="mui-input-clear mui-input" placeholder="请输入密码">
        </div>
    </form>

    <div class="mui-content-padded">
        <button id='login' class="mui-btn mui-btn-block mui-btn-primary">登录</button>
    </div>
    <div class="mui-content-padded oauth-area">

    </div>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/teacher/login.js" type="application/javascript"></script>
</html>
