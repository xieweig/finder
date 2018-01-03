package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.CargoDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.SaveByCargoDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.SaveByRawMaterialDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.SaveBySelfDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;


/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
@Service
public class RestockBillManager {
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    @Resource
    private RestockBillRepository restockBillRepository;

    private BillFactory billFactory = new BillFactory();

    private BillServiceFactory billServiceFactory = new BillServiceFactory();

    public void saveBySelf(SaveBySelfDTO saveBySelfDTO) {
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        BeanUtils.copyProperties(saveBySelfDTO, restockBill);
        restockBill.setBillState(BillStateEnum.SAVED);
        restockBill.setBillType(BillTypeEnum.RESTOCK);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        // restockBill.setBillDetails(new HashSet<>());

        RestockBillService billService = (RestockBillService) billServiceFactory.createBillService(restockBill);
        billService.setBillBehavior(new SaveBehavior());
        billService.setBillRepository(restockBillRepository);
        billService.save();
        billService.sendEvent(applicationEventPublisher);


        //   BillServiceFactory factory = new

    }

    public void saveByCargo(SaveByCargoDTO saveByCargoDTO) {

    }

    public void saveByRawMaterial(SaveByRawMaterialDTO saveByRawMaterialDTO) {

    }

    public Page<CargoDTO> queryCargo(String cargoName, String cargoCode) {


        return null;
    }


}
