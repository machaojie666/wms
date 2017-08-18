package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2017/8/5.
 */
@Setter@Getter@ObjectProp("采购出库单")
public class StockOutcomeBill extends BaseAuditDomain {


    @ObjectProp("出库单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("出库总金额")
    private BigDecimal totalAmount;
    @ObjectProp("出库总数量")
    private BigDecimal totalNumber;
    @ObjectProp("仓库")
    private Depot depot;
    @ObjectProp("客户")
    private Client client;

    private List<StockOutcomeBillItem> items = new ArrayList<>();

}
