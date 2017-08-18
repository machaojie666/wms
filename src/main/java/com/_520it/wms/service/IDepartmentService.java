package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Department;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

public interface IDepartmentService {

	void save(Department dept);

	void delete(Long id);

	void update(Department dept);

	Department get(Long id);

	List<Department> list();
	
	PageResult pageQuery(QueryObject qo);
}
