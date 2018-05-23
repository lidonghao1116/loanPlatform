package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.request.ManagerLoginRequest;
import com.platform.loan.pojo.result.LoginResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.BorrowerLoginProcessor;
import com.platform.loan.template.processor.ManagerLoginProcessor;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerLoginController.java, v 0.1 2018-05-09 下午10:03 caogu.wyp Exp $$
 */
@RestController
public class LoginController {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private ManagerRepository  managerRepository;

    @ApiOperation(value = "借款人登录接口", notes = "输入手机号，短信验证码，图片验证码，验证成功即可登录")
    @RequestMapping(value = "/api/borrower/login", method = RequestMethod.POST)
    public LoginResult borrowerLogin(@RequestBody BorrowerLoginRequest borrowerLoginRequest) {

        return LoanPlatformTemplate.run(new BorrowerLoginProcessor(), borrowerLoginRequest,
            new LoginResult(), borrowerRepository);
    }

    @ApiOperation(value = "信贷经理登录接口", notes = "输入手机号，短信验证码，图片验证码，验证成功即可登录")
    @RequestMapping(value = "/api/manager/login", method = RequestMethod.POST)
    public LoginResult managerLogin(@RequestBody ManagerLoginRequest managerLoginRequest) {

        return LoanPlatformTemplate.run(new ManagerLoginProcessor(), managerLoginRequest,
            new LoginResult(), managerRepository);
    }

}
