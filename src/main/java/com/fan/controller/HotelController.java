package com.fan.controller;

import com.fan.common.Result;
import com.fan.dto.UserDTO;
import com.fan.entity.Hotel;
import com.fan.entity.User;
import com.fan.service.HotelService;
import com.fan.service.UserService;
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
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;
    @GetMapping("/all")
    public Result getAllHotel(UserDTO userDTO) {
        PageInfo<Hotel> pageInfo = hotelService.getAllHotel(userDTO);
        return Result.success(pageInfo);
    }

    /*
     * 前端新增用户或者更新用户信息
     */
    @PostMapping
    public Result saveHotel(@RequestBody Hotel hotel){
        if (hotel.getId() == null) {
            hotelService.saveHotel(hotel);
        } else {
            hotelService.updateHotel(hotel);
        }
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result deleteHotel(@PathVariable Integer id){
        hotelService.deleteHotel(id);
        return Result.success();
    }


}
