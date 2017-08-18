package com._520it.wms.web.action;

import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductService;
import com._520it.wms.service.IProductStockService;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 123 on 2017/8/7.
 */
// 及时库存报表
public class ProductStockAction extends BaseAction {

    @Setter
    private IProductStockService productStockService;
    @Setter
    private IDepotService depotService;
    @Setter
    private IBrandService brandService;
    @Getter
    private ProductStockQueryObject qo = new ProductStockQueryObject();

    @Override
    public String execute() throws Exception {
        put("depots",depotService.list());
        put("brands",brandService.list());
        put("result",productStockService.query(qo));

        return LIST;
    }
}
