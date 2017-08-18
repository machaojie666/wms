package com._520it.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    public static final String LIST = "list";

    protected void put(String name, Object value) {
        ActionContext.getContext().put(name, value);
    }

    protected void putMsg(String msg) {
        try {
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void putAjax(Object obj) {
        try {
            ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(JSON.toJSON(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
