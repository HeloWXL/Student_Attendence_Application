<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/12/1
  Time: 12:27 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet" />
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <style>
        ul li span{
            float: right;
        }
        .mui-title {
            color: #FFFFFF;
        }
        .mui-content>.mui-table-view:first-child {
             margin-top: 0px;
        }
    </style>
    <script>
        var ctx = '${ctx}';
        var student = "${studentsession}";
        var studentId = '${studentsession.studentId}';

    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">考勤记录</h1>
</header>
<div class="mui-content">
    <ul class="mui-table-view">
        <li class="mui-table-view-cell mui-collapse">
            <a  href="#">出勤天数：<span id="count"></span></a>
        </li>
    </ul>
    <ul class="mui-table-view" id="signList">
        <%--打卡记录--%>
    </ul>
    <div>

    </div>
</div>
</body>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/student/attenceList.js" type="application/javascript"></script>
</html>
