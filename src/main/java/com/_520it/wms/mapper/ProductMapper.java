package com._520it.wms.mapper;

import com._520it.wms.domain.Product;
import com._520it.wms.query.ProductQueryObject;
import java.util.List;

public interface ProductMapper {
	void save(Product entity);
	
	void update(Product entity);
	
	void delete(Long id);
	
    Product get(Long id);
    
	List<Product> list();
	
    Long getTotalCount(ProductQueryObject qo);
    
    List<Product> getListData(ProductQueryObject qo);
}