var signId = '';
var status = 0 ;
$(function () {
    if (student == '' || student == null) {
        location.href = ctx + '/studentApi/toLogin';
        return;
    }
    if (path == 'default') {
        mui.alert("请更换你的头像",function () {
            location.href=ctx +'/studentApi/myInfo';
        });
        return;
    }
    getStuNewSignInfo();
    var $starts =  $("#fileElem");
    if($("#success").attr("status")=="0"){
        mui.alert("考勤未开始",function () {
            location.href=ctx+'/studentApi/index';
        });
        return ;
    }else if($("#success").attr("status")=="2"){
        mui.alert("考勤已结束,继续签到将会被记为迟到");
        status = 1;
        $starts.change(function () {
            if($(this).val() != ""){
                start_time(status)
            }
        });
    }else{
        $starts.change(function () {
            if($(this).val() != ""){
                start_time(status)
            }
        });
    }
    var $ends =  $("#fileElem1");
    $ends.change(function () {
        if($(this).val() != ""){
            end_time()
        }
    });
});

function convertDateFromString(dateString) {
    if (dateString) {
        var date = new Date(dateString.replace(/-/,"/"))
        return date;
    }
}

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
function start_time() {
    var formData = new FormData();
    var file =  $("#fileElem")[0].files[0];
    formData.append("file",file);
    var myDate = new Date();
    var now = myDate.toLocaleString();     //获取当前时间
    formData.append("signLocation",locations);
    formData.append("studentId",parseInt(studentId));
    formData.append("courseId",courseId);
    formData.append("status",status);
    formData.append("releaseId",$("#releaseId").val());
    $.ajax({
        url: ctx + '/signApi/insertSign',
        data: formData,
        type: 'post',
        dataType: 'json',
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.body == 1) {
                mui.alert("签到成功");
                //隐藏本身
                $("#start").hide();
                var $start = ' <h3 class="layui-timeline-title">打卡时间 ' + now + '</h3>\n' +
                    '                    <p>\n' +
                    '                        地点：'+locations+'\n' +
                    '                    </p>';
                //获取到上课签到时间
                $("#start-time div").append($start)
                $("#end-time").show()
                $("#end").show()
            }else{
                mui.alert("签到失败");
            }
        }
    })
}

//下课签退
function end_time() {
    var formData = new FormData();
    var file =  $("#fileElem1")[0].files[0];
    formData.append("file",file);
    var myDate = new Date();
    var now = myDate.toLocaleString();     //获取当前时间
    formData.append("signOutLocation",locations);
    formData.append("studentId",parseInt(studentId));
    $.ajax({
        url: ctx + '/signApi/updateSignById',
        type: 'post',
        data: formData,
        dataType: 'json',
        contentType: false,
        processData: false,
        success: function (data) {
            if (data == 1) {
                mui.alert("签退成功");
                //隐藏本身
                $("#end").hide();
                var $end = ' <h3 class="layui-timeline-title">打卡时间 ' + now + '</h3>\n' +
                    '                    <p>\n' +
                    '                        地点：'+locations+'\n' +
                    '                    </p>';
                //获取到上课签到时间
                $("#end-time div").append($end)
                $("#start-time").show()
                $("#start").show()
            }else{
                mui.alert("签退失败");
            }
        }
    })
}