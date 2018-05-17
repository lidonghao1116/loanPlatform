/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.request.VerifyIdNoRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.template.Processor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author caogu.wyp
 * @version $Id: VerifyIdNoProcessor.java, v 0.1 2018-05-17 下午11:29 caogu.wyp Exp $$
 */
public class VerifyIdNoProcessor implements Processor<VerifyIdNoRequest, BaseResult> {
    @Override
    public void process(VerifyIdNoRequest request, BaseResult result, Object... others)
                                                                                       throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];

        BorrowerRepository borrowerRepository = (BorrowerRepository) others[1];

        LoginSession loginSession = JwtUtil.getLoginSession(httpServletRequest);

        if (verifyIdNoPass(request)) {

            BorrowerDO borrowerDo = borrowerRepository.findBorrowerDoByPhoneNo(loginSession
                .getPhoneNo());

            borrowerDo.setIdNo(request.getIdNo());
            borrowerDo.setName(request.getName());

            borrowerRepository.save(borrowerDo);

        } else {
            throw new Exception("实名认证失败");
        }

    }

    private boolean verifyIdNoPass(VerifyIdNoRequest request) {
        //mock
        return true;
    }

}
