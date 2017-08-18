package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by 123 on 2017/8/8.
 */
@Setter@Getter
public class OrderBillChartQueryObject extends QueryObject {

    private String keywords;
    private Long brandId = -1L; // 品牌id
    private Long supplierId = -1L; // 供应商id
    private Date beginDate; // 开始业务时间
    private Date endDate; // 结束业务时间
    private String groupByType = "iu.name";
    public static final Map<String,String> GROUP_BY_TYPES = new LinkedHashMap<>();

    static {
        GROUP_BY_TYPES.put("iu.name","订货人员");
        GROUP_BY_TYPES.put("p.name","货品名称");
        GROUP_BY_TYPES.put("s.name","供应商");
        GROUP_BY_TYPES.put("b.name","货品品牌");
        GROUP_BY_TYPES.put("date_format(bill.vdate,'%Y-%m')","订货日期(月)");
        GROUP_BY_TYPES.put("date_format(bill.vdate,'%Y-%m-%d')","订货日期(日)");
    }

    public String getKeywords(){
        return empty2null(keywords);
    }

    public Date getEndDate() {
        return endDate != null ? DateUtil.getEndDate(endDate) : null;
    }

}
