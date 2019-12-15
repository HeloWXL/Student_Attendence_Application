<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/3
  Time: 11:55 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        var student = "${studentsession}";
        var studentSno = "${studentsession.studentSno}";
    </script>
    <style>
        body {
            font-family: 'Helvetica Neue',Helvetica,sans-serif;
            font-size: 14px;
            line-height: 21px;
            color: #000;
            background-color: #efeff4;
            -webkit-overflow-scrolling: touch;
        }
        .mui-content>.mui-table-view:first-child {
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
        .mui-table-view-cell.mui-active{
            background-color: #0062CC;
        }
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">我的请假记录</h1>
</header>
<div class="mui-content">
    <ul class="mui-table-view" id="leaveList">
    </ul>
    <div id="loading">
        <button type="button" class="mui-btn mui-btn-outlined" style="display: none">加载</button>
    </div>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script>
    $(function () {
        // 判断用户是否登录
        if (student == '' || student == null) {
            location.href = ctx + '/studentApi/toLogin';
            return;
        }
        var pageNo = 1;
        $.ajax({
            url: ctx + '/leaveApi/selectLeaveByPage/',
            dataType: 'json',
            type: 'get',
            data: {pageNo: pageNo, pageSize: 10, studentSno: studentSno},
            contentType: 'application/json; charset=utf-8',
            async: false,
            success: function (data) {
                for (var i = 0; i < data.body.list.length; i++) {
                    var reason = data.body.list[i].leaveTitle;
                    if(reason.length>20){
                        reason = reason.substring(0,20)+".....";
                    }
                    var $node = $('<li class="mui-table-view-cell mui-media">\n' +
                        '            <a href="/leaveApi/selectByPrimaryKey/' + data.body.list[i].leaveId + '">\n' +
                        '                <div class="mui-media-body">\n' +
                        '                    <div>标题：' + reason+ '</div>\n' +
                        '                    <p class=\'mui-ellipsis\'>&nbsp;&nbsp;&nbsp;缘由：' + data.body.list[i].leaveReason + '</p>\n' +
                        '                    <p style="float: right;font-size: 12px">开始时间： ' + data.body.list[i].startTime + ' &nbsp;&nbsp;&nbsp;结束时间：' + data.body.list[i].endTime + '</p>\n' +
                        '                </div>\n' +
                        '            </a>\n' +
                        '        </li>')
                    $("#leaveList").append($node);
                }
            }
        });
    });

    function GMTToStr(time) {
        var date = new Date(time)
        var Str = date.getFullYear() + '-' +
            (date.getMonth() + 1) + '-' +
            date.getDate()
        return Str
    }
</script>

</html>
