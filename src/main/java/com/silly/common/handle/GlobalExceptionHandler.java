package com.silly.common.handle;

import com.silly.common.base.BaseResponse;
import com.silly.common.enums.ResponseCodeEnum;
import com.silly.common.enums.ResponseStatusEnum;
import com.silly.common.exceptions.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 13:14
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResponseException.class)
    public BaseResponse response(ResponseException ex) {
        return BaseResponse.builder()
                .status(ResponseStatusEnum.FAIL.getStatus())
                .code(ex.getCode().getCode())
                .msg(ex.getCode().getMsg())
                .build();
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse ex(Exception ex) {
        log.error("未知异常", ex);
        return BaseResponse.builder()
                .status(ResponseStatusEnum.EXCEPTION.getStatus())
                .code(ResponseCodeEnum.UNKNOWN.getCode())
                .msg(ResponseCodeEnum.UNKNOWN.getMsg())
                .build();
    }
}
