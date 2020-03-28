<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/10/12
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>学生登录</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet" />
    <link href="${ctx }/resources/css/student/login.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>

    <script>
      var ctx = '${ctx }'
    </script>
</head>
<body>
<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed">
        <div class="aui-center">
            <span class="aui-center-title"></span>
        </div>
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-info"></i>
        </a>
    </header>
    <section class="aui-scrollView">
        <div class="aui-account-title">
            <h1>登录</h1>
        </div>

        <div class="aui-account-input">
            <div class="b-line">
                <input type="text" placeholder="请输入学号" id="login_sno">
            </div>
            <div class="">
                <input type="password" placeholder="请输入密码 " id="login_password">
            </div>
        </div>
        <div class="aui-account-button">
            <button id="login_submit">登录</button>
        </div>
        <div class="aui-account-link">
            <a href="javascript:;">联系管理员</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:;">忘记密码</a>
        </div>
    </section>
</section>
</body>
<script type="text/javascript" src="${ctx}/resources/js/jquery-2.1.4.js"></script>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/js/student/login.js"></script>
</html>
