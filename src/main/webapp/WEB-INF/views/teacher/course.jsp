<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/6
  Time: 12:04 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加课程</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet" />
    <link href="${ctx}/resources/css/teacher/course.css" rel="stylesheet" />
    <link href="${ctx}/resources/mui/mui.picker.min.css" rel="stylesheet"/>
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
    <h1 class="mui-title">添加课程</h1>
</header>
<div class="mui-content">
    <div id="cotent">
        <div class="location">
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>授课教师：</label>
                        <input type="text" class="mui-input-clear" id="teacherName" disabled="true" value="${teacher.teacherName}">
                    </div>
                    <div class="mui-input-row">
                        <label>课程名：</label>
                        <input type="text" class="mui-input-clear"  id="courseName" placeholder="请输入课程名">
                    </div>
                    <div class="mui-input-row">
                        <label>专业：</label>
                        <input type="text" class="mui-input-clear" placeholder="请选择上课的专业" id="prefession" name="">
                    </div>

                    <div class="mui-input-row">
                        <label>上课安排：</label>
                        <input type="text" class="mui-input-clear" placeholder="请选择开始时间" id="dateSelect">
                    </div>

                    <div class="mui-input-row">
                        <label>开课时间：</label>
                        <input type="text" class="mui-input-clear" placeholder="请选择开课时间" id="startTime">
                    </div>
                    <div class="mui-input-row">
                        <label>结课时间：</label>
                        <input type="text" class="mui-input-clear" placeholder="请选择结课时间" id="endTime">
                    </div>
                    <div class="btn">
                        <button type="button" class="mui-btn mui-btn-primary" id="submit">提交</button>
                        <button type="button" class="mui-btn">重置</button>
                    </div>
                </li>
            </ul>

        </div>
    </div>
</div>
</body>

<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.picker.min.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/teacher/course.js" type="application/javascript"></script>
</html>
