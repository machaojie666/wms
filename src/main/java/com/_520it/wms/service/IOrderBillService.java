package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;

public interface IOrderBillService {
	void delete(Long id);
	
	void save(OrderBill entity);
	
    OrderBill get(Long id);
    
	void update(OrderBill entity);
	
	PageResult pageQuery(OrderBillQueryObject qo);

	/**
	 * 审核
	 * @param billId
	 */
	void audit(Long billId);
}
