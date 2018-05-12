package com.platform.loan.controller;

import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.GenerateImageProcessor;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 *  图片验证码
 *  https://blog.csdn.net/Colton_Null/article/details/78744240
 * @author caogu.wyp
 * @version $Id: ImageCodeController.java, v 0.1 2018-05-05 下午8:55 caogu.wyp Exp $$
 */
@Controller
public class ImageCodeController {

    @ApiOperation(value = "申请图片验证码", notes = "图片写进response流中，Response Hearder中取IMAGE_CODE_HEADER_KEY")
    @RequestMapping(value = "/api/imagecode/generate", method = RequestMethod.GET)
    public void generateImageCode(BaseRequest request, HttpServletResponse response) {

        LoanPlatformTemplate.run(new GenerateImageProcessor(), request, new BaseResult(), response);

    }

    @ApiOperation(value = "图片验证码示例", notes = "图片验证码生成示例，用于参考")
    @RequestMapping(value = "/imagecode.html", method = RequestMethod.GET)
    public String imageCodeView() {

        return "imagecode";
    }

}
