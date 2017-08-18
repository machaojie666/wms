package com._520it.wms.web.action;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.service.ISupplierService;
import lombok.Getter;
import lombok.Setter;

public class StockIncomeBillAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private IStockIncomeBillService stockIncomeBillService;
	@Setter
	private IDepotService depotService;
	@Getter 
	private StockIncomeBillQueryObject qo=new StockIncomeBillQueryObject();
	@Getter
	private StockIncomeBill stockIncomeBill=new StockIncomeBill();

	@RequiredPermission("入库单列表")
	public String execute(){
		// 查询出所有的仓库
		put("depots",depotService.list());
		try {
			put("result", stockIncomeBillService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("入库单编辑")
	public String input() {
		// 查询出所有的仓库
		put("depots",depotService.list());
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}
	@RequiredPermission("入库单查看")
	public String show() {
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return "show";
	}

	@RequiredPermission("入库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockIncomeBill.getId() == null) {
				stockIncomeBillService.save(stockIncomeBill);
				addActionMessage("保存成功");
            } else {
				stockIncomeBillService.update(stockIncomeBill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("入库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBillService.delete(stockIncomeBill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}
	@RequiredPermission("入库订单审核")
	public String audit()  throws  Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBillService.audit(stockIncomeBill.getId());
				putMsg("审核成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("审核失败");
		}
		return NONE;
	}

}
