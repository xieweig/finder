package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillExtraService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ScanFillBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * @date: 2018/1/2
 * @description:
 * @author：xieweiguang
 */
@Service
public class RestockBillManager extends AbstractBillManager<RestockBill> {

    @Autowired
    private SharedManager sharedManager;


    public RestockBillManager(BillRepository<RestockBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    @Autowired
    private RestockBillExtraService restockBillExtraService;

    public RestockBill findRestockBillBySourceCode(String sourceCode) {
        RestockBill restockBill = restockBillExtraService.findBySourceCode(sourceCode);
        return restockBill;
    }

    /**
     * 保存进货单
     *
     * @param addRestockBillDTO
     */
    public void saveBill(AddRestockBillDTO addRestockBillDTO) {
        verification(addRestockBillDTO);
        // 转换单据
        RestockBill restockBill = addRestockBillToRestockBillDTO(addRestockBillDTO);
        // 保存单据
        save(restockBill);
    }

    /**
     * 提交进货单
     *
     * @param addRestockBillDTO
     */
    public void submitBill(AddRestockBillDTO addRestockBillDTO) {
        // 转换单据
        RestockBill restockBill = addRestockBillToRestockBillDTO(addRestockBillDTO);
        submit(restockBill);
    }

    /**
     * 修改退库单--保存
     *
     * @param billDTO
     */
    public void updateBillToSave(AddRestockBillDTO billDTO) {
        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            throw new DataException("404", "单据编码为空");
        }
/*        // 验证属性
        verification(billDTO);*/
        RestockBill restockBill = restockBillExtraService.findByBillCode(billDTO.getBillCode());
        if (billDTO.getBillDetails() != null && billDTO.getBillDetails().size() > 0) {
            restockBill.getBillDetails().clear();
        }
        // 转换单据
        RestockBill mapBillAfter = restockBillDTOEditAddRestockBill(billDTO, restockBill);

        save(mapBillAfter);
    }

    /**
     * 修改退库单--提交审核
     *
     * @param billDTO
     */
    public void updateBillToSubmit(AddRestockBillDTO billDTO) {
        RestockBill restockBill = restockBillExtraService.findByBillCode(billDTO.getBillCode());
        restockBill.getBillDetails().clear();
        // 转换单据
        RestockBill mapBillAfter = restockBillDTOEditAddRestockBill(billDTO, restockBill);

        submit(mapBillAfter);
    }

    /**
     * 打开退库单
     *
     * @param restockBillCode
     */
    public RestockBill openBill(String restockBillCode) {
        RestockBill restockBill = restockBillExtraService.findByBillCode(restockBillCode);
        // 如果单据是打开状态或者是审核失败状态，则直接返回转换后的退库单据信息
        // 如果单据是提交状态，则进行打开动作
        if (restockBill.getBillState().equals(BillStateEnum.SUBMITTED)) {
            // 打开单据
            restockBill = open(restockBill);
//            return mapOneToDTO(restockBill);
            return restockBill;
        } else {
//            return mapOneToDTO(restockBill);
            return restockBill;
        }
    }

    /**
     * 审核退库单
     *
     * @param restockBillCode
     */
    public void auditBill(String restockBillCode, String auditPersonCode, boolean isSuccess) {
        if (StringUtils.isEmpty(restockBillCode)) {
            throw new DataException("404", "单据编码为空");
        }
        if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }
        RestockBill restockBill = restockBillExtraService.findByBillCode(restockBillCode);
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
        RestockBill restockBill = restockBillExtraService.findByBillCode(bill.getBillCode());
        // 设置入库时间
        restockBill.setInWareHouseTime(new Date());
        // 处理完成
        done(bill);
    }

    /**
     * 根据多条件查询退库单据信息
     *
     * @param conditionQueryRestockBill 查询条件
     * @return
     */
    public Page<RestockBillDTO> findByConditions(ConditionQueryRestockBill conditionQueryRestockBill, BillTypeEnum billType, BillPurposeEnum billPurpose) {
        conditionQueryRestockBill.setBillType(billType);
        conditionQueryRestockBill.setBillPurpose(billPurpose);
        Page<RestockBill> restockBills = restockBillExtraService.findPageByCondition(conditionQueryRestockBill);

        return restockBills.map(this::restockBillToRestockBillDTO);
    }

    /**
     * 转换成子计划单DTO
     *
     * @param restockBill 子计划单
     * @return 子计划单DTO
     */
    private RestockBillDTO restockBillToRestockBillDTO(RestockBill restockBill) {
        //name没有改变
        RestockBillDTO restockBillDTO = new RestockBillDTO();
        restockBillDTO.setOperatorName(sharedManager.findOneByUserCode(restockBill.getOperatorCode()));
        restockBillDTO.setCreateTime(restockBill.getCreateTime());
        restockBillDTO.setSourceCode(restockBill.getSourceCode());
        restockBillDTO.setVariety(restockBill.getTotalVarietyAmount());
        restockBillDTO.setTotalPrice(restockBill.getTotalPrice());
        restockBillDTO.setRootCode(restockBill.getRootCode());
        restockBillDTO.setProgress(restockBill.getProgress());
        restockBillDTO.setPlanMemo(restockBill.getPlanMemo());
        restockBillDTO.setOutMemo(restockBill.getOutStorageMemo());
        restockBillDTO.setOutLocation(restockBill.getOutLocation());
        restockBillDTO.setInOrOutState(restockBill.getInOrOutState());
        restockBillDTO.setInLocation(restockBill.getInLocation());
        restockBillDTO.setBillType(restockBill.getBillType());
        restockBillDTO.setBillState(restockBill.getBillState());
        restockBillDTO.setBillPurpose(restockBill.getBillPurpose());
        restockBillDTO.setInWareHouseTime(restockBill.getInWareHouseTime());
        restockBillDTO.setBasicEnum(restockBill.getBasicEnum());
        restockBillDTO.setAuditMemo(restockBill.getAuditMemo());
        restockBillDTO.setSubmitState(restockBill.getSubmitState());
        restockBillDTO.setAuditState(restockBill.getAuditState());
        restockBillDTO.setBillCode(restockBill.getBillCode());
        restockBillDTO.setAuditPersonName(sharedManager.findOneByUserCode(restockBill.getAuditPersonCode()));
        restockBillDTO.setAmount(restockBill.getTotalAmount());
        restockBillDTO.setBillProperty(restockBill.getBillProperty());
        restockBillDTO.setBillDetails(billDetailToBillDetailDTO(restockBill.getBillDetails()));

        return restockBillDTO;
    }

    /**
     * 保存和提交操作需要用到的DTO转换
     *
     * @param addRestockBillDTO 前端传递的DTO参数信息
     * @return
     */
    private RestockBill addRestockBillToRestockBillDTO(AddRestockBillDTO addRestockBillDTO) {
        //通过工厂方法生成具体种类的单据
        BillFactory billFactory = new BillFactory();
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        // 设置单据的作用
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);

        //设置单据属性
        restockBill.setBillProperty(addRestockBillDTO.getBillProperty());

        // 来源单号
        if (!StringUtils.isEmpty(addRestockBillDTO.getSourceCode())) {
            restockBill.setSourceCode(addRestockBillDTO.getSourceCode());
        }
        // 发起单号
        if (!StringUtils.isEmpty(addRestockBillDTO.getRootCode())) {
            restockBill.setRootCode(addRestockBillDTO.getRootCode());
        }
        // 计划备注
        if (!StringUtils.isEmpty(addRestockBillDTO.getPlanMemo())) {
            restockBill.setPlanMemo(addRestockBillDTO.getPlanMemo());
        }
        // 出库备注
        if (!StringUtils.isEmpty(addRestockBillDTO.getOutMemo())) {
            restockBill.setOutStorageMemo(addRestockBillDTO.getOutMemo());
        }
        // 操作人代码
        restockBill.setOperatorCode(addRestockBillDTO.getOperatorCode());
        // 归属站点
        restockBill.setBelongStationCode(addRestockBillDTO.getOutStation().code());
        //入库站点
        restockBill.setInLocation(addRestockBillDTO.getInStation());
        //出库站点
        restockBill.setOutLocation(addRestockBillDTO.getOutStation());

        Set<RestockBillDetailDTO> detailDTOSet = addRestockBillDTO.getBillDetails();
        //退库数量
        int amount = 0;
        for (RestockBillDetailDTO detailDTO :
                detailDTOSet) {
            amount += detailDTO.getActualAmount();
        }
        restockBill.setTotalAmount(amount);

        //退库品种数
        int variety = detailDTOSet.size();
        restockBill.setTotalVarietyAmount(variety);
        //进度
        restockBill.setProgress(addRestockBillDTO.getProgress());
        //配送总价
        restockBill.setTotalPrice(addRestockBillDTO.getTotalPrice());
        //按货物还是按原料
        restockBill.setBasicEnum(addRestockBillDTO.getBasicEnum());
        // 转换单据明细信息
        Set<RestockBillDetail> detailSet = billDetaiDTOToBillDetail(detailDTOSet);
        // 设置单据明细信息
        restockBill.setBillDetails(detailSet);

        return restockBill;
    }

    /**
     * 保存、提交和修改操作需要用到的DTO转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    private Set<RestockBillDetail> billDetaiDTOToBillDetail(Set<RestockBillDetailDTO> billDetails) {

        Set<RestockBillDetail> detailSet = new HashSet<>();
        for (RestockBillDetailDTO detail : billDetails) {
            RestockBillDetail restockBillDetail = new RestockBillDetail();
            // 设置货物和原料信息
            RawMaterial rawMaterial = detail.getRawMaterial();
            restockBillDetail.setGoods(rawMaterial);
            //应捡数量
            restockBillDetail.setShippedAmount(detail.getShippedAmount());
            //实拣数量
            restockBillDetail.setActualAmount(detail.getActualAmount());

            detailSet.add(restockBillDetail);
        }
        return detailSet;
    }

    /**
     * 前端查询单个单据明细信息将set转换为dto
     *
     * @param restockBillDetails
     * @return
     */
    private List<RestockBillDetailDTO> billDetailToBillDetailDTO(Set<RestockBillDetail> restockBillDetails) {

        List<RestockBillDetailDTO> detailDTOList = new ArrayList<>();
        for (RestockBillDetail detail : restockBillDetails) {
            RestockBillDetailDTO detailDTO = new RestockBillDetailDTO();

            detailDTO.setShippedAmount(detail.getShippedAmount());
            detailDTO.setActualAmount(detail.getActualAmount());
            // 设置货物和原料信息
            RawMaterial rawMaterial = new RawMaterial();
            if (detail.getGoods() instanceof RawMaterial){
               rawMaterial = (RawMaterial) detail.getGoods();
            }
            detailDTO.setRawMaterial(rawMaterial);
            detailDTOList.add(detailDTO);
        }
        return detailDTOList;
    }

    /**
     * 修改需要转换DTO
     *
     * @param addRestockBillDTO
     * @return
     */
    private RestockBill restockBillDTOEditAddRestockBill(AddRestockBillDTO addRestockBillDTO, RestockBill restockBill) {

        //若没有root编码则自己的编码就是root编码
        if (!StringUtils.isEmpty(addRestockBillDTO.getRootCode())) {
            restockBill.setRootCode(restockBill.getRootCode());
        } else {
            restockBill.setRootCode(restockBill.getBillCode());
        }
        // 出库备注
        if (!StringUtils.isEmpty(addRestockBillDTO.getOutMemo())) {
            restockBill.setOutStorageMemo(addRestockBillDTO.getOutMemo());
        }
        Set<RestockBillDetailDTO> detailDTOSet = addRestockBillDTO.getBillDetails();
        //退货数量
        int amount = 0;
        for (RestockBillDetailDTO detailDTO :
                detailDTOSet) {
            amount += detailDTO.getActualAmount();
        }
        restockBill.setTotalAmount(amount);

        //出库库位
        if (restockBill.getOutLocation() != null){
            restockBill.setOutLocation(addRestockBillDTO.getOutStation());
        }
        //退货品种数
        int variety = detailDTOSet.size();
        restockBill.setTotalVarietyAmount(variety);
        //进度
        restockBill.setProgress(addRestockBillDTO.getProgress());
        //配送总价
        restockBill.setTotalPrice(addRestockBillDTO.getTotalPrice());
        //按货物还是按原料
        if (restockBill.getBasicEnum() != null){
            restockBill.setBasicEnum(addRestockBillDTO.getBasicEnum());
        }
        // 转换单据明细信息
        Set<RestockBillDetail> detailSet = billDetaiDTOToBillDetail(detailDTOSet);
        // 设置单据明细信息
        restockBill.getBillDetails().addAll(detailSet);
        return restockBill;
    }

    /**
     * 验证提交数据信息
     *
     * @param addRestockBillDTO
     */
    private void verification(AddRestockBillDTO addRestockBillDTO) {
        //来源单号
        if (!addRestockBillDTO.getBillProperty().equals(SourcePlanTypeEnum.NOPLAN)) {
            if (StringUtils.isEmpty(addRestockBillDTO.getSourceCode())) {
                throw new DataException("500", "来源单号为空");
            }
        }
        if (addRestockBillDTO.getBillProperty() == null) {
            throw new DataException("500", "单据属性为空");
        }
        if (addRestockBillDTO.getInStation() == null) {
            throw new DataException("500", "入库站点为空");
        }
        if (addRestockBillDTO.getOutStation() == null) {
            throw new DataException("500", "出库站点为空");
        }
        if (addRestockBillDTO.getBillDetails() == null) {
            throw new DataException("500", "货物详细为空");
        }
        if (StringUtils.isEmpty(addRestockBillDTO.getOperatorCode())) {
            throw new DataException("500", "操作人编码为空");
        }
        if (addRestockBillDTO.getOutMemo() == null) {
            addRestockBillDTO.setOutMemo("");
        }
        if (addRestockBillDTO.getPlanMemo() == null) {
            addRestockBillDTO.setPlanMemo("");
        }
        if (addRestockBillDTO.getBasicEnum() == null) {
            throw new DataException("500", "按货物按原料为空");
        }
        if (addRestockBillDTO.getTotalPrice() == null) {
            throw new DataException("500", "总价为空");
        }
    }

    /**
     * 通过单据号billCode汇总查询出打包的信息
     *
     * @param billCode
     * @return
     */
    public ScanFillBillDTO findPackageInfoByBillCode(String billCode) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        }
        RestockBill restockBill = restockBillExtraService.findByBillCode(billCode);
        ScanFillBillDTO scanFillBillDTO = restockToMapScanFillBillDTO(restockBill);
        return scanFillBillDTO;
    }

    private ScanFillBillDTO restockToMapScanFillBillDTO(RestockBill restockBill) {
        ScanFillBillDTO scanFillBillDTO = new ScanFillBillDTO();
        List<String> packNumberList = new ArrayList<>();
        scanFillBillDTO.setTotalCount(restockBill.getTotalVarietyAmount());
        scanFillBillDTO.setTotalAmount(restockBill.getTotalAmount());
        scanFillBillDTO.setOperatorCode(restockBill.getOperatorCode());
        scanFillBillDTO.setOutStockTime(restockBill.getOutWareHouseTime());
        Set<RestockBillDetail> restockBillDetailSet = restockBill.getBillDetails();
        Set<String> packageCodeSet = new HashSet<>();
        for (RestockBillDetail restockBillDetail : restockBillDetailSet) {
            packageCodeSet.add(restockBillDetail.getPackageCode());
        }
        for (String packageCode : packageCodeSet) {
            packNumberList.add(packageCode);
        }
        scanFillBillDTO.setPackNumbers(packNumberList);
        return scanFillBillDTO;
    }
    public RestockBill findByRestockBillCode(String restockBillCode) {
        RestockBill restockBill = restockBillExtraService.findByBillCode(restockBillCode);
        return restockBill;
    }
}
