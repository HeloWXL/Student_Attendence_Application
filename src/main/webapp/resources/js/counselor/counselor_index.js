//JavaScript代码区域
layui.use('element', function() {
    var element = layui.element;
});

$(function () {
    if (counselor == '' || counselor == null) {
        location.href = ctx + '/counselorApi/toCounselorLogin';
        return;
    }
    $("iframe").attr("src",ctx+"/counselorApi/toStatistcs")

    $("#index").click(function () {
        $("iframe").attr("src",ctx+"/counselorApi/toStatistcs")
    })
    $("#leave").click(function () {
        $("iframe").attr("src",ctx+"/leaveApi/toCounselorLeaveTable")
    })
    $("#course").click(function () {
        $("iframe").attr("src",ctx+"/courseApi/toCounselorCourseTable")
    })
    $("#student").click(function () {
        $("iframe").attr("src",ctx+"/counselorApi/toCounselorStudentTable")
    })
    $("#teacher").click(function () {
        $("iframe").attr("src",ctx+"/teacherApi/toCounselorTeacherTable")
    })
    $("#profession").click(function () {
        $("iframe").attr("src",ctx+"/professionApi/toCounselorProfessionTable")
    })

    $("#attendce").click(function () {
        $("iframe").attr("src",ctx+"/signApi/toAttenceForCounselor")
    })

})