package cn.sisyphe.coffee.bill.domain.base.purpose;

import ch.lambdaj.group.Group;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.strategy.AbstractCastableStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.DeliveryStrategy;
import cn.sisyphe.coffee.bill.domain.plan.strategy.ReturnedStrategy;
import com.google.common.collect.ImmutableMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private final Map<BillTypeEnum, AbstractCastableStrategy> BILL_TYPE_ENUM_CASTABLE_STRATEGY_MAP = ImmutableMap.of(
            BillTypeEnum.DELIVERY, new DeliveryStrategy(), BillTypeEnum.RETURNED, new ReturnedStrategy());


    /**
     * 用途处理器
     */
    @Override
    public void handle() {
        PlanBill bill = (PlanBill) getBillService().getBill();
        //将详情安装出入站点名字进行分组
        Group<PlanBillDetail> groupedPlanBillDetail = group(bill.getBillDetails(), by(on(PlanBillDetail.class).getInOutStationCode()));
        Set<ItemPayload> payloads = new HashSet<>();

        for (String head : groupedPlanBillDetail.keySet()) {
            ItemPayload itemPayload = new ItemPayload();
            List<PlanBillDetail> planBillDetails = groupedPlanBillDetail.find(head);
            PlanBillDetail firstPlanBillDetail = planBillDetails.get(0);
            itemPayload.setOutLocation(firstPlanBillDetail.getOutLocation());
            itemPayload.setInLocation(firstPlanBillDetail.getInLocation());
            itemPayload.setCastableStrategy(BILL_TYPE_ENUM_CASTABLE_STRATEGY_MAP.get(bill.getBillType()));
            for (PlanBillDetail planBillDetail : planBillDetails) {
                itemPayload.addGood(planBillDetail.getGoods());
            }
            payloads.add(itemPayload);

        }

        for (ItemPayload payload : payloads) {
            payload.doCast(getBillService().getBillRepository());
        }

    }

}
