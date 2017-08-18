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
                    text: '销售总额',
                    subtext: '<s:property value="#groupType"/>',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: <s:property value="#groupByTypeList" escape="false"/>
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        magicType: {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: <s:property value="maxAmount" escape="false"/>
                                }
                            }
                        },
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: <s:property value="#dataList" escape="false"/>
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
