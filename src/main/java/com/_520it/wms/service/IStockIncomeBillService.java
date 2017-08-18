package com._520it.wms.service;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.query.StockIncomeBillQueryObject;

public interface IStockIncomeBillService {
	void delete(Long id);
	
	void save(StockIncomeBill entity);

	StockIncomeBill get(Long id);
    
	void update(StockIncomeBill entity);
	
	PageResult pageQuery(StockIncomeBillQueryObject qo);

	/**
	 * 到货审核入库
	 * @param billId
	 */
	void audit(Long billId);
}
