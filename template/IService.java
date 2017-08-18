package ${packageName}.service;
import java.util.List;
import ${packageName}.domain.${className};
import ${packageName}.page.PageResult;
import ${packageName}.query.${className}QueryObject;

public interface I${className}Service {
	void delete(Long id);
	
	void save(${className} entity);
	
    ${className} get(Long id);
    
    List<${className}> list();
    
	void update(${className} entity);
	
	PageResult pageQuery(${className}QueryObject qo);
}
