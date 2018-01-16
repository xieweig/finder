package cn.sisyphe.coffee.bill.application.returned;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBillDetail;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillExtraService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import cn.sisyphe.coffee.bill.domain.base.model.enums.SourcePlanTypeEnum;
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
public class ReturnedBillManager extends AbstractBillExtraManager<ReturnedBill, ConditionQueryReturnedBill> {

    @Autowired
    private SharedManager sharedManager;

    public ReturnedBillManager(BillRepository<ReturnedBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<ReturnedBill, ConditionQueryReturnedBill> billExtraService, PlanBillExtraService planBillExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, planBillExtraService, sharedManager);
    }


    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.RETURNED;
    }

    @Autowired
    private ReturnedBillExtraService returnedBillExtraService;

    public ReturnedBill findReturnedBillBySourceCode(String sourceCode) {
        ReturnedBill returnedBill = returnedBillExtraService.findBySourceCode(sourceCode);
        return returnedBill;
    }

    /**
     * 保存进货单
     *
     * @param addReturnedBillDTO
     */
    public void saveBill(AddReturnedBillDTO addReturnedBillDTO) {
        verification(addReturnedBillDTO);
        // 转换单据
        ReturnedBill returnedBill = addReturnedBillToReturnedBillDTO(addReturnedBillDTO);
        // 保存单据
        save(returnedBill);
    }

    /**
     * 提交进货单
     *
     * @param addReturnedBillDTO
     */
    public void submitBill(AddReturnedBillDTO addReturnedBillDTO) {
        // 转换单据
        ReturnedBill returnedBill = addReturnedBillToReturnedBillDTO(addReturnedBillDTO);
        submit(returnedBill);
    }

    /**
     * 修改退库单--保存
     *
     * @param billDTO
     */
    public void updateBillToSave(AddReturnedBillDTO billDTO) {
        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            throw new DataException("404", "单据编码为空");
        }
/*        // 验证属性
        verification(billDTO);*/
        ReturnedBill returnedBill = returnedBillExtraService.findByBillCode(billDTO.getBillCode());
        if (billDTO.getBillDetails() != null && billDTO.getBillDetails().size() > 0) {
            returnedBill.getBillDetails().clear();
        }
        // 转换单据
        ReturnedBill mapBillAfter = returnedBillDTOEditAddReturnedBill(billDTO, returnedBill);

        save(mapBillAfter);
    }

    /**
     * 修改退库单--提交审核
     *
     * @param billDTO
     */
    public void updateBillToSubmit(AddReturnedBillDTO billDTO) {
        ReturnedBill returnedBill = returnedBillExtraService.findByBillCode(billDTO.getBillCode());
        returnedBill.getBillDetails().clear();
        // 转换单据
        ReturnedBill mapBillAfter = returnedBillDTOEditAddReturnedBill(billDTO, returnedBill);

        submit(mapBillAfter);
    }

    /**
     * 打开退库单
     *
     * @param returnedBillCode
     */
    public ReturnedBill openBill(String returnedBillCode) {
        ReturnedBill returnedBill = returnedBillExtraService.findByBillCode(returnedBillCode);
        // 如果单据是打开状态或者是审核失败状态，则直接返回转换后的退库单据信息
        // 如果单据是提交状态，则进行打开动作
        if (returnedBill.getBillState().equals(BillStateEnum.SUBMITTED)) {
            // 打开单据
            returnedBill = open(returnedBill);
//            return mapOneToDTO(returnedBill);
            return returnedBill;
        } else {
//            return mapOneToDTO(returnedBill);
            return returnedBill;
        }
    }

    /**
     * 审核退库单
     *
     * @param returnedBillCode
     */
    @Override
    public void auditBill(String returnedBillCode, String auditPersonCode, boolean isSuccess) {
        if (StringUtils.isEmpty(returnedBillCode)) {
            throw new DataException("404", "单据编码为空");
        }
        if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }
        ReturnedBill returnedBill = returnedBillExtraService.findByBillCode(returnedBillCode);
        // 设置审核人编码
        returnedBill.setAuditPersonCode(auditPersonCode);

        audit(returnedBill, isSuccess);
    }

    /**
     * 单据出入库完成
     *
     * @param responseResult
     */
    public void doneBill(ResponseResult responseResult) {
        Map<String, Object> resultMap = responseResult.getResult();
        // 转换出单据信息
        ReturnedBill bill = responseResult.toClassObject(resultMap.get("bill"), ReturnedBill.class);
        // 根据单据编码查询数据库单据信息
        ReturnedBill returnedBill = returnedBillExtraService.findByBillCode(bill.getBillCode());
        // 设置入库时间
        returnedBill.setInWareHouseTime(new Date());
        // 处理完成
        done(bill);
    }

    /**
     * 根据多条件查询退库单据信息
     *
     * @param conditionQueryReturnedBill 查询条件
     * @return
     */
    public Page<ReturnedBillDTO> findByConditions(ConditionQueryReturnedBill conditionQueryReturnedBill, BillTypeEnum billType, BillPurposeEnum billPurpose) {
        conditionQueryReturnedBill.setBillType(billType);
        conditionQueryReturnedBill.setBillPurpose(billPurpose);
        Page<ReturnedBill> returnedBills = returnedBillExtraService.findPageByCondition(conditionQueryReturnedBill);

        return returnedBills.map(this::returnedBillToReturnedBillDTO);
    }

    /**
     * 转换成子计划单DTO
     *
     * @param returnedBill 子计划单
     * @return 子计划单DTO
     */
    private ReturnedBillDTO returnedBillToReturnedBillDTO(ReturnedBill returnedBill) {
        //name没有改变
        ReturnedBillDTO returnedBillDTO = new ReturnedBillDTO();
        returnedBillDTO.setOperatorName(sharedManager.findOneByUserCode(returnedBill.getOperatorCode()));
        returnedBillDTO.setCreateTime(returnedBill.getCreateTime());
        returnedBillDTO.setSourceCode(returnedBill.getSourceCode());
        returnedBillDTO.setVariety(returnedBill.getTotalVarietyAmount());
        returnedBillDTO.setTotalPrice(returnedBill.getTotalPrice());
        returnedBillDTO.setRootCode(returnedBill.getRootCode());
        returnedBillDTO.setProgress(returnedBill.getProgress());
        returnedBillDTO.setPlanMemo(returnedBill.getPlanMemo());
        returnedBillDTO.setOutMemo(returnedBill.getOutStorageMemo());
        returnedBillDTO.setOutLocation(returnedBill.getOutLocation());
        returnedBillDTO.setInOrOutState(returnedBill.getInOrOutState());
        returnedBillDTO.setInLocation(returnedBill.getInLocation());
        returnedBillDTO.setBillType(returnedBill.getBillType());
        returnedBillDTO.setBillState(returnedBill.getBillState());
        returnedBillDTO.setBillPurpose(returnedBill.getBillPurpose());
        returnedBillDTO.setInWareHouseTime(returnedBill.getInWareHouseTime());
        returnedBillDTO.setBasicEnum(returnedBill.getBasicEnum());
        returnedBillDTO.setAuditMemo(returnedBill.getAuditMemo());
        returnedBillDTO.setSubmitState(returnedBill.getSubmitState());
        returnedBillDTO.setAuditState(returnedBill.getAuditState());
        returnedBillDTO.setBillCode(returnedBill.getBillCode());
        returnedBillDTO.setAuditPersonName(sharedManager.findOneByUserCode(returnedBill.getAuditPersonCode()));
        returnedBillDTO.setAmount(returnedBill.getTotalAmount());
        returnedBillDTO.setBillProperty(returnedBill.getBillProperty());
        returnedBillDTO.setBillDetails(billDetailToBillDetailDTO(returnedBill.getBillDetails()));

        return returnedBillDTO;
    }

    /**
     * 保存和提交操作需要用到的DTO转换
     *
     * @param addReturnedBillDTO 前端传递的DTO参数信息
     * @return
     */
    private ReturnedBill addReturnedBillToReturnedBillDTO(AddReturnedBillDTO addReturnedBillDTO) {
        //通过工厂方法生成具体种类的单据
        BillFactory billFactory = new BillFactory();
        ReturnedBill returnedBill = (ReturnedBill) billFactory.createBill(BillTypeEnum.RETURNED);
        // 设置单据的作用
        returnedBill.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
        //设置根单号
        if (addReturnedBillDTO.getRootCode() == null){
            returnedBill.setRootCode(returnedBill.getBillCode());
        }
        //设置单据属性
        returnedBill.setBillProperty(addReturnedBillDTO.getBillProperty());

        // 来源单号
        if (!StringUtils.isEmpty(addReturnedBillDTO.getSourceCode())) {
            returnedBill.setSourceCode(addReturnedBillDTO.getSourceCode());
        }
        // 发起单号
        if (!StringUtils.isEmpty(addReturnedBillDTO.getRootCode())) {
            returnedBill.setRootCode(addReturnedBillDTO.getRootCode());
        }
        // 计划备注
        if (!StringUtils.isEmpty(addReturnedBillDTO.getPlanMemo())) {
            returnedBill.setPlanMemo(addReturnedBillDTO.getPlanMemo());
        }
        // 出库备注
        if (!StringUtils.isEmpty(addReturnedBillDTO.getOutMemo())) {
            returnedBill.setOutStorageMemo(addReturnedBillDTO.getOutMemo());
        }
        // 操作人代码
        returnedBill.setOperatorCode(addReturnedBillDTO.getOperatorCode());
        // 归属站点
        returnedBill.setBelongStationCode(addReturnedBillDTO.getOutStation().code());
        //入库站点
        returnedBill.setInLocation(addReturnedBillDTO.getInStation());
        //出库站点
        returnedBill.setOutLocation(addReturnedBillDTO.getOutStation());

        Set<ReturnedBillDetailDTO> detailDTOSet = addReturnedBillDTO.getBillDetails();
        //退库数量
        int amount = 0;
        for (ReturnedBillDetailDTO detailDTO :
                detailDTOSet) {
            amount += detailDTO.getActualAmount();
        }
        returnedBill.setTotalAmount(amount);

        //退库品种数
        int variety = detailDTOSet.size();
        returnedBill.setTotalVarietyAmount(variety);
        //进度
        returnedBill.setProgress(addReturnedBillDTO.getProgress());
        //配送总价
        returnedBill.setTotalPrice(addReturnedBillDTO.getTotalPrice());
        //按货物还是按原料
        returnedBill.setBasicEnum(addReturnedBillDTO.getBasicEnum());
        // 转换单据明细信息
        Set<ReturnedBillDetail> detailSet = billDetaiDTOToBillDetail(detailDTOSet);
        // 设置单据明细信息
        returnedBill.setBillDetails(detailSet);

        return returnedBill;
    }

    /**
     * 保存、提交和修改操作需要用到的DTO转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    private Set<ReturnedBillDetail> billDetaiDTOToBillDetail(Set<ReturnedBillDetailDTO> billDetails) {

        Set<ReturnedBillDetail> detailSet = new HashSet<>();
        for (ReturnedBillDetailDTO detail : billDetails) {
            ReturnedBillDetail returnedBillDetail = new ReturnedBillDetail();
            // 设置货物和原料信息
            RawMaterial rawMaterial = detail.getRawMaterial();
            returnedBillDetail.setGoods(rawMaterial);
            //应捡数量
            returnedBillDetail.setShippedAmount(detail.getShippedAmount());
            //实拣数量
            returnedBillDetail.setActualAmount(detail.getActualAmount());

            detailSet.add(returnedBillDetail);
        }
        return detailSet;
    }

    /**
     * 前端查询单个单据明细信息将set转换为dto
     *
     * @param returnedBillDetails
     * @return
     */
    private List<ReturnedBillDetailDTO> billDetailToBillDetailDTO(Set<ReturnedBillDetail> returnedBillDetails) {

        List<ReturnedBillDetailDTO> detailDTOList = new ArrayList<>();
        for (ReturnedBillDetail detail : returnedBillDetails) {
            ReturnedBillDetailDTO detailDTO = new ReturnedBillDetailDTO();

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
     * @param addReturnedBillDTO
     * @return
     */
    private ReturnedBill returnedBillDTOEditAddReturnedBill(AddReturnedBillDTO addReturnedBillDTO, ReturnedBill returnedBill) {

        //若没有root编码则自己的编码就是root编码
        if (!StringUtils.isEmpty(addReturnedBillDTO.getRootCode())) {
            returnedBill.setRootCode(returnedBill.getRootCode());
        } else {
            returnedBill.setRootCode(returnedBill.getBillCode());
        }
        // 出库备注
        if (!StringUtils.isEmpty(addReturnedBillDTO.getOutMemo())) {
            returnedBill.setOutStorageMemo(addReturnedBillDTO.getOutMemo());
        }
        Set<ReturnedBillDetailDTO> detailDTOSet = addReturnedBillDTO.getBillDetails();
        //退货数量
        int amount = 0;
        for (ReturnedBillDetailDTO detailDTO :
                detailDTOSet) {
            amount += detailDTO.getActualAmount();
        }
        returnedBill.setTotalAmount(amount);

        //出库库位
        if (returnedBill.getOutLocation() != null){
            returnedBill.setOutLocation(addReturnedBillDTO.getOutStation());
        }
        //退货品种数
        int variety = detailDTOSet.size();
        returnedBill.setTotalVarietyAmount(variety);
        //进度
        returnedBill.setProgress(addReturnedBillDTO.getProgress());
        //配送总价
        returnedBill.setTotalPrice(addReturnedBillDTO.getTotalPrice());
        //按货物还是按原料
        if (returnedBill.getBasicEnum() != null){
            returnedBill.setBasicEnum(addReturnedBillDTO.getBasicEnum());
        }
        // 转换单据明细信息
        Set<ReturnedBillDetail> detailSet = billDetaiDTOToBillDetail(detailDTOSet);
        // 设置单据明细信息
        returnedBill.getBillDetails().addAll(detailSet);
        return returnedBill;
    }

    /**
     * 验证提交数据信息
     *
     * @param addReturnedBillDTO
     */
    private void verification(AddReturnedBillDTO addReturnedBillDTO) {
        //来源单号
        if (!addReturnedBillDTO.getBillProperty().equals(SourcePlanTypeEnum.NOPLAN)) {
            if (StringUtils.isEmpty(addReturnedBillDTO.getSourceCode())) {
                throw new DataException("500", "来源单号为空");
            }
        }
        if (addReturnedBillDTO.getBillProperty() == null) {
            throw new DataException("500", "单据属性为空");
        }
        if (addReturnedBillDTO.getInStation() == null) {
            throw new DataException("500", "入库站点为空");
        }
        if (addReturnedBillDTO.getOutStation() == null) {
            throw new DataException("500", "出库站点为空");
        }
        if (addReturnedBillDTO.getBillDetails() == null) {
            throw new DataException("500", "货物详细为空");
        }
        if (StringUtils.isEmpty(addReturnedBillDTO.getOperatorCode())) {
            throw new DataException("500", "操作人编码为空");
        }
        if (addReturnedBillDTO.getOutMemo() == null) {
            addReturnedBillDTO.setOutMemo("");
        }
        if (addReturnedBillDTO.getPlanMemo() == null) {
            addReturnedBillDTO.setPlanMemo("");
        }
        if (addReturnedBillDTO.getBasicEnum() == null) {
            throw new DataException("500", "按货物按原料为空");
        }
        if (addReturnedBillDTO.getTotalPrice() == null) {
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
        ReturnedBill returnedBill = returnedBillExtraService.findByBillCode(billCode);
        ScanFillBillDTO scanFillBillDTO = returnedToMapScanFillBillDTO(returnedBill);
        return scanFillBillDTO;
    }

    private ScanFillBillDTO returnedToMapScanFillBillDTO(ReturnedBill returnedBill) {
        ScanFillBillDTO scanFillBillDTO = new ScanFillBillDTO();
        List<String> packNumberList = new ArrayList<>();
        scanFillBillDTO.setTotalCount(returnedBill.getTotalVarietyAmount());
        scanFillBillDTO.setTotalAmount(returnedBill.getTotalAmount());
        scanFillBillDTO.setOperatorCode(returnedBill.getOperatorCode());
        scanFillBillDTO.setOutStockTime(returnedBill.getOutWareHouseTime());
        Set<ReturnedBillDetail> returnedBillDetailSet = returnedBill.getBillDetails();
        Set<String> packageCodeSet = new HashSet<>();
        for (ReturnedBillDetail returnedBillDetail : returnedBillDetailSet) {
            packageCodeSet.add(returnedBillDetail.getPackageCode());
        }
        for (String packageCode : packageCodeSet) {
            packNumberList.add(packageCode);
        }
        scanFillBillDTO.setPackNumbers(packNumberList);
        return scanFillBillDTO;
    }
    public ReturnedBill findByReturnedBillCode(String returnedBillCode) {
        ReturnedBill returnedBill = returnedBillExtraService.findByBillCode(returnedBillCode);
        return returnedBill;
    }


}
