package com.junie.debuglog.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by niejun on 2017/3/1.
 */

public class DateUtils {

    /**
     * 获取现在时间
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * @return String
     * @Description : 获取年月日
     * 示例2016-06-03
     */
    public static String getDay() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date(System.currentTimeMillis()));
    }
}
