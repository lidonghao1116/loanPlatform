package com.platform.loan.template.processor;

import com.platform.loan.cache.SimpleCacheUtil;
import com.platform.loan.constant.SystemConfigurationEnum;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.pojo.request.OTPRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.service.OtpService;
import com.platform.loan.template.Processor;
import com.platform.loan.util.RequestCheckUtil;
import com.platform.loan.util.SystemConfigurationUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author caogu.wyp
 * @version $Id: OtpSendProcessor.java, v 0.1 2018-05-12 下午3:58 caogu.wyp Exp $$
 */
public class OtpSendProcessor implements Processor<OTPRequest, BaseResult> {
    @Override
    public void process(OTPRequest request, BaseResult result, Object... others) throws Exception {

        OtpService otpService = (OtpService) others[0];

        RequestCheckUtil.checkOtpRequest(request);

        //校验图片验证码
        verifyImageCode(request);

        String SMSCode = getSMSCode();
        otpService.sendOtp(request.getPhoneNo(), getSMSMessage(SMSCode));
        //放缓存中等登录时取出来验证
        SimpleCacheUtil.addSMSCode(request.getPhoneNo(), request.getBizType(), SMSCode);
    }

    private String getSMSCode() {

        int random = (int) ((Math.random() * 9 + 1) * 100000);
        return String.valueOf(random);

    }

    private String getSMSMessage(String SMSCode) {
        StringBuilder sb = new StringBuilder();

        sb.append(SystemConfigurationUtil
            .getSystemConfigurationValueByKey(SystemConfigurationEnum.CHUANG_LAN_SMS_TEMP));
        //验证码随机6位数

        sb.append(String.valueOf(SMSCode));

        return sb.toString();
    }

    /**
     *
     * @param request
     */
    private void verifyImageCode(OTPRequest request) throws LoanPlatformException {
        String imageCode = SimpleCacheUtil.getImageCode(request.getImageCodeToken());

        if (!StringUtils.endsWith(imageCode, request.getImageCode())) {
            throw new LoanPlatformException("图片验证码验证失败！");
        }

        //验证通过，清空缓存
        SimpleCacheUtil.removeImageCode(request.getImageCodeToken());
    }
}
