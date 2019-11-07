<%--
  Created by IntelliJ IDEA.
  User: wangxianlin
  Date: 2019/11/4
  Time: 10:39 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>统计图</title>
    <link href="${ctx}/resources/plugins/layui/css/layui.css" rel="stylesheet">
    <script>
        var ctx = '${ctx}';
        var counselor = '${counselor}';
    </script>
</head>
<body>
<div class="layui-row layui-col-space1">
    <div class="layui-col-xs6">
        <div class="grid-demo grid-demo-bg1">
            <div id="line_style" style="width:100%;height: 100%;"></div>
        </div>
    </div>
    <div class="layui-col-xs6">
        <div class="grid-demo">
            <div id="bar_style" style="width:100%;height: 100%;"></div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/jquery-2.1.4.js" type="application/javascript"></script>
<script src="${ctx}/resources/plugins/layui/layui.js" type="application/javascript"></script>
<script src="${ctx}/resources/echarts/echarts.min.js" type="application/javascript"></script>
<script>
  //折线图
  var line_style = document.getElementById("line_style");
  var myChart_line_style = echarts.init(line_style);

  if (lineChart() && typeof lineChart() === "object") {
    myChart_line_style.setOption(lineChart(), true);
  }

  // 饼图
  var bar_style = document.getElementById("bar_style");
  var myChart_bar_style = echarts.init(bar_style);

  if (barChart() && typeof barChart() === "object") {
    myChart_bar_style.setOption(barChart(), true);
  }

  function barChart() {
    option = {
      title : {
        text: '某站点用户访问来源',
        subtext: '纯属虚构',
        x:'center'
      },
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
      },
      series : [
        {
          name: '访问来源',
          type: 'pie',
          radius : '55%',
          center: ['50%', '60%'],
          data:[
            {value:335, name:'直接访问'},
            {value:310, name:'邮件营销'},
            {value:234, name:'联盟广告'},
            {value:135, name:'视频广告'},
            {value:1548, name:'搜索引擎'}
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    return option;

  }

  /**
   * 折线图
   */
    function lineChart(){
      option = {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [120, 200, 150, 80, 70, 110, 130],
          type: 'line',
          symbol: 'triangle',
          symbolSize: 20,
          lineStyle: {
            normal: {
              color: 'green',
              width: 4,
              type: 'dashed'
            }
          },
          itemStyle: {
            normal: {
              borderWidth: 3,
              borderColor: 'yellow',
              color: 'blue'
            }
          }
        }]
      };
      return option;
    }
</script>
</html>
