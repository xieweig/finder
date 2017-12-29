package cn.sisyphe.coffee.bill.domain.base.purpose;

import ch.lambdaj.function.closure.Switcher;
import ch.lambdaj.group.Group;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayloadDetail;
import cn.sisyphe.coffee.bill.domain.plan.strategy.AbstractCastableStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.DeliveryStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.ReturnedStrategy;

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
     * 用途处理器
     */
    @Override
    public void handle() {
        PlanBill bill = (PlanBill) getBillService().getBill();
        //将详情安装出入站点名字进行分组
        Group<PlanBillDetail> groupedPlanBillDetail = group(bill.getBillDetails(), by(on(PlanBillDetail.class).getInOutStationCode()));
        Set<PlanBillPayload> payloads = new HashSet<>();

        int index = 0;
        for (String head : groupedPlanBillDetail.keySet()) {
            PlanBillPayload planBillPayload = new PlanBillPayload();
            List<PlanBillDetail> planBillDetails = groupedPlanBillDetail.find(head);
            PlanBillDetail firstPlanBillDetail = planBillDetails.get(0);
            planBillPayload.setOutLocation(firstPlanBillDetail.getOutLocation());
            planBillPayload.setInLocation(firstPlanBillDetail.getInLocation());
            planBillPayload.setCastableStrategy(getSpecStrategy(bill.getSpecificBillType()));
            planBillPayload.setBasicEnum(bill.getBasicEnum());
            planBillPayload.setMemo(bill.getMemo());
            planBillPayload.setParentBillCode(bill.getBillCode());
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

        for (PlanBillPayload payload : payloads) {
            payload.doCast(getBillService().getBillRepository());
        }

    }

    private AbstractCastableStrategy getSpecStrategy(BillTypeEnum billTypeEnum) {
        Switcher<AbstractCastableStrategy> switcher = new Switcher<AbstractCastableStrategy>()
                .addCase(BillTypeEnum.DELIVERY, new DeliveryStrategy())
                .addCase(BillTypeEnum.RETURNED, new ReturnedStrategy());

        return switcher.exec(billTypeEnum);
    }

}
