package com._520it.wms.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;

public class DepartmentAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IDepartmentService deptService;
    @Getter
    private Department dept = new Department();
    @Getter
    private QueryObject qo = new QueryObject();

    @Override
    @RequiredPermission("部门列表")
    public String execute() throws Exception {
        PageResult result = deptService.pageQuery(qo);
        put("result", result);
        return LIST;
    }

    @RequiredPermission("删除部门")
    public String delete() throws Exception {
        try {
            deptService.delete(dept.getId());
            putMsg("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败!");
        }
        return NONE;
    }

    @RequiredPermission("保存或更新部门")
    public String saveOrUpdate() throws Exception {
        try {
            if (dept != null && dept.getId() != null) {
                deptService.update(dept);
                addActionMessage("更新成功!");
            } else {
                deptService.save(dept);
                addActionMessage("添加成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionMessage("更新或添加失败!");
        }
        return SUCCESS;
    }

    @RequiredPermission("编辑部门")
    public String input() throws Exception {
        if (dept != null && dept.getId() != null) {
            dept = deptService.get(dept.getId());
        }
        return INPUT;
    }

}
