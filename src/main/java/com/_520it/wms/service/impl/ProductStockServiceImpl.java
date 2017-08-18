package com._520it.wms.service.impl;

import com._520it.wms.domain.*;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.SaleAccountMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IProductStockService;
import lombok.Setter;

import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2017/8/7.
 */
public class ProductStockServiceImpl implements IProductStockService {
    @Setter
    private ProductStockMapper productStockMapper;
    @Setter
    private SaleAccountMapper saleAccountMapper;

    @Override
    public PageResult query(ProductStockQueryObject qo) {
        int totalCount = productStockMapper.getTotalCount(qo);
        if (totalCount == 0) {
            return PageResult.emptyResult;
        }
        List<?> pageResult = productStockMapper.getListData(qo);
        return new PageResult(pageResult,totalCount,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void stockIncome(StockIncomeBill stockIncomeBill) {
        // 到货入库单中货品对应的仓库
        Long depotId = stockIncomeBill.getDepot().getId();
        // 修改到货入库单
        for (StockIncomeBillItem item : stockIncomeBill.getItems()) {
            // 根据货品id和仓库id判断仓库中是否存在对应货品
            Long productId = item.getProduct().getId();
            ProductStock ps = productStockMapper.selectByDepotIdAndProductId(depotId, productId);
            if (ps == null) {
                // 如果不存在,添加一条库存信息
                Product p = new Product();
                p.setId(productId);
                Depot d = new Depot();
                d.setId(depotId);
                ps = new ProductStock();
                ps.setProduct(p);
                ps.setDepot(d);

                ps.setPrice(item.getCostPrice());
                ps.setStoreNumber(item.getNumber());
                ps.setAmount(item.getAmount());
                productStockMapper.save(ps);

            } else {
                // 如果存在,修改库存信息(存储总额,库存总量,库存价格:)
                // 修改仓库总额
                ps.setAmount(ps.getAmount().add(item.getAmount()));
                // 修改仓库总量
                ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
                // 库存价格(修改后的仓库总额/修改后的仓库总量)
                ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2, RoundingMode.HALF_UP));
                productStockMapper.update(ps);
            }

        }


    }

    @Override
    public void stockOutcome(StockOutcomeBill stockOutcomeBill) {
        // 出库单对应的仓库id
        Long depotId = stockOutcomeBill.getDepot().getId();
        // 修改出库单
        for (StockOutcomeBillItem item : stockOutcomeBill.getItems()) {
            Long productId = item.getProduct().getId();
            // 根据商品对应的仓库和商品id查找仓库中是否存在对应商品
            ProductStock productStock = productStockMapper.selectByDepotIdAndProductId(depotId, productId);
            // 如果不存在某种商品,抛出一个RuntimeException给调用者,spring中,出现RuntimeException会回滚事务
            if (productStock == null) {
                throw new RuntimeException(stockOutcomeBill.getDepot().getName() + ":仓库中不存在商品:" + item.getProduct().getName());
            }
            // 如果某个商品库存不足,也抛出个异常给调用者
            if (productStock.getStoreNumber().compareTo(item.getNumber()) < 0) {
                throw new RuntimeException(stockOutcomeBill.getDepot().getName() + ":仓库中,商品:" + item.getProduct().getName() + "库存不足" + item.getNumber());
            }
            // 减少库存
            productStock.setStoreNumber(productStock.getStoreNumber().subtract(item.getNumber()));
            productStock.setAmount(productStock.getStoreNumber().multiply(productStock.getPrice()));

            productStockMapper.update(productStock);

            // 销售出库完毕,记账
            SaleAccount sa = new SaleAccount();
            sa.setVdate(new Date());
            sa.setNumber(item.getNumber());
            sa.setCostPrice(productStock.getPrice());
            sa.setCostAmount(sa.getNumber().multiply(sa.getCostPrice()).setScale(2,RoundingMode.HALF_UP));
            sa.setSalePrice(item.getSalePrice());
            sa.setSaleAmount(sa.getNumber().multiply(sa.getSalePrice()).setScale(2,RoundingMode.HALF_UP));
            sa.setProduct(item.getProduct());
            sa.setClient(stockOutcomeBill.getClient());
            sa.setSaleman(stockOutcomeBill.getInputUser());

            saleAccountMapper.save(sa);

        }
    }
}
