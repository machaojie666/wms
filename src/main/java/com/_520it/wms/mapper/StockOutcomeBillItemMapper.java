package com._520it.wms.mapper;

import com._520it.wms.domain.StockOutcomeBillItem;

public interface StockOutcomeBillItemMapper {
	void save(StockOutcomeBillItem item);

    void deleteByBillId(Long id);

}