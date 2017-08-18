package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 123 on 2017/8/8.
 */
@Setter@Getter
public class SaleAccount extends BaseDomain {
    private Date vdate; // 销售日期
    private BigDecimal number; // 销售数量
    private BigDecimal costPrice; // 成本价格
    private BigDecimal costAmount; // 成本总额
    private BigDecimal salePrice; // 销售价格
    private BigDecimal saleAmount; // 销售总额
    private Product product; // 销售货品
    private Employee saleman; // 销售人员
    private Client client; // 客户


}
