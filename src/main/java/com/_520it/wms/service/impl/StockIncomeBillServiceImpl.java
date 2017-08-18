package com._520it.wms.service.impl;

import com._520it.wms.domain.*;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.StockIncomeBillItemMapper;
import com._520it.wms.mapper.StockIncomeBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Setter
    private StockIncomeBillMapper mapper;
    @Setter
    private StockIncomeBillItemMapper itemMapper;
    @Setter
    private IProductStockService productStockService;


    public void delete(Long id) {
        // 先删除bill(入库单单对象)的id对应的billItem(入库单明细)
        itemMapper.deleteByBillId(id);

        mapper.delete(id);
    }

    public void save(StockIncomeBill bill) {
        // 设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
        // 重新设置单据的审核状态为:待审核
        bill.setStatus(StockIncomeBill.STATUS_NORMAL);
        // 迭代出单据中的每个明细对象,叠加totalAmount和totalNumber
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (StockIncomeBillItem item : bill.getItems()) {
            // 计算每个明细的小计
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
            item.setAmount(amount);
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(amount);
        }
        // 设置单据的中数量和总额
        bill.setTotalNumber(totalNumber);
        bill.setTotalAmount(totalAmount);
        // 保存入库单对象,先保存入库单对象,获取到id才能保存入库单明细
        mapper.save(bill);
        // 当入库单对象保存之后,此时bill.getId才有值,才能设置给item对象的bill_id
        for (StockIncomeBillItem item : bill.getItems()) {
            item.setBillId(bill.getId());
            // 保存每个明细对象
            itemMapper.save(item);
        }
    }

    public StockIncomeBill get(Long id) {
        return mapper.get(id);
    }

    public void update(StockIncomeBill bill) {
        // 先判断入库单的状态是否是未审核的,已审核的不能修改
        StockIncomeBill incomeBill = mapper.get(bill.getId());
        if (incomeBill.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            // 先删除该订单所有的订单明细
            itemMapper.deleteByBillId(incomeBill.getId());
            // 迭代出单据中的每个明细对象,叠加totalAmount和totalNumber
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            // 计算每个明细的小计
            for (StockIncomeBillItem item : bill.getItems()) {
                BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP);
                // 把计算的结果设置给item对象
                item.setAmount(amount);
                totalNumber = totalNumber.add(item.getNumber());
                totalAmount = totalAmount.add(amount);
                // 把每个明细的id重新赋值
                item.setBillId(bill.getId());
                // 保存每个明细对象
                itemMapper.save(item);

            }
            // 设置单据的中数量和总额
            bill.setTotalNumber(totalNumber);
            bill.setTotalAmount(totalAmount);
            // 修改单据
            mapper.update(bill);
        }
    }


    @Override
    public PageResult pageQuery(StockIncomeBillQueryObject qo) {
        Long count = mapper.getTotalCount(qo);
        if (count <= 0) {
            return PageResult.emptyResult;
        }
        List<StockIncomeBill> listData = mapper.getListData(qo);
        PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void audit(Long billId) {
        // 判断是否处于待审核中
        StockIncomeBill bill = mapper.get(billId);
        if (bill.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            // 设置审核人,审核时间,并改为已审核
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setAuditTime(new Date());
            bill.setStatus(StockIncomeBill.STATUS_AUDIT);
            mapper.updateStatus(bill);

            productStockService.stockIncome(bill);

        }

    }
}
