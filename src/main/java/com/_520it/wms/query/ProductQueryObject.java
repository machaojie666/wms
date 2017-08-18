package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQueryObject extends QueryObject {

    private String keywords;
    private Long brandId = -1L;

    public String getKeywords(){
        return empty2null(keywords);
    }

}
