package com._520it.wms.web.action;

import com._520it.wms.query.ClientQueryObject;
import com._520it.wms.query.OrderBillChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.ISupplierService;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/8/8.
 */
// 处理所有的报表的请求
public class ChartAction extends BaseAction {

    @Setter
    private IChartService chartService;

    @Setter
    private ISupplierService supplierService;

    @Setter
    private IBrandService brandService;

    @Setter
    private IClientService clientService;

    @Getter
    private OrderBillChartQueryObject qo = new OrderBillChartQueryObject();

    @Getter
    private SaleChartQueryObject sqo = new SaleChartQueryObject();

    public String orderChart() {

        put("listData",chartService.orderBillChart(qo));
        put("groupTypes",OrderBillChartQueryObject.GROUP_BY_TYPES);
        put("suppliers",supplierService.list());
        put("brands",brandService.list());
        return "orderChart";
    }

    public String saleChart(){
        put("groupTypes",SaleChartQueryObject.GROUP_BY_TYPES);
        put("clients",clientService.list());
        put("brands",brandService.list());
        put("listData",chartService.saleChart(sqo));
        return "saleChart";
    }

    // 销售报表柱状图
    public String saleChartByBar(){
        put("groupTypes",SaleChartQueryObject.GROUP_BY_TYPES);
        put("clients",clientService.list());
        put("brands",brandService.list());
        List<Map<String, Object>> list = chartService.saleChart(sqo);
        put("listData",list);

        // 分组的类型
        List<Object> groupByTypeList = new ArrayList<>();
        // 对应的销售额
        List<Object> totalAmountList = new ArrayList<>();

        for (Map<String, Object> map : list) {
            groupByTypeList.add(map.get("groupByType"));
            totalAmountList.add(map.get("totalAmount"));
        }
        // 当前分组类型中的数据
        put("groupByTypeList", JSON.toJSONString(groupByTypeList));
        // 当前分组类型对应的数据
        put("totalAmountList", JSON.toJSONString(totalAmountList));

        // 当前分组类型
        put("groupType",SaleChartQueryObject.GROUP_BY_TYPES.get(sqo.getGroupByType()));
        return "saleChartByBar";
    }

    // 销售报表柱饼图
    public String saleChartByPie(){
        put("groupTypes",SaleChartQueryObject.GROUP_BY_TYPES);
        put("clients",clientService.list());
        put("brands",brandService.list());
        List<Map<String, Object>> list = chartService.saleChart(sqo);
        put("listData",list);

        // 分组的类型
        List<Object> groupByTypeList = new ArrayList<>();
        // 分组的类型和数据
        List<Map<String,Object>> dataList = new ArrayList<>();
        BigDecimal maxAmount = BigDecimal.ZERO;
        for (Map<String, Object> map : list) {
            groupByTypeList.add(map.get("groupByType"));
            Map<String,Object> dataMap = new HashMap();
            dataList.add(dataMap);
            dataMap.put("value",map.get("totalAmount"));
            dataMap.put("name",map.get("groupByType"));
            BigDecimal totalAmount = (BigDecimal) map.get("totalAmount");
            if (maxAmount.compareTo(totalAmount) <= 0) {
                maxAmount = totalAmount;
            }
        }
        put("maxAmount",maxAmount);
        // 当前分组类型中的数据
        put("groupByTypeList", JSON.toJSONString(groupByTypeList));
        // 分组类型和数据
        put("dataList", JSON.toJSONString(dataList));
        // 当前分组类型
        put("groupType",SaleChartQueryObject.GROUP_BY_TYPES.get(sqo.getGroupByType()));
        return "saleChartByPie";
    }

}
