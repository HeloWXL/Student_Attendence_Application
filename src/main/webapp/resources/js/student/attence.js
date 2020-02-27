var signId = '';
$(function () {
    if (student == '' || student == null) {
        location.href = ctx + '/studentApi/toLogin';
        return;
    }
    getStuNewSignInfo()
})

// 查询学生当前签到的状态
function getStuNewSignInfo() {
    $.ajax({
        url: ctx + '/signApi/getSignStuId',
        data: {stuId: parseInt(studentId)},
        type: 'get',
        dataType: 'json',
        asnyc:false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            //已签到 未签退
            if (data.isStartStatus == 1 && data.isEndStatus == 0) {
                $("#start").hide();
                var $start = ' <h3 class="layui-timeline-title">打卡时间 ' + data.startTime + '</h3>\n' +
                    '                    <p>\n' +
                    '                        地点：' + data.signLocation + '\n' +
                    '                    </p>';
                //获取到上课签到时间
                $("#start-time div").append($start)
                $("#end-time").show()
                $("#end").show()
            } else if(data.isStartStatus == 1 && data.isEndStatus == 1){//已签到 一起签退
                $("#end").hide()
                $("#start-time").show()
                $("#start").show()
            }
            signId = data.signId;
            return signId;
        }
    })

}

// 上课签到
function start_time(_this) {
    var myDate = new Date();
    var now = myDate.toLocaleString();     //获取当前时间
    var sign = {
        signLocation: locations,
        studentId: parseInt(studentId),
        courseId: 1
    }
    $.ajax({
        url: ctx + '/signApi/insertSign',
        data: JSON.stringify(sign),
        type: 'post',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            if (data.body == 1) {
                //隐藏本身
                $(_this).hide();
                var $start = ' <h3 class="layui-timeline-title">打卡时间 ' + now + '</h3>\n' +
                    '                    <p>\n' +
                    '                        地点：'+locations+'\n' +
                    '                    </p>';
                //获取到上课签到时间
                $("#start-time div").append($start)
                $("#end-time").show()
                $("#end").show()
            }
        }
    })
}

//下课签退
function end_time(_this) {
    var myDate = new Date();
    var now = myDate.toLocaleString();     //获取当前时间

    var sign = {
        signOutLocation: locations,
        signId:signId
    }
    $.ajax({
        url: ctx + '/signApi/updateSignById',
        data: JSON.stringify(sign),
        type: 'post',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            if (data == 1) {
                //隐藏本身
                $(_this).hide();
                var $end = ' <h3 class="layui-timeline-title">打卡时间 ' + now + '</h3>\n' +
                    '                    <p>\n' +
                    '                        地点：'+locations+'\n' +
                    '                    </p>';
                //获取到上课签到时间
                $("#end-time div").append($end)
                $("#start-time").show()
                $("#start").show()
            }
        }
    })
}