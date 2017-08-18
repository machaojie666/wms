package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 123 on 2017/8/2.
 */
@Getter
@Setter
@ObjectProp("品牌管理")
public class Brand extends BaseDomain {

    @ObjectProp("品牌名称")
    private String name;
    @ObjectProp("品牌编码")
    private String sn;
}
