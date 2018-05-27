/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.loan.constant.TopUpOrderStatusEnum;
import com.platform.loan.dao.ManagerRepository;
import com.platform.loan.dao.TopUpOrderRepository;
import com.platform.loan.pojo.modle.CreditManagerDO;
import com.platform.loan.pojo.modle.TopUpOrderDO;
import com.platform.loan.util.LoanLogUtil;
import com.platform.loan.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author caogu.wyp
 * @version $Id: AlipayCallBackController.java, v 0.1 2018-05-27 下午1:10 caogu.wyp Exp $$
 */
@Controller
public class AlipayCallBackController {

    @Autowired
    private TopUpOrderRepository topUpOrderRepository;

    @Autowired
    private ManagerRepository    managerRepository;

    @RequestMapping(value = "/callback/alipay/pay/result", method = RequestMethod.POST)
    public void alipayPayResult(HttpServletRequest httpServletRequest, HttpServletResponse response)
                                                                                                    throws IOException {
        LoanLogUtil.getLogger(AlipayCallBackController.class).info(
            "/callback/alipay/pay/result==============" + httpServletRequest);

        Map<String, String> returnMap = getStringStringMap(httpServletRequest);

        LoanLogUtil.getLogger(AlipayCallBackController.class).info(
            "alipay return returnMap :" + JSONObject.toJSONString(returnMap));

        String topUpOrderId = returnMap.get("out_trade_no");
        BigDecimal amount = new BigDecimal(returnMap.get("buyer_pay_amount"));
        //先查询
        TopUpOrderDO topUpOrderDO = topUpOrderRepository
            .queryTopUpOrderDOByTopUpOrderId(topUpOrderId);

        if (null == topUpOrderDO) {

            LoanLogUtil.getLogger(AlipayCallBackController.class).error(
                "===================loan平台中未找到该订单" + topUpOrderId);
            return;
        }

        if (!TopUpOrderStatusEnum.INIT.getCode().equals(topUpOrderDO.getStatus())) {
            LoanLogUtil.getLogger(AlipayCallBackController.class).error(
                "===================loan平台中订单的状态非INIT，无法完成充值，请人工核对。" + topUpOrderDO.getStatus()
                        + "");
            return;
        }

        if (!(0 == amount.compareTo(topUpOrderDO.getAmount()))) {
            LoanLogUtil.getLogger(AlipayCallBackController.class).error(
                "===================loan平台中INIT的订单金额和充值金额对不上，请人工核对，支付金额为：" + amount
                        + "，INIT中订单金额为：" + topUpOrderDO.getAmount());
            return;
        }

        CreditManagerDO creditManagerDO = managerRepository
            .findCreditManagerDOByPhoneNo(topUpOrderDO.getPhoneNo());

        if (null == creditManagerDO) {

            LoanLogUtil.getLogger(AlipayCallBackController.class).error(
                "===================在数据库中找不到充值信贷经理的账户，手机号为：" + topUpOrderDO.getPhoneNo());

            return;
        }

        try {
            topUpOrderDO.setAccountingTime(TimeUtil.getCurrentTimestamp());
            topUpOrderDO.setModifyTime(TimeUtil.getCurrentTimestamp());
            topUpOrderDO.setAlipayCallBackData(JSONObject.toJSONString(returnMap));
            topUpOrderDO.setStatus(TopUpOrderStatusEnum.PAY_SUCCESS.getCode());
            topUpOrderRepository.save(topUpOrderDO);
            //加钱
            if (null == creditManagerDO.getBalance()) {
                creditManagerDO.setBalance(amount);
            } else {
                creditManagerDO.setBalance(amount.add(creditManagerDO.getBalance()));
            }

            managerRepository.save(creditManagerDO);
        } catch (Exception e) {
            LoanLogUtil.getLogger(AlipayCallBackController.class).error("===================加钱失败：",
                e);
        }

        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write("success");
        response.getWriter().flush();
        response.getWriter().close();
    }

    private Map<String, String> getStringStringMap(HttpServletRequest httpServletRequest) {
        // 参数Map
        Map properties = httpServletRequest.getParameterMap();
        // 返回值Map
        Map<String, String> returnMap = new HashMap<String, String>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
}
