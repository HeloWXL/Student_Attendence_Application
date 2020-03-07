<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>请假记录</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx }';
        var teacher = "${teacher}";
        var tId = "${teacher.teacherId}";
    </script>
    <style>
        body {
            font-family: 'Helvetica Neue', Helvetica, sans-serif;
            font-size: 14px;
            line-height: 21px;
            color: #000;
            background-color: #efeff4;
            -webkit-overflow-scrolling: touch;
        }

        .mui-content > .mui-table-view:first-child {
            margin-top: 0px;
        }

        .mui-title {
            color: #FFFFFF;
        }

        h4 {
            font-family: "lucida console";
            font-weight: 400;
        }

        #success {
            border-radius: 0px 100px;
            position: absolute;
            top: 10px;
            right: 2px;
        }

        #fail {
            border-radius: 0px 100px;
            position: absolute;
            top: 10px;
            right: 2px;
        }

        .mui-btn-outlined {
            width: 100%;
            border: none;
            outline: none;
        }

        /*点击变蓝色高亮*/
        .mui-table-view-cell.mui-active {
            background-color: #0062CC;
        }
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">请假统计</h1>
</header>
<div class="mui-content">
    <ul class="mui-table-view" id="leaveList">
        <c:forEach items="${leave}" var="l">
            <li class="mui-table-view-cell mui-media">
                <c:if test="${l.isRead==0}">
                    <span class="mui-badge mui-badge-primary" id="success">未批阅</span>
                </c:if>
                <c:if test="${l.isRead==1}">
                    <span class="mui-badge mui-badge-success" id="success">已批准</span>
                </c:if>
                <c:if test="${l.isRead==2}">
                    <span class="mui-badge mui-badge-danger" id="success">未批准</span>
                </c:if>
                <a href="/helo/leaveApi/selectByPrimaryKey/${l.leaveId}">
                    <div class="mui-media-body">
                        <div>请假标题：${l.leaveTitle}</div>
                        <div style="color:red">姓名:${l.studentName}</div>
                        <div style="color:red">学号:${l.studentSno}</div>
                        <p style="float: right;font-size: 12px">开始时间： <fmt:formatDate value="${l.startTime}" pattern="yyyy-MM-dd" />
                            &nbsp;&nbsp;&nbsp;结束时间：<fmt:formatDate value="${l.endTime}" pattern="yyyy-MM-dd" /></p>
                        </div>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script>
    $(function () {
        // 判断用户是否登录
        if (teacher == '' || teacher == null) {
            location.href = ctx + '/teacherApi/toLogin';
            return;
        }
    });
</script>

</html>
