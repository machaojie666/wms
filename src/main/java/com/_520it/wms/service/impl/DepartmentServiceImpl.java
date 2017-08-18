package com._520it.wms.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.wms.domain.Department;
import com._520it.wms.mapper.DepartmentMapper;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {
	
	@Setter
	private DepartmentMapper deptMapper;
	@Setter
	private EmployeeMapper empMapper;
	
	@Override
	public void save(Department dept) {
		deptMapper.save(dept);
	}

	@Override
	public void delete(Long id) {
		empMapper.deleteRelation(id);
		deptMapper.delete(id);
	}

	@Override
	public void update(Department dept) {
		deptMapper.update(dept);
	}

	@Override
	public Department get(Long id) {
		Department dept = deptMapper.get(id);
		return dept;
	}

	@Override
	public List<Department> list() {
		List<Department> list = deptMapper.list();
		return list;
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		List<Department> listData = deptMapper.getListData(qo);
		Long totalCount = deptMapper.getTotalCount(qo);
		return new PageResult(listData, totalCount.intValue(), qo.getCurrentPage(), qo.getPageSize());
	}

}
