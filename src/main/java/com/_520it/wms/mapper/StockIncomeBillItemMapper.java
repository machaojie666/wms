package com._520it.wms.mapper;

import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.domain.StockIncomeBillItem;

public interface StockIncomeBillItemMapper {
	void save(StockIncomeBillItem entity);

    void deleteByBillId(Long id);

}