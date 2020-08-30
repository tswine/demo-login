package com.silly.core.service;

import com.silly.core.entity.User;

import java.util.List;

/**
 * @Author: wei.wang7
 * @Date: 2020/8/30 11:53
 */
public interface UserService {
    /***
     * 登录
     * @param user
     */
    String login(User user);

    /**
     * 获取所有
     *
     * @return
     */
    List<User> getAll();

    /**
     * 添加用户
     *
     * @param user
     */
    void add(User user);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    User get(Integer id);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     *  编辑
     * @param user
     */
    void edit(User user);
}
