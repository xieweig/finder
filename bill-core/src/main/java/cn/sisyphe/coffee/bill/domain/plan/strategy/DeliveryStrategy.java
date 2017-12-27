package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;
import cn.sisyphe.coffee.bill.infrastructure.delivery.DeliveryBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ncmao
 * 计划单转成配送单策略
 */

@Service
public class DeliveryStrategy implements CastableStrategy {

    @Autowired
    private DeliveryBillRepository deliveryBillRepository;

    /**
     * 将计划单切分为配送单
     *
     * @param itemPayload 计划单
     */

    @Override
    public void cast(ItemPayload itemPayload) {
        DeliveryBill deliveryBill = pack(itemPayload);

        BillServiceFactory serviceFactory = new BillServiceFactory();
        AbstractBillService billService = serviceFactory.createBillService(deliveryBill);
        billService.dispose(new SaveBehavior());
        billService.setBillRepository(deliveryBillRepository);
        billService.save();


    }

    private DeliveryBill pack(ItemPayload itemPayload) {
        DeliveryBill deliveryBill = (DeliveryBill) new BillFactory().createBill(BillTypeEnum.DELIVERY);
        //出站站点
        deliveryBill.setOutLocation(itemPayload.getOutStation());

        //入站站点
        deliveryBill.setInLocation(itemPayload.getInStation());
        deliveryBill.setBillPurpose(BillPurposeEnum.Plan);

        Set<DeliveryBillDetail> billDetails = new HashSet<>();
        for (AbstractGoods goods : itemPayload.getGoods()) {
            DeliveryBillDetail deliveryBillDetail = new DeliveryBillDetail();
            deliveryBillDetail.setGoods(goods);
            billDetails.add(deliveryBillDetail);
        }
        deliveryBill.setBillDetails(billDetails);
        return deliveryBill;
    }
}
