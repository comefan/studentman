package com.fan.service;

import com.fan.dao.UserDao;
import com.fan.dto.UserDTO;
import com.fan.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<User> getAllUser(UserDTO userDTO) {
        // 开启分页查询
        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
        return PageInfo.of(userDao.findAll(userDTO));
//        return userDao.selectAll();
    }

    public void saveUser(User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        if (user.getId() == null) {
            userDao.insertSelective(user);
        } else {
            userDao.updateByPrimaryKeySelective(user);
        }
    }

    public void deleteUser(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }
}
