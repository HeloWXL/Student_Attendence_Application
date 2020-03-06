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
            , toolbar: '#toolbars'
            ,method:'get'
            , defaultToolbar: []
            , url: ctx + '/teacherApi/selectTeacherByPage' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'checkbox', type: 'checkbox'}
                , {field: 'number', title: '序号', type: 'numbers'}
                , {field: 'teacherTno', title: '工号', width: '10%',align:'center'}
                , {field: 'teacherName', title: '姓名', width: '10%',align:'center'}
                , {field: 'teacherSex', title: '性别', width: '10%',align:'center'}
                , {field: 'teacherJobTitle', title: '职称', width: '10%',align:'center'}
                , {field: 'professionName', title: '专业', width: '20%',align:'center'}
                , {field: 'apartment', title: '学院', width: '20%',align:'center'}
                , {field: 'school', title: '学校', width: '13.2%',align:'center'}
            ]]
            , skin: 'line,row' //表格风格
            , even: true
            , limits: [10, 20, 30]
            , limit: 10 //每页默认显示的数量
        });
    }
    // 工具栏
    table.on('toolbar(teacherfilter)', function(obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.open({
                    id: 1,
                    type: 1,
                    title: ['添加教师'],
                    skin: 'layui-layer-molv',
                    area: '500px',
                    offset: 'auto',
                    content: '<div class="layui-row" id="test" style="margin-top:10px;">' +
                        '    <div class="layui-col-md10">' +
                        '        <form class="layui-form">' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">教师工号:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入教师工号" name="teacherTno" id="teacherTno" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="layui-form-item">\n' +
                        '                <label class="layui-form-label" style="padding-left:-50px;">教师姓名:</label>\n' +
                        '                <div class="layui-input-block">\n' +
                        '                    <input type="text" placeholder="请输入教师姓名" name="teacherName" id="teacherName" class="layui-input">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '<div class="layui-form-item">\n' +
                        '    <label class="layui-form-label">性别：</label>\n' +
                        '    <div class="layui-input-block">\n' +
                        '      <select id="teacherSex" >\n' +
                        '        <option value=""></option>\n' +
                        '        <option value="男">男</option>\n' +
                        '        <option value="女">女</option>\n' +
                        '      </select>\n' +
                        '    </div>\n' +
                        '    </div>\n' +
                        '<div class="layui-form-item">\n' +
                        '    <label class="layui-form-label">职称：</label>\n' +
                        '    <div class="layui-input-block">\n' +
                        '      <select id="teacherJobTitle" >\n' +
                        '        <option value=""></option>\n' +
                        '        <option value="教授">教授</option>\n' +
                        '        <option value="副教授">副教授</option>\n' +
                        '        <option value="讲师">讲师</option>\n' +
                        '        <option value="助教">助教</option>\n' +
                        '        <option value="研究员">研究员</option>\n' +
                        '      </select>\n' +
                        '    </div>\n' +
                        '    </div>\n' +
                        '<div class="layui-form-item">\n' +
                        '    <label class="layui-form-label">专业：</label>\n' +
                        '    <div class="layui-input-block">\n' +
                        '      <select id="professionId" >\n' +
                        '        <option value=""></option>\n' +
                        '        <option value="1">信息管理与信息系统</option>\n' +
                        '        <option value="2">计算机科学与技术</option>\n' +
                        '        <option value="3">物联网</option>\n' +
                        '        <option value="4">大数据科学与技术</option>\n' +
                        '        <option value="7">食品科学与工程</option>\n' +
                        '      </select>\n' +
                        '    </div>\n' +
                        '    </div>\n' +
                    '            <div class="layui-form-item">\n' +
                    '                <label class="layui-form-label" style="padding-left:-50px;">密码:</label>\n' +
                    '                <div class="layui-input-block">\n' +
                    '                    <input type="text" placeholder="请输入密码" name="password" id="password" class="layui-input">\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                        '        </form>\n' +
                        '    </div>\n' +
                        '</div>\n',
                    btn: ['提交', '取消']
                    , success: function(layero) {
                        var form = layui.form;
                        layero.find('.layui-layer-btn').css('text-align', 'center');
                        form.render('select');
                    },
                    btn1: function(index) {
                        //数据校验
                        var teacherTno = $.trim($('#teacherTno').val());
                        if(teacherTno==null||teacherTno==''){
                            layer.msg("教师工号不能为空",{icon:5,time:1500});
                            return;
                        }
                        var teacherName = $.trim($('#teacherName').val());
                        if(teacherName==null||teacherName==''){
                            layer.msg("教师姓名不能为空",{icon:5,time:1500});
                            return;
                        }
                        var teacherSex = $.trim($('#teacherSex').val());
                        if(teacherSex==null||teacherSex==''){
                            layer.msg("性别不能为空",{icon:5,time:1500});
                            return;
                        }
                        var teacherJobTitle = $.trim($('#teacherJobTitle').val());
                        if(teacherJobTitle==null||teacherJobTitle==''){
                            layer.msg("职称不能为空",{icon:5,time:1500});
                            return;
                        }
                        var professionId = $.trim($('#professionId').val());
                        if(professionId==null||professionId==''){
                            layer.msg("专业不能为空",{icon:5,time:1500});
                            return;
                        }
                        var password = $.trim($('#password').val());
                        if(password==null||password==''){
                            layer.msg("密码不能为空",{icon:5,time:1500});
                            return;
                        }
                        // 提交
                        var teacher = {
                            teacherTno:teacherTno,
                            teacherName: teacherName,
                            teacherSex: teacherSex,
                            teacherJobTitle: teacherJobTitle,
                            professionId:professionId,
                            teacherPassword:password
                        };
                        $.ajax({
                            url: ctx + '/teacherApi/insertTeacher',
                            data: JSON.stringify(teacher),
                            dataType: 'json',
                            type: 'post',
                            contentType: 'application/json; charset=utf-8',
                            success: function(data) {
                                if (data.body == 1) {
                                    layer.alert('添加成功',{icon:1,time:1500}, function() {
                                        //关闭弹窗
                                        layer.closeAll();
                                        // 重新刷新表格
                                        table.reload('teacherTable');
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
                    var teacherId = data[0].teacherId;
                    layer.confirm('是否删除？', {title: '提示'}, function(index) {
                        $.ajax({
                            url: ctx + '/teacherApi/deleteByTeacherId',
                            data: {id: teacherId},
                            dataType: 'json',
                            type: 'get',
                            contentType: 'application/json; charset=utf-8',
                            success: function(data) {
                                if (data.body == 1) {
                                    layer.msg('删除成功',{icon:1,time:1500}, function() {
                                        //关闭弹窗
                                        layer.closeAll();
                                        // 重新刷新表格
                                        table.reload('teacherTable');
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
                        id: 2,
                        type: 1,
                        title: ['课程修改'],
                        skin: 'layui-layer-molv',
                        area: '500px',
                        offset: 'auto',
                        content: '<div class="layui-row" id="test" style="margin-top:10px;">' +
                            '    <div class="layui-col-md10">' +
                            '        <form class="layui-form">' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">教师工号:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text" placeholder="请输入教师工号" name="teacherTno" id="teacherTno" class="layui-input" disabled="disabled">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="layui-form-item">\n' +
                            '                <label class="layui-form-label" style="padding-left:-50px;">教师姓名:</label>\n' +
                            '                <div class="layui-input-block">\n' +
                            '                    <input type="text" placeholder="请输入教师姓名" name="teacherName" id="teacherName" class="layui-input">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '<div class="layui-form-item">\n' +
                            '    <label class="layui-form-label">性别：</label>\n' +
                            '    <div class="layui-input-block">\n' +
                            '      <select id="teacherSex" >\n' +
                            '        <option value=""></option>\n' +
                            '        <option value="男">男</option>\n' +
                            '        <option value="女">女</option>\n' +
                            '      </select>\n' +
                            '    </div>\n' +
                            '    </div>\n' +
                            '<div class="layui-form-item">\n' +
                            '    <label class="layui-form-label">职称：</label>\n' +
                            '    <div class="layui-input-block">\n' +
                            '      <select id="teacherJobTitle" >\n' +
                            '        <option value=""></option>\n' +
                            '        <option value="教授">教授</option>\n' +
                            '        <option value="副教授">副教授</option>\n' +
                            '        <option value="讲师">讲师</option>\n' +
                            '        <option value="助教">助教</option>\n' +
                            '        <option value="研究员">研究员</option>\n' +
                            '      </select>\n' +
                            '    </div>\n' +
                            '    </div>\n' +
                            '<div class="layui-form-item">\n' +
                            '    <label class="layui-form-label">专业：</label>\n' +
                            '    <div class="layui-input-block">\n' +
                            '      <select id="professionId" >\n' +
                            '        <option value=""></option>\n' +
                            '        <option value="1">信息管理与信息系统</option>\n' +
                            '        <option value="2">计算机科学与技术</option>\n' +
                            '        <option value="3">物联网</option>\n' +
                            '        <option value="4">大数据科学与技术</option>\n' +
                            '        <option value="7">食品科学与工程</option>\n' +
                            '      </select>\n' +
                            '    </div>\n' +
                            '    </div>\n' +
                            '        </form>\n' +
                            '    </div>\n' +
                            '</div>\n',
                        btn: ['提交', '取消']
                        , success: function(layero) {
                            var form = layui.form;
                            layero.find('.layui-layer-btn').css('text-align', 'center');
                            // 展示在弹出层里面
                            $('#teacherTno').val(data[0].teacherTno);
                            $('#teacherName').val(data[0].teacherName);
                            $('#teacherSex').val(data[0].teacherSex);
                            $('#teacherJobTitle').val(data[0].teacherJobTitle);
                            $('#professionId').val(data[0].professionId);
                            form.render('select');
                        },
                        btn1: function(index) {
                            //数据校验
                            var teacherTno = $.trim($('#teacherTno').val());
                            if(teacherTno==null||teacherTno==''){
                                layer.msg("教师工号不能为空",{icon:5,time:1500});
                                return;
                            }
                            var teacherName = $.trim($('#teacherName').val());
                            if(teacherName==null||teacherName==''){
                                layer.msg("教师姓名不能为空",{icon:5,time:1500});
                                return;
                            }
                            var teacherSex = $.trim($('#teacherSex').val());
                            if(teacherSex==null||teacherSex==''){
                                layer.msg("性别不能为空",{icon:5,time:1500});
                                return;
                            }
                            var teacherJobTitle = $.trim($('#teacherJobTitle').val());
                            if(teacherJobTitle==null||teacherJobTitle==''){
                                layer.msg("职称不能为空",{icon:5,time:1500});
                                return;
                            }

                            var professionId = $.trim($('#professionId').val());
                            if(professionId==null||professionId==''){
                                layer.msg("专业不能为空",{icon:5,time:1500});
                                return;
                            }

                            // 提交
                            var teacher = {
                                teacherTno:teacherTno,
                                teacherName: teacherName,
                                teacherSex: teacherSex,
                                teacherJobTitle: teacherJobTitle,
                                professionId:professionId,
                                teacherId : data[0].teacherId
                            };
                            $.ajax({
                                url: ctx + '/teacherApi/updateStudent',
                                data: JSON.stringify(teacher),
                                dataType: 'json',
                                type: 'post',
                                contentType: 'application/json; charset=utf-8',
                                success: function(data) {
                                    if (data.body == 1) {
                                        layer.alert('修改成功', function() {
                                            layer.closeAll();
                                            table.reload('teacherTable');
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
