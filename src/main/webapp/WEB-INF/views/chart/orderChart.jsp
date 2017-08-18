<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/artDialog.js?skin=green"></script>
    <script type="text/javascript" src="/js/system/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            // 添加日期选择器
            $('[name="qo.beginDate"],[name="qo.endDate"]').addClass("Wdate").click(function () {
                WdatePicker();
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
<s:form id="searchForm" action="chart_orderChart" namespace="/">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qo.beginDate" format="yyyy-MM-dd" var="bd"></s:date>
                        <input type="text" name="qo.beginDate" value="<s:property value="#bd"/>"
                               class="ui_input_txt02"/>
                        ~
                        <s:date name="qo.endDate" format="yyyy-MM-dd" var="ed"></s:date>
                        <input type="text" name="qo.endDate" value="<s:property value="#ed"/>"
                               class="ui_input_txt02"/>
                        货品
                        <s:textfield class="ui_input_txt02" name="qo.keywords"/>
                        供应商
                        <s:select list="#suppliers" name="qo.supplierId" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="所有供应商" id="searchForm_qo_supplierId" class="ui_select01">
                        </s:select>
                        品牌
                        <s:select list="#brands" name="qo.brandId" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="所有品牌" id="searchForm_qo_brandId" class="ui_select01">
                        </s:select>
                        分组
                        <s:select list="#groupTypes" name="qo.groupByType" id="searchForm_qo_groupId" class="ui_select01">
                        </s:select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>分组类型</th>
                        <th>采购总数量</th>
                        <th>采购总金额</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#listData">
                        <tr class="data_tr">
                            <td><s:property value="groupByType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
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
