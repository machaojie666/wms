package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

public class EmployeeAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IEmployeeService empService;
    @Setter
    private IDepartmentService deptService;
    @Setter
    private IRoleService roleService;
    @Getter
    private Employee emp = new Employee();
    @Getter
    @Setter
    private QueryObject qo = new EmployeeQueryObject();
    @Getter
    @Setter
    private Long[] ids;

    @Override
    @RequiredPermission("员工列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            List<Department> depts = deptService.list();
            put("depts", depts);
            PageResult result = empService.pageQuery(qo);
            put("result", result);

            //System.out.println(1 / 0);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("哇,报错啦~");
        }
        return LIST;
    }

    @RequiredPermission("删除员工")
    public String delete() throws Exception {
        try {
            empService.delete(emp.getId());
            putMsg("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败!");
        }
        return NONE;
    }

    @RequiredPermission("更新或保存员工")
    public String saveOrUpdate() throws Exception {
        try {
            if (emp != null && emp.getId() != null) {
                empService.update(emp);
                addActionMessage("更新成功");
            } else {
                empService.save(emp);
                addActionMessage("添加成功");
            }
            //System.out.println(1 / 0);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("更新或添加失败~");
        }
        return SUCCESS;
    }

    @RequiredPermission("编辑员工")
    public String input() throws Exception {
        List<Role> roles = roleService.list();
        put("roles", roles);
        List<Department> depts = deptService.list();
        put("depts", depts);
        if (emp != null && emp.getId() != null) {
            emp = empService.get(emp.getId());
        }
        return INPUT;
    }

    @RequiredPermission("批量删除员工")
    public String batchDelete() throws Exception {
        try {
            empService.batchDelete(ids);
            putMsg("批量删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("批量删除失败");
        }
        return NONE;
    }

}
