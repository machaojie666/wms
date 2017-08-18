package com._520it.wms.web.action;

import com._520it.wms.domain.Client;
import com._520it.wms.query.ClientQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ClientAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private IClientService service;

	@Getter 
	private ClientQueryObject qo=new ClientQueryObject();

	@Getter
	private Client client=new Client();

	@RequiredPermission("客户管理列表")
	public String execute(){
		try {
			put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("客户管理编辑")
	public String input() {
		try {
			if (client.getId() != null) {
                client = service.get(client.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("客户管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (client.getId() == null) {
                service.save(client);
				addActionMessage("保存成功");
            } else {
                service.update(client);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("客户管理删除")
	public String delete()  throws  Exception {
		try {
			if (client.getId() != null) {
                service.delete(client.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

}
