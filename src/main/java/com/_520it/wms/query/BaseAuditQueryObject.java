package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by 123 on 2017/8/7.
 */
@Getter@Setter
public class BaseAuditQueryObject extends QueryObject {
    private int status = -1; // 所有状态
    private Date beginDate; // 开始业务时间
    private Date endDate; // 结束业务时间

    public Date getEndDate() {
        return endDate != null ? DateUtil.getEndDate(endDate) : null;
    }
}
