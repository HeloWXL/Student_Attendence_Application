
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>智能考勤后台管理首页</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="shortcut icon" href="${ctx}/resources/ico/logo.ico"/>
    <script>
      var ctx = '${ctx }';
      var counselor = '${counselor}';
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">学生考勤App后台管理</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${ctx}/resources/images/default.png" class="layui-nav-img">
                    ${counselor.counselorName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${ctx}/counselorApi/removeCounselorSession">退出登录</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a  name=${ctx}/counselorApi/toStatistcs onclick="getUrl(this)">首页</a></li>
                <li class="layui-nav-item"><a  name=${ctx}/counselorApi/toCounselorStudentTable onclick="getUrl(this)">学生管理</a></li>
                <li class="layui-nav-item"><a name=${ctx}/teacherApi/toCounselorTeacherTable onclick="getUrl(this)">教师管理</a></li>
                <li class="layui-nav-item"><a name=${ctx}/professionApi/toCounselorProfessionTable onclick="getUrl(this)">专业管理</a></li>
                <li class="layui-nav-item"><a name=${ctx}/courseApi/toCounselorCourseTable onclick="getUrl(this)">课程管理</a></li>
                <li class="layui-nav-item"><a name=${ctx}/leaveApi/toCounselorLeaveTable onclick="getUrl(this)">请假管理</a></li>
                <li class="layui-nav-item"><a name=${ctx}/signApi/toAttenceForCounselor onclick="getUrl(this)">考勤管理</a></li>
                <li class="layui-nav-item"><a name=${ctx}/FaceApi/toApiView onclick="getUrl(this)">FaceApi管理</a></li>

            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <iframe align="center" width="100%" height="100%" src=${ctx}/counselorApi/toStatistcs frameborder="no" border="0" marginwidth="0"
                                            marginheight="20px" scrolling="no" style="background-color: #FFFFFF;"></iframe>
        </div>
    </div>
</div>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/counselor/counselor_index.js" type="application/javascript"></script>
</body>
</html>
