
$(function() {
  if(student==''||student==null) {
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
  // 通知界面
  $('#notice').click(function() {
      location.href=ctx+'/studentApi/notice';
  });
// 请假页面
  $('#askForLeave').click(function() {
    location.href=ctx+'/leaveApi/toAskForLeave';
  });
// 请假记录
  $('#leaveList').click(function() {
    location.href=ctx+'/leaveApi/toLeaveList/'+studentSno;
  });
//签到界面
  mui('#attendance').on('tap','img',function(){
    location.href=ctx+'/signApi/toAttence/'+professionId;
  });

  mui('#mycourse').on('tap','img',function(){
      location.href=ctx+'/studentApi/myCourse/'+studentSno;
  });
  //我的签到列表
  mui('#attendancelist').on('tap','img',function(){
    location.href=ctx+'/signApi/toAttenceList';
  });

  mui('#mycourselist').on('tap','img',function(){
    mui.alert('正在建设中.....');
  });

});

