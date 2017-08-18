package com._520it.wms.web.action;

import java.util.List;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.service.ISystemMenuService;
import lombok.Getter;
import lombok.Setter;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;

public class RoleAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Setter
    private ISystemMenuService systemMenuService;
    @Getter
    @Setter
    private Role role = new Role();
    @Getter
    private QueryObject qo = new QueryObject();

    @Override
    @RequiredPermission("角色列表")
    public String execute() throws Exception {
        PageResult result = roleService.pageQuery(qo);
        put("result", result);
        return LIST;
    }

    @RequiredPermission("删除角色")
    public String delete() throws Exception {
        try {
            roleService.delete(role.getId());
            putMsg("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败!");
        }
        return NONE;
    }

    @RequiredPermission("保存或更新角色")
    public String saveOrUpdate() throws Exception {
        try {
            if (role.getId() != null) {
                roleService.update(role);
                addActionMessage("更新成功!");
            } else {
                roleService.save(role);
                addActionMessage("添加成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("更新或添加失败!");
        }
        return SUCCESS;
    }

    @RequiredPermission("编辑角色")
    public String input() throws Exception {
        // 取出所有权限,显示在编辑页面
        List<Permission> permissions = permissionService.list();
        put("permissions", permissions);
        List<SystemMenu> menus = systemMenuService.list();
        put("menus",menus);
        if (role != null && role.getId() != null) {
            role = roleService.get(role.getId());
        }
        return INPUT;
    }

}
