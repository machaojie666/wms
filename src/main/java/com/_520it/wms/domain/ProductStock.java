package com._520it.wms.domain;

import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by 123 on 2017/8/6.
 */
// 货品库存
@Getter@Setter
public class ProductStock extends BaseDomain {
    private Product product; // 哪种货品
    private Depot depot; // 哪个仓库
    private BigDecimal price; // 库存价格(移动加权平均算法)
    private BigDecimal storeNumber; // 库存总量
    private BigDecimal amount; // 库存总额

}
