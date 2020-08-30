package com.silly.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @Author: wei.wang7
 * @Date: 2020/8/30 12:27
 */
public class Md5Utils {

    /**
     * 加密
     *
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String str) {
        //直接调用org.springframework.util.DigestUtils 生成加密字符串
        return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
    }

}
