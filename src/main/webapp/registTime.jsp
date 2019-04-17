<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 800px;height:600px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    myChart.setOption({
        title: {
            text: '异步数据加载示例'
        },
        tooltip: {},
        legend: {
            data:['用户注册量']
        },
        xAxis: {
            data: ["一周内","一月内","三个月内","半年内"]
        },
        yAxis: {},
        series: [{
            type: 'bar',
            data: []
        }]
    });
    // 使用刚指定的配置项和数据显示图表。
    $.ajax({
        url:"${app}/user/findRegistCount",
        datatype:"json",
        type:"get",
        success:function (data) {
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '用户注册量',
                    data: data.data
                }]
            });
        }
    })
</script>