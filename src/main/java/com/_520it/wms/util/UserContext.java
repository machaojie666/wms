package com._520it.wms.util;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by 123 on 2017/7/29.
 */
public class UserContext {

    private UserContext() {
    }

    private static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    private static final String PERMISSIONS_IN_SESSION = "PERMISSIONS_IN_SESSION";

    // 保存用户的登录信息
    public static void setCurrentUser(Employee currentUser) {
        if (currentUser == null) {
            // 注销
            ActionContext.getContext().getSession().remove(EMPLOYEE_IN_SESSION);
        } else {
            // 登录
            ActionContext.getContext().getSession().put(EMPLOYEE_IN_SESSION, currentUser);
        }
    }

    // 保存权限表达式
    public static void setUserPermissions(Set<String> expressions) {
        if (expressions == null) {
            // 删除权限
            ActionContext.getContext().getSession().remove(PERMISSIONS_IN_SESSION);
        } else {
            ActionContext.getContext().getSession().put(PERMISSIONS_IN_SESSION, expressions);
        }
    }

    public static Employee getCurrentUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        return (Employee) session.getAttribute(EMPLOYEE_IN_SESSION);
    }

    public static Set<String> getUserPermissions() {
        return (Set<String>) ActionContext.getContext().getSession().get(PERMISSIONS_IN_SESSION);
    }

}
