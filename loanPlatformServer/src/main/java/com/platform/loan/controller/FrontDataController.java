package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerOrderRepository;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.FrontDataResult;
import com.platform.loan.template.LoanPlatformTemplate;
import com.platform.loan.template.processor.FrontDataProcessor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caogu.wyp
 * @version $Id: FrontDataController.java, v 0.1 2018-05-16 上午12:51 caogu.wyp Exp $$
 */
@RestController
public class FrontDataController {

  

    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "在登录的时候下发到前端的jwt", required = true, dataType = "String")
    @ApiOperation(value = "展示数据", notes = "用于前端展示给用户选择的数据")
    @RequestMapping(value = "/api/front/data", method = RequestMethod.GET)
    public FrontDataResult loanApply(BaseRequest request) {

        return LoanPlatformTemplate.run(new FrontDataProcessor(), request, new FrontDataResult());

    }
}
