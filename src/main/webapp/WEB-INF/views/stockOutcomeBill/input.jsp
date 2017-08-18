<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validation-1.8.1/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=green"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript">
        $(function () {
            $("[name='stockOutcomeBill.vdate']").addClass("Wdate").click(function () {
                WdatePicker();
            });
            // 清空当前行的数据
            function removeVal(currentTr) {
                currentTr.find("[tag=pid]").val("");
                currentTr.find("[tag=name]").val("");
                currentTr.find("[tag=salePrice]").val("");
                currentTr.find("[tag=number]").val("");
                currentTr.find("[tag=remark]").val("");
                currentTr.find("[tag=brand]").html("");
                currentTr.find("[tag=amount]").html("");
            }
            $(".appendRow").click(function () {
                var newTr = $("#edit_table_body tr:first").clone();
                $("#edit_table_body").append(newTr);
                removeVal(newTr);
            });
            $("#edit_table_body").on("click", ".removeItem", function () {
                var currentTr = $(this).closest("tr");
                // 如果body中的行数大于1,删除
                if ($("#edit_table_body tr").size() > 1) {
                    currentTr.remove();
                } else {
                    // 如果只剩一行,清空当前行数据
                    removeVal(currentTr);
                }
            }).on("click", ".searchproduct", function () {
                var currentTr = $(this).closest("tr");
                var url = "/product_selectProductList.action";
                $.dialog.open(url, {
                    title: "货品列表",
                    width: 900,
                    height: 500,
                    close: function () {
                        // 接收来自选择商品页面传回的数据
                        var json = $.dialog.data("jsonString");
                        if(json){
                            // 回填数据
                            currentTr.find("[tag=pid]").val(json.id);
                            currentTr.find("[tag=name]").val(json.name);
                            currentTr.find("[tag=salePrice]").val(json.salePrice);
                            currentTr.find("[tag=brand]").html(json.brandName);
                        }
                    }
                });
            }).on("change","[tag='salePrice'],[tag='number']",function () {
                var currentTr = $(this).closest("tr"); // 当前所在行
                var salePrice = currentTr.find("[tag='salePrice']").val();
                var number = currentTr.find("[tag='number']").val();
                if (salePrice && number) {
                    // 计算总额
                    currentTr.find("[tag='amount']").html(salePrice * number).toFixed(2);
                } else {
                    // 如果为空,设置为0
                    currentTr.find("[tag='amount']").html(0);
                }
            });
            // 点击提交按钮
            $(".btn_submit").click(function () {
                $("#edit_table_body tr").each(function (index, item) {
                    // 提交表单之前,获取tbody中的每个tr
                    $(item).find("[tag=pid]").prop("name", "stockOutcomeBill.items[" + index + "].product.id");
                    $(item).find("[tag=salePrice]").prop("name", "stockOutcomeBill.items[" + index + "].salePrice");
                    $(item).find("[tag=number]").prop("name", "stockOutcomeBill.items[" + index + "].number");
                    $(item).find("[tag=remark]").prop("name", "stockOutcomeBill.items[" + index + "].remark");
                })
                $("#editForm").submit();
            });
        });
    </script>


</head>
<body>
<!-- =============================================== -->
<%@include file="/common/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="stockOutcomeBill_saveOrUpdate" id="editForm">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购出库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20"
                         style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="stockOutcomeBill.id"></s:hidden>

                <tr>
                    <td class="ui_text_rt" width="140">出库单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockOutcomeBill.sn" cssClass="ui_input_txt02"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <s:select list="#depots" listKey="id" listValue="name" name="stockOutcomeBill.depot.id"
                                  cssClass="ui_select03"></s:select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">客户</td>
                    <td class="ui_text_lt">
                        <s:select list="#clients" listKey="id" listValue="name" name="stockOutcomeBill.client.id"
                                  cssClass="ui_select03"></s:select>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:date name="stockOutcomeBill.vdate" format="yyyy-MM-dd" var="vdate"/>
                        <input type="text" name="stockOutcomeBill.vdate" class="ui_input_txt02"
                               value="<s:property value="#vdate"/>">
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">出库单明细</td>
                </tr>

                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <s:if test="stockOutcomeBill.id==null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02"
                                                     tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="stockOutcomeBill.items[0].product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="salePrice" name="stockOutcomeBill.items[0].salePrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="stockOutcomeBill.items[0].number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="stockOutcomeBill.items[0].remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="stockOutcomeBill.items">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02"
                                                         name="product.name" tag="name"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <s:hidden name="product.id" tag="pid"/>
                                        </td>
                                        <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                        <td><s:textfield tag="salePrice" name="salePrice"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><s:textfield tag="number" name="number"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><span tag="amount"><s:property value="amount"/></span></td>
                                        <td><s:textfield tag="remark" name="remark"
                                                         cssClass="ui_input_txt02"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;
                        <input type="submit" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;
                        <input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>