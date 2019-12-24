<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/10/12
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>学生首页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet" />
    <link href="${ctx}/resources/css/student/index.css" rel="stylesheet" />
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
      var ctx = '${ctx }';
      var student = "${studentsession}";
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <h1 class="mui-title">学生首页</h1>
</header>
<div class="mui-content">
    <div id="attendance">
        <a href="">
            <img src="${ctx}/resources/images/face.png" />
            <p>学生考勤</p>
        </a>
    </div>

    <div  id="attendancelist">
        <a href="">
            <img src="${ctx}/resources/images/attendenceList.png" />
            <p>考勤记录</p>
        </a>
    </div>

    <div id="mycourse">
        <a href="">
            <img src="${ctx}/resources/images/course.png" />
            <p>我的课程</p>
        </a>
    </div>
    <div id="mycourselist">
        <a href="">
            <img src="${ctx}/resources/images/courseManage.png" />
            <p>我的课表</p>
        </a>
    </div>
    <div>
        <a id="askForLeave">
            <img src="${ctx}/resources/images/leave.png" />
            <p>请假</p>
        </a>
    </div>
    <div>
        <a id="leaveList">
            <img src="${ctx}/resources/images/leaveList.png" />
            <p>请假记录</p>
        </a>
    </div>
</div>
<nav class="mui-bar mui-bar-tab">
    <a class="mui-tab-item mui-active" id="home">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="mui-tab-item">
        <span class="mui-icon mui-icon-chatbubble"></span>
        <span class="mui-tab-label">考勤统计</span>
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
<script src="${ctx}/resources/js/student/index.js" type="application/javascript"></script>
</html>
