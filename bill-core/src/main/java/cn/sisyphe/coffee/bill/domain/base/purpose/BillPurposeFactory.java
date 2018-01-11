package cn.sisyphe.coffee.bill.domain.base.purpose;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.InStorage;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.OutStorage;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.Plan;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.moveStorage;


/**
 * Created by heyong on 2017/12/19 13:58
 * Description: 单据用途处理器工厂类
 *
 * @author heyong
 */
public class BillPurposeFactory {

    public static BillPurpose createPurpose(BillPurposeEnum billPurposeEnum) {
        return new Switcher<BillPurpose>()
                .addCase(Plan, new PlanPurpose())
                .addCase(OutStorage, new OutStoragePurpose())
                .addCase(InStorage, new InStoragePurpose())
                .addCase(moveStorage, new MoveStoragePurpose())
                .exec(billPurposeEnum);
    }
}
