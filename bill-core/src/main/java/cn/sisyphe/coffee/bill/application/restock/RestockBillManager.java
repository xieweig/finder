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
import cn.sisyphe.coffee.bill.domain.restock.*;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
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
    @Autowired
    private RestockBillQueryService restockBillQueryService;
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

        ReadyWays readyWay = saveByRestockBillDTO.getReadyWays();
        Set<RestockBillDetail> details = this.listToSet(saveByRestockBillDTO.getBillDetails(),readyWay);

        restockBill.setBillDetails(details);
    }

    /**
     *
     *notes :
     *  如果@requestbody注解只能直接绑定成list 那么此处需要转换成set
     */
    private Set<RestockBillDetail> listToSet(List<RestockBillDetailsDTO> list, ReadyWays readyWay){
        Set<RestockBillDetail> details = new HashSet<>();

        if (list.size()==0) throw new DataException("30001","单据来源缺失bill_details");

        for (RestockBillDetailsDTO detailDTO : list) {
            RestockBillDetail restockBillDetail = new RestockBillDetail();
            this.detailsCopyPropertiesFromDTO(detailDTO, restockBillDetail, readyWay);
            details.add(restockBillDetail);
        }

        return details;
    }
    /**
     *
     *notes :
     *  billDetailDTO to BillDetail; single
     *  该方法要配合实际前段的业务列表字段 进行增减
     */
    private void detailsCopyPropertiesFromDTO(RestockBillDetailsDTO dto, RestockBillDetail detail, ReadyWays readyWay){
        if (readyWay == ReadyWays.ByRawMaterial){
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
        else if(readyWay == ReadyWays.ByCargo) {
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
        }   else if(readyWay == ReadyWays.ByStationSelf) {

            RawMaterial rawMaterial = new RawMaterial(dto.getRawMaterialCode());
            rawMaterial.setRawMaterialName(dto.getRawMaterialName());
            Cargo cargo = new Cargo(dto.getCargoCode());
            cargo.setCargoName(dto.getCargoName());
            rawMaterial.setCargo(cargo);
            detail.setGoods(rawMaterial);
            detail.setAmount(dto.getAmount());
            detail.setPackageCode(dto.getPackageCode());
            detail.setActualNumber(dto.getActualNumber());


        }   else throw new DataException("30002","缺失拣货方式：自主拣货 or 计划按货物 or 计划按原料");
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



    /**
     * 修改进货单--保存
     *
     * @param billDTO
     */
    public void updateBillToSave(AddRestockBillDTO billDTO) {
        RestockBill restockBill = restockBillQueryService.findOneByBillCode(billDTO.getBillCode());
        restockBill.getBillDetails().clear();
        // 转换单据
        RestockBill mapBillAfter = dtoToMapRestockBillForEdit(billDTO, restockBill);

        save(mapBillAfter);
    }

    /**
     * 修改进货单--提交审核
     *
     * @param billDTO
     */
    public void updateBillToSubmit(AddRestockBillDTO billDTO) {
        RestockBill restockBill = restockBillQueryService.findOneByBillCode(billDTO.getBillCode());
        restockBill.getBillDetails().clear();
        // 转换单据
        RestockBill mapBillAfter = dtoToMapRestockBillForEdit(billDTO, restockBill);

        submit(mapBillAfter);
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

    /**
     * 审核进货单
     *
     * @param restockBillCode
     */
    public void auditBill(String restockBillCode, String auditPersonCode, boolean isSuccess) {
        RestockBill restockBill = restockBillQueryService.findOneByBillCode(restockBillCode);
        // 设置审核人编码
        restockBill.setAuditPersonCode(auditPersonCode);

        audit(restockBill, isSuccess);
    }

    /**
     * 单据出入库完成
     *
     * @param responseResult
     */
    public void doneBill(ResponseResult responseResult) {
        Map<String, Object> resultMap = responseResult.getResult();
        // 转换出单据信息
        RestockBill bill = responseResult.toClassObject(resultMap.get("bill"), RestockBill.class);
        // 根据单据编码查询数据库单据信息
        RestockBill restockBill = restockBillQueryService.findOneByBillCode(bill.getBillCode());
        // 设置入库时间
        restockBill.setInStorageEndTime(new Date());
        // 处理完成
        done(bill);
    }


    /**
     * 保存和提交操作需要用到的DTO转换
     *
     * @param addRestockBillDTO 前端传递的DTO参数信息
     * @return
     */
    private RestockBill dtoToMapRestockBill(AddRestockBillDTO addRestockBillDTO) {
        //通过工厂方法生成具体种类的单据
        BillFactory billFactory = new BillFactory();
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);


        return restockBill;
    }


    /**
     * 保存、提交和修改操作需要用到的DTO转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    private Set<RestockBillDetail> listDetailMapToSetDetail(List<BillDetailDTO> billDetails) {

        Set<RestockBillDetail> detailSet = new HashSet<>();

        return detailSet;
    }

    /**
     * 前端查询单个单据明细信息将set转换为dto
     *
     * @param restockBillDetails
     * @return
     */
    private List<BillDetailDTO> setDetailMapToListMapDetail(Set<RestockBillDetail> restockBillDetails) {

        List<BillDetailDTO> detailDTOList = new ArrayList<>();

        return detailDTOList;
    }


    /**
     * 修改需要转换DTO
     *
     * @param editRestockBillDTO
     * @return
     */
    private RestockBill dtoToMapRestockBillForEdit(AddRestockBillDTO editRestockBillDTO, RestockBill restockBill) {
        return restockBill;
    }


}
