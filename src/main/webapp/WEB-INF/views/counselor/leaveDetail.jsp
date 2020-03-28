<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/><html>
<head>
    <title>Title</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <link rel="shortcut icon" href="${ctx}/resources/ico/logo.ico"/>
    <script>
        var ctx = '${ctx }';
        var counselor = '${counselor}';
    </script>
</head>
<body>
    <div class="layui-row layui-col-space5">
        <div class="layui-col-md6">
            <div class="grid-demo grid-demo-bg1"></div>
        </div>
        <div class="layui-col-md4">
            <div class="grid-demo">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学生姓名：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="studentName"  class="layui-input" value="${leave.student.studentName}">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">学号：</label>
                            <div class="layui-input-inline">
                                <input type="text"   class="layui-input" value="${leave.student.studentSno}">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">标题：</label>
                            <div class="layui-input-inline">
                                <input type="text"  class="layui-input" value="${leave.leaveTitle}">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">课程：</label>
                            <div class="layui-input-inline">
                                <input type="text"  class="layui-input" value="${leave.course.courseName}">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">开始时间：</label>
                            <div class="layui-input-inline">
                                <input type="text"  class="layui-input" value="<fmt:formatDate value="${leave.startTime}" pattern="yyyy-MM-dd"/>">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束时间：</label>
                            <div class="layui-input-inline">
                                <input type="text"  class="layui-input" value="<fmt:formatDate value="${leave.endTime}" pattern="yyyy-MM-dd" />">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">审批状态：</label>
                            <div class="layui-input-inline">
                                <c:if test="${leave.isRead==1}">
                                    <input type="text" class="layui-input" value="已批准">
                                </c:if>
                                <c:if test="${leave.isRead==2}">
                                    <input type="text" class="layui-input" value="未批准">
                                </c:if>
                                <c:if test="${leave.isRead==0}">
                                    <input type="text" class="layui-input"value="未批阅">
                                </c:if>
                            </div>
                        </div>
                        <c:if test="${!empty leave.fileUrl}">
                            <div class="layui-inline">
                                <label class="layui-form-label">附件：</label>
                                <div class="layui-input-inline">
                                    <img src="${ctx}/leaveApi/getLocalImg?path=${leave.fileUrl}" style="width: 150px;height: 150px;">

                                </div>
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
</html>
