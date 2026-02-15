package com.fan.service;

import cn.hutool.core.util.StrUtil;
import com.fan.common.JwtTokenUtils;
import com.fan.dao.AuditDao;
import com.fan.dto.UserDTO;
import com.fan.entity.Audit;
import com.fan.entity.User;
import com.fan.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/15 11:13
 */
@Service
public class AuditService {

    @Resource
    private AuditDao auditDao;

    public PageInfo<Audit> getAllAudits(UserDTO userDTO) {
        User user = JwtTokenUtils.getCurrentUser();
        if (user == null) {
            throw new CustomException("用户未登录！");
        }
        if ("ROLE_STUDENT".equals(user.getRoleCode())) {
            userDTO.setUserId(user.getId().toString());
        }
        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
        return PageInfo.of(auditDao.findAll(userDTO));
    }

    public void saveAudit(Audit audit) {
        if (StrUtil.isEmpty(audit.getName())){
            throw new CustomException("请假缘由不能为空！");
        }
        auditDao.insertSelective(audit);
    }

    public void updateAudit( Audit audit) {
        auditDao.updateByPrimaryKeySelective(audit);
    }

    public void delAudit(Integer id) {
        auditDao.deleteByPrimaryKey(id);
    }
}
