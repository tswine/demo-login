package com.silly.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silly.common.base.BaseResponse;
import com.silly.common.enums.ResponseCodeEnum;
import com.silly.common.enums.ResponseStatusEnum;
import com.silly.common.exceptions.ResponseException;
import com.silly.common.provider.CacheProvider;
import com.silly.core.entity.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 权限拦截器
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 15:40
 */
public class AuthInterceptor implements HandlerInterceptor {

    CacheProvider cacheProvider;

    public AuthInterceptor( CacheProvider cacheProvider){
        this.cacheProvider=cacheProvider;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        User user = cacheProvider.get(token);
        if (user != null) {
            return true;
        }
        throw ResponseException.builder(ResponseCodeEnum.INVALID);
    }
}
