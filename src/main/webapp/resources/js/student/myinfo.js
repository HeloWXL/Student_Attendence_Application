$(function() {
  if(student==''||student==null) {
    location.href = ctx+'/studentApi/toLogin';
    return;
  }
  var $file =  $("#fileElem");
  $file.change(function () {
      if($(this).val() != ""){
          uploadBySno()
      }
  });

  // 获取session的值
  $.ajax({
    url: ctx+'/studentApi/getStudentSession',
    data: {studentBean: 'studentsession'},
    dataType: 'json',
    type: 'post',
    success: function(data) {
      //用户头像展示
      if(data.body.studentPic=='default'){
          $('img[name="userPic"]').attr('src', ctx+'/resources/images/default.png');
      }else{
          $('img[name="userPic"]').attr('src', ctx+'/uploadApi/getLocalImg?path='+data.body.studentPic);
      }
      $('input[name="username"]').val(data.body.studentName);
      $('input[name="sno"]').val(data.body.studentSno);
      $('input[name="age"]').val(data.body.studentAge);
      $('input[name="sex"]').val(data.body.studentSex);
      // $('input[name="qq"]').val(data.body.studentQq);
      $('input[name="email"]').val(data.body.studentEmail);
      $('input[name="profession"]').val(data.body.professions.professionName);
      $('input[name="apartment"]').val(data.body.professions.apartment);
      $('input[name="school"]').val(data.body.professions.school);
    }
  });
});

/**
 * 更新头像
 */
function uploadBySno() {
    var formData = new FormData();
    var file =  $("#fileElem")[0].files[0];
    formData.append("file",file);
    formData.append("studentId",parseInt(studentId));
    $.ajax({
        url: ctx + '/studentApi/updatePicBySid',
        data: formData,
        type: 'post',
        dataType: 'json',
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.code == 200) {
                $("#head").attr("src",ctx+'/uploadApi/getLocalImg?path='+data.data);
            }else{
                mui.alert(data.data);
            }
        }
    })
}

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

