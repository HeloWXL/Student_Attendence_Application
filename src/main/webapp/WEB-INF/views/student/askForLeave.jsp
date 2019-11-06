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
        var ctx = '${ctx }'
    </script>
</head>
<body>
<%--头部导航--%>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="javascript:history.back(-1);"></span>
    <h1 class="mui-title">请假</h1>
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
                        <input type="text" class="mui-input-clear" id="leaveTitle">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>开始时间：</label>
                        <input type="text" class="mui-input-clear" placeholder="请选择开始时间" id="start">
                    </div>
                </li>
                <li class="mui-table-view-cell">
                    <div class="mui-input-row">
                        <label>结束时间：</label>
                        <input type="text" class="mui-input-clear" placeholder="请选择结束时间" id="end">
                    </div>
                </li>
                <div class="mui-input-row">
                    <label>事由：</label>
                    <textarea id="leaveReason" rows="8" placeholder="  请写明请假缘由"
                              style="width: 95%; margin-left: 8px;border: 1px solid #BBBBBB;"></textarea>
                </div>
                <div class="btn">
                    <button type="button" class="mui-btn mui-btn-primary" id="submit">提交</button>
                    <button type="button" class="mui-btn" id="reset">重置</button>
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

<script type="text/javascript">
    $(function () {
        // 判断用户是否登录
        if ("${studentsession}" == '' || "${studentsession}" == null) {
            location.href = ctx + '/studentApi/toLogin';
            return;
        }
        // 开始日期选择
        $('#start').click(function () {
            document.activeElement.blur();
            var $dom = $('#start');
            dateSelect($dom);
        });
        //结束日期选择
        $('#end').click(function () {
            document.activeElement.blur();
            var dom1 = $('#end');
            dateSelect(dom1);
        });

        // 提交请假单
        $('#submit').click(function () {
            $('#studentsno').removeAttr("disabled");
            var studentSno = $.trim($("#studentsno").val());
            if(studentSno==null||studentSno==''){
                mui.alert("学号不能为空");
                return;
            }
            var leaveTitle = $.trim($("#leaveTitle").val());
            if(leaveTitle==null||leaveTitle==''){
                mui.alert("请假标题不能为空");
                return;
            }
            var start = $.trim($("#start").val());
            if(start==null||start==''){
                mui.alert("开始时间不能为空");
                return;
            }
            var end = $.trim($("#end").val());
            if(end==null||end==''){
                mui.alert("结束时间不能为空");
                return;
            }
            var leaveReason = $.trim($("#leaveReason").val());
            if(leaveReason==null||leaveReason==''){
                mui.alert("请假原因不能为空");
                return;
            }

            // 将数据封装成对象
            var leave = {
                studentSno: studentSno,
                leaveReason: leaveReason,
                startTime: getDate(start),
                endTime: getDate(end),
                leaveTitle: leaveTitle,
                coundelorId: 1
            };
            // 向后台提交数据
            $.ajax({
                url: ctx + '/leaveApi/insertSelective',
                data: JSON.stringify(leave),
                dataType: 'json',
                type: 'post',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.body == 1) {
                        mui.alert('提交成功', function () {
                            history.back(-1);
                        });
                    } else {
                        mui.alert('提交失败', function () {
                            reset();
                        });
                    }
                }, error: function (e) {
                    mui.alert('服务器内部错误');
                }
            });
        });
        $("#reset").click(function () {
            $('#leaveReason').val('');
            $('#start').val('');
            $('#end').val('');
            $('#leaveTitle').val('');
        });
    });

    function getDate(strDate) {
        var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
            function (a) {
                return parseInt(a, 10) - 1;
            }).match(/\d+/g) + ')');
        return date;
    }

    // 时间选择函数
    function dateSelect(dom) {
        var $a = dom;
        var Date = new mui.DtPicker({
            type: 'date'
        });
        Date.show(function (item) {
            //这里你可以用console 看看回调函数中的item的值
            var endDate = item.y.text + '-' + item.m.text + '-' + item.d.text;
            $a.val(endDate);
        });
    }

</script>
</html>
