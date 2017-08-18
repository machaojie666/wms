package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by 123 on 2017/8/7.
 */
@Setter@Getter
public class BaseAuditDomain extends BaseDomain {
    public static final int STATUS_NORMAL = 0;//  待审核
    public static final int STATUS_AUDIT= 1; // 已审核

    @ObjectProp("审核状态")
    private int status = STATUS_NORMAL;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("审核人")
    private Employee auditor;
    @ObjectProp("录入时间")
    private Date inputTime;
    @ObjectProp("制单人")
    private Employee inputUser;

}
