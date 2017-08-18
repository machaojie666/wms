<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2017/7/28
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/artDialog.js?skin=green"></script>
    <script type="text/javascript" src="/js/system/commonAll.js"></script>

    <title>PSS-部门管理</title>
    <style>
        .alt td {
            background: #000000 !important;
        }
    </style>
</head>

<body>

<s:form id="searchForm" action="department.action">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <input type="button" value="新增" data-url="<s:url namespace="/" action="department_input"/>"
                               class="ui_input_btn01 btn_input"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>名称</th>
                        <th>编码</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td>
                                <s:a namespace="/" action="department_input">
                                    <s:param name="dept.id" value="id"></s:param>
                                    编辑
                                </s:a>

                                <s:url namespace="/" action="department_delete" var="deleteURL">
                                    <s:param name="dept.id" value="id"></s:param>
                                </s:url>
                                <a href="javascript:;" data-url="<s:property value="deleteURL"/>"
                                   class="btn_delete">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <%--引入分页条--%>
            <jsp:include page="/common/common_page.jsp"></jsp:include>
        </div>
    </div>
</s:form>
<%--引入消息提示--%>
<jsp:include page="/common/common_msg.jsp"></jsp:include>
</body>
</html>
