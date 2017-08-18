package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 123 on 2017/8/5.
 */
@Setter
@Getter
@ObjectProp("仓库")
public class Depot extends BaseDomain {

    @ObjectProp("仓库名称")
    private String name;
    @ObjectProp("仓库地址")
    private String location;


}
