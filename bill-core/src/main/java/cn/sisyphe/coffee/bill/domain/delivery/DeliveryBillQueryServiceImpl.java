package cn.sisyphe.coffee.bill.domain.delivery;

import cn.sisyphe.coffee.bill.domain.plan.PlanBillQueryService;
import cn.sisyphe.coffee.bill.infrastructure.delivery.DeliveryBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


/**
 * 查询配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 11:08
 **/
@Service
public class DeliveryBillQueryServiceImpl implements DeliveryBillQueryService {


    @Autowired
    private DeliveryBillRepository deliveryBillRepository;

    @Autowired
    PlanBillQueryService planBillQueryService;

    /**
     * 根据单号查询单个配送单
     *
     * @param billCode
     * @return
     */
    @Override
    public DeliveryBill findOneByBillCode(String billCode) {

        return deliveryBillRepository.findOneByBillCode(billCode);
    }


    /**
     * 多条件分页查询配送计划单
     *
     * @param conditionQueryPlanBill
     * @return
     */
    @Override
    public Page<DeliveryBill> findPageByCondition(ConditionQueryPlanBill conditionQueryPlanBill) {
        //return planBillQueryService.findPageByCondition(conditionQueryPlanBill);

        return null;
    }


}
