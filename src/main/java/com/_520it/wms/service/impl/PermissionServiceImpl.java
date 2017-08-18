package com._520it.wms.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.web.action.BaseAction;

public class PermissionServiceImpl implements IPermissionService,
		ApplicationContextAware {

	@Setter
	private PermissionMapper permissionMapper;

	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

	@Override
	public void reload() {
		// 从数据库中获取所有的权限对象
		List<Permission> list = permissionMapper.list();
		Set<String> expressions = new HashSet<>();
		// 迭代权限对象,取出权限表达式,添加到Set集合中,在添加时判断权限表达式是否存在
		for (Permission per : list) {
			String exp = per.getExpression();
			expressions.add(exp);
		}
		// 根据类型获取所有继承自BaseAction的action对象 
		Map<String, BaseAction> actionMap = ctx
				.getBeansOfType(BaseAction.class);
		// 从map中取出所有action对象
		Collection<BaseAction> actions = actionMap.values();
		// 迭代action
		Iterator<BaseAction> it = actions.iterator();
		while (it.hasNext()) {
			BaseAction action = it.next();
			// 迭代所有action对象,获取action中所有方法
			Method[] methods = action.getClass().getMethods();
			for (Method m : methods) {
				// 判断方法上是否有自定义的权限注解
				if (m.isAnnotationPresent(RequiredPermission.class)) {
					RequiredPermission requiredPermission = m
							.getAnnotation(RequiredPermission.class);
					// 获取注解中传递的数据:权限名
					String permissionName = requiredPermission.value();
					// action的全限定名
					String actionName = action.getClass().getName();
					// 方法名
					String methodName = m.getName();
					// 拼接权限表达式
					String expression = actionName + ":" + methodName;
					if(!expressions.contains(expression)){
						// 把权限名和权限表达式封装成权限对象保存到数据库
						Permission p = new Permission();
						p.setName(permissionName);
						p.setExpression(expression);
						permissionMapper.save(p);
					}
				}
			}

		}

	}

	@Override
	public void save(Permission p) {
		permissionMapper.save(p);
	}

	@Override
	public void delete(Long id) {
		permissionMapper.delete(id);
	}

	@Override
	public List<Permission> list() {
		List<Permission> list = permissionMapper.list();
		return list;
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = permissionMapper.getTotalCount(qo);
		List<Permission> listData = permissionMapper.getListData(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

}
