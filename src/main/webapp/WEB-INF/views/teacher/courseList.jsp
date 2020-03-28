<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>

<html>
<head>
    <title>课程列表</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/css/teacher/courseList.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx}';
        var teacher = "${teacher}";
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="history.back()"></span>
    <h1 class="mui-title">我的课程</h1>
</header>

<div class="mui-content">
    <ul class="mui-table-view" id="commentDetail" style="margin-top: 0;">
        <c:forEach items="${course.list}" var="c">
            <a href="${ctx}/teacherApi/selectCourseDetailByCid/${c.courseId}">
                <li class="mui-table-view-cell" style="border-bottom: #dddddd solid 1px">
                    <c:if test="${(c.starttime<now)&&(c.endtime>now)}">
                        <span class="mui-badge mui-badge-success" id="success">进行中...</span>
                    </c:if>
                    <c:if test="${c.endtime<now}">
                        <span class="mui-badge mui-badge-primary" id="success">已结课</span>
                    </c:if>
                    <c:if test="${now<c.starttime}">
                        <span class="mui-badge mui-badge-danger" id="success">未开课!</span>
                    </c:if>
                    <h4>${c.courseName}</h4>

                    <p>
                        <c:if test="${c.classarrangement==1}">
                            上课时间：<span>星期一</span>
                        </c:if>
                        <c:if test="${c.classarrangement==2}">
                            上课时间：<span>星期二</span>
                        </c:if>
                        <c:if test="${c.classarrangement==3}">
                            上课时间：<span>星期三</span>
                        </c:if>
                        <c:if test="${c.classarrangement==4}">
                            上课时间：<span>星期四</span>
                        </c:if>
                        <c:if test="${c.classarrangement==5}">
                            上课时间：<span>星期五</span>
                        </c:if>
                    </p>
                    <p>
                        专业：<span>${c.professions.professionName}</span>
                    </p>
                    <p>
                        上课人数：<span>${course.count}</span>人
                    </p>
                    <p>
                        开课时间：<span><fmt:formatDate value="${c.starttime}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;结课时间：
                        <span><fmt:formatDate value="${c.endtime}" pattern="yyyy-MM-dd"/></span>
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
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/teacher/courseList.js" type="application/javascript"></script>
</html>
