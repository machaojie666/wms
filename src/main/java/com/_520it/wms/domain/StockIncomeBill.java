package com._520it.wms.domain;

import com.sun.xml.internal.rngom.parse.host.Base;
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
@Setter@Getter@ObjectProp("采购入库单")
public class StockIncomeBill extends BaseAuditDomain {

    @ObjectProp("入库单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("入库总金额")
    private BigDecimal totalAmount;
    @ObjectProp("入库总数量")
    private BigDecimal totalNumber;
    @ObjectProp("仓库")
    private Depot depot;

    private List<StockIncomeBillItem> items = new ArrayList<>();
}
