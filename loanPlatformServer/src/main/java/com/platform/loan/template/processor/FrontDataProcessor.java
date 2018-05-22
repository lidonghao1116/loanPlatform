/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template.processor;

import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.FrontDataResult;
import com.platform.loan.template.Processor;

import java.util.Arrays;

/**
 *
 * @author caogu.wyp
 * @version $Id: FrontDataProcessor.java, v 0.1 2018-05-16 上午12:56 caogu.wyp Exp $$
 */
public class FrontDataProcessor implements Processor<BaseRequest, FrontDataResult> {
    @Override
    public void process(BaseRequest request, FrontDataResult frontDataResult, Object... others)
                                                                                               throws Exception {

        frontDataResult.setEduLevelList(Arrays.asList("高中", "初中", "中专", "大专", "本科及以上"));
        frontDataResult.setProfessionList(Arrays.asList("上班族", "自由职业", "私营个体", "事业单位"));
        frontDataResult.setIncomeTypeList(Arrays.asList("现金发放", "转帐工资", "银行代发"));
        frontDataResult.setCreditRecordList(Arrays.asList("信用良好","无信用卡或贷款", "1年内逾期超过3次或90天",
            "1年内逾期少于3次且少于90天"));
        frontDataResult.setCreditLimitList(Arrays.asList("无", "3000元以下", "3000～10000",
            "10000~30000", "30000以上"));
        frontDataResult.setHouseInfoList(Arrays.asList("无房产", "有房产", "有房产不接受抵押", "有房产接受抵押"));
        frontDataResult.setCarInfoList(Arrays.asList("有车", "无车", "有车可以接受抵押","有车不接受抵押"));
        frontDataResult.setPersonalnsuranceList(Arrays.asList("无", "投保人寿且投保2年以下", "投保人寿且投保2年以上"));
        frontDataResult.setInsuranceCompanyList(Arrays.asList("安邦保险", "中国平安", "前海人寿", "中国人寿",
            "太平洋保险", "泰康人寿", "新华保险", "阳光保险", "招商信诺", "中国天平", "其他"));
        frontDataResult.setInsuranceCoverageList(Arrays.asList("5万及以下", "5万至20万", "20万以上"));

    }
}
