package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by 123 on 2017/7/31.
 */
@Getter@Setter@ObjectProp("系统菜单")
public class SystemMenu extends BaseDomain {
    @ObjectProp("菜单名称")
    private String name;
    @ObjectProp("菜单编码")
    private String sn;
    @ObjectProp("URL")
    private String url;
    @ObjectProp("上级菜单")
    private SystemMenu parent;
    private List<SystemMenu> children;

    public String getParentName(){
        if(getParent()==null){
            return "根目录";
        } else {
            return parent.getName();
        }
    }

}