package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.StockOutcomeBillItemMapper;
import com._520it.wms.page.PageResult;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.mapper.StockOutcomeBillMapper;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Setter
    private StockOutcomeBillMapper mapper;
    @Setter
    private StockOutcomeBillItemMapper itemMapper;
    @Setter
    private IProductStockService productStockService;

    public void delete(Long id) {
        // 先删除bill(出库单对象)的id对应的billItem(出库单明细)
        itemMapper.deleteByBillId(id);
        mapper.delete(id);
    }

    public void save(StockOutcomeBill bill) {
        // 设置出库单制单人和出库单制单时间
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
        // 重新设置单据的审核状态为待审核(防止通过地址栏传递status数据)
        bill.setStatus(StockOutcomeBill.STATUS_NORMAL);
        // 初始化totalNumber和totalAmount,迭代totalNumber和totalAmount
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (StockOutcomeBillItem item : bill.getItems()) {
            // 计算每条明细的小计
            BigDecimal amount = item.getSalePrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
            item.setAmount(amount);
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(item.getAmount());
        }
        bill.setTotalNumber(totalNumber);
        bill.setTotalAmount(totalAmount);
        // 保存出库单对象
        mapper.save(bill);
        for (StockOutcomeBillItem item : bill.getItems()) {
            // 给每个明细对象设置其对应的billId
            item.setBillId(bill.getId());
            // 保存每个明细对象
            System.out.println(item);
            System.out.println(itemMapper);
            itemMapper.save(item);
        }
    }

    public StockOutcomeBill get(Long id) {
        return mapper.get(id);
    }

    public void update(StockOutcomeBill bill) {
        // 判断出库单的状态是否是已经审核的,已经审核的不能修改
        StockOutcomeBill stockOutcomeBill = mapper.get(bill.getId());
        if (stockOutcomeBill.getStatus() == StockOutcomeBill.STATUS_NORMAL) {
            // 先删除该出库单所有的明细
            itemMapper.deleteByBillId(bill.getId());
            // 再把修改后的数据添加到出库单中
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (StockOutcomeBillItem item : bill.getItems()) {
                // 计算每个明细的小计
                BigDecimal amount = item.getSalePrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
                item.setAmount(amount);
                totalNumber = totalNumber.add(item.getNumber());
                totalAmount = totalAmount.add(item.getAmount());
                // 把每个明细的billId重新赋值
                item.setBillId(bill.getId());
                // 保存每个明细对象
                itemMapper.save(item);

            }
            bill.setTotalNumber(totalNumber);
            bill.setTotalAmount(totalAmount);
            mapper.update(bill);
        }

    }

    @Override
    public PageResult pageQuery(StockOutcomeBillQueryObject qo) {
        Long count = mapper.getTotalCount(qo);
        if (count <= 0) {
            return PageResult.emptyResult;
        }
        List<StockOutcomeBill> listData = mapper.getListData(qo);
        PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void audit(Long billId) {
        // 判断是否是未审核状态
        StockOutcomeBill stockOutcomeBill = mapper.get(billId);
        if (stockOutcomeBill.getStatus() == StockOutcomeBill.STATUS_NORMAL) {
            // 设置审核人,审核时间并设置为已审核
            stockOutcomeBill.setAuditor(UserContext.getCurrentUser());
            stockOutcomeBill.setAuditTime(new Date());
            stockOutcomeBill.setStatus(StockOutcomeBill.STATUS_AUDIT);
            // 更改状态
            mapper.updateStatus(stockOutcomeBill);
            // 销售出库,改变库存量
            productStockService.stockOutcome(stockOutcomeBill);
        }

    }
}
