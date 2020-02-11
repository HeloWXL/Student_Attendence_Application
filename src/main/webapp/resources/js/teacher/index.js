$(function () {
    // 判断教师是否登录，未登录直接跳转到登录界面
    if (teacher == null || teacher == '') {
        location.href = ctx + "/teacherApi/toLogin"
    }
    //添加课程
    mui("#addCourse").on('tap', 'img', function () {
        location.href = ctx + '/teacherApi/toCourse';
    });
    //课程列表
    mui("#courselist").on('tap', 'img', function () {
        location.href = ctx + '/teacherApi/toCourseList';
    });
    //请假记录列表
    mui("#leavelist").on('tap', 'img', function () {
        location.href = ctx + '/teacherApi/toLeaveList';
    })

    mui("#mycourselist").on('tap', 'img', function () {
        mui.alert("正在建设中.....")
    })
    mui("#mycourselist").on('tap', 'img', function () {
        mui.alert("正在建设中.....")
    })
    //我的信息
    $("#myinfo").click(function () {
        location.href = ctx + '/teacherApi/toPerson';
    });
    //首页
    $("#home").click(function () {
        location.href = ctx + '/teacherApi/toIndex';
    });
})