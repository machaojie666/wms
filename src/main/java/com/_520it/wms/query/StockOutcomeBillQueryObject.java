package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter
public class StockOutcomeBillQueryObject extends BaseAuditQueryObject {
    private Long depotId = -1L; // 仓库id

    private Long clientId = -1L; // 客户id



}
