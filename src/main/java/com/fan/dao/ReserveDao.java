package com.fan.dao;

import com.fan.dto.UserDTO;
import com.fan.entity.Hotel;
import com.fan.entity.Reserve;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/1/25 16:27
 */
@Repository
public interface ReserveDao extends Mapper<Reserve> {
    List<Reserve> findAll(@Param("userDTO") UserDTO userDTO);

    @Select("select * from hotel where name = #{name}")
    Hotel findByName(String name);
}
