package com.fan.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fan.entity.User;
import com.fan.exception.CustomException;
import com.fan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/3 21:27
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }
        // 2. 开始执行认证
        if (StrUtil.isBlank(token)) {
            throw new CustomException("无token，请重新登录");
        }
        // 获取 token中的 userID
        String userId;
        User user;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            // 根据token中的userid查询数据库
            user = userService.findById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg, e);
            throw new CustomException(errMsg);
        }
        if (user == null) {
            throw new CustomException("用户不存在，请重新登录");
        }

        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg, e);
            throw new CustomException(errMsg);
        }
        return true;
    }

}
