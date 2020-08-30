package com.silly.common.base;

import com.silly.common.enums.ResponseCodeEnum;
import com.silly.common.enums.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 11:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    /**
     * 请求状态
     *
     * @see ResponseCodeEnum
     */
    private Integer status;
    /**
     * 响应错误码
     *
     * @see ResponseCodeEnum
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据实体对象
     */
    private T data;

    public static BaseResponse success() {
        BaseResponse response = new BaseResponse();
        response.setStatus(ResponseStatusEnum.SUCCESS.getStatus());
        response.setMsg("操作成功");
        return response;
    }
}
