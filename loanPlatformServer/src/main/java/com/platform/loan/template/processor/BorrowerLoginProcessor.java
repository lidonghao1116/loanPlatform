/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.LoginUserTypeEnum;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.request.BorrowerLoginRequest;
import com.platform.loan.pojo.result.LoginResult;
import com.platform.loan.service.common.CommonMethod;
import com.platform.loan.template.Processor;
import com.platform.loan.util.RequestCheckUtil;
import com.platform.loan.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author caogu.wyp
 * @version $Id: BorrowerLoginProcessor.java, v 0.1 2018-05-12 下午3:39 caogu.wyp Exp $$
 */
public class BorrowerLoginProcessor implements Processor<BorrowerLoginRequest, LoginResult> {

    @Override
    public void process(BorrowerLoginRequest request, LoginResult result, Object... others)
                                                                                           throws Exception {

        BorrowerRepository borrowerRepository = (BorrowerRepository) others[0];

        //请求参数判空
        RequestCheckUtil.checkBorrowerLoginRequest(request);

        //校验短信
        CommonMethod.verifyOTP(request.getPhoneNo(), LoginUserTypeEnum.BORROWER.getCode(),
            request.getSmsCode());

        //更新用户信息&&查询是否身份认证
        updateBorrowerInfo(request, borrowerRepository, result);
        //下发登陆token
        result.setAccessToken(CommonMethod.buildLoginAccessToken(request.getPhoneNo(),
            LoginUserTypeEnum.BORROWER.getCode()));

    }

    private void updateBorrowerInfo(BorrowerLoginRequest borrowerLoginRequest,
                                    BorrowerRepository borrowerRepository, LoginResult result) {
        BorrowerDO borrowerDO = borrowerRepository.findBorrowerDoByPhoneNo(borrowerLoginRequest
            .getPhoneNo());

        if (null == borrowerDO) {
            BorrowerDO newBorrower = new BorrowerDO();
            newBorrower.setPhoneNo(borrowerLoginRequest.getPhoneNo());
            newBorrower.setCreateTime(TimeUtil.getCurrentTimestamp());
            borrowerRepository.save(newBorrower);
            result.setHaveVerifyIdNo(false);
            return;
        }

        if (StringUtils.isNotBlank(borrowerDO.getIdNo())) {

            result.setHaveVerifyIdNo(true);
        } else {

            result.setHaveVerifyIdNo(false);
        }
    }
}
