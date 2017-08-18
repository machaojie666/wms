package com._520it.wms.mapper;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {
    void save(SystemMenu entity);

    void update(SystemMenu entity);

    void delete(Long id);

    SystemMenu get(Long id);

    List<SystemMenu> list();

    Long getTotalCount(SystemMenuQueryObject qo);

    List<SystemMenu> getListData(SystemMenuQueryObject qo);

    List<Map<String, Object>> getMenusByParentSn(String parentSn);

    List<Map<String, Object>> queryMenusByParentSnAndEmployeeId(@Param("paramSn") String parentSn, @Param("employeeId") Long employeeId);

}