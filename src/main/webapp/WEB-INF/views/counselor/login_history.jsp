<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/12/15
  Time: 12:00 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆历史</title>
</head>
<body>
<div class="layui-row">
    <div class="layui-col-xs12">
        <div class="grid-demo grid-demo-bg1">
            <!-- 模块名 -->
            <blockquote class="layui-elem-quote">登陆历史</blockquote>
            <%--            表格--%>
            <div class="center">
                <table id="demo" lay-filter="historyfilter"></table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
