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
        var ctx = '${ctx }'
    </script>
    <style>
        .mui-title{
            color: #FFFFFF;
        }
        h4{
            font-family: "lucida console";
            font-weight: 400;
        }
        #success{
            border-radius: 0px 100px;
            position: absolute;
            top: 10px;
            right: 2px;
        }
        #fail{
            border-radius: 0px 100px;
            position: absolute;
            top: 10px;
            right: 2px;
        }
        .mui-btn-outlined{
            width: 100%;
            border: none;
            outline: none;
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
    $(function() {
        // 判断用户是否登录
        if("${studentsession}"==''||"${studentsession}"==null) {
            location.href = ctx+'/studentApi/toLogin';
            return;
        }
        var pageNo = 1;
        var studentSno ="${studentsession.studentSno}";
        $.ajax({
            url:ctx+'/leaveApi/selectLeaveByPage/',
            dataType:'json',
            type:'get',
            data:{pageNo:pageNo,pageSize:10,studentSno:studentSno},
            contentType: 'application/json; charset=utf-8',
            async:false,
            success:function(data) {
                for(var i = 0 ;i<data.body.list.length;i++){
                    // var $node = $('<li class="mui-table-view-cell">\n' +
                    //     '<span class="mui-badge mui-badge-success" id="success">已批阅</span>\n' +
                    //     '<a href = "/leaveApi/selectByPrimaryKey/'+data.body.list[i].leaveId+'">\n' +
                    //     '<h4>'+data.body.list[i].leaveTitle+'</h4>\n' +
                    //     '<p>\n' +
                    //     '请假课程：<span>高等数学</span>\n' +
                    //     '</p>\n' +
                    //     '<p>开始时间：<span>'+data.body.list[i].startTime+'</span>&nbsp;&nbsp;&nbsp;结束时间：<span>'+data.body.list[i].endTime+'</span></p>\n' +
                    //     '\n' +
                    //     '</a>\n' +
                    //     '</li>');
                    var $node = $('<li class="mui-table-view-cell">' +
                        '<span class="mui-badge mui-badge-success" id="success">已批阅</span>\n' +
                        '<a href = "/leaveApi/selectByPrimaryKey/'+data.body.list[i].leaveId+'">' +
                        '<div class="mui-card">\n' +
                        '\t<div class="mui-card-header">' +
                        '<h4>'+data.body.list[i].leaveTitle+'</h4>' +
                        '</div>\n' +
                        '\t<div class="mui-card-content">' +
                        '<p>请假课程：<span>高等数学</span></p>' +
                        '</div>\n' +
                        '\t<div class="mui-card-footer">' +
                        ' <p>开始时间：<span>'+data.body.list[i].startTime+'</span>&nbsp;&nbsp;&nbsp;结束时间：<span>'+data.body.list[i].endTime+'</span></p>\n' +
                        '</div>\n' +
                        '</div>\n'+
                        '</li>')

                    $("#leaveList").append($node);
                }
            }
        });
    });

    function GMTToStr(time){
        var date = new Date(time)
        var Str=date.getFullYear() + '-' +
            (date.getMonth()+1) + '-' +
            date.getDate()
        return Str
    }
</script>

</html>
