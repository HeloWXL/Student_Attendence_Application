$(function() {
    if(student==''||student==null) {
        location.href = ctx+'/studentApi/toLogin';
        return;
    }
});

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