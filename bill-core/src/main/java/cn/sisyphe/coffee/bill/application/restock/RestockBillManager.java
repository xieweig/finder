package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;


/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
@Service
public class RestockBillManager extends AbstractBillManager<RestockBill> {

    public RestockBillManager(BillRepository<RestockBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

      //@config @bean @resource 不然可能被回收？
   // private BillFactory billFactory = new BillFactory();

    /**
     *
     *notes :
     *  退库出库单 保存
     */
    public void saveByRestockBill(SaveByRestockBillDTO SaveByRestockBillDTO) {
        /**
         *
         *notes :
         *  初始化bill
         */
        BillFactory billFactory = new BillFactory();
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        restockBill.setBillType(BillTypeEnum.RESTOCK);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        /**
         *
         *notes :BeanUtils.copyProperties(SaveByRestockBillDTO, restockBill);
         *    内部转换属性方法
         */
        this.billCopyPropertiesFromDTO(SaveByRestockBillDTO, restockBill);

        save(restockBill);


    }


    /**
     *
     *notes :
     *  billDTO to Bill ;包括关联的details ;single
     */
    private void billCopyPropertiesFromDTO(SaveByRestockBillDTO saveByRestockBillDTO, RestockBill restockBill){

        //究竟是不是dto提供bill_code？
        restockBill.setBillCode(saveByRestockBillDTO.getBillCode());

        restockBill.setRemarks(saveByRestockBillDTO.getRemarks());

        restockBill.setCreateTime(saveByRestockBillDTO.getCreateTime());

        //进入入库站点的**库
        Station inStation = new Station(saveByRestockBillDTO.getInStationCode());
        restockBill.setInLocation(inStation);

        //从出库站点的正常库 出
        Station outStation = new Station(saveByRestockBillDTO.getOutStationCode());
        Storage storage = new Storage();
        storage.setStorageCode(saveByRestockBillDTO.getStorageCode());
        outStation.setStorage(storage);
        restockBill.setOutLocation(outStation);
        Boolean readyByCargo = saveByRestockBillDTO.getReadyByCargo();

        Set<RestockBillDetail> details = this.listToSet(saveByRestockBillDTO.getBillDetails(),readyByCargo);

        restockBill.setBillDetails(details);
    }

    /**
     *
     *notes :
     *  如果@requestbody注解只能直接绑定成list 那么此处需要转换成set
     */
    private Set<RestockBillDetail> listToSet(List<RestockBillDetailsDTO> list, Boolean readyByCargo){
        Set<RestockBillDetail> details = new HashSet<>();

        if (list.size()==0) throw new DataException("30001","单据来源缺失bill_details");

        for (RestockBillDetailsDTO detailDTO : list) {
            RestockBillDetail restockBillDetail = new RestockBillDetail();
            this.detailsCopyPropertiesFromDTO(detailDTO, restockBillDetail, readyByCargo);
            details.add(restockBillDetail);
        }

        return details;
    }
    /**
     *
     *notes :
     *  billDetailDTO to BillDetail; single
     */
    private void detailsCopyPropertiesFromDTO(RestockBillDetailsDTO dto, RestockBillDetail detail, Boolean readyByCargo){
        if (readyByCargo == false){
        RawMaterial rawMaterial = new RawMaterial(dto.getRawMaterialCode());
        rawMaterial.setRawMaterialName(dto.getRawMaterialName());

        Cargo cargo = new Cargo(dto.getCargoCode());
        cargo.setCargoName(dto.getCargoName());
        rawMaterial.setCargo(cargo);

        detail.setGoods(rawMaterial);
        detail.setAmount(dto.getAmount());
        detail.setPackageCode(dto.getPackageCode());
        detail.setActualNumber(dto.getActualNumber());

        detail.setDetailsRemarks(dto.getCargoRemarks());
        }
        else {
            RawMaterial rawMaterial = new RawMaterial(dto.getRawMaterialCode());
            rawMaterial.setRawMaterialName(dto.getRawMaterialName());

            Cargo cargo = new Cargo(dto.getCargoCode());
            cargo.setCargoName(dto.getCargoName());
            rawMaterial.setCargo(cargo);
            detail.setGoods(rawMaterial);
            detail.setAmount(dto.getAmount());
            detail.setPackageCode(dto.getPackageCode());
            detail.setActualNumber(dto.getActualNumber());
            detail.setExpectedNumber(dto.getExpectedNumber());
        }
    }
    /**
     *
     *notes :
     *  一步到位不经过保存直接提交
     */
    public void submitByRestockBill(SaveByRestockBillDTO saveByRestockBillDTO){

        BillFactory billFactory = new BillFactory();
        RestockBill restockBill = (RestockBill)billFactory.createBill(BillTypeEnum.RESTOCK);
        restockBill.setBillType(BillTypeEnum.RESTOCK);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);

        this.billCopyPropertiesFromDTO(saveByRestockBillDTO,restockBill);

        submit(restockBill);


    }




}
