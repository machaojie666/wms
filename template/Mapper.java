package ${packageName}.mapper;

import ${packageName}.domain.${className};
import ${packageName}.query.${className}QueryObject;
import java.util.List;

public interface ${className}Mapper {
	void save(${className} entity);
	
	void update(${className} entity);
	
	void delete(Long id);
	
    ${className} get(Long id);
    
	List<${className}> list();
	
    Long getTotalCount(${className}QueryObject qo);
    
    List<${className}> getListData(${className}QueryObject qo);
}