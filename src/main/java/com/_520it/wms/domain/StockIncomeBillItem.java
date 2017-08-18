package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by 123 on 2017/8/5.
 */
@Setter@Getter
public class StockIncomeBillItem extends BaseDomain {
    private BigDecimal costPrice; // 采购价格
    private BigDecimal number; // 入库数量
    private BigDecimal amount; // 入库金额小计
    private String remark; // 备注
    private Product product; // 对应的商品
    private Long billId; // 对应的单据id

}
