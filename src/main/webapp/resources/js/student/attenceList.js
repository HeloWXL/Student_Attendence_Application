var page = 1 ;
$(function () {
    if (student == '' || student == null) {
        location.href = ctx + '/studentApi/toLogin';
        return;
    }
    loadStuSignList(page,studentId)
})

/**
 * 跳转到详情页面
 */
function toAttencedetailView(_this) {
    var signId  = $(_this).attr("id");
    console.log(signId);
    location.href=ctx+"/signApi/toAttenceDetail/"+signId;
}

/**
 * 加载学生考勤列表
 * @param page
 * @param studentId
 */
function loadStuSignList(page,studentId) {
    $.ajax({
        url:ctx+'/signApi/getSignStuByPage',
        data:{
            pageNo:page,
            pageSize:10,
            stuId:studentId
        },
        dataType:'json',
        type:'get',
        success:function (data) {
            $('#signList').empty();
            $('#count').html(data.count);

            for(var i = 0 ; i<data.data.length;i++){
                var $node = $(' <li id = "'+data.data[i].signId+'" class="mui-table-view-cell mui-collapse" onclick="toAttencedetailView(this)">\n' +
                    '            <a href='+data.data[i].signId+'>'+data.data[i].startTime+'<span class="mui-icon mui-icon-arrowright"></span></a>\n' +
                    '        </li>')
                $('#signList').append($node);
            }
        }
    })
}