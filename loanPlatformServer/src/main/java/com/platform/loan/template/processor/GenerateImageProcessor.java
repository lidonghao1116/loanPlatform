/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.ImageCodeResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.RandomValidateCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 *
 * @author caogu.wyp
 * @version $Id: GenerateImageProcessor.java, v 0.1 2018-05-12 下午3:44 caogu.wyp Exp $$
 */
public class GenerateImageProcessor implements Processor<BaseRequest, ImageCodeResult> {

    @Override
    public void process(BaseRequest request, ImageCodeResult imageCodeResult, Object... others)
                                                                                               throws Exception {

        HttpServletResponse response = (HttpServletResponse) others[0];

        BufferedImage image = RandomValidateCodeUtil.getRandcode(response);

        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        ImageIO.write(image, "JPEG", response.getOutputStream());

    }
}
