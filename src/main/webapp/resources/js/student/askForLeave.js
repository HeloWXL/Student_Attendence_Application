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
        var formData = new FormData();
        var file = $("#file")[0].files[0];
        formData.append("file",file);

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
        var course = $.trim($('#courseName').attr('name'))
        if(course==null||course==''){
            mui.alert("课程不能为空");
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
        formData.append("studentSno",studentSno);
        formData.append("leaveReason",leaveReason);
        formData.append("startTime",start);
        formData.append("endTime",end);
        formData.append("leaveTitle",leaveTitle);
        formData.append("coundelorId",1);
        formData.append("courseId",parseInt(course));

        // 向后台提交数据insertSelective
        $.ajax({
            url: ctx + '/leaveApi/insertSelective',
            data: formData,
            dataType: 'json',
            type: 'post',
            processData:false,  //tell jQuery not to process the data
            contentType: false,  //tell jQuery not to set contentType
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
    /**
     * 清空
     */
    $("#reset").click(function () {
        $('#leaveReason').val('');
        $('#start').val('');
        $('#end').val('');
        $('#leaveTitle').val('');
    });
});


var courseList =loadCourse(pid);
// 课程选择
var Picker = new mui.PopPicker();
Picker.setData(courseList);
courseName.addEventListener('tap', function(event) {
    var $dom  = $(this);
    Picker.show(function(items) {
        var vals = items[0].text;
        var name = items[0].value;
        $dom.val(vals);
        $dom.attr('name',name);
    });
}, false);



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
        type: 'datetime'
    });
    Date.show(function (item) {
        console.log(item)
        //这里你可以用console 看看回调函数中的item的值
        var endDate = item.y.text + '-' + item.m.text + '-' + item.d.text;
        $a.val(item.value);
    });
}

/**
 * 加载课程列表
 * @returns {any[]}
 */
function loadCourse(id) {
    var courseList = new Array();
    $.ajax({
        url:ctx+'/courseApi/getCourseNameByProsessionId',
        type:'get',
        async:false,
        dataType:'json',
        data:{
            pid:id
        },
        success:function (data) {
            for(var i = 0 ;i<data.length;i++){
                var obj = new Object();
                obj.value = data[i].courseId;
                obj.text = data[i].courseName;
                courseList.push(obj)
            }
        }
    })
    return courseList;
}
