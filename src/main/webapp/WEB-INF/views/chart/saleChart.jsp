<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validation-1.8.1/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=green"></script>
    <script type="text/javascript" src="/js/system/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            // 添加日期选择器
            $('[name="sqo.beginDate"],[name="sqo.endDate"]').addClass("Wdate").click(function () {
                WdatePicker();
            });
            $(".btn_pie").click(function () {
                var param = $("#searchForm").serialize();
                var url = "/chart_saleChartByPie.action?" + param;
                $.dialog.open(url, {
                    title: "销售额饼图",
                    width: 800,
                    height: 600,
                });
            });
            $(".btn_bar").click(function () {
                var param = $("#searchForm").serialize();
                var url = "/chart_saleChartByBar.action?" + param;
                $.dialog.open(url, {
                    title: "销售额柱状图",
                    width: 800,
                    height: 600,
                });
            });
        });
    </script>
    <title>PSS-采购订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<!-- 消息提示 -->
<jsp:include page="/common/common_msg.jsp"></jsp:include>
<s:form id="searchForm" action="chart_saleChart" namespace="/">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="sqo.beginDate" format="yyyy-MM-dd" var="bd"></s:date>
                        <input type="text" name="sqo.beginDate" value="<s:property value="#bd"/>"
                               class="ui_input_txt02"/>
                        ~
                        <s:date name="sqo.endDate" format="yyyy-MM-dd" var="ed"></s:date>
                        <input type="text" name="sqo.endDate" value="<s:property value="#ed"/>"
                               class="ui_input_txt02"/>
                        货品
                        <s:textfield class="ui_input_txt02" name="sqo.keywords"/>
                        客户
                        <s:select list="#clients" name="sqo.clientId" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="客户" id="searchForm_sqo_clientId" class="ui_select01">
                        </s:select>
                        品牌
                        <s:select list="#brands" name="sqo.brandId" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="所有品牌" id="searchForm_sqo_brandId" class="ui_select01">
                        </s:select>
                        分组
                        <s:select list="#groupTypes" name="sqo.groupByType" id="searchForm_sqo_groupId" class="ui_select01">
                        </s:select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="饼图" class="ui_input_btn01 btn_pie" data-page="1"/>
                        <input type="button" value="柱状图" class="ui_input_btn01 btn_bar" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>分组类型</th>
                        <th>销售总数量</th>
                        <th>销售总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#listData">
                        <tr class="data_tr">
                            <td><s:property value="groupByType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="grossProfit"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>
