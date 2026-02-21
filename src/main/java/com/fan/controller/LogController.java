package com.fan.controller;

import com.fan.common.Result;
import com.fan.dto.UserDTO;
import com.fan.entity.Log;
import com.fan.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/20 10:40
 */
@RestController
@RequestMapping("/log")
@CrossOrigin
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping("/all")
    public Result getAllLogs(UserDTO userDTO) {
        PageInfo<Log> pageInfo = logService.getAllLogs(userDTO);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/{id}")
    public Result deleteLog(@PathVariable Integer id) {
        logService.deleteLog(id);
        return Result.success();
    }
}
