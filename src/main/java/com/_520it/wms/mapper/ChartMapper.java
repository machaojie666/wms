package com._520it.wms.mapper;

import com._520it.wms.query.OrderBillChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/8/8.
 */
public interface ChartMapper {

    List<Map<String,Object>> orderBillChart(OrderBillChartQueryObject qo);


    List<Map<String,Object>> querySaleChart(SaleChartQueryObject qo);

}
