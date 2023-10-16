package com.liangcha.common.utils;

/**
 * 字符串操作类
 *
 * @author 凉茶
 */
public class StringUtil {
    /**
     * @param template 消息模板
     * @param params   参数数组
     * @return 格式化后的消息
     */
    public static String format(String template, Object... params) {
        for (Object param : params) {
            template = template.replaceFirst("\\{}", param.toString());
        }
        return template;
    }
}
