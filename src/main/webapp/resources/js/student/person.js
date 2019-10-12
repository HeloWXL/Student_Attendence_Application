$(function() {
    $('#myinfo').click(function() {
        location.href=ctx+'/studentApi/person';
    });
    $('#home').click(function() {
        location.href=ctx+'/studentApi/index';
    });
    $('#back').click(function() {
        location.href='/studentApi/removeStudentSession';
    });
    // 获取session的值
    $.ajax({
        url:ctx+'/studentApi/getStudentSession',
        data:{studentBean:'studentsession'},
        dataType:'json',
        type:'post',
        success:function (data) {
            if(data.body==null){
                location.href=ctx+'/studentApi/toLogin';
            }
            $('img[name=\'userPic\']').attr('src',data.body.studentPic);
            $('input[name=\'username\']').val(data.body.studentName);
            $('input[name=\'sno\']').val(data.body.studentSno);
            $('input[name=\'age\']').val(data.body.studentAge);
            $('input[name=\'sex\']').val(data.body.studentSex);
            $('input[name=\'qq\']').val(data.body.studentQq);
            $('input[name=\'email\']').val(data.body.studentEmail);
            $('input[name=\'profession\']').val(data.body.profession.professionName);
            $('input[name=\'apartment\']').val(data.body.profession.apartment);
            $('input[name=\'school\']').val(data.body.profession.school);
        }
    });
});