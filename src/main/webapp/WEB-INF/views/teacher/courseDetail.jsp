<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/11/6
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程详情</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
      var ctx = '${ctx}';
      var teacher = "${teacher}";
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">课程详情</h1>
</header>
<div class="mui-content">
    <ul class="mui-table-view">
        <li class="mui-table-view-cell">
            <div class="mui-input-row">
                <label>课程名</label>
                <input type="text" class="mui-input-clear" value="${course.courseName}" disabled="true">
            </div>

            <div class="mui-input-row">
                <label>上课时间</label>
                <input type="text" class="mui-input-clear" value="${course.classarrangement}" disabled="true">
            </div>

            <div class="mui-input-row">
                <label>开课时间</label>
                <input type="text" class="mui-input-clear" value="${course.starttime}" disabled="true" id="start">
            </div>
            <div class="mui-input-row">
                <label>结课时间</label>
                <input type="text" class="mui-input-clear" value="${course.endtime}" disabled="true" id="end">
            </div>

            <div class="mui-input-row">
                <label>教师姓名</label>
                <input type="text" class="mui-input-clear" value="${course.teacher.teacherName}" disabled="true">
            </div>

            <div class="mui-input-row">
                <label>教师职称</label>
                <input type="text" class="mui-input-clear" value="${course.teacher.teacherJobTitle}" disabled="true">
            </div>

            <div class="mui-input-row">
                <label>专业</label>
                <input type="text" class="mui-input-clear" value="${course.professions.professionName}" disabled="true">
            </div>

            <div class="mui-input-row">
                <label>学院</label>
                <input type="text" class="mui-input-clear" value="${course.professions.apartment}" disabled="true">
            </div>

            <div class="mui-input-row">
                <label>学校</label>
                <input type="text" class="mui-input-clear" value="${course.professions.school}" disabled="true">
            </div>
    </ul>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script type="text/javascript" src="${ctx}/resources/js/teacher/courseDetail.js"></script>
</html>
