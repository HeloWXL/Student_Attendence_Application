<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/10/19
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>考勤管理</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/resources/ico/logo.ico"  type=”image/x-icon”>
    <script>
        var ctx = '${ctx}';
        var counselor = '${counselor}';
    </script>
</head>
<body>
<div class="layui-row">
    <div class="layui-col-xs12">
        <div class="grid-demo grid-demo-bg1">
            <!-- 模块名 -->
            <blockquote class="layui-elem-quote">考勤管理</blockquote>
            <%--            操作--%>
            <div class="top">
                <form class="layui-form" action="">
                    <div class="layui-inline">
                        <label class="layui-form-label">姓名：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="userName" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline" id="btn">
                        <div class="layui-inline">
                            <button type="button" class="layui-btn layui-btn-normal" id="query">查询</button>
                        </div>
                        <div class="layui-inline">
                            <button type="button" class="layui-btn layui-btn-normal" id="reset">重置</button>
                        </div>
                    </div>
                </form>
            </div>
            <%--            表格--%>
            <div class="center">
                <table id="demo" lay-filter="leavefilter"></table>
            </div>
        </div>
    </div>
</div>
<!-- 表格标签工具栏 -->
<script type="text/html" id="toolbars">
    <div class="layui-btn-container">
        <div class="layui-btn-group">
            <button type="button" id="export" class="layui-btn layui-btn-normal">
                <i class="layui-icon">&#xe67b;</i>导出
            </button>
        </div>
    </div>
</script>

<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/counselor/counselor_sign.js" type="application/javascript"></script>
</body>
</html>
