package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 123 on 2017/8/8.
 */
@Setter@Getter
public class SaleChartQueryObject extends QueryObject {

    private String keywords;
    private Long brandId = -1L; // 品牌id
    private Long clientId = -1L; // 客户id
    private Date beginDate; // 开始业务时间
    private Date endDate; // 结束业务时间
    private String groupByType = "b.name";
    public static final Map<String,String> GROUP_BY_TYPES = new LinkedHashMap<>();

    static {
        GROUP_BY_TYPES.put("sm.name","销售人员");
        GROUP_BY_TYPES.put("p.name","货品名称");
        GROUP_BY_TYPES.put("c.name","客户");
        GROUP_BY_TYPES.put("b.name","货品品牌");
        GROUP_BY_TYPES.put("date_format(sa.vdate,'%Y-%m')","销售日期(月)");
        GROUP_BY_TYPES.put("date_format(sa.vdate,'%Y-%m-%d')","销售日期(日)");
    }

    public String getKeywords(){
        return empty2null(keywords);
    }

    public Date getEndDate() {
        return endDate != null ? DateUtil.getEndDate(endDate) : null;
    }

}
