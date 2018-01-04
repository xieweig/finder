package cn.sisyphe.coffee.bill.domain.base.purpose;

import ch.lambdaj.function.closure.Switcher;
import ch.lambdaj.group.Group;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayloadDetail;
import cn.sisyphe.coffee.bill.domain.plan.strategy.AbstractCastableStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.AdjustStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.DeliveryStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.RestockStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.ReturnedStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.group.Groups.by;

/**
 * Created by heyong on 2017/12/19 14:03
 * Description: 计划用途处理器
 *
 * @author heyong
 */
public class PlanPurpose extends AbstractBillPurpose {

    /**
     * 用途处理器，根据站点对的数量，判断有多少个子计划单
     */
    @SuppressWarnings("unchecked")
    @Override
    public void handle() {
        PlanBill bill = (PlanBill) getBillService().getBill();
        //将详情按照出入站点名字进行分组
        Group<PlanBillDetail> groupedPlanBillDetail = group(bill.getBillDetails(), by(on(PlanBillDetail.class).getInOutStationCode()));
        Set<PlanBillPayload> payloads = new HashSet<>();

        int index = 0;
        for (String head : groupedPlanBillDetail.keySet()) {
            PlanBillPayload planBillPayload = new PlanBillPayload();
            List<PlanBillDetail> planBillDetails = groupedPlanBillDetail.find(head);
            PlanBillDetail firstPlanBillDetail = planBillDetails.get(0);
            planBillPayload.setOutLocation(firstPlanBillDetail.getOutLocation());
            planBillPayload.setInLocation(firstPlanBillDetail.getInLocation());
            planBillPayload.setTransferLocation(firstPlanBillDetail.getTransferLocation());
            planBillPayload.setBillType(BillTypeEnum.PLAN);
            planBillPayload.setCastableStrategy(getSpecStrategy(bill.getSpecificBillType()));
            planBillPayload.setBasicEnum(bill.getBasicEnum());
            planBillPayload.setMemo(bill.getMemo());
            planBillPayload.setParentBillCode(bill.getBillCode());
            //TODO 子计划单号的规则还会更新，原型还没有确定
            planBillPayload.setBillCode(bill.getBillCode() + "_" + index);
            for (PlanBillDetail planBillDetail : planBillDetails) {
                PlanBillPayloadDetail planBillPayloadDetail = new PlanBillPayloadDetail();
                planBillPayloadDetail.setAmount(planBillDetail.getAmount());
                planBillPayloadDetail.setGoods(planBillDetail.getGoods());
                planBillPayload.addGoodsDetail(planBillPayloadDetail);
            }
            payloads.add(planBillPayload);
            index++;

        }
        List<PlanBill> splitedPlanBills = new ArrayList<>();
        for (PlanBillPayload payload : payloads) {
            splitedPlanBills.addAll(payload.doCast());
        }
        getBillService().getBillRepository().save(splitedPlanBills);

    }

    /**
     * 根据不同的单据类型选择不同的策略
     *
     * @param billTypeEnum 单据类型
     * @return 选择出的策略
     */
    private AbstractCastableStrategy getSpecStrategy(BillTypeEnum billTypeEnum) {
        Switcher<AbstractCastableStrategy> switcher = new Switcher<AbstractCastableStrategy>()
                .addCase(BillTypeEnum.DELIVERY, new DeliveryStrategy())
                .addCase(BillTypeEnum.RETURNED, new ReturnedStrategy())
                .addCase(BillTypeEnum.ADJUST, new AdjustStrategy())
                .addCase(BillTypeEnum.RESTOCK, new RestockStrategy());

        return switcher.exec(billTypeEnum);
    }

}
