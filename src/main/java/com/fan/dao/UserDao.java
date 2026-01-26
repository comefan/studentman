package com.fan.dao;

import com.fan.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/1/25 16:27
 */
@Repository
public interface UserDao extends Mapper<User> {
    List<User> findAll();
}
