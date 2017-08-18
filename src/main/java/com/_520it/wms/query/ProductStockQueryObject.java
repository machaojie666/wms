package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 123 on 2017/8/7.
 */
@Setter@Getter
public class ProductStockQueryObject extends QueryObject {

    private String keywords;
    private Long brandId = -1L;
    private Long depotId = -1L;
    private Integer limitNumber;

    public String getKeywords(){
        return empty2null(keywords);
    }

}
