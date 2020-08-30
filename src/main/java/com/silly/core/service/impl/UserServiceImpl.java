package com.silly.core.service.impl;

import com.silly.common.enums.ResponseCodeEnum;
import com.silly.common.exceptions.ResponseException;
import com.silly.common.provider.CacheProvider;
import com.silly.common.utils.Md5Utils;
import com.silly.common.utils.StringUtils;
import com.silly.core.entity.User;
import com.silly.core.mapper.UserMapper;
import com.silly.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wei.wang7
 * @Date: 2020/8/30 11:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CacheProvider cacheProvider;

    @Override
    public String login(User user) {
        //设置密码加密
        String passWord = Md5Utils.encrypt(user.getPassWord());
        User newUser = userMapper.getByUserName(user.getUserName());
        if (newUser == null) {
            throw ResponseException.builder(ResponseCodeEnum.USERNAME_NOT_EXIST);
        }
        if (!passWord.equalsIgnoreCase(newUser.getPassWord())) {
            throw ResponseException.builder(ResponseCodeEnum.USERNAME_PASSWORD_ERROR);
        }
        //生成token，返回给客户端
        String token = StringUtils.getToken();
        //将token存入缓存，此处只是存在内存中，重启后失败
        cacheProvider.set(token, newUser);
        return token;
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public void add(User user) {
        User dbUser = userMapper.getByUserName(user.getUserName());
        if (dbUser != null) {
            throw ResponseException.builder(ResponseCodeEnum.USERNAME_EXIST);
        }
        user.setPassWord(Md5Utils.encrypt(user.getPassWord()));
        userMapper.insert(user);
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw ResponseException.builder(ResponseCodeEnum.USERNAME_NOT_EXIST);
        }
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void edit(User user) {
        User dbUser = userMapper.selectByPrimaryKey(user.getId());
        if (dbUser == null) {
            throw ResponseException.builder(ResponseCodeEnum.USERNAME_NOT_EXIST);
        }
        //判断用户门口是否重名
        if (!dbUser.getUserName().equalsIgnoreCase(user.getUserName())) {
            if (userMapper.getByUserName(user.getUserName()) != null) {
                throw ResponseException.builder(ResponseCodeEnum.USERNAME_EXIST);
            }
        }
        user.setPassWord(Md5Utils.encrypt(user.getPassWord()));
        //更新数据
        userMapper.updateByPrimaryKey(user);
    }
}
