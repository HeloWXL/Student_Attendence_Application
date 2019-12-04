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
        var studentId = "${studentsession.studentId}";
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
            <li class="layui-timeline-item" id="start-time">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">上课签到</h4>
                </div>
            </li>
            <li class="layui-timeline-item" id="end-time" style="display: none">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">下课签退</h4>
                </div>
            </li>
        </ul>
    </div>

    <div class="c-center">
        <div class="center">
            <div class="mui-card" id="start" onclick="start_time(this)">
                <!--内容区-->
                <div class="mui-card-content">
                    上课签到
                    <p>08:00:12</p>
                </div>
            </div>

            <div class="mui-card" id="end" style="display: none">
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
<script>
    $(function () {
        if(student==''||student==null) {
            location.href = ctx+'/studentApi/toLogin';
            return;
        }
    })
// 上课签到
function start_time(_this) {
        var myDate = new Date();
        var now=myDate.toLocaleString();     //获取当前时间
        console.log(now)
        var sign = {
            signLocation:'科大国创软件股份有限公司-合肥',
            studentId:parseInt(studentId),
            courseId:1
        }
        $.ajax({
            url:ctx+'/signApi/insertSign',
            data: JSON.stringify(sign),
            type:'post',
            dataType:'json',
            contentType: 'application/json; charset=utf-8',
            success:function (data) {
                if(data.body==1){
                    //隐藏本身
                    $(_this).hide();
                    $("#un-start-time").show();
                    var $start = ' <h3 class="layui-timeline-title">打卡时间 '+now+'</h3>\n' +
                        '                    <p>\n' +
                        '                        地点：科大国创软件股份有限公司-合肥\n' +
                        '                    </p>';
                    //获取到上课签到时间
                    $("#start-time div").append($start)
                    $("#end-time").show()
                    $("#end").show()
                }
            }
        })
    }
</script>
</html>
