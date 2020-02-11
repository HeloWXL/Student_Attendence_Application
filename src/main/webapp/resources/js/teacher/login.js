$(function() {
    $('#login').click(function() {
        var tno  = $.trim($('#tno').val());
        if(tno==''||tno==null){
            mui.alert('请输入工号');
            return;
        }
        var password = $('#password').val();
        if(tno==''||tno==null){
            mui.alert('请输入密码');
            return;
        }
        login(tno,password);
    });
});
// 教师登录
function login(tno,password) {
    // 教师登录
    $.ajax({
        url:ctx+'/teacherApi/checkLogin',
        data:{tno:tno,password:password},
        dataType:'json',
        type:'post',
        success:function(data) {
            if(data.body == true){
                location.href=ctx+'/teacherApi/toIndex';
            }else{
                mui.alert("用户名或密码错误！！")
            }
        },error:function(e) {
            mui.alert("Server Inter Error")
        }
    });
}