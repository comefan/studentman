package com.fan.dao;

import com.fan.dto.UserDTO;
import com.fan.entity.Audit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/15 10:55
 */
@Repository
public interface AuditDao extends Mapper<Audit> {

    List<Audit> findAll(@Param("userDTO") UserDTO userDTO);
}
