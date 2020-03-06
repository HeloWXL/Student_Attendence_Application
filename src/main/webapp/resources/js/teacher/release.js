$(function () {
    // 判断教师是否登录，未登录直接跳转到登录界面
    if (teacher == null || teacher == '') {
        location.href = ctx + "/teacherApi/toLogin"
    }

    var professionList = locationProfession();
    var courseList = loadCourse();
    $('#startTime').click(function () {
        var $dom = $('#startTime');
        dateSelect($dom);
    });
    $('#endTime').click(function () {
        var dom1 = $('#endTime');
        dateSelect(dom1);
    });


    // 专业选择
    var Picker = new mui.PopPicker();
    Picker.setData(professionList);
    prefession.addEventListener('tap', function (event) {
        var $dom = $(this);
        Picker.show(function (items) {
            var vals = items[0].text;
            var name = items[0].value;
            $dom.val(vals);
            $dom.attr('name', name);
        });
    }, false);

    //课程选择
    var PickerCourse = new mui.PopPicker();
    PickerCourse.setData(courseList);
    courseId.addEventListener('tap', function (event) {
        var $dom = $(this);
        PickerCourse.show(function (items) {
            var vals = items[0].text;
            var name = items[0].value;
            $dom.val(vals);
            $dom.attr('name', name);
        });
    }, false);

    // 提交数据，并进行数据校验
    $('#submit').click(function () {
        var courseId = $.trim($('#courseId').attr('name'));
        if (courseId == '' || courseId == null) {
            mui.alert("课程不能为空");
            return;
        }
        var profession = $.trim($('#prefession').attr('name'));
        if (profession == '' || profession == null) {
            mui.alert("专业名称不能为空");
            return;
        }
        var starttime = $('#startTime').val();
        if (starttime == '' || starttime == null) {
            mui.alert("开课时间不能为空");
            return;
        }
        var endtime = $('#endTime').val();
        if (endtime == '' || endtime == null) {
            mui.alert("结课时间不能为空");
            return;
        }
        var release = {
            teacherId: teacherId,
            startTime:   new Date(starttime),
            endTime:  new Date(endtime),
            professionId: profession,
            courseId: courseId
        };
        submitAddCourse(release)
    });

    /**
     * 加载专业列表
     * @returns {any[]}
     */
    function locationProfession() {
        var professionList = new Array();
        $.ajax({
            url: ctx + '/professionApi/loadProfession',
            type: 'get',
            async: false,
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var obj = new Object();
                    obj.value = data[i].professionId;
                    obj.text = data[i].professionName;
                    professionList.push(obj)
                }
            }
        })
        return professionList;
    }

    /**
     * 加载专业列表
     * @returns {any[]}
     */
    function loadCourse() {
        var courseList = new Array();
        $.ajax({
            url: ctx + '/courseApi/loadCourseByTid',
            type: 'get',
            data: {
                tid: teacherId
            },
            async: false,
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var obj = new Object();
                    obj.value = data[i].courseId;
                    obj.text = data[i].courseName;
                    courseList.push(obj)
                }
            }
        })
        return courseList;
    }


    /**
     * 后台提交数据
     */
    function submitAddCourse(release) {
        $.ajax({
            url: ctx + '/releaseApi/insertRelease',
            data: JSON.stringify(release),
            dataType: 'json',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                if (data == 1) {
                    mui.alert('提交成功', function () {
                        history.back(-1);
                    });
                } else {
                    mui.alert('提交失败', function () {
                        reset();
                    });
                }
            }
        });
    }

    Date.prototype.format = function (format) {
        var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1
                    ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
        }
        return format;
    }

    /**
     * 时间选择
     * @param dom
     */
    function dateSelect(dom) {
        var $a = dom;
        var Date = new mui.DtPicker({
            type: 'datetime'
        });
        Date.show(function (item) {
            //这里你可以用console 看看回调函数中的item的值
            var endDate = item.y.text + '-' + item.m.text + '-'+item.d.text +" "+item.h.text+":"+item.i.text+":00";
            $a.val(endDate);
        });
    }
});