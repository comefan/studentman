package com.fan.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fan.entity.User;
import com.fan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/2 21:32
 */
@Component
public class JwtTokenUtils {
    private static UserService staticUserService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    /*
     * 生成token
     */
    public static String genToken(String userId, String sign){
        return JWT.create().withAudience(userId) //将 userId 保存到 token，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token 过期
                .sign(Algorithm.HMAC256(sign)); // 以 sign 作为 token 的密钥
    }

    /*
    * 获取当前登录的用户信息
     */
    public static User getCurrentUser(){
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)){
                token = request.getParameter("token");
            }
            if( StrUtil.isBlank(token )) {
                log.error("获取当前登录的token失败，token：{}" , token);
                return null;
            }
            //解析token，获取用户的id
            String userId = JWT.decode(token).getAudience().get(0);
            return staticUserService.findById(Integer.valueOf(userId));
        } catch (Exception e) {
            log.error("获取当前登录的用户信息失败，token：{}" , token, e);
            return null;
        }
    }
}
