package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.db.DbStation;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.HashSet;
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

        this.billCopyPropertiesFromDTO(saveByRawMaterialDTO, restockBill);


        this.save_init(restockBill);

    }

    public Page<CargoDTO> queryCargo(String cargoName, String cargoCode) {


        return null;
    }
    private void detailsCopyPropertiesFromDTO(RestockBillDetailsDTO dto, RestockBillDetail detail){

        RawMaterial rawMaterial = new RawMaterial(dto.getRawMaterialCode());
        rawMaterial.setRawMaterialName(dto.getRawMaterialName());

        Cargo cargo = new Cargo(dto.getCargoCode());
        cargo.setCargoName(dto.getCargoName());
        rawMaterial.setCargo(cargo);

        detail.setGoods(rawMaterial);
        detail.setAmount(dto.getAmount());
        detail.setPackageCode(dto.getPackageCode());
        detail.setNumber(dto.getNumber());

        detail.setDetailsRemarks(dto.getCargoRemarks());

    }
    private Set<RestockBillDetail> listToSet(List<RestockBillDetailsDTO> list){
        Set<RestockBillDetail> details = new HashSet<>();
        for (RestockBillDetailsDTO detailDTO:list
             ) {
            RestockBillDetail restockBillDetail = new RestockBillDetail();
            this.detailsCopyPropertiesFromDTO(detailDTO, restockBillDetail);
            details.add(restockBillDetail);
        }
        return details;
    }
    private void billCopyPropertiesFromDTO(SaveByRawMaterialDTO saveByRawMaterialDTO, RestockBill restockBill){

        restockBill.setBillCode(saveByRawMaterialDTO.getBillCode());

        restockBill.setRemarks(saveByRawMaterialDTO.getRemarks());

        restockBill.setCreateTime(saveByRawMaterialDTO.getCreateTime());

        //进入入库站点的**库
        Station inStation = new Station(saveByRawMaterialDTO.getInStationCode());
        restockBill.setInLocation(inStation);

        //从出库站点的正常库  出
        Station outStation = new Station(saveByRawMaterialDTO.getOutStationCode());
        Storage storage = new Storage();
        storage.setStorageCode(saveByRawMaterialDTO.getStorageCode());
        outStation.setStorage(storage);
        restockBill.setOutLocation(outStation);

        Set<RestockBillDetail> details = this.listToSet(saveByRawMaterialDTO.getBillDetails());




    }


}
