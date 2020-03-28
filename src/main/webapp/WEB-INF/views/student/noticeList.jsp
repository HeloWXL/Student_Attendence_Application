<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>请假通知列表</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx }';
        var student = "${studentsession}";
    </script>
    <style>
        .mui-title {
            color: #FFFFFF;
        }
        #success{
            border-radius: 0px 100px;
            position: absolute;
            top: 10px;
            right: 2px;
        }
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="history.back()"></span>
    <h1 class="mui-title">请假通知列表</h1>
</header>
<div class="mui-content">
    <ul class="mui-table-view">
        <c:forEach items="${noticeList}" var="n">
            <li class="mui-table-view-cell">
                <c:if test="${n.state==0}">
                    <span class="mui-badge mui-badge-danger" id="success">未读</span>
                </c:if>
                <c:if test="${n.state==1}">
                    <span class="mui-badge mui-badge-success" id="success">已读</span>
                </c:if>
                <a onclick="toNotice(${n.leaveId},${n.id})">
                    <div class="mui-card-header mui-card-media">
                        <img src="${ctx}/studentApi/getLocalImg?path=${studentsession.studentPic}"/>
                        <div class="mui-media-body">
                                ${studentsession.studentName}
                            <p>请假课程： ${n.courseName}</p>
                            <p>通知时间： <fmt:formatDate value="${n.ceateTime}" pattern="yyyy-MM-dd" /></p>
                        </div>
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
        if (student == '' || student == null) {
            location.href = ctx + '/studentApi/toLogin';
            return;
        }
    });
    function toNotice(leave_id,id) {
        $.ajax({
            url:ctx+'/leaveNotice/updateNoticeById',
            data:{id:id},
            dataType:'json',
            type:'get',
            success:function (res) {
                if(res==1){
                    console.info("已读");
                }
            },error:function (e) {
                console.log(e)
            }
        });
        location.href = ctx + '/leaveApi/selectByPrimaryKey/'+leave_id;
    }
</script>
</html>
