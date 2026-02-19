package com.fan.service;

import com.fan.dao.HotelDao;
import com.fan.dao.ReserveDao;
import com.fan.dto.UserDTO;
import com.fan.entity.Hotel;
import com.fan.entity.Reserve;
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
public class ReserveService {
    @Resource
    private ReserveDao reserveDao;

    @Resource
    private HotelDao hotelDao;
    public PageInfo<Reserve> getAllReserve(UserDTO userDTO) {
        // 开启分页查询
        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
        return PageInfo.of(reserveDao.findAll(userDTO));
//        return userDao.selectAll();
    }

    public void saveReserve(Reserve reserve) {
        // 校验酒店是否存在
        Hotel hotel = hotelDao.selectByPrimaryKey(reserve.getHotelId());
        if (hotel == null) {
            throw new CustomException("酒店房间不存在");
        }
        // 校验酒店是否有足够的房间
        if (hotel.getNum() < 1){
            throw new CustomException("酒店房间不足");
        }
        reserveDao.insertSelective(reserve);
        // 酒店房间数减一
        hotel.setNum(hotel.getNum() - 1);
        // 更新酒店房间数
        hotelDao.updateByPrimaryKeySelective(hotel);


    }



    public void deleteReserve(Integer id) {
        reserveDao.deleteByPrimaryKey(id);
    }



    public Reserve findById(Integer id) {
        return reserveDao.selectByPrimaryKey(id);
    }
}
