$(function () {
    // 判断用户是否登录
    if (student == '' || student == null) {
        location.href = ctx + '/studentApi/toLogin';
        return;
    }
    // 开始日期选择
    $('#start').click(function () {
        document.activeElement.blur();
        var $dom = $('#start');
        dateSelect($dom);
    });
    //结束日期选择
    $('#end').click(function () {
        document.activeElement.blur();
        var dom1 = $('#end');
        dateSelect(dom1);
    });

    // 提交请假单
    $('#submit').click(function () {
        $('#studentsno').removeAttr("disabled");
        var studentSno = $.trim($("#studentsno").val());
        if(studentSno==null||studentSno==''){
            mui.alert("学号不能为空");
            return;
        }
        var leaveTitle = $.trim($("#leaveTitle").val());
        if(leaveTitle==null||leaveTitle==''){
            mui.alert("请假标题不能为空");
            return;
        }
        var start = $.trim($("#start").val());
        if(start==null||start==''){
            mui.alert("开始时间不能为空");
            return;
        }
        var end = $.trim($("#end").val());
        if(end==null||end==''){
            mui.alert("结束时间不能为空");
            return;
        }
        var leaveReason = $.trim($("#leaveReason").val());
        if(leaveReason==null||leaveReason==''){
            mui.alert("请假原因不能为空");
            return;
        }

        // 将数据封装成对象
        var leave = {
            studentSno: studentSno,
            leaveReason: leaveReason,
            startTime: getDate(start),
            endTime: getDate(end),
            leaveTitle: leaveTitle,
            coundelorId: 1
        };
        // 向后台提交数据
        $.ajax({
            url: ctx + '/leaveApi/insertSelective',
            data: JSON.stringify(leave),
            dataType: 'json',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                if (data.body == 1) {
                    mui.alert('提交成功', function () {
                        history.back(-1);
                    });
                } else {
                    mui.alert('提交失败', function () {
                        reset();
                    });
                }
            }, error: function (e) {
                mui.alert('服务器内部错误');
            }
        });
    });
    $("#reset").click(function () {
        $('#leaveReason').val('');
        $('#start').val('');
        $('#end').val('');
        $('#leaveTitle').val('');
    });
});

function getDate(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
        function (a) {
            return parseInt(a, 10) - 1;
        }).match(/\d+/g) + ')');
    return date;
}

// 时间选择函数
function dateSelect(dom) {
    var $a = dom;
    var Date = new mui.DtPicker({
        type: 'date'
    });
    Date.show(function (item) {
        //这里你可以用console 看看回调函数中的item的值
        var endDate = item.y.text + '-' + item.m.text + '-' + item.d.text;
        $a.val(endDate);
    });
}
