package com.platform.loan.template.processor;

import com.platform.loan.constant.BorrowerOrderStatusEnum;
import com.platform.loan.constant.LoanTypeEnum;
import com.platform.loan.dao.BorrowerOrderRepository;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.BorrowerOrderDO;
import com.platform.loan.pojo.request.LoanApplyRequest;
import com.platform.loan.pojo.result.LoanApplyResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanPriceUtil;
import com.platform.loan.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author caogu.wyp
 * @version $Id: LoanApplyProcessor.java, v 0.1 2018-05-12 下午3:50 caogu.wyp Exp $$
 */
public class LoanApplyProcessor implements Processor<LoanApplyRequest, LoanApplyResult> {

    @Override
    public void process(LoanApplyRequest request, LoanApplyResult loanApplyResult, Object... others)
                                                                                                    throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) others[0];
        BorrowerRepository borrowerRepository = (BorrowerRepository) others[1];
        BorrowerOrderRepository borrowerOrderRepository = (BorrowerOrderRepository) others[2];

        String phoneNo = JwtUtil.getLoginSession(httpServletRequest).getPhoneNo();
        //保存一张新订单
        saveOrder(request, borrowerOrderRepository, phoneNo);

        if (LoanTypeEnum.CREDIT_LOAN.getLoanName().equals(request.getLoanType())) {
            saveCREDIT_LOANBorrowerInfo(request, borrowerRepository, phoneNo);
        }

        if (LoanTypeEnum.WEI_LI_DAI.getLoanName().equals(request.getLoanType())) {

            saveWEI_LI_DAIBorrowerInfo(request, borrowerRepository, phoneNo);
        }

    }

    private void saveWEI_LI_DAIBorrowerInfo(LoanApplyRequest request,
                                            BorrowerRepository borrowerRepository, String phoneNo) {
        //信用贷款，需要更新下个人信息
        BorrowerDO borrower = borrowerRepository.findBorrowerDoByPhoneNo(phoneNo);
        borrower.setWeiLiDaiTotal(request.getWeiLiDaiTotal());
        borrower.setWeiLiDaiKeJie(request.getWeiLiDaiKeJie());

        borrowerRepository.save(borrower);
    }

    private void saveCREDIT_LOANBorrowerInfo(LoanApplyRequest request,
                                             BorrowerRepository borrowerRepository, String phoneNo) {
        //信用贷款，需要更新下个人信息
        BorrowerDO borrower = borrowerRepository.findBorrowerDoByPhoneNo(phoneNo);

        borrower.setModifyTime(TimeUtil.getCurrentTimestamp());
        borrower.setEduLevel(request.getEduLevel());
        borrower.setProfession(request.getProfession());
        borrower.setMonthlyIncome(request.getMonthlyIncome());
        borrower.setIncomeType(request.getIncomeType());
        borrower.setCreditRecord(request.getCreditRecord());
        borrower.setCreditLimit(request.getCreditLimit());
        borrower.setHouseInfo(request.getHouseInfo());
        borrower.setCarInfo(request.getCarInfo());
        borrower.setInsuranceCompany(request.getInsuranceCompany());
        borrower.setInsuranceCoverage(request.getInsuranceCoverage());
        borrower.setPersonalnsurance(request.getPersonalnsurance());

        borrowerRepository.save(borrower);
    }

    private void saveOrder(LoanApplyRequest request,
                           BorrowerOrderRepository borrowerOrderRepository, String phoneNo) {
        BorrowerOrderDO borrowerOrderDO = new BorrowerOrderDO();
        borrowerOrderDO.setBorrowerPhoneNo(phoneNo);
        borrowerOrderDO.setCreateTime(TimeUtil.getCurrentTimestamp());
        borrowerOrderDO.setModifyTime(TimeUtil.getCurrentTimestamp());
        borrowerOrderDO.setLoanCity(request.getLoanCity());
        borrowerOrderDO.setLoanDeadline(request.getLoanDeadline());
        borrowerOrderDO.setLoanLimit(request.getLoanLimit());
        borrowerOrderDO.setLoanPurpose(request.getLoanPurpose());
        borrowerOrderDO.setOrderType(request.getLoanType());
        borrowerOrderDO.setPrice(LoanPriceUtil.getPrice(request));
        borrowerOrderDO.setOrderStatus(BorrowerOrderStatusEnum.AUDIT.getStatus());
        borrowerOrderRepository.save(borrowerOrderDO);
    }
}
