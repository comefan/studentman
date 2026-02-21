package com.fan.dao;

import com.fan.dto.UserDTO;
import com.fan.entity.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LogDao extends Mapper<Log> {
    List<Log> findAll(@Param("userDTO") UserDTO userDTO);
}
