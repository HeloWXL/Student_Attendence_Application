layui.use('table', function () {
    var table = layui.table;
    // 加载表格数据
    loadData(table,counselorId);
    // 查询
    $('#query').click(function () {
    });


});

/**
 * 加载数据列表
 */
function loadData(table,counselorId) {
    table.render({
        id: 'apiTable',
        elem: '#demo'
        // , toolbar: '#toolbars'
        , method: 'get'
        , defaultToolbar: []
        , url: ctx + '/FaceApi/getApiListByPage' //数据接口
        ,where:{
            counselorId:counselorId
        }

        , page: true //开启分页
        , cols: [[ //表头
            {field: 'checkbox', type: 'checkbox'}
            , {field: 'number', title: '序号', type: 'numbers'}
            , {field: 'counselorName', title: '创建人', width: 120, align: 'center'}
            , {field: 'apiUrl', title: 'Api接口地址', width: 120, align: 'center'}
            , {field: 'apiKey', title: 'Api密钥', width: 120, align: 'center'}
            , {field: 'apiSecret', title: 'Api私钥', width: 120}
            , {field: 'creareTime', title: '创建时间', width: 120}
            , {field: 'state', title: '启用状态', width: 120}
        ]]
        , skin: 'line,row' //表格风格
        , even: true
        , limits: [10, 20, 30]
        , limit: 10 //每页默认显示的数量
    });
}