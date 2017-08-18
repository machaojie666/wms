package com._520it.wms.service.impl;

import java.util.List;

import com._520it.wms.page.PageResult;

import com._520it.wms.domain.Supplier;
import com._520it.wms.mapper.SupplierMapper;
import com._520it.wms.query.SupplierQueryObject;
import com._520it.wms.service.ISupplierService;
import lombok.Setter;
public class SupplierServiceImpl implements ISupplierService {
	@Setter
	private SupplierMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Supplier entity) {
		  mapper.save(entity);
	}

	public Supplier get(Long id) {
		return mapper.get(id);
	}

	public List<Supplier> list() {
		return mapper.list();
	}

	public void update(Supplier entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SupplierQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<Supplier> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
