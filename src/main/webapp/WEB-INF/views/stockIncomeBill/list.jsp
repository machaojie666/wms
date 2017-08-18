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
            $("[name='qo.beginDate'],[name='qo.endDate']").addClass("Wdate").click(function () {
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
<s:form id="searchForm" action="stockIncomeBill" namespace="/">
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
                        供应商
                        <s:select list="#depots" name="qo.depotId" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="所有仓库" id="searchForm_qo_depotId" class="ui_select01">
                        </s:select>
                        状态
                        <s:select list="#{0:'未审核',1:'已审核'}" headerKey="-1"
                                  headerValue="所有状态" name="qo.status" class="ui_select01">
                        </s:select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="stockIncomeBill_input"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>入库单编号</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>入库总数量</th>
                        <th>入库总金额</th>
                        <th>录入人</th>
                        <th>审核人</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr class="data_tr">
                            <td>
                                <input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/>
                            </td>

                            <td>
                                <s:property value="sn"/>
                            </td>

                            <td>
                                <s:date name="vdate" format="yyyy-MM-dd" var="vdate"/>
                                <s:property value="#vdate"/>
                            </td>

                            <td>
                                <s:property value="depot.name"/>
                            </td>

                            <td>
                                <s:property value="totalNumber"/>
                            </td>

                            <td>
                                <s:property value="totalAmount"/>
                            </td>

                            <td>
                                <s:property value="inputUser.name"/>
                            </td>

                            <td>
                                <s:property value="auditor.name"/>
                            </td>
                            <td>
                                <s:if test="status==0">
                                    <span style="color:red">未审核</span>
                                </s:if>
                                <s:else>
                                    <span style="color:green">已审核</span>
                                </s:else>
                            </td>

                            <td>
                                <s:if test="status==0">
                                    <s:url namespace="" action="stockIncomeBill_audit" var="auditUrl">
                                        <s:param name="stockIncomeBill.id" value="id"></s:param>
                                    </s:url>
                                    <a href="javascript:;" class="btn_audit" data-url="<s:property value="#auditUrl"/>">
                                        审核
                                    </a>
                                    <s:a namespace="" action="stockIncomeBill_input">
                                        <s:param name="stockIncomeBill.id" value="id"></s:param>
                                        编辑
                                    </s:a>
                                    <s:url namespace="" action="stockIncomeBill_delete" var="deleteUrl">
                                        <s:param name="stockIncomeBill.id" value="id"></s:param>
                                    </s:url>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url="<s:property value="#deleteUrl"/>">
                                        删除
                                    </a>
                                </s:if>
                                <s:else>
                                    <s:a namespace="" action="stockIncomeBill_show">
                                        <s:param name="stockIncomeBill.id" value="id"></s:param>
                                        查看
                                    </s:a>
                                </s:else>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 分页条 -->
        <jsp:include page="/common/common_page.jsp"></jsp:include>
    </div>
</s:form>
</body>
</html>
