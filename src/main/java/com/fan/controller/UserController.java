package com.fan.controller;

import com.fan.common.Result;
import com.fan.dto.UserDTO;
import com.fan.entity.User;
import com.fan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/1/25 19:27
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/all")
    public Result getAllUser(UserDTO userDTO) {
        PageInfo<User> pageInfo = userService.getAllUser(userDTO);
        return Result.success(pageInfo);
    }
    @PostMapping
    public Result saveUser(@RequestBody User user){
        userService.saveUser(user);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return Result.success();
    }

}
