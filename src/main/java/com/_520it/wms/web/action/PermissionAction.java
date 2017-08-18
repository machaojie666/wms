package com._520it.wms.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;

public class PermissionAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IPermissionService permissionService;
    @Setter
    @Getter
    private Permission permission;

    @Getter
    private QueryObject qo = new QueryObject();

    @Override
    @RequiredPermission("权限列表")
    public String execute() throws Exception {
        PageResult result = permissionService.pageQuery(qo);
        put("result", result);
        return LIST;
    }

    @RequiredPermission("删除权限")
    public String delete() throws Exception {
        try {
            permissionService.delete(permission.getId());
            putMsg("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败!");
        }
        return NONE;
    }

    @RequiredPermission("加载权限")
    public String reload() throws Exception {
        try {
            permissionService.reload();
            putMsg("加载成功!");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("加载失败!");
        }
        return NONE;
    }

}
