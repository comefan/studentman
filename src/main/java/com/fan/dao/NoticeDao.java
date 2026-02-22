package com.fan.dao;

import com.fan.dto.UserDTO;
import com.fan.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NoticeDao extends Mapper<Notice> {
    List<Notice> findAll(@Param("userDTO")UserDTO userDTO);

    @Select("select * from notice order by time desc limit 5")
    List<Notice> getTop5();
}
