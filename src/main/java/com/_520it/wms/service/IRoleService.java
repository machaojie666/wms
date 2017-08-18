package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

public interface IRoleService {

	void save(Role dept);

	void delete(Long id);

	void update(Role dept);

	Role get(Long id);

	List<Role> list();
	
	PageResult pageQuery(QueryObject qo);
}
