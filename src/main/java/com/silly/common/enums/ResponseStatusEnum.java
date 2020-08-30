package com.silly.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态枚举
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 11:40
 */
@AllArgsConstructor
@Getter
public enum ResponseStatusEnum {
    SUCCESS(200, "请求成功"),
    FAIL(400, "请求错误"),
    EXCEPTION(500, "未知异常");

    /**
     * 请求状态
     */
    private Integer status;
    private String msg;
}
