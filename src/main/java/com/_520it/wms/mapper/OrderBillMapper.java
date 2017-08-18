package com._520it.wms.mapper;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.query.OrderBillQueryObject;
import java.util.List;

public interface OrderBillMapper {
	void save(OrderBill entity);
	
	void update(OrderBill entity);
	
	void delete(Long id);
	
    OrderBill get(Long id);
    
    Long getTotalCount(OrderBillQueryObject qo);
    
    List<OrderBill> getListData(OrderBillQueryObject qo);

    void updateStatus(OrderBill bill);
}