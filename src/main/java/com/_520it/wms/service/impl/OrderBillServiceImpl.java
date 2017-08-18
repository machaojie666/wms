package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.mapper.OrderBillItemMapper;
import com._520it.wms.page.PageResult;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.mapper.OrderBillMapper;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;
import org.springframework.core.annotation.Order;

public class OrderBillServiceImpl implements IOrderBillService {
    @Setter
    private OrderBillMapper mapper;
    @Setter
    private OrderBillItemMapper itemMapper;

    public void delete(Long id) {
        // 先删除bill(订单对象)的id对应的billItem(订单明细)
        itemMapper.deleteByBillId(id);

        mapper.delete(id);
    }

    public void save(OrderBill bill) {
        // 设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
        // 重新设置单据的审核状态为:待审核
        bill.setStatus(OrderBill.STATUS_NORMAL);
        // 迭代出单据中的每个明细对象,叠加totalAmount和totalNumber
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderBillItem item : bill.getItems()) {
            // 计算每个明细的小计
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber().setScale(2, RoundingMode.HALF_UP));
            item.setAmount(amount);
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(amount);
        }
        // 设置单据的中数量和总额
        bill.setTotalNumber(totalNumber);
        bill.setTotalAmount(totalAmount);
        // 保存订单对象,先保存订单对象,获取到id才能保存订单明细
        mapper.save(bill);
        // 当订单对象保存之后,此时bill.getId才有值,才能设置给item对象的bill_id
        for (OrderBillItem item : bill.getItems()) {
            item.setBillId(bill.getId());
            // 保存每个明细对象
            itemMapper.save(item);
        }
    }

    public OrderBill get(Long id) {
        return mapper.get(id);
    }

    public void update(OrderBill bill) {
        // 先判断订单的状态是否是未审核的,以审核的不能修改
        if (bill.getStatus() == OrderBill.STATUS_NORMAL) {
            // 先删除该订单所有的订单明细
            itemMapper.deleteByBillId(bill.getId());
            // 迭代出单据中的每个明细对象,叠加totalAmount和totalNumber
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (OrderBillItem item : bill.getItems()) {
                // 计算每个明细的小计
                BigDecimal amount = item.getCostPrice().multiply(item.getNumber());
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
    public PageResult pageQuery(OrderBillQueryObject qo) {
        Long count = mapper.getTotalCount(qo);
        if (count <= 0) {
            return PageResult.emptyResult;
        }
        List<OrderBill> listData = mapper.getListData(qo);
        PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void audit(Long billId) {
        OrderBill bill = mapper.get(billId);
        // 判断是否处于待审核中
        if (bill.getStatus() == OrderBill.STATUS_NORMAL) {
            // 设置审核人,审核时间,并改为已审核
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setAuditTime(new Date());
            bill.setStatus(OrderBill.STATUS_AUDIT);

        }
        mapper.updateStatus(bill);

    }
}
