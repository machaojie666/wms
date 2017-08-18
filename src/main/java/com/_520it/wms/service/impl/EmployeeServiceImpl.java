package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements IEmployeeService {
	
	@Setter
	private EmployeeMapper empMapper;
	@Setter
	private PermissionMapper permissionMapper;
	@Override
	public void save(Employee e) {
		if (e.getDept().getId() == -1) {
			e.setDept(null);
		}
		empMapper.save(e);
		// 保存员工和角色的关系
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			empMapper.saveRelation(e.getId(),role.getId());
		}
	}

	@Override
	public void delete(Long id) {
		empMapper.delete(id);
	}

	@Override
	public void update(Employee e) {
		empMapper.update(e);
		// 先将拥有的权限删除(否则会重复保存中间表的数据)
		empMapper.updateRelation(e.getId());
		// 再将提交过来的权限保存在中间表中
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			empMapper.saveRelation(e.getId(),role.getId());
		}
	}

	@Override
	public Employee get(Long id) {
		Employee e = empMapper.get(id);
		return e;
	}

	@Override
	public List<Employee> list() {
		List<Employee> list = empMapper.list();
		return list;
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		List<Employee> listData = empMapper.getListData(qo);
		Long totalCount = empMapper.getTotalCount(qo);
		return new PageResult(listData, totalCount.intValue(), qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public Employee checkLogin(String username, String password) {
		Employee employee = empMapper.checkLogin(username,password);
		if(employee == null){
			return employee;
		}
		//Set<String> expressions = new HashSet<>();
		/*List<Role> roles = employee.getRoles();
		for (Role role : roles) {
			List<Permission> permissions = permissionMapper.getByRoleId(role.getId());
			for (Permission permission : permissions) {
				String expression = permission.getExpression();
				expressions.add(expression);
			}
		}*/
		// 在permissionMapper中编写一条sql,根据Employee的id查询出对应的权限表达式
		Set<String> expressions = permissionMapper.getByEmployeeId(employee.getId());

		UserContext.setUserPermissions(expressions);
		return employee;
	}

	@Override
	public void batchDelete(Long[] ids) {
		empMapper.batchDelete(ids);
	}

}
