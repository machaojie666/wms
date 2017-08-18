package com._520it.wms.service.impl;

import java.util.List;

import com._520it.wms.page.PageResult;

import com._520it.wms.domain.Brand;
import com._520it.wms.mapper.BrandMapper;
import com._520it.wms.query.BrandQueryObject;
import com._520it.wms.service.IBrandService;
import lombok.Setter;
public class BrandServiceImpl implements IBrandService {
	@Setter
	private BrandMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Brand entity) {
		  mapper.save(entity);
	}

	public Brand get(Long id) {
		return mapper.get(id);
	}

	public List<Brand> list() {
		return mapper.list();
	}

	public void update(Brand entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(BrandQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<Brand> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
