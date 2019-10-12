
$(function () {


  $("#myinfo").click(function() {
    location.href=ctx+'/studentApi/person';
  });
  $("#home").click(function() {
    location.href=ctx+'/studentApi/index';
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
})

