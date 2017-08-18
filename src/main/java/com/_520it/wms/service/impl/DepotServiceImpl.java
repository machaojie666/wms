package com._520it.wms.service.impl;

import java.util.List;

import com._520it.wms.page.PageResult;

import com._520it.wms.domain.Depot;
import com._520it.wms.mapper.DepotMapper;
import com._520it.wms.query.DepotQueryObject;
import com._520it.wms.service.IDepotService;
import lombok.Setter;
public class DepotServiceImpl implements IDepotService {
	@Setter
	private DepotMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Depot entity) {
		  mapper.save(entity);
	}

	public Depot get(Long id) {
		return mapper.get(id);
	}

	public List<Depot> list() {
		return mapper.list();
	}

	public void update(Depot entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(DepotQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<Depot> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
