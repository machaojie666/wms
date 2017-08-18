package com._520it.wms.web.action;

import com._520it.wms.util.UserContext;
import lombok.Setter;

import com._520it.wms.domain.Employee;
import com._520it.wms.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Setter
	private String username;
	@Setter
	private String password;
	@Setter
	private IEmployeeService empService;
	
	@Override
	public String execute() throws Exception {
		if(username == null || password == null){
			return LOGIN;
		}
		Employee employee = empService.checkLogin(username,password);
		if(employee == null){
			// 添加错误信息
			addActionError("亲,帐号或密码错误,重新登录吧~");
			putMsg("亲,帐号或密码错误,重新登录吧~");
			return LOGIN;
		} else {
			UserContext.setCurrentUser(employee);
			return "main";
		}
	}
}
