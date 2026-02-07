package com.fan.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.fan.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

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
    /**
     * 文件下载
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        if(!FileUtil.isDirectory(FILE_UPLOAD_PATH)) {
            FileUtil.mkdir(FILE_UPLOAD_PATH);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(FILE_UPLOAD_PATH);
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(FILE_UPLOAD_PATH + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println("文件下载失败");
        }
    }
}
