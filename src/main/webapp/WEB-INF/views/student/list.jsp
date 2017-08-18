<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/artDialog.js?skin=green"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>
<title>PSS-学生管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/common/common_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="student" namespace="/">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url="<s:url namespace="/" action="student_input"/>" />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>年龄</th> 							<th>姓名</th> 
							<th>操作</th>
						</tr>
						<tbody>
							<s:iterator value="#result.listData">
								<tr class="data_tr">
									<td>
										<input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>" />
									</td>
									<td>
										<s:property value="id" />
									</td>
									<td>
										<s:property value="age" />
									</td>
									<td>
										<s:property value="name" />
									</td>
									<td>
										<s:a namespace="" action="student_input">
											<s:param name="student.id" value="id"></s:param>
                                    		编辑
                                    	</s:a>
										<s:url namespace="" action="student_delete" var="deleteUrl">
											<s:param name="student.id" value="id"></s:param>
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
