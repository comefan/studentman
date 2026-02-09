package com.fan.dao;

import com.fan.dto.BookDTO;
import com.fan.entity.BookType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BookTypeDao extends Mapper<BookType> {
    List<BookType> findAll(@Param("bookDTO") BookDTO bookDTO);
    
    @Select("select * from booktype where name = #{name} limit 1;")
    BookType findByName(String name);

    @Select("select * from booktype;")
    List<BookType> findAlls();
}
