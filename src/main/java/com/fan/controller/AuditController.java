package com.fan.controller;

import com.fan.common.Result;
import com.fan.dto.UserDTO;
import com.fan.entity.Audit;
import com.fan.service.AuditService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/15 11:14
 */
@RestController
@RequestMapping("/audit")
public class AuditController {
    @Resource
    private AuditService auditService;

    @GetMapping("/all")
    public Result getAllAudits(UserDTO userDTO){
        PageInfo<Audit> pageInfo = auditService.getAllAudits(userDTO);
        return Result.success(pageInfo);
    }

    @PostMapping
    public Result saveAudit(@RequestBody Audit audit) {
        if (audit.getId() == null) {
            auditService.saveAudit(audit);
        }else {
            auditService.updateAudit(audit);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delAudit(@PathVariable Integer id) {
        auditService.delAudit(id);
        return Result.success();
    }
}
