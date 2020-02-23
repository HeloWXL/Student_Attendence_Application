$(function() {
  var pageNo = 1;
  // 判断教师是否登录，未登录直接跳转到登录界面
  if (teacher == null || teacher == '') {
    location.href = ctx + "/teacherApi/toLogin"
  }
  $.ajax({
    url:ctx+'/courseApi/selectCourseListByTno/',
    dataType:'json',
    type:'get',
    data:{pageNo:pageNo,pageSize:10,tid:teacherId},
    contentType: 'application/json; charset=utf-8',
    success:function(data) {
      for(var i = 0 ;i<data.body.list.length;i++){
        var day = getDay(data.body.list[i].classarrangement)

        var start = data.body.list[i].starttime;
        var end = data.body.list[i].endtime;

        var $node = $('<li class="mui-table-view-cell">\n' +
            '      <span class="mui-badge mui-badge-success" id="success">'+courseStatus(start,end)+'</span>\n' +
            '      <a href = +"/selectCourseDetailByCid/'+data.body.list[i].courseId+'>\n' +
            '        <h4>'+data.body.list[i].courseName+'</h4>\n' +
            '        <p>\n' +
            '          上课时间：<span>'+day+'</span>\n' +
            '        </p>\n' +
            '        <p>\n' +
            '          专业：<span>信管</span>\n' +
            '        </p>\n' +
            '        <p>\n' +
            '          上课人数：<span>'+data.body.count+'</span>人\n' +
            '        </p>\n' +
            '        <p>开课时间：<span>'+start+'</span>&nbsp;&nbsp;&nbsp;结课时间：<span>'+end+'</span></p>\n' +
            '      </a>\n' +
            '    </li>');
        $("#commentDetail").append($node);
      }
    }
  });
});

//获取星期几
function getDay(n) {
  switch (n) {
    case 1:
      return "星期一";
      break;
    case 2:
      return "星期二";
      break;
    case 3:
      return "星期三";
      break;
    case 4:
      return "星期四";
      break;
    case 5:
      return "星期五";
      break;

  }
}


function courseStatus(start,end) {
  var now = new Date();

  var start_time = start.replace("-","/");
  start_time = new Date(Date.parse(start_time));

  var end_time = start.replace("-","/");
  end_time = new Date(Date.parse(end_time));

  if(now>start_time&&now<end_time){
    return "进行中";
  }

  if(now<start_time){
    return "未开课";
  }

  if(now>end_time){
    return "已结课";
  }

}