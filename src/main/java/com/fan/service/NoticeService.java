package com.fan.service;

import cn.hutool.core.util.StrUtil;
import com.fan.dao.NoticeDao;
import com.fan.dto.UserDTO;
import com.fan.entity.Notice;
import com.fan.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/21 11:20
 */
@Service
public class NoticeService {
    @Resource
    private NoticeDao noticeDao;

    public PageInfo<Notice> findAll(UserDTO userDTO) {
        // 分页
        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
        return PageInfo.of(noticeDao.findAll(userDTO));
    }

    public void save(Notice notice) {
        if (StrUtil.isEmpty(notice.getName())) {
            throw new CustomException("公告名称不能为空");
        }
        noticeDao.insertSelective(notice);
    }

    public void update(Notice notice) {
        if (StrUtil.isEmpty(notice.getName())) {
            throw new CustomException("公告名称不能为空");
        }
        noticeDao.updateByPrimaryKeySelective(notice);
    }

    public void delete(Integer id) {
        noticeDao.deleteByPrimaryKey(id);
    }

    public List<Notice> getTop5() {
        return noticeDao.getTop5();
    }
}
