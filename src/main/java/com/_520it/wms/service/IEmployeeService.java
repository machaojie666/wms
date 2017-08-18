package com._520it.wms.service;

import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IEmployeeService {

	void save(Employee e);

	void delete(Long id);

	void update(Employee e);

	Employee get(Long id);

	List<Employee> list();
	
	PageResult pageQuery(QueryObject qo);

	Employee checkLogin(String username, String password);

    void batchDelete(Long[] ids);
}
