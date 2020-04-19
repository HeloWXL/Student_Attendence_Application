
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>管理员首页</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="shortcut icon" href="${ctx}/resources/ico/logo.ico"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/user.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/global_style.css">
    <script>
        var ctx = '${ctx }';
        var admin = '${admin}';
    </script>
</head>
<body>
<div class="tm-tpl tpl-white-hn" style-name="tpl-white-hn">
    <!--导航栏-->
    <div class="tpl-left-sidebar">
        <ul>
            <li class="logo">
                <img class="user-upload" src="${ctx}/resources/images/default.png" alt="" title="admin">
                <span>${admin.nickName}</span>
            </li>
            <li class="nav-item">
                <a class="a-item" name=${ctx}/counselorApi/toStatistcs onclick="getUrl(this)"><i
                        class="fa fa-database nav-icon"></i><span>首页</span></a>
            </li>
            <li class="nav-item">
                <a class="a-item" name=${ctx}/professionApi/toCounselorProfessionTable onclick="getUrl(this)"><i
                        class="fa fa-database nav-icon"></i><span>专业管理</span></a>
            </li>
            <li class="nav-item">
                <a class="a-item" name=${ctx}/courseApi/toCounselorCourseTable onclick="getUrl(this)"><i
                        class="fa fa-database nav-icon"></i><span>课程管理</span></a>
            </li>
            <li class="nav-item">
                <a class="a-item" name=${ctx}/teacherApi/toCounselorTeacherTable onclick="getUrl(this)"><i
                        class="fa fa-user-secret nav-icon"></i><span>教师管理</span></a>
            </li>

            <li class="nav-item">
                <a class="a-item" name=${ctx}/counselorApi/toCounselorStudentTable onclick="getUrl(this)"><i
                        class="fa fa-user-secret nav-icon"></i><span>学生管理</span></a>
            </li>
            <li class="nav-item">
                <a class="a-item" name=${ctx}/leaveApi/toCounselorLeaveTable onclick="getUrl(this)"><i
                        class="fa fa-user-secret nav-icon"></i><span>请假管理</span></a>
            </li>

            <li class="nav-item">
                <a class="a-item" name=${ctx}/signApi/toAttenceForCounselor onclick="getUrl(this)"><i
                        class="fa fa-user-secret nav-icon"></i><span>考勤管理</span></a>
            </li>
        </ul>
    </div>
    <!--右侧内容-->
    <div class="tpl-right-item">
        <div class="tpl-body">
            <!--头部-->
            <div class="tpl-header">
                <div class="tpl-header-fluid">
                    <div class="tpl-button switch-list">
                        <i class="fa fa-hand-o-left"></i>
                    </div>
                    <div class="tpl-button text">
                        <a href=""><i class="fa fa-home"></i> 首页</a>
                    </div>
                    <div class="tpl-userbar">
                        <ul>
                            <li><a id="jbzl">${admin.nickName}</a></li>
                            <li><a href="${ctx}/logoutUser" class="logout"><i class="fa fa-power-off"></i>退出登录</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--内容-->
            <div class="tpl-content">
                <div class="content-pannel clearfix" style="height: 810px">
                    <iframe align="center" width="100%" height="100%" src="${ctx}/counselorApi/toStatistcs" frameborder="no"
                            border="0" marginwidth="0"
                            marginheight="20px" scrolling="no" style="background-color: #FFFFFF;"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script>
    /**
     * 跳转页面
     * @param _this
     */
    function getUrl(_this) {
        var url = $(_this).attr('name');
        $("iframe").attr("src", url)
    }
</script>
</html>
