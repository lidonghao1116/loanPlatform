/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.constant.ProcessResultEnum;
import com.platform.loan.dao.GrabRecordRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.LoginSession;
import com.platform.loan.pojo.modle.GrabRecordDO;
import com.platform.loan.pojo.request.ProcessOrderRequest;
import com.platform.loan.pojo.result.ProcessOrderResult;
import com.platform.loan.template.Processor;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author caogu.wyp
 * @version $Id: ProcessOrderProcessor.java, v 0.1 2018-05-24 上午1:07 caogu.wyp Exp $$
 */
public class ProcessOrderProcessor implements Processor<ProcessOrderRequest, ProcessOrderResult> {
    @Override
    public void process(ProcessOrderRequest processOrderRequest,
                        ProcessOrderResult processOrderResult, Object... others) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];
        GrabRecordRepository grabRecordRepository = (GrabRecordRepository) others[1];
        LoginSession loginSession = JwtUtil.getLoginSession(httpServletRequest);

        GrabRecordDO grabRecordDO = getGrabRecordDO(processOrderRequest, grabRecordRepository,
            loginSession);

        ProcessResultEnum processResultEnum = getProcessResultEnum(processOrderRequest);

        grabRecordDO.setProcessResult(processOrderRequest.getProcessorResult());
        grabRecordDO.setStatus(processResultEnum.getStatus());
        grabRecordRepository.save(grabRecordDO);
    }

    private ProcessResultEnum getProcessResultEnum(ProcessOrderRequest processOrderRequest) {
        ProcessResultEnum processResultEnum = ProcessResultEnum.getByDesc(processOrderRequest
            .getProcessorResult());

        if (null == processResultEnum) {
            throw new LoanPlatformException("未定义的处理结果：" + processOrderRequest.getProcessorResult());

        }
        return processResultEnum;
    }

    private GrabRecordDO getGrabRecordDO(ProcessOrderRequest processOrderRequest,
                                         GrabRecordRepository grabRecordRepository,
                                         LoginSession loginSession) {
        GrabRecordDO grabRecordDO = grabRecordRepository.findGrabRecordDO(
            processOrderRequest.getOrderId(), loginSession.getPhoneNo());

        if (null == grabRecordDO) {
            throw new LoanPlatformException("处理订单失败，数据库中该用户没有这笔订单，order："
                                            + processOrderRequest.getOrderId() + "loginUserPhone:"
                                            + loginSession.getPhoneNo());
        }
        return grabRecordDO;
    }
}
