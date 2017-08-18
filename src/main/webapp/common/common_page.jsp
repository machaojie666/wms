<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div class="ui_tb_h30">
	<div class="ui_flt" style="height: 30px; line-height: 30px;">
		共有
		<span class="ui_txt_bold04"><s:property value="#result.totalCount"></s:property></span>
		条记录，当前第
		<span class="ui_txt_bold04"><s:property value="qo.currentPage"></s:property>/<s:property
				value="#result.totalPage"></s:property></span>
		页
	</div>
	<div class="ui_frt">
		<input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
		<input type="button" value="上一页" class="ui_input_btn01 btn_page"
			   data-page="<s:property value="#result.prePage"></s:property>"/>
		<input type="button" value="下一页" class="ui_input_btn01 btn_page"
			   data-page="<s:property value="#result.nextPage"></s:property>"/>
		<input type="button" value="尾页" class="ui_input_btn01 btn_page"
			   data-page="<s:property value="#result.totalPage"></s:property>"/>

		<s:select list="{5,10,15,20}" name="qo.pageSize" class="ui_select02"></s:select>
		转到第<s:textfield name="qo.currentPage" class="ui_input_txt01"/>页
		<input type="button" class="ui_input_btn01 btn_page" value="跳转"/>
	</div>
</div>