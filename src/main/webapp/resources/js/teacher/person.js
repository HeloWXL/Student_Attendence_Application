$(function() {
    // 判断教师是否登录，未登录直接跳转到登录界面
    if (teacher == null || teacher == '') {
        location.href = ctx + "/teacherApi/toLogin"
    }
    $('#myinfo').click(function() {
        location.href=ctx+'/teacherApi/toPerson';
    });
    $('#home').click(function() {
        location.href=ctx+'/teacherApi/toIndex';
    });
    $('#back').click(function() {
        location.href=ctx+'/teacherApi/removeTeacherSession';
    });
    // 获取session的值
    $.ajax({
        url:ctx+'/teacherApi/getTeacherSession',
        data:{teacherBean:'teacher'},
        dataType:'json',
        type:'post',
        success:function (data) {
            $('input[name=\'name\']').val(data.body.teacherName);
            $('input[name=\'tno\']').val(data.body.teacherTno);
            $('input[name=\'age\']').val(data.body.teacherAge);
            $('input[name=\'sex\']').val(data.body.teacherSex);
            $('input[name=\'job_title\']').val(data.body.teacherJobTitle);
            $('input[name=\'profession\']').val(data.body.profession.professionName);
            $('input[name=\'apartment\']').val(data.body.profession.apartment);
            $('input[name=\'school\']').val(data.body.profession.school);
        }
    });
});