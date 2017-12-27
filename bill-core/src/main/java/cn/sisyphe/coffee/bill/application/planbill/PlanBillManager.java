package cn.sisyphe.coffee.bill.application.planbill;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SubmitBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 * @Date 2017/12/27 11:36
 * @description
 */
@Service
public class PlanBillManager {

    @Autowired
    private PlanBillRepository planBillRepository;

    /**
     * 创建计划单
     *
     * @param planBillDTO 计划单DTO
     */

    public void create(PlanBillDTO planBillDTO) {

        Bill planBill = new BillFactory().createBill(BillTypeEnum.PLAN);
        //TODO map数据
        planBill.setBillPurpose(BillPurposeEnum.Plan);

        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.dispose(new SaveBehavior());
        billService.setBillRepository(planBillRepository);
        billService.save();

//        Group<TradeRecord> groupInOutFlag = group(currentTradeRecords, by(on(TradeRecord.class).getInOutFlag()));

    }

    public void submit(PlanBillDTO planBillDTO) {
        PlanBill planBill = planBillRepository.findByBillCode(planBillDTO.getBillCode());
        //TODO map前端数据
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.dispose(new SubmitBehavior());
        billService.setBillRepository(planBillRepository);
        billService.save();
    }

    public void pass(PlanBillDTO planBillDTO) {
        PlanBill planBill = planBillRepository.findByBillCode(planBillDTO.getBillCode());
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.dispose(new PurposeBehavior());
        billService.setBillRepository(planBillRepository);
        billService.save();
    }
}
