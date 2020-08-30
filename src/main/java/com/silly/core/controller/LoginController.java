package com.silly.core.controller;

import com.silly.common.annotation.Log;
import com.silly.common.base.BaseResponse;
import com.silly.common.enums.ResponseCodeEnum;
import com.silly.common.enums.ResponseStatusEnum;
import com.silly.common.exceptions.ResponseException;
import com.silly.common.utils.StringUtils;
import com.silly.core.entity.User;
import com.silly.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录控制器
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 11:36
 */
@Log
@RestController
@RequestMapping(value = "/logon")
public class LoginController {

    @Autowired
    UserService userService;


    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login")
    public BaseResponse<String> login(User user) {
        checkParam(user);
        String token = userService.login(user);
        return BaseResponse.<String>builder()
                .status(ResponseStatusEnum.SUCCESS.getStatus())
                .msg("请求成功")
                .data(token)
                .build();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public BaseResponse list() {
        List<User> data = userService.getAll();
        return BaseResponse.<List<User>>builder()
                .status(ResponseStatusEnum.SUCCESS.getStatus())
                .msg("请求成功")
                .data(data)
                .build();
    }


    /**
     * 获取明细
     *
     * @return
     */
    @RequestMapping(value = "/get/{id}")
    public BaseResponse<User> get(@PathVariable Integer id) {
        User user = userService.get(id);
        return BaseResponse.<User>builder()
                .status(ResponseStatusEnum.SUCCESS.getStatus())
                .msg("请求成功")
                .data(user)
                .build();
    }

    /**
     * 增加
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add")
    public BaseResponse add(User user) {
        checkParam(user);
        userService.add(user);
        return BaseResponse.success();
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public BaseResponse delete(Integer id) {
        userService.delete(id);
        return BaseResponse.success();
    }

    /**
     * 编辑
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit")
    public BaseResponse edit(User user) {
        checkParam(user);
        userService.edit(user);
        return BaseResponse.success();
    }

    /**
     * 校验参数
     *
     * @param user
     */
    private void checkParam(User user) {
        if (user == null) {
            throw ResponseException.builder(ResponseCodeEnum.USERNAME_EMPTY);
        }
        if (StringUtils.isBlank(user.getUserName())) {
            throw ResponseException.builder(ResponseCodeEnum.USERNAME_EMPTY);
        }
        if (StringUtils.isBlank(user.getPassWord())) {
            throw ResponseException.builder(ResponseCodeEnum.PASSWORD_EMPTY);
        }
    }

}
