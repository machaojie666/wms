package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.Product;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;

public interface IProductService {
	void delete(Long id);
	
	void save(Product entity);
	
    Product get(Long id);
    
    List<Product> list();
    
	void update(Product entity);
	
	PageResult pageQuery(ProductQueryObject qo);
}
