package com._520it.wms.service;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;

/**
 * Created by 123 on 2017/8/7.
 */
public interface IProductStockService {

    PageResult query(ProductStockQueryObject qo);

    void stockIncome(StockIncomeBill bill);

    void stockOutcome(StockOutcomeBill bill);
}
