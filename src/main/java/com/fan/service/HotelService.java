package com.fan.service;

import com.fan.common.JwtTokenUtils;
import com.fan.dao.HotelDao;
import com.fan.dao.UserDao;
import com.fan.dto.UserDTO;
import com.fan.entity.Hotel;
import com.fan.entity.User;
import com.fan.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/1/25 19:36
 */
@Service
public class HotelService {
    @Resource
    private HotelDao hotelDao;
    public PageInfo<Hotel> getAllHotel(UserDTO userDTO) {
        // 开启分页查询
        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
        return PageInfo.of(hotelDao.findAll(userDTO));
//        return userDao.selectAll();
    }

    public void saveHotel(Hotel hotel) {
        //判断用户是否为空
        if (StringUtil.isEmpty(hotel.getName())) {
            throw new CustomException("酒店不能为空");
        }
        //判断酒店是否存在
        Hotel existHotel = hotelDao.findByName(hotel.getName());
        if (existHotel != null){
            throw new CustomException("酒店已存在");
        }
        hotelDao.insertSelective(hotel);

    }
    public void updateHotel(Hotel hotel) {
        hotelDao.updateByPrimaryKeySelective(hotel);
    }

    public void deleteHotel(Integer id) {
        hotelDao.deleteByPrimaryKey(id);
    }



    public Hotel findById(Integer id) {
        return hotelDao.selectByPrimaryKey(id);
    }
}
