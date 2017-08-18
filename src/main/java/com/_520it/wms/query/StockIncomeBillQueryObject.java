package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by 123 on 2017/8/5.
 */
@Getter
@Setter
public class StockIncomeBillQueryObject extends BaseAuditQueryObject {

    private Long depotId = -1L; // 仓库id

}
