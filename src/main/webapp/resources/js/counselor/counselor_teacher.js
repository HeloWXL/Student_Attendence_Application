layui.use('table', function() {
    var table = layui.table;

    // 加载表格数据
    loadData();
    // 查询
    $('#query').click(function() {
    });

    //加载列表数据
    function loadData() {
        table.render({
            id: 'teacherTable',
            elem: '#demo'
            // , toolbar: '#toolbars'
            ,method:'get'
            , defaultToolbar: []
            , url: ctx + '/teacherApi/selectTeacherByPage' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'checkbox', type: 'checkbox'}
                , {field: 'number', title: '序号', type: 'numbers'}
                , {field: 'teacherTno', title: '工号', width: 200}
                , {field: 'teacherName', title: '姓名', width: 300}
                , {field: 'teacherSex', title: '性别', width: 120}
                , {field: 'teacherJobTitle', title: '职称', width: 120}
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
