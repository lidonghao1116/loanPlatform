/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.platform.loan.constant.CommonConstants;
import com.platform.loan.constant.TopUpOrderStatusEnum;
import com.platform.loan.dao.TopUpOrderRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.TopUpOrderDO;
import com.platform.loan.pojo.request.CreateOrderRequest;
import com.platform.loan.pojo.result.CreateOrderResult;
import com.platform.loan.service.LoanSpringConfigUtil;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanLogUtil;
import com.platform.loan.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author caogu.wyp
 * @version $Id: CreateOrderProcessor.java, v 0.1 2018-05-27 上午1:32 caogu.wyp Exp $$
 */
public class CreateOrderProcessor implements Processor<CreateOrderRequest, CreateOrderResult> {

    @Override
    public void process(CreateOrderRequest createOrderRequest, CreateOrderResult result,
                        Object... others) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];
        TopUpOrderRepository topUpOrderRepository = (TopUpOrderRepository) others[1];

        LoginSession loginSession = JwtUtil.getLoginSession(httpServletRequest);

        BigDecimal amount = createOrderRequest.getCount();
        if (null == amount || !(1 == amount.compareTo(new BigDecimal(0)))) {
            throw new LoanPlatformException("充值金额不合法：" + amount);
        }
        String topUpOrderId = UUID.randomUUID().toString();
        String form = getFormString(amount, topUpOrderId);
        result.setPayForm(form);
        System.out.println("form:===================" + form);
        createOrder(topUpOrderRepository, loginSession, amount, topUpOrderId);

    }

    private void createOrder(TopUpOrderRepository topUpOrderRepository, LoginSession loginSession,
                             BigDecimal amount, String topUpOrderId) {
        TopUpOrderDO topUpOrderDO = topUpOrderRepository
            .queryTopUpOrderDOByTopUpOrderId(topUpOrderId);

        if (null != topUpOrderDO) {
            throw new LoanPlatformException("重复申请支付！");
        }
        TopUpOrderDO newTopUpOrderDO = new TopUpOrderDO();
        newTopUpOrderDO.setTopUpOrderId(topUpOrderId);
        newTopUpOrderDO.setPhoneNo(loginSession.getPhoneNo());
        newTopUpOrderDO.setStatus(TopUpOrderStatusEnum.INIT.getCode());
        newTopUpOrderDO.setAmount(amount);
        newTopUpOrderDO.setCreateTime(TimeUtil.getCurrentTimestamp());
        topUpOrderRepository.save(newTopUpOrderDO);

        System.out.println("==========newTopUpOrderDO save Success : " + newTopUpOrderDO);
    }

    private String getFormString(BigDecimal amount, String topUpOrderId) {
        AlipayClient alipayClient = new DefaultAlipayClient(
            "https://openapi.alipay.com/gateway.do", CommonConstants.ALIPAY_APPID,
            CommonConstants.PRIVATE_KEY_PKCS8, "json", "UTF-8", CommonConstants.ALIPAY_PUBLIC_KEY,
            "RSA2"); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(LoanSpringConfigUtil.LOAN_FRONT_DOMAIN + "xd_jl/main.html");
        alipayRequest.setNotifyUrl(LoanSpringConfigUtil.LOAN_SERVER_DOMAIN
                                   + "callback/alipay/pay/result");//在公共参数中设置回跳和通知地址

        //
        alipayRequest.setBizContent("{" + " \"out_trade_no\":\"" + topUpOrderId + "\","
                                    + " \"total_amount\":\"" + amount + "\","
                                    + " \"subject\":\"51充值订单\","
                                    + " \"product_code\":\"QUICK_WAP_PAY\"" + " }");//填充业务参数

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            LoanLogUtil.getLogger(CreateOrderProcessor.class).error(" 支付异常", e);
        }
        return form;
    }

}
