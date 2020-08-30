package com.silly.common.exceptions;

import com.silly.common.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 自定义异常类
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 12:08
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseException extends RuntimeException {
    private ResponseCodeEnum code;

    public static ResponseException builder(ResponseCodeEnum coe) {
        ResponseException ex = new ResponseException();
        ex.setCode(coe);
        return ex;
    }

}
