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
        var $node = $('<li class="mui-table-view-cell">\n' +
            '      <span class="mui-badge mui-badge-success" id="success">未开课</span>\n' +
            '      <a href = "/courseApi/selectCourseDetailByCid/'+data.body.list[i].courseId+'">\n' +
            '        <h4>'+data.body.list[i].courseName+'</h4>\n' +
            '        <p>\n' +
            '          上课时间：<span>'+data.body.list[i].classarrangement+'</span>\n' +
            '        </p>\n' +
            '        <p>\n' +
            '          专业：<span>信管</span>\n' +
            '        </p>\n' +
            '        <p>\n' +
            '          上课人数：<span>'+data.body.count+'</span>人\n' +
            '        </p>\n' +
            '        <p>开课时间：<span>'+data.body.list[i].starttime+'</span>&nbsp;&nbsp;&nbsp;结课时间：<span>'+data.body.list[i].endtime+'</span></p>\n' +
            '      </a>\n' +
            '    </li>');
        $("#commentDetail").append($node);
      }
    }
  });
});