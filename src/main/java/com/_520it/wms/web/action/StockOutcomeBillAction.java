package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class StockOutcomeBillAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private IStockOutcomeBillService service;
	@Setter
	private IDepotService depotService;
	@Setter
	private IClientService clientService;
	@Getter
	private StockOutcomeBillQueryObject qo=new StockOutcomeBillQueryObject();

	@Getter private StockOutcomeBill stockOutcomeBill=new StockOutcomeBill();

	@RequiredPermission("采购出库单列表")
	public String execute(){
		// 查询出所有的仓库
		put("depots",depotService.list());
		// 查询出所有的客户
		put("clients",clientService.list());
		try {
			put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}
	@RequiredPermission("出库单查看")
	public String show() {
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBill = service.get(stockOutcomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return "show";
	}

	@RequiredPermission("采购出库单编辑")
	public String input() {
		// 查询出所有的仓库
		put("depots",depotService.list());
		// 查询出所有的客户
		put("clients", clientService.list());
		try {
			if (stockOutcomeBill.getId() != null) {
                stockOutcomeBill = service.get(stockOutcomeBill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("采购出库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockOutcomeBill.getId() == null) {
                service.save(stockOutcomeBill);
				addActionMessage("保存成功");
            } else {
                service.update(stockOutcomeBill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("采购出库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
                service.delete(stockOutcomeBill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}
	@RequiredPermission("出库订单审核")
	public String audit() {
		try {
			if (stockOutcomeBill.getId() != null) {
				service.audit(stockOutcomeBill.getId());
				putMsg("审核成功");
			}
		} catch (Exception e) {
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
