package com._520it.wms.service.impl;

import java.util.List;

import com._520it.wms.page.PageResult;

import com._520it.wms.domain.Product;
import com._520it.wms.mapper.ProductMapper;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IProductService;
import lombok.Setter;
public class ProductServiceImpl implements IProductService {
	@Setter
	private ProductMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Product entity) {
		  mapper.save(entity);
	}

	public Product get(Long id) {
		return mapper.get(id);
	}

	public List<Product> list() {
		return mapper.list();
	}

	public void update(Product entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ProductQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}

		List<Product> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
