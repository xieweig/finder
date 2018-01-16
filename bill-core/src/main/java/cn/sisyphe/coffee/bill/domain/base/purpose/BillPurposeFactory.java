package cn.sisyphe.coffee.bill.domain.base.purpose;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.IN_STORAGE;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.OUT_STORAGE;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.PLAN;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.MOVE_STORAGE;


/**
 * Created by heyong on 2017/12/19 13:58
 * Description: 单据用途处理器工厂类
 *
 * @author heyong
 */
public class BillPurposeFactory {

    public static BillPurpose createPurpose(BillPurposeEnum billPurposeEnum) {
        return new Switcher<BillPurpose>()
                .addCase(PLAN, new PlanPurpose())
                .addCase(OUT_STORAGE, new OutStoragePurpose())
                .addCase(IN_STORAGE, new InStoragePurpose())
                .addCase(MOVE_STORAGE, new MoveStoragePurpose())
                .exec(billPurposeEnum);
    }
}
