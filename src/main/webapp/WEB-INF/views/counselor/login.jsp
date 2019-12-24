<%--
  Created by IntelliJ IDEA.
  User: 王咸林
  Date: 2019/11/5
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>辅导员登录</title>
    <link href="${ctx}/resources/css/counselor/counselor_login.css" rel="stylesheet">
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/resources/ico/logo.ico"  type=”image/x-icon”>

    <script>
        var ctx = '${ctx }'
    </script>
</head>
<body>
<div class="videozz"></div>
<div class="box">
    <div class="box-a">
        <div class="m-2">
            <div class="m-2-1">
                <form>
                    <div class="m-2-2">
                        <input type="text" placeholder="请输入账号" id="cno"/>
                    </div>
                    <div class="m-2-2">
                        <input type="password" placeholder="请输入密码" id="password"/>
                    </div>
                    <div class="m-2-2">
                        <button type="submit" value="登录" id="login"/>
                        登录
                    </div>
                </form>
            </div>
        </div>
        <div class="m-5">
            <div id="m-5-id-1">
                <div id="m-5-2">
                    <div id="m-5-id-2">
                    </div>
                    <div id="m-5-id-3"></div>
                </div>
            </div>
        </div>
        <div class="m-10"></div>
        <div class="m-xz7"></div>
        <div class="m-xz8 xzleft"></div>
        <div class="m-xz9"></div>
        <div class="m-xz9-1"></div>
        <!-- <div class="m-x10"></div>
        <div class="m-x11"></div>
        <div class="m-x12 xzleft"></div>
        <div class="m-x13"></div>
        <div class="m-x14 xzleft"></div>
        <div class="m-x15"></div>
        <div class="m-x16 xzleft"></div>-->
        <div class="m-x17 xzleft"></div>
        <div class="m-x18"></div>
        <div class="m-x19 xzleft"></div>
        <div class="m-x20"></div>
        <div class="m-8"></div>
        <div class="m-9">
            <div class="masked1" id="sx8">智能考勤App后台管理系统</div>
        </div>
        <div class="m-11">
            <div class="m-k-1">
                <div class="t1"></div>
            </div>
            <div class="m-k-2">
                <div class="t2"></div>
            </div>
            <div class="m-k-3">
                <div class="t3"></div>
            </div>
            <div class="m-k-4">
                <div class="t4"></div>
            </div>
            <div class="m-k-5">
                <div class="t5"></div>
            </div>
            <div class="m-k-6">
                <div class="t6"></div>
            </div>
            <div class="m-k-7">
                <div class="t7"></div>
            </div>
        </div>
        <div class="m-14">
            <div class="ss"></div>
        </div>
        <div class="m-15-a">
            <div class="m-15-k">
                <div class="m-15xz1">
                    <div class="m-15-dd2"></div>
                </div>
            </div>
        </div>
        <div class="m-16"></div>
        <div class="m-17"></div>
        <div class="m-18 xzleft"></div>
        <div class="m-19"></div>
        <div class="m-20 xzleft"></div>
        <div class="m-21"></div>
        <div class="m-22"></div>
        <div class="m-23 xzleft"></div>
        <div class="m-24" id="localtime"></div>
    </div>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/counselor/counselor_login.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/getComputerInfo.js" type="application/javascript"></script>

</html>
