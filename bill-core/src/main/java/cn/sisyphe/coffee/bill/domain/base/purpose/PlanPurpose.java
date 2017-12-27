package cn.sisyphe.coffee.bill.domain.base.purpose;

import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyong on 2017/12/19 14:03
 * Description: 计划用途处理器
 * @author heyong
 */
public class PlanPurpose extends AbstractBillPurpose {

    /**
     * 用途处理器
     */
    @Override
    public void handle() {
        getBillService().getBill();

        List<ItemPayload> payloads = new ArrayList<>();

        for (ItemPayload payload : payloads) {
            payload.doCast();
        }

    }


}
