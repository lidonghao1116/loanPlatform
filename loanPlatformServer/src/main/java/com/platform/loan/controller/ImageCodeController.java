/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.util.RandomValidateCodeUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 *  图片验证码
 *  https://blog.csdn.net/Colton_Null/article/details/78744240
 * @author caogu.wyp
 * @version $Id: ImageCodeController.java, v 0.1 2018-05-05 下午8:55 caogu.wyp Exp $$
 */
@Controller
public class ImageCodeController {

    @ApiOperation(value = "申请图片验证码", notes = "申请图片验证码，图片写进response流中")
    @RequestMapping(value = "/imagecode/generate", method = RequestMethod.GET)
    public void generateImageCode(HttpServletRequest request, HttpServletResponse response) {

        BufferedImage image = RandomValidateCodeUtil.getRandcode();

        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            //输出验证码图片方法
            RandomValidateCodeUtil.getRandcode();
            ImageIO.write(image, "JPEG", response.getOutputStream());

        } catch (Exception e) {
            System.out.println("将内存中的图片通过流动形式输出到客户端失败>>>>");
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/imagecode.html", method = RequestMethod.GET)
    public String imageCodeView() {

        return "imagecode";
    }

}
