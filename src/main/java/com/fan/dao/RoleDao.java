package com.fan.dao;

import com.fan.dto.BookDTO;
import com.fan.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleDao extends Mapper<Role> {
    List<Role> findAll(@Param("bookDTO") BookDTO bookDTO);
    
    @Select("select * from rolemanage where name = #{roleName} limit 1;")
    Role findByRoleName(String roleName);

    @Select("select * from rolemanage;")
    List<Role> findAlls();
}
