package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.db.DbStation;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
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
import java.util.Set;


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
    //@config @bean @resource
    private BillFactory billFactory = new BillFactory();

    private BillServiceFactory billServiceFactory = new BillServiceFactory();

    private void save_init(RestockBill restockBill){
        //restockBill.setBillState(BillStateEnum.SAVED);这一步会在set Behavior中
        // 初始化单据为：退库 出库单
        restockBill.setBillType(BillTypeEnum.RESTOCK);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        //根据单据生成对应的BillServer;
        RestockBillService billService = (RestockBillService) billServiceFactory.createBillService(restockBill);
        //会setBillBehavior;
        billService.setBillBehavior(new SaveBehavior());
        billService.setBillRepository(restockBillRepository);
        billService.save();
        billService.sendEvent(applicationEventPublisher);

    }
    public void saveBySelf(SaveBySelfDTO saveBySelfDTO) {
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        BeanUtils.copyProperties(saveBySelfDTO, restockBill);
        this.save_init(restockBill);

    }

    public void saveByCargo(SaveByCargoDTO saveByCargoDTO) {

    }

    public void saveByRawMaterial(SaveByRawMaterialDTO saveByRawMaterialDTO) {
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        /**
         *
         *notes :BeanUtils.copyProperties(saveByRawMaterialDTO, restockBill);
         *    内部转换属性方法
         */
        this.copyPropertiesFrom(saveByRawMaterialDTO, restockBill);
        this.save_init(restockBill);

    }

    public Page<CargoDTO> queryCargo(String cargoName, String cargoCode) {


        return null;
    }
    private Set<RestockBillDetail> listToSet(List<RestockBillDetail> list){
        return null;
    }
    private void copyPropertiesFrom(SaveByRawMaterialDTO saveByRawMaterialDTO, RestockBill restockBill){

        restockBill.setBillCode(saveByRawMaterialDTO.getBillCode());
        //下句不正确 应该是存取入库代码
        restockBill.setRootCode(saveByRawMaterialDTO.getStorageCode());

        restockBill.setRemarks(saveByRawMaterialDTO.getRemarks());

        restockBill.setCreateTime(saveByRawMaterialDTO.getCreateTime());

        //restockBill.setOutLocation();
    }


}
