package com._520it.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.QueryObject;

public interface EmployeeMapper {
	
	void save(Employee e);
	
	void delete(Long id);
	
	void update(Employee e);
	
	Employee get(Long id);
	
	List<Employee> list();
	
	List<Employee> getListData(QueryObject qo);
	
	Long getTotalCount(QueryObject qo);
	/**
	 * 删除用户和部门的关系
	 * @param id 用户的id
	 */
	void deleteRelation(Long id);

	void saveRelation(@Param("empId")Long id, @Param("roleId")Long id2);
	/**
	 * 删除用户和角色的关系
	 * @param id 用户的id
	 */
	void updateRelation(Long empId);
	
	/**
	 * 登录校验,登录成功,返回当前对象,不成功返回null
	 * @param username
	 * @param password
	 */
	Employee checkLogin(@Param("username")String username, @Param("password")String password);

    void batchDelete(Long[] ids);
}
