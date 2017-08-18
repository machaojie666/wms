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
    <script type="text/javascript" src="/js/plugins/fancyBox/jquery.fancybox.pack.js"></script>
    <link type="text/css" rel="stylesheet" href="/js/plugins/fancyBox/jquery.fancybox.css">
    <script type="text/javascript">
        $(function () {
            // 图片弹出层
            $(".fcc").fancybox();
        });
    </script>
    <title>PSS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<!-- 消息提示 -->
<jsp:include page="/common/common_msg.jsp"></jsp:include>
<s:form id="searchForm" action="product" namespace="/">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        编码/名称
                        <s:textfield class="ui_input_txt02" name="qo.keywords"/>
                        品牌
                        <s:select list="#brands" class="ui_select01" name="qo.brandId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有品牌"></s:select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="product_input"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>货品图片</th>
                        <th>货品名称</th>
                        <th>货品编码</th>
                        <th>货品品牌</th>
                        <th>成本价格</th>
                        <th>销售价格</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr class="data_tr">
                            <td>
                                <input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/>
                            </td>

                            <td>
                                <a class="fcc" href="<s:property value="imagePath" />"
                                   title="<s:property value="name"/>">
                                    <img src="<s:property value="smallImagePath" />" class="list_img">
                                </a>
                            </td>
                            <td>
                                <s:property value="name"/>
                            </td>
                            <td>
                                <s:property value="sn"/>
                            </td>
                            <td>
                                <s:property value="brand.name"/>
                            </td>
                            <td>
                                <s:property value="costPrice"/>
                            </td>
                            <td>
                                <s:property value="salePrice"/>
                            </td>
                            <td>
                                <s:a namespace="" action="product_input">
                                    <s:param name="product.id" value="id"/>
                                    编辑
                                </s:a>
                                <s:url namespace="" action="product_delete" var="deleteUrl" escapeAmp="false">
                                    <s:param name="product.id" value="id"></s:param>
                                    <s:param name="product.imagePath" value="imagePath"></s:param>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">
                                    删除
                                </a>
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
