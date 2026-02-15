package com.fan.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fan.dao.BookTypeDao;
import com.fan.dao.RoleDao;
import com.fan.dto.BookDTO;
import com.fan.entity.BookType;
import com.fan.entity.Role;
import com.fan.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 12:16
 */
@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;

    public PageInfo<Role> getAllRoles(BookDTO bookDTO) {
        PageHelper.startPage(bookDTO.getPageNum(), bookDTO.getPageSize());
        List<Role> roles = roleDao.findAll(bookDTO);
        return PageInfo.of(roles);
    }

    /**
     * 获取所有书籍类型
     * @return
     */
    public List<Role> getAllRoles(){
        return roleDao.findAlls();
    }

    /**
     * 保存书籍类型
     * @param role
     * fanjq 2026/2/8 12:32
     */
    public void saveRole(Role role) {
        if (StrUtil.isEmpty(role.getName())) {
            throw new CustomException("角色名称不能为空");
        }
        Role findRole = roleDao.findByRoleName(role.getName());
        if (findRole != null) {
            throw new CustomException("角色名称已存在");
        }
        roleDao.insertSelective(role);
    }

    public void updateRole(Role role) {
        if (StrUtil.isEmpty(role.getName())) {
            throw new CustomException("角色名称不能为空");
        }
        roleDao.updateByPrimaryKeySelective(role);
    }

    public void deleteRole(Integer id){
        roleDao.deleteByPrimaryKey(id);
    }

    public void exportExcel(HttpServletResponse response) {
        // 导出excel
        List<Role> alls = roleDao.findAlls();
        if (CollectionUtil.isEmpty(alls)) {
            throw new CustomException("暂无角色数据");
        }
        List<Map<String,Object>> list = new ArrayList<>();
        for (Role role : alls) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("角色名称", role.getName());
            map.put("角色编号", role.getCode());
            list.add(map);
        }
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=roles.xlsx");
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
        List<Role> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Role.class);
        if (!CollectionUtil.isEmpty(infoList)){
            for (Role role : infoList){
                try {
                    this.saveRole(role);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
