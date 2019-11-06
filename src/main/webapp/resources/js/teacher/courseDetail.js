$(function() {
  // 判断教师是否登录，未登录直接跳转到登录界面
  if (teacher == null || teacher == '') {
    location.href = ctx + "/teacherApi/toLogin"
  }

  window.addEventListener('swiperight', function() {
    history.back(-1);
  });

  $(function() {
    var startTime = GMTToStr($("#start").val())
    $("#start").val(startTime)
    var endTime = GMTToStr($("#end").val())
    $("#end").val(endTime)
  });

  function GMTToStr(time) {
    var date = new Date(time);
    var Str = date.getFullYear() + '-' +
        (date.getMonth() + 1) + '-' +
        date.getDate();
    return Str;
  }
})
