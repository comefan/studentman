package com.fan.service;

import cn.hutool.core.util.StrUtil;
import com.fan.dao.BookDao;
import com.fan.dto.BookDTO;
import com.fan.entity.Book;
import com.fan.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/4 21:30
 */
@Service
public class BookService {
    @Resource
    private BookDao bookDao;

    public PageInfo<Book> getAllBooks(BookDTO bookDTO) {
        PageHelper.startPage(bookDTO.getPageNum(), bookDTO.getPageSize());
        List<Book> books = bookDao.findAll(bookDTO);
        return PageInfo.of(books);
    }

    public void saveBook(Book book) {
        if (StrUtil.isEmpty(book.getName())) {
            throw new CustomException("书名不能为空");
        }
        Book fbook = bookDao.findByName(book.getName());
        if (fbook != null) {
            throw new CustomException("书名已存在");
        }
        bookDao.insertSelective(book);
    }

    public void updateBook(Book book) {
        if (StrUtil.isEmpty(book.getName())) {
            throw new CustomException("书名不能为空");
        }
        bookDao.updateByPrimaryKeySelective(book);
    }
    /**
     * 删除书籍
     * @param id 书籍id
     */
    public void deleteBook(Integer id){
        bookDao.deleteByPrimaryKey(id);
    }
}
