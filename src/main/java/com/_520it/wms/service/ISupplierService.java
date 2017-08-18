package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.Supplier;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SupplierQueryObject;

public interface ISupplierService {
	void delete(Long id);
	
	void save(Supplier entity);
	
    Supplier get(Long id);
    
    List<Supplier> list();
    
	void update(Supplier entity);
	
	PageResult pageQuery(SupplierQueryObject qo);
}
