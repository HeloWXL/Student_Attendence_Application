$(function() {
    if(student==''||student==null) {
        location.href = ctx+'/studentApi/toLogin';
        return;
    }
    $.ajax({
        url:ctx+'/studentApi/selectCourseBySno',
        dataType:'json',
        type:'get',
        data:{sno:sno},
        contentType: 'application/json; charset=utf-8',
        success:function(data) {
            for(var i = 0 ;i<data.body.length;i++){
                var day = getDay(data.body[i].courseList[0].classarrangement);
                var start = data.body[i].courseList[0].starttime;
                var end = data.body[i].courseList[0].endtime;
                var $node = $('<li class="mui-table-view-cell">\n'+courseStatus(start,end)+
                    '        <h4>'+data.body[i].courseList[0].courseName+'</h4>\n' +
                    '        <p>\n' +
                    '          上课时间：<span>'+day+'</span>\n' +
                    '        </p>\n' +
                    '        <p>\n' +
                    '          专业：<span>'+data.body[i].courseList[0].professions.professionName+'</span>\n' +
                    '        </p>\n' +
                    '        <p>\n' +
                    '          教师：<span>'+data.body[i].courseList[0].teacher.teacherName+'</span>\n' +
                    '        </p>\n' +
                    '        <p>\n' +
                    '          职称：<span>'+data.body[i].courseList[0].teacher.teacherJobTitle+'</span>\n' +
                    '        </p>\n' +
                    '        <p>开课时间：<span>'+start+'</span>&nbsp;&nbsp;&nbsp;结课时间：<span>'+end+'</span></p>\n' +
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
    var end_time = end.replace("-","/");
    end_time = new Date(Date.parse(end_time));

    if(now>start_time&&now<end_time){
        var str = '<span class="mui-badge mui-badge-success" id="success">进行中</span>\n';
        return str;
    }
    if(now<start_time){
        var str = '<span class="mui-badge mui-badge-danger" id="success">未开课</span>\n';
        return str;
    }

    if(now>end_time){
        var str = '<span class="mui-badge mui-badge-primary" id="success">已结课</span>\n';
        return str;
    }
}