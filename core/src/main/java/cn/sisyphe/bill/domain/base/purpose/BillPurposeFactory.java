package cn.sisyphe.bill.domain.base.purpose;


import cn.sisyphe.bill.domain.base.model.enums.BillPurposeEnum;

/**
 * Created by heyong on 2017/12/19 13:58
 * Description: 单据用途处理器工厂类
 * @author heyong
 */
public class BillPurposeFactory {

    public static BillPurpose createPurpose(BillPurposeEnum billPurposeEnum) {
        BillPurpose purpose = null;

        switch (billPurposeEnum) {
            case Plan:
                purpose = new PlanPurpose();
                break;
            case OutStorage:
                purpose = new OutStoragePurpose();
                break;
            case InStorage:
                purpose = new InStoragePurpose();
                break;
            default:
                break;
        }

        return purpose;
    }
}
