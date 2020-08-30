package com.silly.common.utils;

import java.util.UUID;

/**
 * @Author: wei.wang7
 * @Date: 2020/8/30 12:22
 */
public class StringUtils {

    private StringUtils() {
        throw new RuntimeException("不能被实例化");
    }


    /**
     * 生成token
     *
     * @return
     */
    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        if (cs == null) {
            return true;
        }
        int l = cs.length();
        if (l > 0) {
            for (int i = 0; i < l; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
