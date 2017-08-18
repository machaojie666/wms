package com._520it.wms.mapper;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 123 on 2017/8/6.
 */
public interface ProductStockMapper {

    void save(ProductStock ps);

    void update(ProductStock ps);


    ProductStock selectByDepotIdAndProductId(@Param("depotId") Long depotId, @Param("productId") Long productId);

    int getTotalCount(ProductStockQueryObject qo);

    List<?> getListData(ProductStockQueryObject qo);
}
