package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.result.BorrowerLoginResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.BorrowerLoginProcessor;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerLoginController.java, v 0.1 2018-05-09 下午10:03 caogu.wyp Exp $$
 */
@RestController
public class BorrowerLoginController {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @ApiOperation(value = "借款人登录接口", notes = "输入手机号，短信验证码，图片验证码，验证成功即可登录")
    @RequestMapping(value = "/api/borrower/login", method = RequestMethod.POST)
    public BorrowerLoginResult login(BorrowerLoginRequest request) {

        return LoanPlatformTemplate.run(new BorrowerLoginProcessor(), request,
            new BorrowerLoginResult(), borrowerRepository);

    }

}
