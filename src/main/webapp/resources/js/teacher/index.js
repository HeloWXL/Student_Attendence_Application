$(function () {
  // 判断教师是否登录，未登录直接跳转到登录界面
  if (teacher == null || teacher == '') {
    location.href = ctx + "/teacherApi/toLogin"
  }
  mui("#addCourse").on('tap', 'img', function () {
    location.href = ctx+'/teacherApi/toCourse';
  })
  mui("#courselist").on('tap', 'img', function () {
    location.href = ctx+'/teacherApi/toCourseList';
  })
  mui("#attendancelist").on('tap', 'img', function () {
    mui.alert("正在建设中.....")
  })
  mui("#mycourselist").on('tap', 'img', function () {
    mui.alert("正在建设中.....")
  })
  $("#myinfo").click(function () {
    location.href = ctx + '/teacherApi/toPerson';
  });
  $("#home").click(function () {
    location.href = ctx + '/teacherApi/toIndex';
  });
})