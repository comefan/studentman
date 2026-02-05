package com.fan.dao;

import com.fan.dto.BookDTO;
import com.fan.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BookDao extends Mapper<Book> {

    List<Book> findAll(@Param("bookDTO") BookDTO bookDTO);
    @Select("select * from book where name = #{name} limit 1")
    Book findByName(String name);
}
