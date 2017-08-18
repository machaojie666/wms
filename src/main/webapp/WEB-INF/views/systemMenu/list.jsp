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
<title>PSS-系统菜单管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/common/common_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="systemMenu" namespace="/" method="post">
		<s:hidden name="qo.parentId"></s:hidden>
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<s:url namespace="/" action="systemMenu_input" var="inputVar">
								<s:param name="qo.parentId" value="qo.parentId"></s:param>
							</s:url>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url="<s:property value="#inputVar"/>"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					当前:
					<s:a namespace="/" action="systemMenu">
						根目录
					</s:a>
					<s:iterator value="#parents">
						>
						<s:a namespace="/" action="systemMenu">
							<s:property value="name"></s:property>
							<s:param name="qo.parentId" value="id"></s:param>
						</s:a>
					</s:iterator>
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>菜单名称</th>
							<th>上级菜单</th>
							<th>菜单编码</th>
							<th>URL</th>
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
										<s:property value="name" />
									</td>

									<td>
										<s:property value="parentName" />
									</td>

									<td>
										<s:property value="sn" />
									</td>
									<td>
										<s:property value="url" />
									</td>

									<td>
										<s:a namespace="" action="systemMenu_input">
											<s:param name="systemMenu.id" value="id"></s:param>
											<s:param name="qo.parentId" value="qo.parentId"></s:param>
                                    		编辑
                                    	</s:a>
										<s:url namespace="" action="systemMenu_delete" var="deleteUrl">
											<s:param name="systemMenu.id" value="id"></s:param>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">
											删除 
										</a>
										<s:url namespace="" action="systemMenu" var="selectChildsUrl">
											<s:param name="qo.parentId" value="id"></s:param>
										</s:url>
										<a href="<s:property value="#selectChildsUrl"/>">查看子菜单</a>
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
