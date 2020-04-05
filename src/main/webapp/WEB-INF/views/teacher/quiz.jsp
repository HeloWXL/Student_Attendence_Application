<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>提问</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="${ctx}/resources/mui/mui.min.css" rel="stylesheet"/>
    <link href="${ctx}/resources/mui/mui.picker.min.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${ctx}/resources/ico/app.ico"/>
    <script>
        var ctx = '${ctx}';
        var teacher = "${teacher}";
        var teacherid ="${teacher.teacherId}";
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
    <div class="mui-input-row">
        <label>课程：</label>
        <input type="text" class="mui-input-clear" id="courseName" placeholder="请选择请假课程">
    </div>
    <div class="c1">
        <button type="button" class="mui-btn mui-btn-primary" id="random">随机提问</button>
    </div>
    <div class="c1">
        <button type="button" class="mui-btn mui-btn" id="quiz">提问</button>
    </div>
</div>
<div id="quizStuList" style="display: none">
    <ul class="mui-table-view" id="studengList" >
    </ul>
</div>
</body>
<%--引入js文件--%>
<script src="${ctx}/resources/mui/mui.min.js" type="application/javascript"></script>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/mui/mui.picker.min.js" type="application/javascript"></script>
<%--引入自定义js文件--%>
<script src="${ctx}/resources/js/teacher/quiz.js" type="application/javascript"></script>

<script>
    $(function () {

        // 绑定课程
        var courseList =loadCourse(teacherid);
        // 课程选择
        var Picker = new mui.PopPicker();
        Picker.setData(courseList);
        courseName.addEventListener('tap', function(event) {
            var $dom  = $(this);
            Picker.show(function(items) {
                var vals = items[0].text;
                var name = items[0].value;
                $dom.val(vals);
                $dom.attr('name',name);
            });
        }, false);

        $('#random').click(function () {
            var courseid = $("#courseName").val();
            if(courseid==''||courseid==null){
                mui.alert("请选择课程");
                return;
            }
            $(this).hide();
            $(this).parent().siblings().hide();
            $('#quizStuList').show();
            var $node = geiStudentListbyRandom($("#courseName").attr("name"));
            $("#studengList").append($node);
        });

        $('#quiz').click(function () {
            var courseid = $("#courseName").val();
            if(courseid==''||courseid==null){
                mui.alert("请选择课程");
                return;
            }
            $(this).hide();
            $(this).parent().siblings().hide();
            $('#quizStuList').show();
            geiStudentList($("#courseName").attr("name"));

        });
    });

    //随机抽取
    function geiStudentListbyRandom(cid){
        var node = '';
        $.ajax({
            url:ctx+'/studentApi/selectStudentByCid/',
            dataType:'json',
            data:{
                cid:cid
            },
            type:'get',
            async:false,
            success:function(data) {
                var i = 0;
                if(data.body.length==0){
                    node = '';
                }else if(data.body.length==1){
                    node = ('<li class="mui-table-view-cell">\n'+
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
                }else{
                    i = Math.floor(Math.random()*data.body.length);
                    node = ('<li class="mui-table-view-cell">\n'+
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
                }
            }
        });
        return node;
    }

    function geiStudentList(cid){
        $.ajax({
            url:ctx+'/studentApi/selectStudentByCid/',
            dataType:'json',
            data:{
                cid:cid
            },
            type:'get',
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
    /**
     * 加载课程列表
     * @returns {any[]}
     */
    function loadCourse(teacherid) {
        var courseList = new Array();
        $.ajax({
            url:ctx+'/teacherApi/getCourseByTid',
            type:'get',
            async:false,
            dataType:'json',
            data:{
                tid:teacherid
            },
            success:function (data) {
                for(var i = 0 ;i<data.list.length;i++){
                    var obj = new Object();
                    obj.value = data.list[i].courseId;
                    obj.text = data.list[i].courseName;
                    courseList.push(obj)
                }
            }
        });
        return courseList;
    }
</script>
</html>
