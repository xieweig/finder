package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运货单
 * Created by Administrator on 2017/12/28.
 */
@Service
public class IWayBillServiceImpl implements IWayBillService {

    @Autowired
    private WayBillRepository wayBillRepository;

    /**
     * @param wayBill
     */
    @Override
    public void createWayBill(WayBill wayBill) {



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

    }
}
