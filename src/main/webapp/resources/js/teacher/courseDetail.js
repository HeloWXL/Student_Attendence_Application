$(function() {
  window.addEventListener('swiperight', function() {
    history.back(-1);
  });

  $(function() {
    var startTime = GMTToStr($("#start").val());
    $("#start").val(startTime);
    var endTime = GMTToStr($("#end").val());
    $("#end").val(endTime);
    var cid = $("#courseId").val();
    geiStudentList(cid)

  });

    /**
     * 根据课程的ID查询学生
     * @param cid
     */
  function geiStudentList(cid){
      $.ajax({
          url:ctx+'/studentApi/selectStudentByCid/',
          dataType:'json',
          data:{
            cid:cid
          },
          type:'get',
          contentType: 'application/json; charset=utf-8',
          success:function(data) {
              for(var i = 0 ;i<data.body.length;i++){
                  var $node = $('<li class="mui-table-view-cell">\n'+
                      '      <a >\n' +
                      '        <h4>'+data.body[i].studentName+'</h4>\n' +
                      '        <p>\n' +
                      '          学号：<span>'+data.body[i].studentSno+'</span>\n' +
                      '        </p>\n' +
                      '        <p>\n' +
                      '          性别：<span>'+data.body[i].studentSex+'</span>\n' +
                      '        </p>\n' +
                      '      </a>\n' +
                      '    </li>');
                  $("#studengList").append($node);
              }
          }
      });
  }


  function GMTToStr(time) {
    var date = new Date(time);
    var Str = date.getFullYear() + '-' +
        (date.getMonth() + 1) + '-' +
        date.getDate();
    return Str;
  }
})
