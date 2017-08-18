package ${packageName}.service.impl;

import java.util.List;

import com._520it.wms.page.PageResult;

import ${packageName}.domain.${className};
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.query.${className}QueryObject;
import ${packageName}.service.I${className}Service;
import lombok.Setter;
public class ${className}ServiceImpl implements I${className}Service {
	@Setter
	private ${className}Mapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(${className} entity) {
		  mapper.save(entity);
	}

	public ${className} get(Long id) {
		return mapper.get(id);
	}

	public List<${className}> list() {
		return mapper.list();
	}

	public void update(${className} entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(${className}QueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<${className}> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
