package com.fan.service;

import com.fan.common.AutoLog;
import com.fan.common.JwtTokenUtils;
import com.fan.dao.UserDao;
import com.fan.dto.UserDTO;
import com.fan.entity.User;
import com.fan.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

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
        //判断用户是否为空
        if (StringUtil.isEmpty(user.getName())) {
            throw new CustomException("用户不能为空");
        }
        //判断用户是否存在
        User existUser = userDao.findByName(user.getName());
        if (existUser != null){
            throw new CustomException("用户已存在");
        }
        // 密码为空时，默认密码为123456
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userDao.insertSelective(user);

    }
    public void updateUser(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }
    public void deleteUser(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }

    public User login(User user) {
        // 1.判断用户名是否为空
        if (StringUtil.isEmpty(user.getName())){
            throw new CustomException("用户名不能为空");
        }
        // 2.判断密码是否为空
        if (StringUtil.isEmpty(user.getPassword())){
            throw new CustomException("密码不能为空");
        }
        // 3.判断用户名密码是否正确
        User loginUser = userDao.findByNameAndPsd(user.getName(), user.getPassword());
        if ( loginUser == null) {
            throw new CustomException("用户名或密码错误");
        }
        //生成该用户对应的token，跟着user一起返回前端
        String token = JwtTokenUtils.genToken(loginUser.getId().toString(), loginUser.getPassword());
        loginUser.setToken(token);
        return loginUser;
    }

    public User findById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }
}
