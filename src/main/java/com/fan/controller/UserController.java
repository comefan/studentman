package com.fan.controller;

import com.fan.entity.User;
import com.fan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/1/25 19:27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

}
