package com._520it.wms.service.impl;

import com._520it.wms.mapper.ChartMapper;
import com._520it.wms.query.OrderBillChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IChartService;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/8/8.
 */
public class ChartServiceImpl implements IChartService {

    @Setter
    private ChartMapper chartMapper;

    @Override
    public List<Map<String, Object>> orderBillChart(OrderBillChartQueryObject qo) {
        return chartMapper.orderBillChart(qo);
    }

    @Override
    public List<Map<String, Object>> saleChart(SaleChartQueryObject qo) {
        return chartMapper.querySaleChart(qo);
    }
}
