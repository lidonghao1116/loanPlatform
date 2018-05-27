/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.pojo.request.AlipayPayRequest;
import com.platform.loan.util.LoanLogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author caogu.wyp
 * @version $Id: AlipayCallBackController.java, v 0.1 2018-05-27 下午1:10 caogu.wyp Exp $$
 */
@Controller
public class AlipayCallBackController {

    @RequestMapping(value = "/callback/alipay/pay/result", method = RequestMethod.POST)
    public void alipayPayResult(@RequestBody AlipayPayRequest alipayPayRequest,
                                HttpServletResponse response) throws IOException {

        LoanLogUtil.getLogger(AlipayCallBackController.class).info(
            "alipayPayRequest:" + alipayPayRequest);

        //返回结果必须是success
        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write("success");
        response.getWriter().flush();
        response.getWriter().close();
    }
}
