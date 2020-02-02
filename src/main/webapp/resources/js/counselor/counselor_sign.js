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
            , toolbar: '#toolbars'
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

    //考勤记录导出
    $("#export").click(function () {
        //获取当前页
        var recodePage1 = $(".layui-laypage-skip .layui-input").val();
        //获取当前页条数
        var recodeLimit1 = $(".layui-laypage-limits").find("option:selected").val();

        if(recodePage1 == undefined || recodeLimit1 == undefined){
            recodePage1 = 0;
            recodeLimit1 = 10;
        }
        var form = document.createElement("form"); //创建一个 form
        document.body.appendChild(form); //添加到 body 中

        var page = document.createElement("input"); //创建一个输入
        page.type = "text";
        page.id = "page";
        page.name = "page";
        page.display = 'none';
        page.value = recodePage1;
        form.appendChild(page);

        var limit = document.createElement("input"); //创建一个输入
        limit.type = "text";
        limit.id = "limit";
        limit.name = "limit";
        limit.display = 'none';
        limit.value = recodeLimit1;
        form.appendChild(limit);

        form.method = "POST"; //form 的提交方式
        form.action = ctx+"/signApi/exportSign"; //form 提交路径
        form.id = "form2";

        form.submit(); //对该 form 执行提交
        $("#form2").remove();
    });
});
