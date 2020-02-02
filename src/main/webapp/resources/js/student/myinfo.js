$(function() {

  if(student==''||student==null) {
    location.href = ctx+'/studentApi/toLogin';
  }

  // 获取session的值
  $.ajax({
    url: ctx+'/studentApi/getStudentSession',
    data: {studentBean: 'studentsession'},
    dataType: 'json',
    type: 'post',
    success: function(data) {
      $('img[name="userPic"]').attr('src', ctx+data.body.studentPic);
      $('input[name="username"]').val(data.body.studentName);
      $('input[name="sno"]').val(data.body.studentSno);
      $('input[name="age"]').val(data.body.studentAge);
      $('input[name="sex"]').val(data.body.studentSex);
      // $('input[name="qq"]').val(data.body.studentQq);
      $('input[name="email"]').val(data.body.studentEmail);
      $('input[name="profession"]').val(data.body.profession.professionName);
      $('input[name="apartment"]').val(data.body.profession.apartment);
      $('input[name="school"]').val(data.body.profession.school);
    }
  });


  // 返回个人信息页面
  $('#myinfo').click(function() {
    location.href = ctx+'/studentApi/myInfo';
  });
  // 返回首页
  $('#home').click(function() {
    location.href = ctx+'/studentApi/index';
  });
  // 退出登录
  $('#back').click(function() {
    location.href = ctx+'/studentApi/removeStudentSession';
  });
})

