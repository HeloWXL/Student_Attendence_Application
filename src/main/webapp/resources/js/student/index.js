
$(function() {
  if('${studentsession}'==''||'${studentsession}'==null) {
    location.href = ctx+'/studentApi/toLogin';
    return;
  }
// 个人信息界面
  $('#myinfo').click(function() {
    location.href=ctx +'/studentApi/myInfo';
  });
// 首页界面
  $('#home').click(function() {
    location.href=ctx+'/studentApi/index';
  });
// 请假页面
  $('#askForLeave').click(function() {
    location.href=ctx+'/studentApi/askForLeave';
  });


  mui('#attendance').on('tap','img',function(){
    mui.alert('正在建设中.....');
  });

  mui('#mycourse').on('tap','img',function(){
    mui.alert('正在建设中.....');
  });

  mui('#attendancelist').on('tap','img',function(){
    mui.alert('正在建设中.....');
  });

  mui('#mycourselist').on('tap','img',function(){
    mui.alert('正在建设中.....');
  });

});
