/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.cache.SimpleCacheUtil;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.result.BorrowerLoginResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.RequestCheckUtil;
import com.platform.loan.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerLoginProcessor.java, v 0.1 2018-05-12 下午3:39 caogu.wyp Exp $$
 */
public class BorrowerLoginProcessor implements Processor<BorrowerLoginRequest, BorrowerLoginResult> {

    @Override
    public void process(BorrowerLoginRequest request, BorrowerLoginResult result, Object... others)
                                                                                                   throws Exception {

        BorrowerRepository borrowerRepository = (BorrowerRepository) others[0];

        //请求参数判空
        RequestCheckUtil.checkBorrowerLoginRequest(request);
        //校验短信
        verifyOTP(request);
        //更新用户信息
        updateBorrowerInfo(request, borrowerRepository);
        //下发token
        initAccessToken(result, request);
    }

    private void updateBorrowerInfo(BorrowerLoginRequest borrowerLoginRequest,
                                    BorrowerRepository borrowerRepository) {

        if (null == borrowerRepository.findBorrowerDoByPhoneNo(borrowerLoginRequest.getPhoneNo())) {

            BorrowerDO newBorrower = new BorrowerDO();
            newBorrower.setPhoneNo(borrowerLoginRequest.getPhoneNo());
            newBorrower.setCreateTime(TimeUtil.getCurrentTimestamp());
            borrowerRepository.save(newBorrower);

        }

    }

    private void initAccessToken(BorrowerLoginResult result, BorrowerLoginRequest request)
                                                                                          throws UnsupportedEncodingException {

        LoginSession loginSession = new LoginSession();
        loginSession.setPhoneNo(request.getPhoneNo());
        loginSession.setBiz("borrower");
        result.setAccessToken(JwtUtil.createJwt(loginSession));

    }

    /**
     *
     * @param borrowerLoginRequest
     */
    private void verifyOTP(BorrowerLoginRequest borrowerLoginRequest) throws LoanPlatformException {

        String SMSCode = SimpleCacheUtil.getSMSCode(borrowerLoginRequest.getPhoneNo());

        if (!StringUtils.endsWith(SMSCode, borrowerLoginRequest.getSMSCode())) {
            throw new LoanPlatformException("短信验证码验证失败！");
        }
        //验证通过，清空缓存
        SimpleCacheUtil.removeSMSCode(borrowerLoginRequest.getPhoneNo());
    }
}
