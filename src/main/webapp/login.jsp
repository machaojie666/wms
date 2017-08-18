<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2017/7/29
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>小码哥PSS（演示版）</title>
    <link href="style/login_css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/system/login.js"></script>
</head>
<body>
<div id="login_center">
    <div id="login_area">
        <div id="login_box">
            <div id="login_form">
                <s:form id="submitForm" action="login" namespace="/">
                    <div id="login_tip">
                        <s:if test="hasActionErrors()">
                            <span id="login_err" class="sty_txt2"><s:actionerror/></span>
                        </s:if>
                    </div>
                    <div>
                        用户名：<s:textfield name="username" class="username" id="name" value="admin"/>
                    </div>
                    <div>
                        密&nbsp;&nbsp;&nbsp;&nbsp;码：<s:password name="password" class="pwd" id="pwd"
                    />
                    </div>
                    <div id="btn_area">
                        <input type="button" class="login_btn" id="login_sub" value="登  录">
                        <input type="reset" class="login_btn" id="login_ret" value="重 置">
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
