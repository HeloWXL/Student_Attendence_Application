layui.use(['table','upload'], function () {
    var table = layui.table;
    var upload = layui.upload;
    // 加载表格数据
    loadData();
    // 查询
    $('#query').click(function () {
    });

    table.on('toolbar(coursefilter)', function (obj) {
        if(obj.event=="import"){
            layer.open({
                type: 1,
                title: ['课程批量导入'],
                skin: 'layui-layer-molv',
                area: '350px',
                offset: 'auto',
                content: '<div class="layui-row"  style="margin-top:10px;">' +
                '    <div class="layui-col-md12" style="text-align: center">' +
                '<div class="layui-upload" style="margin-bottom: 10px;">\n' +
                '  <button type="button" class="layui-btn layui-btn-normal" id="uploadSelect">选择文件</button>\n' +
                '  <button type="button" class="layui-btn" id="upload">开始上传</button>\n' +
                '</div>'+
                '    </div>\n' +
                '</div>\n'
                , success: function(layero) {
                    var upload  = layui.upload;
                    //选完文件后不自动上传
                    upload.render({
                        elem: '#uploadSelect'
                        ,url: ctx+'/courseApi/importCourse'
                        ,auto: false
                        ,bindAction: '#upload'
                        ,done: function(res){
                            layer.msg(res.msg)
                            layer.closeAll()
                        }
                        ,accept: 'file'
                    });
                }
            });
        }
    });
    //加载列表数据
    function loadData() {
        table.render({
            id: 'courseTable',
            elem: '#demo'
            , toolbar: '#toolbars'
            , method: 'get'
            , defaultToolbar: []
            , url: ctx + '/courseApi/selectCourseByPage' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                 {field: 'number', title: '序号', type: 'numbers'}
                , {field: 'courseName', title: '课程名称', width: 120,align:'center'}
                , {field: 'teacherName', title: '教师姓名', width: 120,align:'center'}
                , {field: 'teacherJobTitle', title: '教师职称', width: 120,align:'center'}
                , {
                    field: 'classarrangement', title: '开课安排(星期)',align:'center', width: 150, templet: function (d) {
                            return '<span style="color: green;">'+getDay(d.classarrangement)+'</span>'
                    }
                }
                , {field: 'starttime', title: '开课时间', width: 120}
                , {field: 'endtime', title: '结课时间', width: 120}
                , {field: 'professionName', title: '专业', width: 120}
                , {field: 'apartment', title: '学院', width: 120}
                , {field: 'school', title: '学校', width: 120}
            ]]
            , skin: 'line,row' //表格风格
            , even: true
            , limits: [10, 20, 30]
            , limit: 10 //每页默认显示的数量
        });
    }
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
