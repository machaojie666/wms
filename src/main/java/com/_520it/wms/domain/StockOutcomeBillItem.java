package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by 123 on 2017/8/5.
 */
// 出库单明细
@Setter@Getter
public class StockOutcomeBillItem extends BaseDomain {
    private BigDecimal salePrice; // 出库价格
    private BigDecimal number; // 出库数量
    private BigDecimal amount; // 出库金额小计
    private String remark; // 备注
    private Product product; // 对应的商品
    private Long billId; // 对应的单据id

}
