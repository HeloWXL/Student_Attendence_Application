<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/11/2
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>请假</title>
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
                        <input type="text" class="mui-input-clear" disabled="true" id="studentname"
                               value="${studentsession.studentName}">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>学号：</label>
                        <input type="text" class="mui-input-clear" disabled="true" id="studentsno"
                               value="${studentsession.studentSno}">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>标题：</label>
                        <input type="text" class="mui-input-clear" id="leaveTitle" value="${leave.leaveTitle}">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>开始时间：</label>
                        <input type="text" class="mui-input-clear" id="start">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>结束时间：</label>
                        <input type="text" class="mui-input-clear" id="end">
                    </div>
                </li>
                <div class="mui-input-row">
                    <label>事由：</label>
                    <textarea id="leaveReason" rows="8"
                              style="width: 95%; margin-left: 8px;border: 1px solid #BBBBBB;"
                              >${leave.leaveReason}</textarea>
                </div>
            </ul>
        </div>

    </div>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.picker.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script>
    var startTime =  new Date('${leave.startTime}');
    var endTime =  new Date('${leave.endTime}');

    $('#start').val(formatDate(startTime));
    $('#end').val(formatDate(endTime));
    // js时间格式化
    function formatDate(date) {
        var myyear = date.getFullYear();
        var mymonth = date.getMonth() + 1;
        var myweekday = date.getDate();

        if (mymonth < 10) {
            mymonth = "0" + mymonth;
        }
        if (myweekday < 10) {
            myweekday = "0" + myweekday;
        }
        return (myyear + "-" + mymonth + "-" + myweekday);
    }
</script>


</html>
