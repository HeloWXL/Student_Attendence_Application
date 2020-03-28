<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>请假详情</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/mui/mui.picker.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/css/student/askForLeave.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx }';
        var student = "${studentsession}";
    </script>
</head>
<body>
<%--头部导航--%>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">请假详情</h1>
</header>
<%--中间部分--%>
<div class="mui-content">
    <div id="cotent">
        <div class="location">
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>姓名：</label>
                        <input type="text" class="mui-input-clear" value="${leave.student.studentName}">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>学号：</label>
                        <input type="text" class="mui-input-clear" value="${leave.student.studentSno}">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>标题：</label>
                        <input type="text" class="mui-input-clear" value="${leave.leaveTitle}">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>课程：</label>

                        <input type="text" class="mui-input-clear" value="${leave.course.courseName}">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>请假状态：</label>
                        <c:if test="${leave.isRead==1}">
                            <input type="text" class="mui-input-clear" id="status" value="已批准">
                        </c:if>
                        <c:if test="${leave.isRead==2}">
                            <input type="text" class="mui-input-clear" id="status" value="未批准">
                        </c:if>
                        <c:if test="${leave.isRead==0}">
                            <input type="text" class="mui-input-clear" id="status" value="未批阅">
                        </c:if>
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>开始时间：</label>
                        <input type="text" class="mui-input-clear"
                               value="<fmt:formatDate value="${leave.startTime}" pattern="yyyy-MM-dd" />">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>结束时间：</label>
                        <input type="text" class="mui-input-clear"
                               value="<fmt:formatDate value="${leave.endTime}" pattern="yyyy-MM-dd" />">
                    </div>
                </li>
            </ul>
            <div class="mui-input-row">
                <label>事由：</label>
                <textarea id="leaveReason" rows="8"
                          style="width: 95%; margin-left: 8px;border: 1px solid #BBBBBB;"
                >${leave.leaveReason}</textarea>
            </div>

            <c:if test="${!empty leave.fileUrl}">
                <div class="mui-input-row" style="margin-top: 20px">
                    <label>附件：</label>
                    <img src="${ctx}/leaveApi/getLocalImg?path=${leave.fileUrl}"
                         style="width: 150px;height: 150px;">
                </div>
            </c:if>
        </div>

    </div>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.picker.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
</html>
