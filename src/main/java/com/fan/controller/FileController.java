package com.fan.controller;

import cn.hutool.core.io.FileUtil;
import com.fan.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/5 22:43
 */
@RestController
@RequestMapping("/files")
public class FileController {
    // 文件上传存储路径
    private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/files/";

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        synchronized (FileController.class) {
           String flag = System.currentTimeMillis() + "";
           String fileName = file.getOriginalFilename();
           try {
               if (!FileUtil.isDirectory(FILE_UPLOAD_PATH)) {
                   FileUtil.mkdir(FILE_UPLOAD_PATH);
               }
               // 文件存储形式： 时间戳+文件名
               FileUtil.writeBytes(file.getBytes(), FILE_UPLOAD_PATH + flag + "-" + fileName);
               System.out.println(FILE_UPLOAD_PATH + flag + "-" + fileName + " 上传成功");
               Thread.sleep(1L);
           } catch (Exception e) {
               return Result.fail("文件上传失败");
           }
           return Result.success(flag);
        }
    }
}
