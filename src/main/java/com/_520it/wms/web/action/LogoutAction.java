package com._520it.wms.web.action;

import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by 123 on 2017/7/30.
 */
public class LogoutAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // 退出当前用户,从session中删除此用户信息
        UserContext.setCurrentUser(null);
        // 删除权限
        UserContext.setUserPermissions(null);
        return LOGIN;
    }
}
