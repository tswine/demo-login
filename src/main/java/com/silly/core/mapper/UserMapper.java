package com.silly.core.mapper;

import com.silly.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息 mapper层
 *
 * @Author: wei.wang7
 * @Date: 2020/7/26 17:09
 */
@Mapper
public interface UserMapper {

    /**
     * 根据主键获取实体对象
     *
     * @param id
     * @return 获取的实体对象
     */
    User selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 数据库受影响行数
     */
    Integer deleteByPrimaryKey(@Param("id") Integer id);

    /***
     * 插入实体对象
     * @param entity 实体对象
     * @return 插入的条数
     */
    Integer insert(@Param("entity") User entity);

    /**
     * 通过主键更新数据
     *
     * @param entity 实体对象
     * @return 数据库受影响行数
     */
    Integer updateByPrimaryKey(@Param("entity") User entity);

    /**
     * 通过用户名获取用户信息
     *
     * @param userName
     * @return
     */
    User getByUserName(@Param("userName") String userName);

    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();
}