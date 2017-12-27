package cn.sisyphe.bill.domain.base.model;


import cn.sisyphe.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.bill.domain.delivery.purchase.PurchaseBill;

/**
 * Created by heyong on 2017/12/25 18:21
 * Description: 单据工厂类
 * @author heyong
 */
public class BillFactory {

    /**
     * 创建单据
     * @param billType
     * @return
     */
    public Bill createBill(BillTypeEnum billType) {
        Bill bill = null;

        switch (billType) {
            case PURCHASE:
                bill = new PurchaseBill();
                break;
            case DELIVERY:
                bill = new DeliveryBill();
                break;
            default:
                break;
        }

        return bill;
    }
}
