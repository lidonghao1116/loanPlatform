/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.platform.loan.pojo.request.CallBackRequest;
import com.platform.loan.pojo.result.BaseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 *  https://docs.51datakey.com/docs/fund/302#task_submit
 * @author caogu.wyp
 * @version $Id: CallBackController.java, v 0.1 2018-05-18 下午10:44 caogu.wyp Exp $$
 */
@RestController
public class CallBackController {

    @RequestMapping(value = "/callback/providentfund/submit", method = RequestMethod.POST)
    public BaseResult providentFundSubmit(@RequestBody CallBackRequest callBackRequest,
                                          HttpServletResponse response) {
        //授权申请成功
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/providentfund/finish", method = RequestMethod.POST)
    public BaseResult providentFundFinish(@RequestBody CallBackRequest callBackRequest,
                                          HttpServletResponse response) {
        //授权完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/providentfund/fail", method = RequestMethod.POST)
    public BaseResult providentFundFail(@RequestBody CallBackRequest callBackRequest,
                                        HttpServletResponse response) {
        //数据采集失败
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/providentfund/bill", method = RequestMethod.POST)
    public BaseResult providentFundBill(@RequestBody CallBackRequest callBackRequest,
                                        HttpServletResponse response) {
        //数据采集完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/providentfund/report", method = RequestMethod.POST)
    public BaseResult providentFundReport(@RequestBody CallBackRequest callBackRequest,
                                          HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    //=============//
    @RequestMapping(value = "/callback/socialsecurity/submit", method = RequestMethod.POST)
    public BaseResult socialSecuritySubmit(@RequestBody CallBackRequest callBackRequest,
                                           HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/socialsecurity/finish", method = RequestMethod.POST)
    public BaseResult socialSecurityFinish(@RequestBody CallBackRequest callBackRequest,
                                           HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/socialsecurity/fail", method = RequestMethod.POST)
    public BaseResult socialSecurityFail(@RequestBody CallBackRequest callBackRequest,
                                         HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/socialsecurity/bill", method = RequestMethod.POST)
    public BaseResult socialSecurityBill(@RequestBody CallBackRequest callBackRequest,
                                         HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

    @RequestMapping(value = "/callback/socialsecurity/report", method = RequestMethod.POST)
    public BaseResult socialSecurityReport(@RequestBody CallBackRequest callBackRequest,
                                           HttpServletResponse response) {
        //报告处理完成
        System.out.println(callBackRequest);
        response.setStatus(201);
        return new BaseResult();
    }

}
