package com.silly.common.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 15:36
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Log {
    /**
     * 是否跳过记录记录
     * 下载文件需要将skip=true,跳过日志记录，不然要报错
     *
     * @return
     */
    boolean skip() default false;

}
