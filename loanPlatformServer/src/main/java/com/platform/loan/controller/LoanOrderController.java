package com.platform.loan.controller;

import com.platform.loan.dao.BorrowerOrderRepository;
import com.platform.loan.dao.BorrowerRepository;
import com.platform.loan.pojo.LoanOrderViewModel;
import com.platform.loan.pojo.modle.BorrowerDO;
import com.platform.loan.pojo.modle.BorrowerOrderDO;
import com.platform.loan.pojo.request.LoanOrderRequest;
import com.platform.loan.pojo.request.LoanOrderResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanOrderController.java, v 0.1 2018-05-20 下午9:08 caogu.wyp Exp $$
 */
@RestController
public class LoanOrderController {

    @Autowired
    private BorrowerOrderRepository borrowerOrderRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @ApiOperation(value = "查询可抢状态的订单", notes = "此查询接口不需要做登陆校验")
    @RequestMapping(value = "/api/manager/order/grab", method = RequestMethod.GET)
    public LoanOrderResult queryInitLoanOrderList(LoanOrderRequest loanOrderRequest){

        System.out.println(loanOrderRequest);
        /**
         * 1 查询的订单，状态都是ENABLE_GRAB的，可抢状态
         * 2 数据脱敏
         */
        LoanOrderResult result = new LoanOrderResult();

        result.setPageNum(1);

        Iterable<BorrowerOrderDO> borrowerOrderList = borrowerOrderRepository.findAll();

        List<LoanOrderViewModel> list = new ArrayList<>();

        borrowerOrderList.forEach(borrowerOrderDO -> {

            LoanOrderViewModel model = new LoanOrderViewModel();
            model.setApplyTime(borrowerOrderDO.getCreateTime().toString());
            model.setMaskPhoneNo(borrowerOrderDO.getBorrowerPhoneNo()+"***");
            model.setPrice(borrowerOrderDO.getPrice().toString());
            model.setOrderType(borrowerOrderDO.getOrderType());

            //查人信息
            BorrowerDO borrowerDO = borrowerRepository.findBorrowerDoByPhoneNo(borrowerOrderDO.getBorrowerPhoneNo());
            if(null != borrowerDO){
                model.setMaskBorrowerName(borrowerDO.getName()+"***");
                model.setProfession(borrowerDO.getProfession());
            }
            list.add(model);
        });

        result.setViewList(list);

        return  result;
    }


}
