package com.fan.controller;

import com.fan.common.Result;
import com.fan.dto.BookDTO;
import com.fan.entity.Book;
import com.fan.entity.BookType;
import com.fan.exception.CustomException;
import com.fan.service.BookService;
import com.fan.service.BookTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 12:08
 */
@RestController
@RequestMapping("/bookType")
public class BookTypeController {
    @Resource
    private BookTypeService bookTypeService;

    @GetMapping("/all")
    public Result getAllBookTypes(BookDTO bookDTO){
        PageInfo<BookType> pageInfo = bookTypeService.getAllBookTypes(bookDTO);
        return Result.success(pageInfo);
    }

    @PostMapping
    public Result saveBookType(@RequestBody BookType bookType) {
        if (bookType.getId() == null) {
            bookTypeService.saveBookType(bookType);
        }else {
            bookTypeService.updateBookType(bookType);
        }
        return Result.success();
    }

    /**
     * 删除书籍
     * @param id 书籍id
     */
    @DeleteMapping("/{id}")
    public Result deleteBookType(@PathVariable("id") Integer id) {
        bookTypeService.deleteBookType(id);
        return Result.success();
    }

    @DeleteMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        for (Integer id :ids) {
            bookTypeService.deleteBookType(id);
        }
        return Result.success();
    }

    @GetMapping("/exportExcel")
    public Result exportExcel(HttpServletResponse response) {
        // 导出excel
        bookTypeService.exportExcel(response);
        return Result.success();
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file){
        // 上传文件
        try {
            bookTypeService.upload(file);
        } catch (IOException e) {
            throw new CustomException("上传文件失败");
        }
        return Result.success("上传文件成功");
    }

}
