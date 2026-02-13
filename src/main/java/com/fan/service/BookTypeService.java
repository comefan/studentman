package com.fan.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fan.dao.BookTypeDao;
import com.fan.dto.BookDTO;
import com.fan.entity.BookType;
import com.fan.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 12:16
 */
@Service
public class BookTypeService {
    @Resource
    private BookTypeDao bookTypeDao;

    public PageInfo<BookType> getAllBookTypes(BookDTO bookDTO) {
        PageHelper.startPage(bookDTO.getPageNum(), bookDTO.getPageSize());
        List<BookType> bookTypes = bookTypeDao.findAll(bookDTO);
        return PageInfo.of(bookTypes);
    }

    /**
     * 获取所有书籍类型
     * @return
     */
    public List<BookType> getAllBookTypes(){
        return bookTypeDao.findAlls();
    }

    /**
     * 保存书籍类型
     * @param bookType
     * fanjq 2026/2/8 12:32
     */
    public void saveBookType(BookType bookType) {
        if (StrUtil.isEmpty(bookType.getName())) {
            throw new CustomException("书籍类型名称不能为空");
        }
        BookType findBookType = bookTypeDao.findByName(bookType.getName());
        if (findBookType != null) {
            throw new CustomException("书籍类型名称已存在");
        }
        bookTypeDao.insertSelective(bookType);
    }

    public void updateBookType(BookType bookType) {
        if (StrUtil.isEmpty(bookType.getName())) {
            throw new CustomException("书籍类型名称不能为空");
        }
        bookTypeDao.updateByPrimaryKeySelective(bookType);
    }

    public void deleteBookType(Integer id){
        bookTypeDao.deleteByPrimaryKey(id);
    }

    public void exportExcel(HttpServletResponse response) {
        // 导出excel
        List<BookType> alls = bookTypeDao.findAlls();
        if (CollectionUtil.isEmpty(alls)) {
            throw new CustomException("暂无书籍类型数据");
        }
        List<Map<String,Object>> list = new ArrayList<>();
        for (BookType bookType : alls) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("分类名称", bookType.getName());
            map.put("描述", bookType.getDescription());
            list.add(map);
        }
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=bookTypes.xlsx");
        try {
            writer.flush(response.getOutputStream(),true);
        } catch (Exception e) {
            throw new CustomException("导出excel失败");
        }
        writer.close();
        IoUtil.close(System.out);
    }

    public void upload(MultipartFile file) throws IOException {
        // 上传文件
        List<BookType> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(BookType.class);
        if (!CollectionUtil.isEmpty(infoList)){
            for (BookType bookType : infoList){
                try {
                    this.saveBookType(bookType);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
