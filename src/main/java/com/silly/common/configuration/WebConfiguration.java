package com.silly.common.configuration;

import com.silly.common.interceptor.AuthInterceptor;
import com.silly.common.interceptor.CorsInterceptor;
import com.silly.common.provider.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 15:42
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    CacheProvider cacheProvider;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor( new AuthInterceptor(cacheProvider))
                .addPathPatterns("/**")
                .excludePathPatterns("/logon/login");
    }
}
