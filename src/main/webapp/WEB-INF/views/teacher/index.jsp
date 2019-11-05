<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/10/15
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>教师首页</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <link href="${ctx}/resources/css/teacher/index.css" rel="stylesheet"/>
    <script>
        var ctx = '${ctx}';
        var teacher = "${teacher}";
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <h1 class="mui-title">教师首页</h1>
</header>
<div class="mui-content">
    <div id="addCourse">
        <a href="">
            <img src="${ctx}/resources/images/course.png"/>
            <p>添加课程</p>
        </a>
    </div>
    <div id="courselist">
        <a href="">
            <img src="${ctx}/resources/images/courseManage.png"/>
            <p>我的课程</p>
        </a>
    </div>
    <div id="attendancelist">
        <a href="">
            <img src="${ctx}/resources/images/attendence.png"/>
            <p>创建考勤</p>
        </a>
    </div>
    <div id="mycourselist">
        <a href="">
            <img src="${ctx}/resources/images/attendenceList.png"/>
            <p>考勤统计</p>
        </a>
    </div>
</div>
<!-- 底部选项卡 -->
<nav class="mui-bar mui-bar-tab">
    <a class="mui-tab-item mui-active" id="home">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="mui-tab-item">
        <span class="mui-icon mui-icon-chatbubble"></span>
        <span class="mui-tab-label">消息</span>
    </a>
    <a class="mui-tab-item" id="myinfo">
        <span class="mui-icon mui-icon-person"></span>
        <span class="mui-tab-label">我的信息</span>
    </a>
</nav>
</body>

<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script type="text/javascript">

    $(function () {
        // 判断教师是否登录，未登录直接跳转到登录界面
        if (teacher == null || teacher == '') {
            location.href = ctx + "/teacherApi/toLogin"
        }
        mui("#addCourse").on('tap', 'img', function () {
            location.href = '/teacherApi/toCourse';
        })
        mui("#courselist").on('tap', 'img', function () {
            location.href = '/teacherApi/toCourseList';
        })
        mui("#attendancelist").on('tap', 'img', function () {
            mui.alert("正在建设中.....")
        })
        mui("#mycourselist").on('tap', 'img', function () {
            mui.alert("正在建设中.....")
        })
        $("#myinfo").click(function () {
            location.href = ctx + '/teacherApi/toPerson';
        });
        $("#home").click(function () {
            location.href = ctx + '/teacherApi/toIndex';
        });
    })

</script>

</html>
