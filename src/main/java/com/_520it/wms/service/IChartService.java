package com._520it.wms.service;

import com._520it.wms.query.OrderBillChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/8/8.
 */
public interface IChartService {


    List<Map<String,Object>> orderBillChart(OrderBillChartQueryObject qo);

    List<Map<String,Object>> saleChart(SaleChartQueryObject qo);
}
