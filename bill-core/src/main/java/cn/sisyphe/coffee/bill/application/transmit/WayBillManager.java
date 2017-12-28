package cn.sisyphe.coffee.bill.application.transmit;


import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运货单
 */
@Service
public class WayBillManager {
//
//    @Autowired
//    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private WayBillRepository wayBillRepository;


    /**
     * DTO转换
     *
     * @param editWayBillDTO
     * @return
     */

    private WayBill convertBillDTOToWayBill(EditWayBillDTO editWayBillDTO) {
        WayBill wayBill = new WayBill();
        //
        wayBill.setBillCode(editWayBillDTO.getWayBillCode());
        return wayBill;
    }

    /**
     * @param editWayBillDTO
     * @throws DataException
     */
    private void checkParams(EditWayBillDTO editWayBillDTO) throws DataException {

        if (editWayBillDTO == null) {
            throw new DataException("", "");
        }
    }

    /**
     * 创建运货单
     *
     * @param wayBill
     * @throws DataException
     */
    public void createWayBill(WayBill wayBill) throws DataException {
        // 3.创建单据服务


        System.out.println(wayBill);

        //工厂
        new BillFactory().createBill(BillTypeEnum.TRANSMIT);
        // 单据用途
        wayBill.setBillPurpose(BillPurposeEnum.OutStorage);


        BillServiceFactory serviceFactory = new BillServiceFactory();
        AbstractBillService billService = serviceFactory.createBillService(wayBill);
        // 4.处理保存动作
        billService.dispose(new SaveBehavior());


        // 5.设置数据仓库和保存单据
        billService.setBillRepository(wayBillRepository);
        billService.save();

        // 6.发送事件
        //billService.sendEvent(applicationEventPublisher);

    }

}
