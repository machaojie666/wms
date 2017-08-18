<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2017/7/28
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/system/employee.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validation-1.8.1/jquery.validate.js"></script>
</head>
<body>
<s:form name="editForm" action="/employee_saveOrUpdate.action" id="editForm">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">用户编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="emp.id"></s:hidden>
                <tr>
                    <td class="ui_text_rt" width="140">用户名</td>
                    <td class="ui_text_lt">
                        <s:textfield name="emp.name" class="ui_input_txt02"/>
                    </td>
                </tr>
                <s:if test="emp.id == null">
                    <tr>
                        <td class="ui_text_rt" width="140">密码</td>
                        <td class="ui_text_lt">
                            <s:password name="emp.password" id="password" class="ui_input_txt02"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="ui_text_rt" width="140">验证密码</td>
                        <td class="ui_text_lt">
                            <s:password name="repassword" class="ui_input_txt02"/>
                        </td>
                    </tr>
                </s:if>
                <tr>
                    <td class="ui_text_rt" width="140">Email</td>
                    <td class="ui_text_lt">
                        <s:textfield name="emp.email" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">年龄</td>
                    <td class="ui_text_lt">
                        <s:textfield name="emp.age" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">所属部门</td>
                    <td class="ui_text_lt">
                        <s:select list="#depts" name="emp.dept.id" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="--请选择--"></s:select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">超级管理员</td>
                    <td class="ui_text_lt">
                        <s:checkbox name="emp.admin" class="ui_checkbox01"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">角色</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <s:select list="#roles" listKey="id" listValue="name" name="" multiple="true"
                                              class="ui_multiselect01 allRoles"></s:select>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <s:select list="emp.roles" listKey="id" listValue="name"
                                              name="emp.roles.id" multiple="true"
                                              class="ui_multiselect01 selectRoles"></s:select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
<jsp:include page="/common/common_msg.jsp"></jsp:include>
</body>
</html>