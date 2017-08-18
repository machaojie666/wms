package com._520it.wms.web.interceptor;

import java.util.Map;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 从session中获取当前登录的对象,如果为null,没有登录,回到登录页面
		Employee employee = UserContext.getCurrentUser();
		if(employee == null){
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
