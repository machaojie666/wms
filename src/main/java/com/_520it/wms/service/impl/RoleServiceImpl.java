package com._520it.wms.service.impl;

import java.util.List;

import com._520it.wms.domain.SystemMenu;
import lombok.Setter;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.mapper.RoleMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;

public class RoleServiceImpl implements IRoleService {

	@Setter
	private RoleMapper roleMapper;

	@Override
	public void save(Role r) {
		roleMapper.save(r);
		// 保存角色和权限的关系  保存在中间表中
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.saveRelation(r.getId(), permission.getId());
		}
		// 保存角色和菜单的关系  保存在中间表中
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			roleMapper.saveRoleMenuRelation(r.getId(), menu.getId());
		}
	}

	@Override
	public void delete(Long id) {
		roleMapper.delete(id);
	}

	@Override
	public void update(Role r) {
		roleMapper.update(r);
		// 更新角色和权限的关系  保存在中间表中
		// 先将拥有的权限删除(否则会重复保存中间表的数据)
		roleMapper.updateRelation(r.getId());
		// 再将提交过来的权限保存在中间表中
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.saveRelation(r.getId(), permission.getId());
		}

		// 更新角色和菜单的关系  保存在中间表中
		// 先将拥有的菜单删除(否则会重复保存中间表的数据)
		roleMapper.updateRoleMenuRelation(r.getId());
		// 再将提交过来的菜单保存在中间表中
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			roleMapper.saveRoleMenuRelation(r.getId(), menu.getId());
		}
	}

	@Override
	public Role get(Long id) {
		Role dept = roleMapper.get(id);
		return dept;
	}

	@Override
	public List<Role> list() {
		List<Role> list = roleMapper.list();
		return list;
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		List<Role> listData = roleMapper.getListData(qo);
		Long totalCount = roleMapper.getTotalCount(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

}
