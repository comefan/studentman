package com.fan.controller;

import com.fan.common.Result;
import com.fan.dto.BookDTO;
import com.fan.entity.Book;
import com.fan.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/4 21:31
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/all")
    public Result getAllBooks(BookDTO bookDTO){
        PageInfo<Book> pageInfo = bookService.getAllBooks(bookDTO);
        return Result.success(pageInfo);
    }

    @PostMapping
    public Result saveBook(@RequestBody Book book) {
        if (book.getId() == null) {
            bookService.saveBook(book);
        }else {
            bookService.updateBook(book);
        }
        return Result.success();
    }

    /**
     * 删除书籍
     * @param id 书籍id
     */
    @DeleteMapping("/{id}")
    public Result deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return Result.success();
    }

}
