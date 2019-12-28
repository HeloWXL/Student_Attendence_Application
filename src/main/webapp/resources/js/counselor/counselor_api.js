layui.use('table', function () {
    var table = layui.table;
    // 加载表格数据
    loadData(table,counselorId);
    // 查询
    $('#query').click(function () {
    });

    /**
     * 工具栏监听事件
     */
    table.on('toolbar(apifilter)', function(obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.open({
                    id: 'addApi',
                    type: 1,
                    title: ['添加Api配置'],
                    skin: 'layui-layer-molv',
                    area: '550px',
                    offset: 'auto',
                    content: '<div class="layui-row" id="test" style="margin-top:10px;">' +
                        '    <div class="layui-col-md10">' +
                        '        <form class="layui-form">' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">Api接口地址:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入Api接口地址" name="apiUrl" id="apiUrl" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">Api密钥:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入Api密钥" name="apiKey" id="apiKey" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">Api私钥:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入Api私钥" name="apiSecret" id="apiSecret" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '        </form>\n' +
                        '    </div>\n' +
                        '</div>\n',
                    btn: ['提交', '取消']
                    , success: function(layero) {
                        layero.find('.layui-layer-btn').css('text-align', 'center');
                    },
                    //确认
                    btn1: function(index) {
                        //数据校验
                        var apiUrl = $.trim($('#apiUrl').val());
                        if(apiUrl==null&&apiUrl==''){
                            layer.msg('Api接口地址不能为空',{icon:5,time:1500});
                            return;
                        }
                        var apiKey = $.trim($('#apiKey').val());
                        if(apiKey==null&&apiKey==''){
                            layer.msg('Api密钥不能为空',{icon:5,time:1500});
                            return;
                        }

                        var apiSecret = $("#apiSecret").val();
                        if(apiSecret==null&&apiSecret==''){
                            layer.msg('Api私钥不能为空',{icon:5,time:1500});
                            return;
                        }
                        // 提交
                        var api = {
                            apiUrl:apiUrl,
                            apiKey:apiKey,
                            apiSecret:apiSecret,
                            counsrlorId:counselorId
                        };
                        // 向后台提交数据
                        insertApi(api,table);
                    },
                    // 取消
                    btn2: function(index, layero) {
                        layer.close(index);
                    }
                });
                break;
            case 'delete':
                if (data.length == 0) {
                    layer.msg('请选择一行');
                } else {
                    var length = data.length;
                    var ids = [];
                    for (var i = 0; i < length; i++) {
                        ids.push(data[i].apiId);
                    }
                    layer.confirm('是否删除？', {title: '提示'}, function(index) {
                        deleteApi(ids,length,table)
                    });
                }
                break;
            case 'update':
                if (data.length == 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能选择一行');
                } else {
                    layer.open({
                        id: 'updateApi',
                        type: 1,
                        title: ['Api配置修改'],
                        skin: 'layui-layer-molv',
                        area: '500px',
                        offset: 'auto',
                        content: '<div class="layui-row" id="test" style="margin-top:10px;">' +
                            '    <div class="layui-col-md10">' +
                            '        <form class="layui-form">' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">创建人:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text" placeholder="请输入Api接口地址" name="counselorName" id="counselorName" class="layui-input" disabled="disabled">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">Api接口地址:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text" placeholder="请输入Api接口地址" name="apiUrl" id="apiUrl" class="layui-input">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">Api密钥:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text" placeholder="请输入Api密钥" name="apiKey" id="apiKey" class="layui-input">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">Api私钥:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text" placeholder="请输入Api私钥" name="apiSecret" id="apiSecret" class="layui-input">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">创建时间:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text"  name="createTime" id="createTime" class="layui-input" disabled="disabled">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">是否启用:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text" name="state" id="state" class="layui-input" disabled="disabled"> \n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '        </form>\n' +
                            '    </div>\n' +
                            '</div>\n',
                        btn: ['提交', '取消']
                        , success: function(layero) {
                            layero.find('.layui-layer-btn').css('text-align', 'center');
                            // 展示在弹出层里面
                            $('#counselorName').val(data[0].counselorName);
                            $('#apiUrl').val(data[0].apiUrl);
                            $('#apiKey').val(data[0].apiKey);
                            $('#apiSecret').val(data[0].apiSecret);
                            $('#createTime').val(data[0].creareTime);
                            $('#state').val(data[0].state);
                        },
                        btn1: function(index) {
                            //数据校验
                            var apiUrl = $.trim($('#apiUrl').val());
                            if(apiUrl==null&&apiUrl==''){
                                layer.msg('Api接口地址不能为空',{icon:5,time:1500});
                                return;
                            }
                            var apiKey = $.trim($('#apiKey').val());
                            if(apiKey==null&&apiKey==''){
                                layer.msg('Api密钥不能为空',{icon:5,time:1500});
                                return;
                            }
                            var apiSecret = $.trim($('#apiSecret').val());
                            if(apiSecret==null&&apiSecret==''){
                                layer.msg('Api私钥不能为空',{icon:5,time:1500});
                                return;
                            }
                            // 提交
                            var api = {
                                apiUrl: apiUrl,
                                apiKey: apiKey,
                                apiSecret:apiSecret,
                                apiId:data[0].apiId
                            };
                            updateApi(api,table);
                        },
                        btn2: function(index, layero) {
                            layer.close(index);
                        }
                    });
                }
        }
    });
});

/**
 * 加载数据列表
 * @param table
 * @param counselorId
 */
function loadData(table,counselorId) {
    table.render({
        id: 'apiTable',
        elem: '#demo'
        , toolbar: '#toolbars'
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
            , {field: 'counselorName', title: '创建人', width: '10%', align: 'center'}
            , {field: 'apiUrl', title: 'Api接口地址', width: '15%', align: 'center'}
            , {field: 'apiKey', title: 'Api密钥', width: '15%', align: 'center'}
            , {field: 'apiSecret', title: 'Api私钥', width: '15%'}
            , {field: 'creareTime', title: '创建时间', width: '15%'}
            , {field: 'state', title: '启用状态', width: '10%'}
        ]]
        , skin: 'line,row' //表格风格
        , even: true
        , limits: [10, 20, 30]
        , limit: 10 //每页默认显示的数量
    });
}

/**
 * 删除Api
 * @param ids ,length
 */
function deleteApi(ids,length,table) {
    $.ajax({
        url: ctx + '/FaceApi/deleteFaceApi',
        data: JSON.stringify(ids),
        dataType: 'json',
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            if (data == length) {
                layer.msg('删除成功',{icon:1,time:1500}, function() {
                    //关闭弹窗
                    layer.closeAll();
                    // 重新刷新表格
                    table.reload('apiTable');
                });
            } else {
                layer.msg('删除失败',{icon:5,time:1500});
            }
        }
    });
}

/**
 * 修改Api
 * @param Api
 */
function updateApi(api,table) {
    $.ajax({
        url: ctx + '/FaceApi/updateFaceApi',
        data: JSON.stringify(api),
        dataType: 'json',
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            if (data == 1) {
                layer.alert('修改成功', function() {
                    layer.closeAll();
                    table.reload('apiTable');
                });
            } else {
                layer.msg('修改失败',{icon:5,time:1500});
            }
        }
    });
}

/**
 * 添加Api
 * @param Api
 */
function insertApi(api,table) {
    $.ajax({
        url: ctx + '/FaceApi/insertFaceApi',
        data: JSON.stringify(api),
        dataType: 'json',
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            if (data== 1) {
                layer.alert('添加成功',{icon:1,time:1500}, function() {
                    //关闭弹窗
                    layer.closeAll();
                    // 重新刷新表格
                    table.reload('apiTable');
                });
            } else {
                layer.msg('添加失败',{icon:5,time:1500});
            }
        }, error: function(e) {
            layer.msg('服务器内部错误');
        }
    });
}