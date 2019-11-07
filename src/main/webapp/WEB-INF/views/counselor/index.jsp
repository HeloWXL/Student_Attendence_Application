<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/10/19
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>后台管理首页</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <script>
      var ctx = '${ctx }'
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">学生考勤App后台管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <%--<ul class="layui-nav layui-layout-left">--%>
            <%--<li class="layui-nav-item"><a href="">控制台</a></li>--%>
            <%--<li class="layui-nav-item"><a href="">商品管理</a></li>--%>
            <%--<li class="layui-nav-item"><a href="">用户</a></li>--%>
            <%--<li class="layui-nav-item">--%>
                <%--<a href="javascript:;">其它系统</a>--%>
                <%--<dl class="layui-nav-child">--%>
                    <%--<dd><a href="">邮件管理</a></dd>--%>
                    <%--<dd><a href="">消息管理</a></dd>--%>
                    <%--<dd><a href="">授权管理</a></dd>--%>
                <%--</dl>--%>
            <%--</li>--%>
        <%--</ul>--%>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退出登录</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a id="index">首页</a></li>
                <li class="layui-nav-item"><a id="student">学生管理</a></li>
                <li class="layui-nav-item"><a id="teacher">教师管理</a></li>
                <li class="layui-nav-item"><a id="profession">专业管理</a></li>
                <li class="layui-nav-item"><a id="course">课程管理</a></li>
                <li class="layui-nav-item"><a id="leave">请假管理</a></li>
                <li class="layui-nav-item"><a id="attendce">考勤管理</a></li>
<%--                <li class="layui-nav-item layui-nav-itemed">--%>
<%--                    <a class="" href="javascript:;">学生管理</a>--%>
<%--                    <dl class="layui-nav-child">--%>
<%--                        <dd><a href="javascript:;">列表一</a></dd>--%>
<%--                        <dd><a href="javascript:;">列表二</a></dd>--%>
<%--                        <dd><a href="javascript:;">列表三</a></dd>--%>
<%--                        <dd><a href="">超链接</a></dd>--%>
<%--                    </dl>--%>
<%--                </li>--%>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <iframe align="center" width="100%" height="100%" src="" frameborder="no" border="0" marginwidth="0"
                                            marginheight="20px" scrolling="no" style="background-color: #FFFFFF;"></iframe>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © email - wangxinalin@icloud.com - author by -王咸林
    </div>
</div>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script>
  //JavaScript代码区域
  layui.use('element', function() {
    var element = layui.element;
  });

  $(function () {
      $("iframe").attr("src",ctx+"/counselorApi/toStatistcs")

      $("#index").click(function () {
       $("iframe").attr("src",ctx+"/counselorApi/toStatistcs")
      })
      $("#leave").click(function () {
          $("iframe").attr("src",ctx+"/leaveApi/toCounselorLeaveTable")
      })
      $("#course").click(function () {
          $("iframe").attr("src",ctx+"/courseApi/toCounselorCourseTable")
      })
      $("#student").click(function () {
          $("iframe").attr("src",ctx+"/counselorApi/toCounselorStudentTable")
      })
      $("#teacher").click(function () {
          $("iframe").attr("src",ctx+"/teacherApi/toCounselorTeacherTable")
      })
      $("#profession").click(function () {
          $("iframe").attr("src",ctx+"/professionApi/toCounselorProfessionTable")
      })
  })
</script>
</body>
</html>
