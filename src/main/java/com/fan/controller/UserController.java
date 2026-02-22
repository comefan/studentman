package com.fan.controller;

import com.fan.common.AutoLog;
import com.fan.common.CaptureConfig;
import com.fan.common.Result;
import com.fan.dto.UserDTO;
import com.fan.entity.User;
import com.fan.service.UserService;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    /*
     * 前端新增用户或者更新用户信息
     */
    @PostMapping
    @AutoLog("新增用户或者更新用户信息")
    public Result saveUser(@RequestBody User user){
        if (user.getId() == null) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }
        return Result.success();
    }
    @DeleteMapping("/{id}")
    @AutoLog("删除用户")
    public Result deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return Result.success();
    }

    @PostMapping("/login")
    @AutoLog("用户登录")
    public Result login(@RequestBody User user, @RequestParam("key") String key, HttpServletRequest request){
        // 从map中获取验证码
        String captcha = CaptureConfig.CAPTURE_MAP.get(key);
        // 验证码校验
        if (captcha == null || !captcha.equals(user.getVerCode().toLowerCase())) {
            CaptchaUtil.clear(request);
            CaptureConfig.CAPTURE_MAP.remove(key);
            return Result.fail("验证码错误");
        }
        CaptchaUtil.clear(request);
        CaptureConfig.CAPTURE_MAP.remove(key);
        User loginUser = userService.login(user);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.saveUser(user);
        return Result.success();
    }

}
