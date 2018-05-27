/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.platform.loan.constant.CommonConstants;
import com.platform.loan.pojo.request.CreateOrderRequest;
import com.platform.loan.pojo.result.BaseResult;
import com.platform.loan.service.LoanSpringConfigUtil;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanLogUtil;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author caogu.wyp
 * @version $Id: CreateOrderProcessor.java, v 0.1 2018-05-27 上午1:32 caogu.wyp Exp $$
 */
public class CreateOrderProcessor implements Processor<CreateOrderRequest, BaseResult> {
    @Override
    public void process(CreateOrderRequest createOrderRequest, BaseResult result, Object... others)
                                                                                                   throws Exception {

        HttpServletResponse httpServletResponse = (HttpServletResponse) others[0];

        //要充值的个数

        BigDecimal amount = createOrderRequest.getCount();

        if (null == amount) {
            return;
        }
        UUID topUpOrderId = UUID.randomUUID();

        //先落一条单据，状态为init，支付完之后，变成success

        AlipayClient alipayClient = new DefaultAlipayClient(
            "https://openapi.alipay.com/gateway.do", CommonConstants.ALIPAY_APPID,
            CommonConstants.PRIVATE_KEY_PKCS8, "json", "UTF-8", CommonConstants.ALIPAY_PUBLIC_KEY,
            "RSA2"); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest
            .setReturnUrl(LoanSpringConfigUtil.LOAN_FRONT_DOMAIN + "xd_jl/pay_result.html");
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
        httpServletResponse.setContentType("text/html;charset=" + "UTF-8");
        httpServletResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();

    }

}
