package com._520it.wms.mapper;

import java.util.List;

import com._520it.wms.domain.Department;
import com._520it.wms.query.QueryObject;

public interface DepartmentMapper {
	
	void save(Department dept);
	
	void delete(Long id);
	
	void update(Department dept);
	
	Department get(Long id);
	
	List<Department> list();
	
	List<Department> getListData(QueryObject qo);
	
	Long getTotalCount(QueryObject qo);
	
}
