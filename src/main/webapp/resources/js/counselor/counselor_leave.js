layui.use('table', function () {
    var table = layui.table;

    // 加载表格数据
    loadData(table);
    /**
     * 查询
     */
    $('#query').click(function () {
    });
});

/**
 * 不批准 -method
 * @param id
 */
function notAgreeLeaves(id) {
    layui.use('table', function () {
        var table = layui.table;
        $.ajax({
            url: ctx + '/leaveApi/notAgreeLeaves',
            data: {
                id: id
            },
            dataType: 'json',
            type: 'get',
            success: function (data) {
                if (data.body == 1) {
                    layer.msg("操作成功");
                    // 加载表格数据
                    loadData(table);
                } else {
                    layer.msg("操作失败");
                    // 加载表格数据
                    loadData(table);                }
            }
        })
    });
}

/**
 * 批准 -method
 * @param id
 * @constructor
 */
function AgreeLeaves(id) {
    layui.use('table', function () {
        var table = layui.table;
        $.ajax({
            url: ctx + '/leaveApi/agreeLeaves',
            data: {
                id: id
            },
            dataType: 'json',
            type: 'get',
            success: function (data) {
                if (data.body == 1) {
                    layer.msg("操作成功");
                    // 加载表格数据
                    loadData(table);
                } else {
                    layer.msg("操作失败");
                    // 加载表格数据
                    loadData(table);
                }
            }
        })
    });

}

/**
 * 加载数据列表-method
 * @param table
 */
function loadData(table) {
    table.render({
        id: 'leaveTable',
        elem: '#demo'
        , method: 'get'
        , defaultToolbar: []
        , url: ctx + '/leaveApi/selectByPage' //数据接口
        , page: true //开启分页
        , cols: [[ //表头
            {field: 'number', title: '序号', type: 'numbers'}
            , {
                field: 'student', title: '学生姓名', width: '10%',align:'center', templet: function (d) {
                    return '<span>'+d.student.studentName+'</span>'
                }
            }
            , {field: 'leaveTitle', title: '请假标题', width: '10%',align:'center'}
            , {field: 'leaveReason', title: '请假缘由', width: '20%',align:'center'}
            , {field: 'startTime', title: '开始时间', width: '15%',align:'center'}
            , {field: 'endTime', title: '结束时间', width: '15%',align:'center'}
            , {
                field: 'isRead', title: '状态', width: '10%', templet: function (d) {
                    if (d.isRead == 1) {
                        return '<span style="color: green;">批准</span>'
                    } else if(d.isRead == 2){
                        return '<span style="color: blue;">不批准</span>'
                    }else{
                        return '<span style="color: red;">未批阅</span>'

                    }
                }
            }
            , {
                title: '操作', width: '20%',fixed:'right',align:'center', templet: function (d) {
                    if (d.isRead == 1||d.isRead==2) {
                        return '<button type="button" class="layui-btn layui-btn-normal" onclick="view(' + d.leaveId + ')">查看</button>';
                    }
                    return '<button type="button" class="layui-btn layui-btn-normal" onclick="notAgreeLeaves('+ d.leaveId + ')">不同意</button>' +
                        '<button type="button" class="layui-btn layui-btn-normal" onclick="AgreeLeaves('+ d.leaveId + ')">同意</button>' +
                        '<button type="button" class="layui-btn layui-btn-normal" onclick="view(\' + d.leaveId + \')">查看</button>\n';
                }
            }
        ]]
        , skin: 'line,row' //表格风格
        , even: true
        , limits: [10, 20, 30]
        , limit: 10 //每页默认显示的数量
    });
}


/**
 * 查看 -method
 * @param id
 */
function view(id) {
    layer.open({
        type: 2,
        content: ctx+'/leaveApi/leaveDetail/'+id,
        area:['400px', '600px'],
        offset: 'auto',
        shade: 0.3,
        anim: 1

    })
}




