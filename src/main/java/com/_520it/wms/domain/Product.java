package com._520it.wms.domain;

import com.alibaba.fastjson.JSON;
import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 123 on 2017/8/2.
 */
@Setter@Getter@ObjectProp("商品管理")
public class Product extends BaseDomain {
    @ObjectProp("货品名称")
    private String name;
    @ObjectProp("货品编码")
    private String sn;
    @ObjectProp("成本价格")
    private BigDecimal costPrice;
    @ObjectProp("销售价格")
    private BigDecimal salePrice;
    @ObjectProp("货品图片")
    private String imagePath;
    @ObjectProp("货品描述")
    private String intro;
    @ObjectProp("货品品牌")
    private Brand brand;

    public String getJsonString(){
        Map<String,Object> json = new HashMap<>();
        json.put("id",id);
        json.put("name",name);
        json.put("costPrice",costPrice);
        json.put("brandName",brand.getName());
        json.put("salePrice",salePrice);
        return JSON.toJSONString(json);
    }



    public String getSmallImagePath(){
        String fileName = imagePath.substring(0, imagePath.lastIndexOf(".")) + "_small";
        String ext = imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
        return fileName + ext;
    }

}
