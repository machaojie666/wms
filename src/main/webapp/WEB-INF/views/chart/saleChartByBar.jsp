<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/echarts/echarts-all.js"></script>
    <script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('main'));
            option = {
                title: {
                    text: '销售报表',
                    subtext: '<s:property value="#groupType" escape="false"/>',
                    x:'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['销售总额'],
                    x:'left'
                },
                toolbox: {
                    show: true,
                    feature: {
                        // 辅助线
                        /*mark: {show: true},
                        dataView: {show: true, readOnly: false},*/

                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                // 拖动功能
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: <s:property value="#groupByTypeList" escape="false"/>
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '销售总额',
                        type: 'bar',
                        data: <s:property value="#totalAmountList" escape="false"/>,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };

            // 为echarts对象加载数据
            myChart.setOption(option);
        });

    </script>
</head>
<body>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:600px;width: 800px"></div>

</body>
</html>
