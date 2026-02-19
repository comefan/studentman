package com.fan.controller;

import cn.hutool.core.date.DateUtil;
import com.fan.common.Result;
import com.fan.dto.UserDTO;
import com.fan.entity.Hotel;
import com.fan.entity.Reserve;
import com.fan.service.HotelService;
import com.fan.service.ReserveService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/18 19:27
 */
@CrossOrigin
@RestController
@RequestMapping("/reserve")
public class ReserveController {
    @Resource
    private ReserveService reserveService;
    @GetMapping("/all")
    public Result getAllReserve(UserDTO userDTO) {
        PageInfo<Reserve> pageInfo = reserveService.getAllReserve(userDTO);
        return Result.success(pageInfo);
    }

    /*
     * 前端新增用户或者更新用户信息
     */
    @PostMapping
    public Result saveReserve(@RequestBody Reserve reserve){
        reserve.setTime(DateUtil.now());
        reserveService.saveReserve(reserve);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result deleteReserve(@PathVariable Integer id){
        reserveService.deleteReserve(id);
        return Result.success();
    }


}
