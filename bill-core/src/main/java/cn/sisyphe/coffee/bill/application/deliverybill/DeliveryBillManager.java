package cn.sisyphe.coffee.bill.application.deliverybill;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillQueryService;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryBillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/4.
 */
@Service
public class DeliveryBillManager extends AbstractBillManager<PlanBill> {

    @Autowired
    private DeliveryBillQueryService deliveryBillQueryService;


    @Autowired
    public DeliveryBillManager(BillRepository<PlanBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }


    /**
     * 根据单号查看单据
     *
     * @param billCode
     * @return
     */
    public DeliveryBillDTO findOneByBillCode(String billCode) {

        DeliveryBillDTO deliveryBillDTO = new DeliveryBillDTO();
        DeliveryBill deliveryBill = deliveryBillQueryService.findOneByBillCode(billCode);
        return deliveryBillDTO.convertBillToDTO(deliveryBill);
    }
}
