package com._520it.wms.service.impl;

import java.util.List;
import java.util.Map;

import com._520it.wms.page.PageResult;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.SystemMenuMapper;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

public class SystemMenuServiceImpl implements ISystemMenuService {
    @Setter
    private SystemMenuMapper mapper;

    public void delete(Long id) {
        mapper.delete(id);
    }

    public void save(SystemMenu entity) {
        mapper.save(entity);
    }

    public SystemMenu get(Long id) {
        return mapper.get(id);
    }

    public List<SystemMenu> list() {
        return mapper.list();
    }

    public void update(SystemMenu entity) {
        mapper.update(entity);
    }

    @Override
    public PageResult pageQuery(SystemMenuQueryObject qo) {
        Long count = mapper.getTotalCount(qo);
        if (count <= 0) {
            return PageResult.emptyResult;
        }
        List<SystemMenu> listData = mapper.getListData(qo);
        PageResult pageResult = new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public List<Map<String, Object>> getMenusByParentSn(String parentSn) {
        if (UserContext.getCurrentUser().getAdmin()) {
            return mapper.getMenusByParentSn(parentSn);
        } else {
            return mapper.queryMenusByParentSnAndEmployeeId(parentSn,UserContext.getCurrentUser().getId());
        }
    }
}
