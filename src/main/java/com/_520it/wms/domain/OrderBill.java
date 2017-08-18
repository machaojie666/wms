package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2017/8/4.
 */
@Setter@Getter
@ObjectProp("采购订单")
public class OrderBill extends BaseAuditDomain {

    @ObjectProp("订单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("采购总金额")
    private BigDecimal totalAmount;
    @ObjectProp("采购总数量")
    private BigDecimal totalNumber;
    @ObjectProp("供应商")
    private Supplier supplier;

    private List<OrderBillItem> items = new ArrayList<>();

}
