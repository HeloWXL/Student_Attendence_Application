layui.use('table', function () {
    var table = layui.table;

    // 加载表格数据
    loadData();
    // 查询
    $('#query').click(function () {
    });

    //加载列表数据
    function loadData() {
        table.render({
            id: 'signTable',
            elem: '#demo'
            , method: 'get'
            , defaultToolbar: []
            , url: ctx + '/signApi/getSignByPage' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'checkbox', type: 'checkbox'}
                , {field: 'number', title: '序号', type: 'numbers'}
                , {field: 'studentName', title: '学生', width: '8%',align:'center'}
                , {field: 'signLocation', title: '签到地点', width: '20%'}
                , {field: 'signLocation', title: '签退地点', width: '20%'}
                , {field: 'startTime', title: '开始时间', width: '15%',align:'center'}
                , {field: 'endTime', title: '结束时间', width: '15%',align:'center'}
                , {
                    field: 'isStartStatus', title: '是否签到', width: '8%',align:'center', templet: function (d) {
                        if (d.isStartStatus == 1) {
                            return '<span style="color: green;">已签到</span>'
                        }
                    }
                }
                , {
                    field: 'isEndStatus', title: '是否签退', width: '8%',align:'center', templet: function (d) {
                        if (d.isEndStatus == 1) {
                            return '<span style="color: green;">已签退</span>'
                        } else {
                            return '<span style="color: red;">未签退</span>'
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
