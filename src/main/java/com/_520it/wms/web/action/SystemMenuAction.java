package com._520it.wms.web.action;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.vo.SystemMenuVO;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class SystemMenuAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private ISystemMenuService service;

	@Getter 
	private SystemMenuQueryObject qo=new SystemMenuQueryObject();

	@Getter
	private SystemMenu systemMenu=new SystemMenu();

	@RequiredPermission("系统菜单列表")
	public String execute(){
		try {
			//查询时,将当前的菜单的所有的父类菜单查询出来放到一个集合中
			SystemMenu parent = service.get(qo.getParentId());
			List<SystemMenuVO> parents = new ArrayList<>();
			while (parent != null) {
				SystemMenuVO vo = new SystemMenuVO();
				vo.setId(parent.getId());
				vo.setName(parent.getName());
				parents.add(vo);
				parent = parent.getParent();
			}
			Collections.reverse(parents);
			put("parents",parents);
			put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("系统菜单编辑")
	public String input() {
		try {
			SystemMenu parent = service.get(qo.getParentId());
			if (parent == null) {
				put("parentName","根目录");
			} else {
				put("parentName",parent.getName());
				put("parentId",parent.getId());
			}
			if (systemMenu.getId() != null) {
                systemMenu = service.get(systemMenu.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("系统菜单保存/更新")
	public String saveOrUpdate() {
		try {
			if (systemMenu.getId() == null) {
                service.save(systemMenu);
				addActionMessage("保存成功");
            } else {
                service.update(systemMenu);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("系统菜单删除")
	public String delete()  throws  Exception {
		try {
			if (systemMenu.getId() != null) {
                service.delete(systemMenu.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String getMenusByParentSn(){
		List<Map<String, Object>> list = service.getMenusByParentSn(qo.getParentSn());
		putAjax(list);
		return NONE;
	}

}
