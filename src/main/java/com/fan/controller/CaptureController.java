package com.fan.controller;

import com.fan.common.CaptureConfig;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/22 10:50
 */
@CrossOrigin
@RestController
@RequestMapping
public class CaptureController {
    @RequestMapping("/captcha")
    public void captcha(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // png 图片验证码
//        SpecCaptcha captcha = new SpecCaptcha(135, 33 ,5);
//        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
//        // 验证码存入map
//        CaptureConfig.CAPTURE_MAP.put(key, captcha.text().toLowerCase());
//        CaptchaUtil.out(captcha, request, response);

        //算术类型 JDK17不再适用
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(135, 33);
        captcha.setLen(3); //几位数运算，默认是两位
        captcha.getArithmeticString(); //获取运算的公式：3+2=？
        captcha.text(); //获取运算的结果
        CaptureConfig.CAPTURE_MAP.put(key, captcha.text().toLowerCase());
        CaptchaUtil.out(captcha, request, response);


    }
}
