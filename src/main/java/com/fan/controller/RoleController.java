package com.fan.controller;

import com.fan.common.Result;
import com.fan.dto.BookDTO;
import com.fan.entity.BookType;
import com.fan.entity.Role;
import com.fan.exception.CustomException;
import com.fan.service.BookTypeService;
import com.fan.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/all")
    public Result getAllRoles(BookDTO bookDTO){
        PageInfo<Role> pageInfo = roleService.getAllRoles(bookDTO);
        return Result.success(pageInfo);
    }
    @GetMapping
    public Result getAll(){
        return Result.success(roleService.getAllRoles());
    }

    @PostMapping
    public Result saveRole(@RequestBody Role role) {
        if (role.getId() == null) {
            roleService.saveRole(role);
        }else {
            roleService.updateRole(role);
        }
        return Result.success();
    }

    /**
     * 删除角色
     * @param id 角色id
     */
    @DeleteMapping("/{id}")
    public Result deleteRole(@PathVariable("id") Integer id) {
        roleService.deleteRole(id);
        return Result.success();
    }

    @DeleteMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        for (Integer id :ids) {
            roleService.deleteRole(id);
        }
        return Result.success();
    }

    @GetMapping("/exportExcel")
    public Result exportExcel(HttpServletResponse response) {
        // 导出excel
        roleService.exportExcel(response);
        return Result.success();
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file){
        // 上传文件
        try {
            roleService.upload(file);
        } catch (IOException e) {
            throw new CustomException("上传文件失败");
        }
        return Result.success("上传文件成功");
    }

}
