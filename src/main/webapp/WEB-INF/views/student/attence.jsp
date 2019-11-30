<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/29
  Time: 9:44 下午
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
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <style>

        #start div{
            width: 200px;
            height: 200px;
            border-radius: 50%;
            background-color: #93D1FF;
            margin: 0 auto;
            text-align: center;
            line-height: 200px;
        }
        #end div{
            width: 200px;
            height: 200px;
            border-radius: 50%;
            background-color: #93D1FF;
            margin: 0 auto;
            text-align: center;
            line-height: 200px;
        }
        .mui-card{
            background-color:#efeff4;
            box-shadow: none;
        }
        .mui-title {
            color: #FFFFFF;
        }
    </style>
    <script>
        var ctx = '${ctx}';
        var student = "${studentsession}";
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">考勤</h1>
</header>
<div class="mui-content">
    <div class="top" style="margin-top: 10px;">
        <ul class="layui-timeline">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">上班打卡</h4>
                    <h3 class="layui-timeline-title">打卡时间 08:45</h3>
                    <p>
                        地点：科大国创软件股份有限公司-合肥
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">下班打卡</h4>
                    <h3 class="layui-timeline-title">打卡时间 18:45</h3>
                    <p>
                        地点：科大国创软件股份有限公司-合肥
                    </p>
                </div>
            </li>

        </ul>
    </div>

    <div class="c-center">
        <div class="center">
            <div class="mui-card" id="start">
                <!--内容区-->
                <div class="mui-card-content">
                    上班打卡
                    08:00:12
                </div>
            </div>

            <div class="mui-card" id="end">
                <!--内容区-->
                <div class="mui-card-content">
                    下班打卡
                    18:00:12
                </div>
            </div>
        </div>

    </div>

</div>
</body>

<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>

</html>
