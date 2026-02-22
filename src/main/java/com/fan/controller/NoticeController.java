package com.fan.controller;

import com.fan.common.AutoLog;
import com.fan.common.Result;
import com.fan.dto.UserDTO;
import com.fan.entity.Notice;
import com.fan.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/21 11:19
 */
@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;
    @GetMapping("/")
    public Result findAll(UserDTO userDTO) {
        PageInfo<Notice> pageInfo = noticeService.findAll(userDTO);
        return Result.success(pageInfo);
    }

    @GetMapping("/getTop5")
    public Result getTop5() {
        return Result.success(noticeService.getTop5());
    }

    @PostMapping
    @AutoLog("更新公告")
    public Result save(@RequestBody Notice notice) {
        if (notice.getId() == null) {
            noticeService.save(notice);
        } else {
            noticeService.update(notice);
        }
        return Result.success();
    }
    @DeleteMapping("/{id}")
    @AutoLog("删除公告")
    public Result delete(@PathVariable Integer id) {
        noticeService.delete(id);
        return Result.success();
    }
}
