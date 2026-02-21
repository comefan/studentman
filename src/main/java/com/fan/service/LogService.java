package com.fan.service;

import com.fan.dao.LogDao;
import com.fan.dto.UserDTO;
import com.fan.entity.Log;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/20 10:31
 */
@Service
public class LogService {
    @Resource
    private LogDao logDao;

    public PageInfo<Log> getAllLogs(UserDTO userDTO) {
        //开启分页查询
        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
        return PageInfo.of(logDao.findAll(userDTO));
    }

    /**
     * 新增日志
     * @param log
     */
    public void insertLog(Log log) {
        logDao.insertSelective(log);
    }

    public void deleteLog(Integer id) {
        logDao.deleteByPrimaryKey(id);
    }
}
