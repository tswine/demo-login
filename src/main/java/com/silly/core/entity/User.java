package com.silly.core.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户信息
 *
 * @Author silly
 * @Date 2020-8-30 12:54:57
 */
@Data
public class User implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 数据库静态常量字段列
     */
    public static final String FIELD_ID = "id";
    public static final String FIELD_USER_NAME = "userName";
    public static final String FIELD_PASS_WORD = "passWord";

    /**
     * 
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;

}