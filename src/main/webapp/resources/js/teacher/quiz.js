$(function () {
    // 判断教师是否登录，未登录直接跳转到登录界面
    if (teacher == null || teacher == '') {
        location.href = ctx + "/teacherApi/toLogin"
    }

});