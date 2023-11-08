package com.liangcha.common.utils;

/**
 * 字符串工具类
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

    /**
     * @param connection 连接字符
     * @param str        参数数组
     * @return 使用connection连接后的字符串
     */
    public static String join(String connection, String... str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
            if (i != str.length - 1) {
                sb.append(connection);
            }
        }
        return sb.toString();
    }
}
