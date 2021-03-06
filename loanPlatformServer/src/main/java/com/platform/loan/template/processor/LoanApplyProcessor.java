package com.platform.loan.template.processor;

import com.platform.loan.constant.LoanTypeEnum;
import com.platform.loan.constant.ResultCodeEnum;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.dao.OrderRepository;
import com.platform.loan.dao.SysConfigRepository;
import com.platform.loan.exception.LoanPlatformException;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.OrderDO;
import com.platform.loan.pojo.request.LoanApplyRequest;
import com.platform.loan.pojo.result.LoanApplyResult;
import com.platform.loan.template.Processor;
import com.platform.loan.util.LoanUtil;
import com.platform.loan.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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
        OrderRepository borrowerOrderRepository = (OrderRepository) others[2];
        SysConfigRepository sysConfigRepository = (SysConfigRepository) others[3];

        String phoneNo = JwtUtil.getLoginSession(httpServletRequest).getPhoneNo();

        //如果申请过类似订单，无法继续重复申请
        if (isRepeat(borrowerOrderRepository, phoneNo, request.getLoanType())) {
            throw new LoanPlatformException(ResultCodeEnum.LOAN_REPEAT, "重复申请");
        }

        //保存一张新订单
        saveOrder(request, borrowerOrderRepository, phoneNo, sysConfigRepository);

        if (LoanTypeEnum.CREDIT_LOAN.getLoanName().equals(request.getLoanType())) {
            saveCREDIT_LOANBorrowerInfo(request, borrowerRepository, phoneNo);
        }

        if (LoanTypeEnum.WEI_LI_DAI.getLoanName().equals(request.getLoanType())) {

            saveWEI_LI_DAIBorrowerInfo(request, borrowerRepository, phoneNo);
        }

    }

    private boolean isRepeat(OrderRepository orderRepository, String phoneNo, String loanTypeCode) {

        List<OrderDO> orders = orderRepository.findOrderDO(phoneNo, loanTypeCode);

        if (orders != null && orders.size() > 0) {
            return true;
        }
        return false;
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

    private void saveOrder(LoanApplyRequest request, OrderRepository borrowerOrderRepository,
                           String phoneNo, SysConfigRepository sysConfigRepository) {
        OrderDO borrowerOrderDO = new OrderDO();
        borrowerOrderDO.setOrderId(UUID.randomUUID().toString());
        borrowerOrderDO.setBorrowerPhoneNo(phoneNo);
        borrowerOrderDO.setCreateTime(TimeUtil.getCurrentTimestamp());
        borrowerOrderDO.setModifyTime(TimeUtil.getCurrentTimestamp());
        borrowerOrderDO.setLoanCity(request.getLoanCity());
        borrowerOrderDO.setLoanDeadline(request.getLoanDeadline());
        borrowerOrderDO.setLoanLimit(request.getLoanLimit());
        borrowerOrderDO.setLoanPurpose(request.getLoanPurpose());
        borrowerOrderDO.setLoanType(request.getLoanType());
        borrowerOrderDO.setPrice(LoanUtil.getPrice(request, sysConfigRepository));
        borrowerOrderRepository.save(borrowerOrderDO);
    }

}
