package com.liangcha.common.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author 凉茶
 */
public class TimeUtil {
    /**
     * 根据时间模板得到字符串
     *
     * @param template 模板
     */
    public static String getTimeForTemplate(String template) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);
        return simpleDateFormat.format(new Date());
    }

}
