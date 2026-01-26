package com.fan.service;

import com.fan.dao.UserDao;
import com.fan.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/1/25 19:36
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    public List<User> getAllUser() {
        return userDao.findAll();
//        return userDao.selectAll();
    }
}
