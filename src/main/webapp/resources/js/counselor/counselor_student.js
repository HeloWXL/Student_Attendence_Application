layui.use('table', function() {
  var table = layui.table;

  // 加载表格数据
  loadData();
  // 查询
  $('#query').click(function() {
  });

  /**
   * 加载学生列表
   */
  function loadData() {
    table.render({
      id: 'studentTable',
      elem: '#demo'
      , toolbar: '#toolbars'
      ,method:'get'
      , defaultToolbar: []
      , url: ctx + '/studentApi/selectStudentByPage' //数据接口
      , page: true //开启分页
      , cols: [[ //表头
        {field: 'checkbox', type: 'checkbox'}
        , {field: 'number', title: '序号', type: 'numbers'}
        , {field: 'studentSno', title: '学号', width: '13.2%',align:'center'}
        , {field: 'studentName', title: '学生姓名', width: '10%',align:'center'}
        , {field: 'studentSex', title: '性别', width: '10%',align:'center'}
        , {field: 'studentAge', title: '年龄', width: '10%',align:'center'}
        , {field: 'studentEmail', title: '邮箱', width: '20%',align:'center'}
        , {field: 'studentQq', title: 'QQ', width: '15%',align:'center'}
        , {field: 'createTime', title: '创建时间', width: '15%',align:'center'}
      ]]
      , skin: 'line,row' //表格风格
      , even: true
      , limits: [10, 20, 30]
      , limit: 10 //每页默认显示的数量
    });
  }

  /**
   * 工具栏监听事件
   */
  table.on('toolbar(studentfilter)', function(obj) {
    var checkStatus = table.checkStatus(obj.config.id);
    var data = checkStatus.data; //获取选中的数据
    switch (obj.event) {
      case 'add':
        layer.open({
          id: 'addStudent',
          type: 1,
          title: ['添加学生'],
          skin: 'layui-layer-molv',
          area: '500px',
          offset: 'auto',
          content: '<div class="layui-row" id="test" style="margin-top:10px;">' +
          '    <div class="layui-col-md10">' +
          '        <form class="layui-form">' +
          '            <div class="layui-form-item">\n' +
          '                <label class="layui-form-label" style="padding-left:-50px;">姓名:</label>\n' +
          '                <div class="layui-input-block">\n' +
          '                    <input type="text" placeholder="请输入学生姓名" name="studentName" id="studentName" class="layui-input">\n' +
          '                </div>\n' +
          '            </div>\n' +
          '            <div class="layui-form-item">\n' +
          '                <label class="layui-form-label" style="padding-left:-50px;">学号:</label>\n' +
          '                <div class="layui-input-block">\n' +
          '                    <input type="text" placeholder="请输入学生学号" name="studentSno" id="studentSno" class="layui-input">\n' +
          '                </div>\n' +
          '            </div>\n' +
              '<div class="layui-form-item">\n' +
              '    <label class="layui-form-label">专业:</label>\n' +
              '    <div class="layui-input-block" id="profession">\n' +
              '    </div>\n' +
              '  </div>'+
          '            <div class="layui-form-item">\n' +
          '                <label class="layui-form-label" style="padding-left:-50px;">性别:</label>\n' +
          '                <div class="layui-input-block">\n' +
          '                    <input type="text" placeholder="请输入学生性别" name="studentSex" id="studentSex" class="layui-input">\n' +
          '                </div>\n' +
          '            </div>\n' +
          '            <div class="layui-form-item">\n' +
          '                <label class="layui-form-label" style="padding-left:-50px;">年龄:</label>\n' +
          '                <div class="layui-input-block">\n' +
          '                    <input type="text" placeholder="请输入学生年龄" name="studentAge" id="studentAge" class="layui-input">\n' +
          '                </div>\n' +
          '            </div>\n' +
          '            <div class="layui-form-item">\n' +
          '                <label class="layui-form-label" style="padding-left:-50px;">邮箱:</label>\n' +
          '                <div class="layui-input-block">\n' +
          '                    <input type="text" placeholder="请输入学生邮箱" name="studentEmail" id="studentEmail" class="layui-input">\n' +
          '                </div>\n' +
          '            </div>\n' +
          '            <div class="layui-form-item">\n' +
          '                <label class="layui-form-label" style="padding-left:-50px;">QQ:</label>\n' +
          '                <div class="layui-input-block">\n' +
          '                    <input type="text" placeholder="请输入QQ" name="studentQq" id="studentQq" class="layui-input">\n' +
          '                </div>\n' +
          '            </div>\n' +
          '            <div class="layui-form-item">\n' +
          '                <label class="layui-form-label" style="padding-left:-50px;">学生密码:</label>\n' +
          '                <div class="layui-input-block">\n' +
          '                    <input type="text" placeholder="请输入学生密码" name="studentPassword" id="studentPassword" class="layui-input">\n' +
          '                </div>\n' +
          '            </div>\n' +
          '        </form>\n' +
          '    </div>\n' +
          '</div>\n',
          btn: ['提交', '取消']
          , success: function(layero) {
            var form = layui.form;
            layero.find('.layui-layer-btn').css('text-align', 'center');
            $("#profession").append(locationProfession())
            form.render('select');
          },
          //确认
          btn1: function(index) {
            //数据校验
            var studentName = $.trim($('#studentName').val());
            if(studentName==null&&studentName==''){
              layer.msg('姓名不能为空',{icon:5,time:1500});
              return;
            }
            var studentSno = $.trim($('#studentSno').val());
            if(studentSno==null&&studentSno==''){
              layer.msg('学号不能为空',{icon:5,time:1500});
              return;
            }

            var professionId = $("select[name=\"professionId\"]").val();
            if(professionId==null&&professionId==''){
              layer.msg('专业不能为空',{icon:5,time:1500});
              return;
            }
            var studentSex = $.trim($('#studentSex').val());
            if(studentSex==null&&studentSex==''){
              layer.msg('性别不能为空',{icon:5,time:1500});
              return;
            }
            var studentAge = $.trim($('#studentAge').val());
            if(studentAge==null&&studentAge==''){
              layer.msg('年龄不能为空',{icon:5,time:1500});
              return;
            }
            var studentEmail = $.trim($('#studentEmail').val());
            if(studentEmail==null&&studentEmail==''){
              layer.msg('邮箱不能为空',{icon:5,time:1500});
              return;
            }

            var studentQq = $.trim($('#studentQq').val());
            if(studentQq==null&&studentQq==''){
              layer.msg('QQ不能为空',{icon:5,time:1500});
              return;
            }

            var studentPassword = $.trim($('#studentPassword').val());
            if(studentPassword==null&&studentPassword==''){
              layer.msg('密码不能为空',{icon:5,time:1500});
              return;
            }
            // 提交
            var student = {
              studentSno:studentSno,
              professionId:parseInt(professionId),
              studentSex:studentSex,
              studentAge:studentAge,
              studentEmail:studentEmail,
              studentQq:studentQq,
              studentName: studentName,
              studentPassword: studentPassword
            };
            // 向后台提交数据
            insertStudent(student);
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
          var studentId = [];
          for (var i = 0; i < length; i++) {
            studentId.push(data[i].studentId);
          }
          layer.confirm('是否删除？', {title: '提示'}, function(index) {
            deleteStudent(studentId,length)
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
            '                <label class="layui-form-label" style="padding-left:-50px;">姓名:</label>\n' +
            '                <div class="layui-input-block">\n' +
            '                    <input type="text" placeholder="请输入学生姓名" name="studentName" id="studentName" class="layui-input">\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="layui-form-item">\n' +
            '                <label class="layui-form-label" style="padding-left:-50px;">学号:</label>\n' +
            '                <div class="layui-input-block">\n' +
            '                    <input type="text" placeholder="请输入学生学号" name="studentSno" id="studentSno" class="layui-input">\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="layui-form-item">\n' +
            '                <label class="layui-form-label" style="padding-left:-50px;">性别:</label>\n' +
            '                <div class="layui-input-block">\n' +
            '                    <input type="text" placeholder="请输入学生性别" name="studentSex" id="studentSex" class="layui-input">\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="layui-form-item">\n' +
            '                <label class="layui-form-label" style="padding-left:-50px;">年龄:</label>\n' +
            '                <div class="layui-input-block">\n' +
            '                    <input type="text" placeholder="请输入学生年龄" name="studentAge" id="studentAge" class="layui-input">\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="layui-form-item">\n' +
            '                <label class="layui-form-label" style="padding-left:-50px;">邮箱:</label>\n' +
            '                <div class="layui-input-block">\n' +
            '                    <input type="text" placeholder="请输入学生邮箱" name="studentEmail" id="studentEmail" class="layui-input">\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="layui-form-item">\n' +
            '                <label class="layui-form-label" style="padding-left:-50px;">QQ:</label>\n' +
            '                <div class="layui-input-block">\n' +
            '                    <input type="text" placeholder="请输入QQ" name="studentQq" id="studentQq" class="layui-input">\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="layui-form-item">\n' +
            '                <label class="layui-form-label" style="padding-left:-50px;">学生密码:</label>\n' +
            '                <div class="layui-input-block">\n' +
            '                    <input type="text" placeholder="请输入学生密码" name="studentPassword" id="studentPassword" class="layui-input">\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </form>\n' +
            '    </div>\n' +
            '</div>\n',
            btn: ['提交', '取消']
            , success: function(layero) {
              layero.find('.layui-layer-btn').css('text-align', 'center');
              // 展示在弹出层里面
              $('#studentName').val(data[0].studentName);
              $('#studentAge').val(data[0].studentAge);
              $('#studentSno').val(data[0].studentSno);
              $('#studentSex').val(data[0].studentSex);
              $('#studentQq').val(data[0].studentQq);
              $('#studentEmail').val(data[0].studentEmail);
              $('#studentPassword').val();
            },
            btn1: function(index) {
              //数据校验
              var studentName = $.trim($('#studentName').val());
              if(studentName==null&&studentName==''){
                layer.msg('姓名不能为空',{icon:5,time:1500});
                return;
              }

              var studentPassword = $.trim($('#studentPassword').val());
              if(studentPassword==null&&studentPassword==''){
                layer.msg('密码不能为空',{icon:5,time:1500});
                return;
              }
              // 提交
              var student = {
                studentId: data[0].studentId,
                studentPassword: studentPassword
              };
              updateStudent(student);
            },
            btn2: function(index, layero) {
              layer.close(index);
            }
          });
        }
    }
  });

  /**
   * 删除学生
   * @param studentId ,length
   */
  function deleteStudent(studentId,length) {
    $.ajax({
      url: ctx + '/studentApi/deleteByStudentId',
      data: JSON.stringify(studentId),
      dataType: 'json',
      type: 'post',
      contentType: 'application/json; charset=utf-8',
      success: function(data) {
        if (data.body == length) {
          layer.msg('删除成功',{icon:1,time:1500}, function() {
            //关闭弹窗
            layer.closeAll();
            // 重新刷新表格
            table.reload('studentTable');
          });
        } else {
          layer.msg('删除失败',{icon:5,time:1500});
        }
      }
    });
  }

  /**
   * 加载专业列表
   * @returns
   */
  function locationProfession() {
    var professionStr = "<select name=\"professionId\" ><option value=''>请选择专业</option>";
    $.ajax({
      url:ctx+'/professionApi/loadProfession',
      type:'get',
      async:false,
      dataType:'json',
      success:function (data) {
        for(var i = 0 ;i<data.length;i++){
          var node = ('<option value="'+data[i].professionId+'">'+data[i].professionName+'</option>');
          professionStr+=node;
        }
      }
    });
    return professionStr+"</select>";
  }

  /**
   * 修改学生信息
   * @param student
   */
  function updateStudent(student) {
    $.ajax({
      url: ctx + '/studentApi/updateStudentPasswordByAdmin',
      data: JSON.stringify(student),
      dataType: 'json',
      type: 'post',
      contentType: 'application/json; charset=utf-8',
      success: function(data) {
        if (data == 1) {
          layer.alert('修改成功', function() {
            layer.closeAll();
            table.reload('studentTable');
          });
        } else {
          layer.msg('修改失败',{icon:5,time:1500});
        }

      }
    });
  }

  /**
   * 添加学生
   * @param student
   */
  function insertStudent(student) {
    $.ajax({
      url: ctx + '/studentApi/insertStudent',
      data: JSON.stringify(student),
      dataType: 'json',
      type: 'post',
      contentType: 'application/json; charset=utf-8',
      success: function(data) {
        if (data.body == 1) {
          layer.alert('添加成功',{icon:1,time:1500}, function() {
            //关闭弹窗
            layer.closeAll();
            // 重新刷新表格
            table.reload('studentTable');
          });
        } else {
          layer.msg('添加失败',{icon:5,time:1500});
        }
      }, error: function(e) {
        layer.msg('服务器内部错误');
      }
    });
  }

});
