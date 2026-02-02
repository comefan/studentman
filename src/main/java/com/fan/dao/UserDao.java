package com.fan.dao;

import com.fan.dto.UserDTO;
import com.fan.entity.User;
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
public interface UserDao extends Mapper<User> {
    List<User> findAll(@Param("userDTO") UserDTO userDTO);

    @Select("select * from user where name = #{name} limit 1")
    User findByName(@Param("name") String name);

    @Select("select * from user where name = #{name} and password = #{password} limit 1")
    User findByNameAndPsd(@Param("name") String name, @Param("password") String password);
}
