package com._520it.wms.mapper;

import java.util.List;
import java.util.Set;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

public interface PermissionMapper {
	
	void save(Permission p);
	
	void delete(Long id);
	
	List<Permission> list();
	
	List<Permission> getListData(QueryObject qo);
	
	Long getTotalCount(QueryObject qo);
	
	/**
	 * 根据角色id获取当前角色拥有的权限
	 * @param roleId
	 * @return
	 */
	List<Permission> getByRoleId(Long roleId);

	Set<String> getByEmployeeId(Long empId);
}
