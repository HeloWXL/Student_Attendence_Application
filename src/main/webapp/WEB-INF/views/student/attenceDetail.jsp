<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/29
  Time: 10:34 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>打卡详情</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx}';
        var student = "${studentsession}";
        var signs = '${sign}';
    </script>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">签到详情</h1>
</header>
<div class="mui-content">
    <!--日历容器-->
    <div id="calendar">

    </div>
    <div class="top" style="margin-top: 10px;">
        <ul class="layui-timeline">
            <li class="layui-timeline-item" id="start">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">上班打卡</h4>
                    <h3 class="layui-timeline-title">上课签到</h3>
                    <p>
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item" id="end">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h4 class="layui-timeline-title">下课签退</h4>
                    <h3 class="layui-timeline-title"></h3>
                    <p>
                    </p>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script>
    if('${sign.isStartStatus}'==1&&'${sign.isEndStatus}'==0){
        $("#start").show();
        var start = new Date('${sign.startTime}');
        $("#start h3").html("签到时间："+ dateFormat("YYYY-mm-dd HH:MM", start));
        $("#start p").html("地点："+'${sign.signLocation}')
        $("#end").hide();
    }else if('${sign.isStartStatus}'==1&&'${sign.isEndStatus}'==1){
        $("#start").show();
        var start = new Date('${sign.startTime}');
        $("#start h3").html("签到时间："+dateFormat("YYYY-mm-dd HH:MM", start));
        $("#start p").html("地点："+'${sign.signLocation}')
        $("#end").show();
        var end = new Date('${sign.endTime}');
        $("#end h3").html("签到时间："+dateFormat("YYYY-mm-dd HH:MM", end));
        $("#end p").html("地点："+'${sign.signOutLocation}')
    }

    function dateFormat(fmt, date) {
        var ret;
        var opt = {
            "Y+": date.getFullYear().toString(),        // 年
            "m+": (date.getMonth() + 1).toString(),     // 月
            "d+": date.getDate().toString(),            // 日
            "H+": date.getHours().toString(),           // 时
            "M+": date.getMinutes().toString(),         // 分
            "S+": date.getSeconds().toString()          // 秒
            // 有其他格式化字符需求可以继续添加，必须转化成字符串
        };
        for (var k in opt) {
            ret = new RegExp("(" + k + ")").exec(fmt);
            if (ret) {
                fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
            };
        };
        return fmt;
    }
</script>
<%--<script src="${ctx}/resources/js/student/attenceDetail.js" type="application/javascript"></script>--%>
</html>
