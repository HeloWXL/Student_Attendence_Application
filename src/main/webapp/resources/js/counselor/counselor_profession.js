layui.use('table', function() {
    var table = layui.table;

    // 加载表格数据
    loadProfessionData();
    // 查询
    $('#query').click(function() {
    });

    //加载列表数据
    function loadProfessionData() {
        table.render({
            id: 'professionTable',
            elem: '#demo'
            ,toolbar: '#toolbars'
            ,method:'get'
            , defaultToolbar: []
            , url: ctx + '/professionApi/selectProfessionByPage' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'checkbox', type: 'checkbox'}
                , {field: 'number', title: '序号', type: 'numbers'}
                , {field: 'professionName', title: '专业', width: 200,align:'center'}
                , {field: 'apartment', title: '学院', width: 200,align:'center'}
                , {field: 'school', title: '学校', width: 200,align:'center'}
                , {field: 'createTime', title: '创建时间', width: 200,align:'center'}
            ]]
            , skin: 'line,row' //表格风格
            , even: true
            , limits: [10, 20, 30]
            , limit: 10 //每页默认显示的数量
        });
    }

    // 工具栏
    table.on('toolbar(professionfilter)', function(obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.open({
                    id: 1,
                    type: 1,
                    title: ['添加专业'],
                    skin: 'layui-layer-molv',
                    area: '500px',
                    offset: 'auto',
                    content: '<div class="layui-row" id="test" style="margin-top:10px;">' +
                        '    <div class="layui-col-md10">' +
                        '        <form class="layui-form">' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">专业名称:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入专业名称" name="professionName" id="professionName" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">学院名称:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入学院名称" name="apartment" id="apartment" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">学校名称:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入学校名称" name="school" id="school" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '        </form>\n' +
                        '    </div>\n' +
                        '</div>\n',
                    btn: ['提交', '取消']
                    , success: function(layero) {
                        layero.find('.layui-layer-btn').css('text-align', 'center');
                    },
                    btn1: function(index) {
                        //数据校验
                        var professionName = $.trim($('#professionName').val());
                        if(professionName==null&&professionName==''){
                            layer.msg("专业名称不能为空",{icon:5,time:1500});
                            return;
                        }
                        var apartment = $.trim($('#apartment').val());
                        if(apartment==null&&apartment==''){
                            layer.msg("学院名称不能为空",{icon:5,time:1500});
                            return;
                        }
                        var school = $.trim($('#school').val());
                        if(school==null&&school==''){
                            layer.msg("学校名称不能为空",{icon:5,time:1500});
                            return;
                        }
                        // 提交
                        var profession = {
                            professionName: professionName,
                            apartment: apartment,
                            school: school
                        };
                        $.ajax({
                            url: ctx + '/professionApi/insertProfession',
                            data: JSON.stringify(profession),
                            dataType: 'json',
                            type: 'post',
                            contentType: 'application/json; charset=utf-8',
                            success: function(data) {
                                if (data.body == 1) {
                                    layer.alert('添加成功',{icon:1,time:1500}, function() {
                                        //关闭弹窗
                                        layer.closeAll();
                                        // 重新刷新表格
                                        table.reload('professionTable');
                                    });
                                } else {
                                    layer.msg('添加失败',{icon:5,time:1500});
                                }
                            }, error: function(e) {
                                layer.msg('服务器内部错误');
                            }
                        });
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
                    var professionId = data[0].professionId;
                    layer.confirm('是否删除？', {title: '提示'}, function(index) {
                        $.ajax({
                            url: ctx + '/professionApi/deleteByStudentId',
                            data: {professionId: professionId},
                            dataType: 'json',
                            type: 'get',
                            contentType: 'application/json; charset=utf-8',
                            success: function(data) {
                                if (data.body == 1) {
                                    layer.msg('删除成功',{icon:1,time:1500}, function() {
                                        //关闭弹窗
                                        layer.closeAll();
                                        // 重新刷新表格
                                        table.reload('professionTable');
                                    });
                                } else {
                                    layer.msg('删除失败',{icon:5,time:1500});
                                }
                            }
                        });
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
                        id: 'update',
                        type: 1,
                        title: ['课程修改'],
                        skin: 'layui-layer-molv',
                        area: '500px',
                        offset: 'auto',
                        content: '<div class="layui-row" id="test" style="margin-top:10px;">' +
                            '    <div class="layui-col-md10">' +
                            '        <form class="layui-form">' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">专业名称:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text"   name="professionName" id="professionName" class="layui-input" disabled="disabled">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">学院名称:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text"   name="apartment" id="apartment" class="layui-input" disabled="disabled">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">学校名称:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text"   name="school" id="school" class="layui-input">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '        </form>\n' +
                            '    </div>\n' +
                            '</div>\n',
                        btn: ['提交', '取消']
                        , success: function(layero) {
                            layero.find('.layui-layer-btn').css('text-align', 'center');
                            // 展示在弹出层里面
                            $('#professionName').val(data[0].professionName);
                            $('#apartment').val(data[0].apartment);
                            $('#school').val(data[0].school);
                        },
                        btn1: function(index) {
                            //数据校验
                            var professionName = $.trim($('#professionName').val());
                            if(professionName==null&&professionName==''){
                                layer.msg("专业不能为空",{icon:5,time:1500});
                                return;
                            }
                            var apartment = $.trim($('#apartment').val());
                            if(apartment==null&&apartment==''){
                                layer.msg("学院名称不能为空",{icon:5,time:1500});
                                return;
                            }
                            var school = $.trim($('#school').val());
                            if(school==null&&school==''){
                                layer.msg("学校名称不能为空",{icon:5,time:1500});
                                return;
                            }
                            // 提交
                            // 提交
                            var profession = {
                                professionName: professionName,
                                apartment: apartment,
                                school: school,
                                professionId:data[0].professionId
                            };
                            $.ajax({
                                url: ctx + '/professionApi/updateProfession',
                                data: JSON.stringify(profession),
                                dataType: 'json',
                                type: 'post',
                                contentType: 'application/json; charset=utf-8',
                                success: function(data) {
                                    if (data.body == 1) {
                                        layer.msg('修改成功',{icon:1,time:1500}, function() {
                                            layer.closeAll();
                                            table.reload('professionTable');
                                        });
                                    } else {
                                        layer.msg('修改失败',{icon:5,time:1500});
                                    }

                                }
                            });
                        },
                        btn2: function(index, layero) {
                            layer.close(index);
                        }
                    });
                }
        }
    });

});
