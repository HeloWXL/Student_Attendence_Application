//JavaScript代码区域
layui.use('element', function() {
    var element = layui.element;
});
function getUrl(_this){
    var url =$(_this).attr('name');
    $("iframe").attr("src",url)
}
$(function () {
    if (counselor == '' || counselor == null) {
        location.href = ctx + '/counselorApi/toCounselorLogin';
        return;
    }
})