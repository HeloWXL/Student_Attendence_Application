
if('${sign.isStartStatus}'==1&&'${sign.isEndStatus}'==0){
    $("#start").show();
    var start = new Date('${sign.startTime}')
    console.log(start)
    $("#start h3").html("签到时间："+dateFormat("YYYY-mm-dd HH:MM:SS", start));
    $("#start p").html("地点："+'${sign.signLocation}')
    $("#end").hide();
}else if('${sign.isStartStatus}'==1&&'${sign.isEndStatus}'==1){
    $("#start").show();
    $("#start h3").html("签到时间："+'${sign.startTime}');
    $("#start p").html("地点："+'${sign.signLocation}')
    $("#end").show();
    $("#end h3").html("签到时间："+'${sign.endTime}');
    $("#end p").html("地点："+'${sign.signOutLocation}')
}

//时间格式化
function dateFormat(fmt, date) {
    var ret;
    var opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (var k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}