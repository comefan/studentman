package com.fan.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.fan.entity.Log;
import com.fan.entity.User;
import com.fan.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/20 11:23
 */
@Component
@Aspect
public class AspectLog {
    @Resource
    private LogService logService;

    Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Around("@annotation(autoLog)")
    public Object around(ProceedingJoinPoint joinPoint, AutoLog autoLog) throws Throwable {
//        logger.info("进入方法：{}", joinPoint.getSignature().getName());
        //操作
        String name = autoLog.value();
        //ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String ip = attributes.getRequest().getRemoteAddr();
        //操作人
        User user = JwtTokenUtils.getCurrentUser();
        String username = "";
        if (user != null){
            username = user.getName();
        }

        // 执行目标方法

        Result result = (Result) joinPoint.proceed();
        if (ObjUtil.isNotNull(result.getData())) {
            if (result.getData() instanceof User) {
                username = ((User) result.getData()).getName();
            }
        }
//        logger.info("方法：{} 执行完毕", joinPoint.getSignature().getName());
        // 记录日志
        Log log = new Log(null,name,username, ip , DateUtil.now());
        logService.insertLog(log);
        return result;
    }

}
