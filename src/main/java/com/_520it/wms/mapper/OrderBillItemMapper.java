package com._520it.wms.mapper;

import com._520it.wms.domain.OrderBillItem;
import java.util.List;

public interface OrderBillItemMapper {
	void save(OrderBillItem entity);


    void deleteByBillId(Long id);
}