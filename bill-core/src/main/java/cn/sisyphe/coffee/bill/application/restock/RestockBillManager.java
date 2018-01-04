package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillQueryService;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    @Resource
    private RestockBillRepository restockBillRepository;
    @Autowired
    private RestockBillQueryService restockBillQueryService;
    //@config @bean @resource
    private BillFactory billFactory = new BillFactory();

    private BillServiceFactory billServiceFactory = new BillServiceFactory();
    public void submitByRestockBill(SaveByRestockBillDTO saveByRestockBillDTO){

    }

    private void save_init(RestockBill restockBill){
        //restockBill.setBillState(BillStateEnum.SAVED);这一步会在set Behavior中
        // 初始化单据为：退库 出库单
        restockBill.setBillType(BillTypeEnum.RESTOCK);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);

        //根据单据生成对应的BillServer;
        RestockBillService billService = (RestockBillService) billServiceFactory.createBillService(restockBill);
        //会setBillBehavior;
        billService.dispose(new SaveBehavior());

        billService.setBillRepository(restockBillRepository);

        billService.save();
        //billService.sendEvent(applicationEventPublisher);

    }

    public void saveBySelf(SaveBySelfDTO saveBySelfDTO) {
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        BeanUtils.copyProperties(saveBySelfDTO, restockBill);
        this.save_init(restockBill);

    }

    public void saveByCargo(SaveByCargoDTO saveByCargoDTO) {
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        //this.billCopyPropertiesFromDTO();
    }

    public Page<CargoDTO> queryCargo(String cargoName, String cargoCode) {

        return null;
    }

    public void saveByRestockBill(SaveByRestockBillDTO saveByRawMaterialDTO) {
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        /**
         *
         *notes :BeanUtils.copyProperties(saveByRawMaterialDTO, restockBill);
         *    内部转换属性方法
         */

        this.billCopyPropertiesFromDTO(saveByRawMaterialDTO, restockBill);


        this.save_init(restockBill);

    }

    /**
     *
     *notes :
     *  billDTO to Bill ;包括关联的details ;single
     */
    private void billCopyPropertiesFromDTO(SaveByRestockBillDTO saveByRestockBillDTO, RestockBill restockBill){

        restockBill.setBillCode(saveByRestockBillDTO.getBillCode());

        restockBill.setRemarks(saveByRestockBillDTO.getRemarks());

        restockBill.setCreateTime(saveByRestockBillDTO.getCreateTime());

        //进入入库站点的**库
        Station inStation = new Station(saveByRestockBillDTO.getInStationCode());
        restockBill.setInLocation(inStation);

        //从出库站点的正常库  出
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
        for (RestockBillDetailsDTO detailDTO:list
                ) {
            RestockBillDetail restockBillDetail = new RestockBillDetail();
            this.detailsCopyPropertiesFromDTO(detailDTO, restockBillDetail, readyByCargo);
            details.add(restockBillDetail);
        }
        System.out.println("details: "+details.size());
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
     * 根据多条件查询进货单据信息
     *
     * @param conditionQueryRestockBill 查询条件
     * @return
     */
    public QueryRestockBillDTO findByConditions(ConditionQueryRestockBill conditionQueryRestockBill) {
        Page<RestockBill> restockBillPage = restockBillQueryService.findPageByCondition(conditionQueryRestockBill);

        QueryRestockBillDTO QueryRestockBillDTO = new QueryRestockBillDTO();
        // 转换
        List<RestockBillDTO> billDTOList = toMapDTO(restockBillPage.getContent());
        // 总数
        QueryRestockBillDTO.setTotalNumber(restockBillPage.getTotalElements());
        // 进货单据数据
        QueryRestockBillDTO.setContent(billDTOList);

        return QueryRestockBillDTO;
    }

    private List<RestockBillDTO> toMapDTO(List<RestockBill> content) {
        List<RestockBillDTO> restockBillDTOList = new ArrayList<>();

        return restockBillDTOList;
    }


    public QueryOneRestockBillDTO openBill(String restockBillCode) {
        RestockBill restockBill = restockBillQueryService.findOneByBillCode(restockBillCode);
        // 如果单据是打开状态或者是审核失败状态，则直接返回转换后的进货单据信息
        if (restockBill.getBillState().equals(BillStateEnum.OPEN)
                || restockBill.getBillState().equals(BillStateEnum.AUDIT_FAILURE)) {
            return mapOneToDTO(restockBill);
        }

        // 打开单据
        restockBill = open(restockBill);

        return mapOneToDTO(restockBill);
    }

    private QueryOneRestockBillDTO mapOneToDTO(RestockBill restockBill) {
        return null;
    }
}
