<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/6
  Time: 12:04 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>

<html>
<head>
    <title>考勤列表</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/css/teacher/courseList.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx}';
        var teacher = "${teacher}";
        var teacherId = "${teacher.teacherId}";
    </script>
    <style>
        h4{
            color: #0C0C0C;
        }
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="history.back()"></span>
    <h1 class="mui-title">考勤列表</h1>
</header>

<div class="mui-content">
    <ul class="mui-table-view" id="commentDetail" style="margin-top: 0;">
        <c:forEach items="${release}" var="r">
            <a href="#">
                <li class="mui-table-view-cell">
                    <c:if test="${(r.startTime<now)&&(r.endTime>now)}">
                        <span class="mui-badge mui-badge-success" id="success">进行中</span>
                    </c:if>
                    <c:if test="${r.endTime<now}">
                        <span class="mui-badge mui-badge-primary" id="success">已过期</span>
                    </c:if>
                    <c:if test="${now<r.startTime}">
                        <span class="mui-badge mui-badge-danger" id="success">未开始</span>
                    </c:if>
                    <h4>课程：${r.courseName}</h4>
                    <p>
                        专业：<span>${r.professionName}</span>
                    </p>
                    <p>
                        开始时间：<span><fmt:formatDate value="${r.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    </p>
                    <p>
                        结束时间：
                        <span><fmt:formatDate value="${r.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    </p>
                </li>
            </a>
        </c:forEach>
    </ul>
    <div id="loading" style="display: none;">
        <button type="button" class="mui-btn mui-btn-outlined">加载</button>
    </div>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
</html>
