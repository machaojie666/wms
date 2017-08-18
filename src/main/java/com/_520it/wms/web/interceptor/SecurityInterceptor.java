package com._520it.wms.web.interceptor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 判断当前用户是否有权限访问当前action方法对应的权限
		Set<String> expressions = UserContext.getUserPermissions();
		Employee employee = UserContext.getCurrentUser();
		// 1.如果当前用户是超级管理员,放行
		if(employee.getAdmin()!=null && employee.getAdmin()){
			return invocation.invoke();
		}
		// 2.如果当前访问的方法上没有权限相关的注解,放行
		// 获取当前访问的action对象
		Object action = invocation.getProxy().getAction();
		// 获取访问的方法名
		String methodName = invocation.getProxy().getMethod();
		// 再获取方法对象
		Method method = action.getClass().getMethod(methodName);
		// 如果方法上没有相关的注解,放行
		if(!method.isAnnotationPresent(RequiredPermission.class)){
			return invocation.invoke();
		}
		// 3.当前用户有访问权限,放行
		// action的全限定名
		String actionName = action.getClass().getName();
		// 拼接当前访问的方法的权限表达式
		String expression = actionName + ":" + methodName;
		if(!expressions.contains(expression)){
			return "nopermission";
		}
		return invocation.invoke();
	}

}
