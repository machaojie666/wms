package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class OrderBillQueryObject extends BaseAuditQueryObject {
    private Long supplierId = -1L; // 供应商id

}
