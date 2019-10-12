$(function() {
  $('#login').click(function() {
    var sno  = $('#login_sno').val();
    var password = $('#login_password').val();

    if(sno==""||sno==null){
    	mui.alert("请输入学号");
    	return;
		}

    if(password==""||password==null){
      mui.alert("请输入密码");
      return;
    }

    $.ajax({
      url:ctx+'/studentApi/checkLogin',
      data:{sno:sno,password:password},
      dataType:'json',
      type:'post',
      success:function(data) {
        if(data.body == true){
          location.href=ctx+'/student/index.html';
        }else{
          mui.alert('学号或密码错误');
        }
      },eror:function(e) {
        mui.alert('Server Inter Error');
      }
    });


  });
});