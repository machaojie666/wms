package com._520it.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;

public interface RoleMapper {
	
	void save(Role r);
	
	void delete(Long id);
	
	void update(Role r);
	
	Role get(Long id);
	
	List<Role> list();
	
	List<Role> getListData(QueryObject qo);
	
	Long getTotalCount(QueryObject qo);

	/**
	 * 保存角色和权限的关系
	 * @param rId
	 * @param pId
	 */
	void saveRelation(@Param("roleId") Long rId, @Param("permissionId")Long pId);
	/**
	 * 解除某个角色和权限的中间表的关系
	 * @param id 角色id
	 */
	void updateRelation(Long id);

	/**
	 * 添加角色和菜单的关系
	 * @param rId
	 * @param mId
	 */
	void saveRoleMenuRelation(@Param("roleId")Long rId, @Param("menuId") Long mId);

	/**
	 * 删除角色和菜单的关系
	 * @param id
	 */
	void updateRoleMenuRelation(Long id);
}
