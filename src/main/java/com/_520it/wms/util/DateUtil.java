package com._520it.wms.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 123 on 2017/8/5.
 */
public class DateUtil {

    // 当前天的最后一秒
    public static Date getEndDate(Date current){
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }

}
