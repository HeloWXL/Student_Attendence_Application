$(function() {
    // 判断教师是否登录，未登录直接跳转到登录界面
    if (teacher == null || teacher == '') {
        location.href = ctx + "/teacherApi/toLogin"
    }
    var professionList =locationProfession();
    var dateList = [{
        value: '1',
        text: '星期一'
    }, {
        value: '2',
        text: '星期二'
    }, {
        value: '3',
        text: '星期三'
    }, {
        value: '4',
        text: '星期四'
    }, {
        value: '5',
        text: '星期五'
    }];
    
    $('#startTime').click(function() {
        var $dom = $('#startTime');
        dateSelect($dom);
    });
    $('#endTime').click(function() {
        var dom1 = $('#endTime');
        dateSelect(dom1);
    });
    // 专业选择
    var Picker = new mui.PopPicker();
    Picker.setData(professionList);
    prefession.addEventListener('tap', function(event) {
        var $dom  = $(this);
        Picker.show(function(items) {
            var vals = items[0].text;
            var name = items[0].value;
            $dom.val(vals);
            $dom.attr('name',name);
        });
    }, false);


    // 上课时间选择
    var DatePicker = new mui.PopPicker();
    DatePicker.setData(dateList);
    var btn = document.getElementById('dateSelect');
    btn.addEventListener('tap', function(event) {
        var $dom  = $(this);
        DatePicker.show(function(items) {
            var vals = items[0].text;
            var name = items[0].value;
            $dom.val(vals);
            $dom.attr('name',name);
        });
    }, false);

    // 提交数据，并进行数据校验
    $('#submit').click(function() {
        var courseName = $.trim($('#courseName').val());
        if(courseName==''||courseName==null){
            mui.alert("课程名不能为空");
            return;
        }
        var profession = $.trim($('#prefession').attr('name'));
        if(profession==''||profession==null){
            mui.alert("专业名称不能为空");
            return;
        }
        var classarrangement = $('#dateSelect').attr('name');
        if(classarrangement==''||classarrangement==null){
            mui.alert("课程安排不能为空");
            return;
        }
        var starttime = $('#startTime').val();
        if(starttime==''||starttime==null){
            mui.alert("开课时间不能为空");
            return;
        }
        var endtime = $('#endTime').val();
        if(endtime==''||endtime==null){
            mui.alert("结课时间不能为空");
            return;
        }
        var course  = {
            courseName:courseName,
            classarrangement:classarrangement,
            starttime:starttime,
            endtime:endtime,
            profession:profession,
            teacherId:teacherId
        };
        submitAddCourse(course)
    });
    /**
     * 加载专业列表
     * @returns {any[]}
     */
    function locationProfession() {
        var professionList = new Array();
        $.ajax({
            url:ctx+'/professionApi/loadProfession',
            type:'get',
            async:false,
            dataType:'json',
            success:function (data) {
                for(var i = 0 ;i<data.length;i++){
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
     * 后台提交数据
     */
    function submitAddCourse(course) {
        $.ajax({
            url:ctx+'/courseApi/insertCourse',
            data:JSON.stringify(course),
            dataType:'json',
            type:'post',
            contentType: 'application/json; charset=utf-8',
            success:function(data) {
                if(data.body==1){
                    mui.alert('提交成功',function() {
                        history.back(-1);
                    });
                }else{
                    mui.alert('提交失败',function() {
                        reset();
                    });
                }
            }
        });
    }

    /**
     * 字符串转日期格式，strDate要转为日期格式的字符串
     * @param strDate
     * @returns {any}
     */
    function getDate(strDate) {
        var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
            function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
        return date;
    }
    
    /**
     * 时间选择
     * @param dom
     */
    function dateSelect(dom) {
        var $a = dom;
        var Date = new mui.DtPicker({
            type: 'month'
        });
        Date.show(function(item) {
            //这里你可以用console 看看回调函数中的item的值
            var endDate = item.y.text + '-' + item.m.text;
            $a.val(endDate);
        });
    }
});