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
            id: 'leaveTable',
            elem: '#demo'
            // , toolbar: '#toolbars'
            ,method:'get'
            , defaultToolbar: []
            , url: ctx + '/leaveApi/selectByPage' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'checkbox', type: 'checkbox'}
                , {field: 'number', title: '序号', type: 'numbers'}
                , {field: 'leaveTitle', title: '请假标题', width: 200}
                , {field: 'leaveReason', title: '请假缘由', width: 300}
                , {field: 'startTime', title: '开始时间', width: 120}
                , {field: 'endTime', title: '结束时间', width: 120}
                , {
                    field: 'isRead', title: '状态', width: 80, templet: function (d) {
                        if(d.isRead==1){
                            return '<span style="color: green;">已批阅</span>'
                        }else{
                            return '<span style="color: red;">未批阅</span>'
                        }
                    }
                }
            ]]
            , skin: 'line,row' //表格风格
            , even: true
            , limits: [10, 20, 30]
            , limit: 10 //每页默认显示的数量
        });
    }
});
