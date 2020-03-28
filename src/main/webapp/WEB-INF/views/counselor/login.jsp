
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>辅导员登录</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/resources/ico/logo.ico" type=”image/x-icon”>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Custom Theme files -->
    <link href="${ctx}/resources/css/counselor/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- //Custom Theme files -->
    <!-- Style-CSS -->
    <link rel="stylesheet" href="${ctx}/resources/css/counselor/css/font-awesome.css">
    <!-- web font -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <!--web font-->
    <script>
        var ctx = '${ctx }'
    </script>
</head>
<body>
<!--//header-->
<div class="main-content-agile">
    <div class="sub-main-w3">
        <h2>学生智能考勤后台管理系统</h2>
        <form>
            <div class="pom-agile">
                <span class="fa fa-user" aria-hidden="true"></span>
                <input placeholder="教师工号（例如：1001）" name="Name" class="user" type="text" required="" id="cno">
            </div>
            <div class="pom-agile">
                <span class="fa fa-key" aria-hidden="true"></span>
                <input placeholder="密码（例如：123456）" name="Password" class="pass" type="password" required="" id="password">
            </div>
            <div class="sub-w3l">
                <div class="sub-agile">
                    <input type="checkbox" id="brand1" value="">
                    <label for="brand1">
                        <span></span>记住我</label>
                </div>
                <a href="#">忘记密码?</a>
                <div class="clear"></div>
            </div>
            <div class="right-w3l">
                <input type="submit" value="Login" id="login">
            </div>
        </form>
    </div>
</div>

</body>
<%--引入js文件--%>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script>
    $(function () {
        $("#login").click(function (e) {
            e.preventDefault();
            var cno = $("#cno").val();
            if (cno == null && cno == '') {
                alert('工号不能为空');
                return;
            }
            var password = $("#password").val();
            if (password == null && password == '') {
                alert('密码不能为空');
                return;
            }
            login(cno, password);
        })
    })

    //登录
    function login(cno, password) {
        $.ajax({
            url: ctx + '/counselorApi/checkLogin',
            data: {
                cno: cno,
                password: password
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data.body == true) {
                    location.href = ctx + "/counselorApi/toCounselorIndex";
                } else {
                    alert('工号或密码错误');
                }
            }, error: function (e) {
                alert('服务器内部错误');
            }
        });
    }

</script>
</html>
