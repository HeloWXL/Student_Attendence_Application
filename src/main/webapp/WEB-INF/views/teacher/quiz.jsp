<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>提问</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx}';
        var teacher = "${teacher}";
    </script>
    <style>
        .mui-content {
            text-align: center;
        }
        .c1 button {
            width: 300px;
            height: 50px;
            margin-top: 20px;
        }
        li div{
            float: left;
        }
    </style>
</head>
<body>
<header class="mui-bar mui-bar-nav mui-badge-primary">
    <span class="mui-icon mui-icon-back" onclick="history.back()"></span>
    <h1 class="mui-title" style="color: #ffffff;">提问</h1>
</header>
<div class="mui-content">
    <div class="c1">
        <button type="button" class="mui-btn mui-btn-primary" id="random">随机提问</button>
    </div>
    <div class="c1">
        <button type="button" class="mui-btn mui-btn" id="quiz">提问</button>
    </div>
</div>
<div id="quizStuList" style="display: none">
    <div style="text-align: center;" >
        <h3>学生列表</h3>
    </div>
    <ul class="mui-table-view" id="studengList" >
    </ul>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/teacher/quiz.js" type="application/javascript"></script>

<script>
    $(function () {
        $('#random').click(function () {
            $(this).hide();
            $(this).parent().siblings().hide();
        });

        $('#quiz').click(function () {
            $(this).hide();
            $(this).parent().siblings().hide();
            $('#quizStuList').show()
            geiStudentList(15)
        });
    });

    function geiStudentList(cid){
        $.ajax({
            url:ctx+'/studentApi/selectStudentByCid/',
            dataType:'json',
            data:{
                cid:cid
            },
            type:'get',
            contentType: 'application/json; charset=utf-8',
            success:function(data) {
                for(var i = 0 ;i<data.body.length;i++){
                    var $node = $('<li class="mui-table-view-cell">\n'+
                        '      \n' +
                        ' <div style="border-radius: 50%;width: 100px" >' +
                        '         <img src='+ctx+'/studentApi/getLocalImg?path='+data.body[i].studentPic+' width="100px" height="100px"/>' +
                        ' </div>\n'+
                        '        <div>' +
                        '         <h4>'+data.body[i].studentName+'</h4>\n' +
                        '        <p>\n' +
                        '          学号：<span>'+data.body[i].studentSno+'</span>\n' +
                        '        </p>\n' +
                        '        <p>\n' +
                        '          性别：<span>'+data.body[i].studentSex+'</span>\n' +
                        '        </p>' +
                        '        </div> \n' +
                        '      \n' +
                        '    </li>');
                    $("#studengList").append($node);
                }
            }
        });
    }
</script>
</html>
