package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by 123 on 2017/8/4.
 */
// 采购订单明细
@Setter
@Getter
public class OrderBillItem extends BaseDomain {

    private BigDecimal costPrice; // 采购价格
    private BigDecimal number; // 采购数量
    private BigDecimal amount; // 采购金额小计
    private String remark; // 备注
    private Product product; // 对应的商品
    private Long billId; // 对应的单据id

}
