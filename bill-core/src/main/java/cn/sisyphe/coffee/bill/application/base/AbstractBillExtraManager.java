package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by heyong on 2018/1/16 16:01
 * Description:
 *
 * @author heyong
 */
public abstract class AbstractBillExtraManager<T extends Bill, D extends BillDTO, Q extends ConditionQueryBill> extends AbstractBillManager<T> {

    private BillExtraService<T, Q> billExtraService;
    private PlanBillExtraService planBillExtraService;
    private SharedManager sharedManager;

    public BillExtraService<T, Q> getBillExtraService() {
        return billExtraService;
    }

    public void setBillExtraService(BillExtraService<T, Q> billExtraService) {
        this.billExtraService = billExtraService;
    }

    public PlanBillExtraService getPlanBillExtraService() {
        return planBillExtraService;
    }

    public void setPlanBillExtraService(PlanBillExtraService planBillExtraService) {
        this.planBillExtraService = planBillExtraService;
    }

    public SharedManager getSharedManager() {
        return sharedManager;
    }

    public void setSharedManager(SharedManager sharedManager) {
        this.sharedManager = sharedManager;
    }

    public AbstractBillExtraManager(BillRepository<T> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<T, Q> billExtraService, PlanBillExtraService planBillExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher);
        this.billExtraService = billExtraService;
        this.planBillExtraService = planBillExtraService;
        this.sharedManager = sharedManager;
    }


    /**
     * 暂存调剂计划
     *
     * @param billDTO 前端dto
     * @return billCode 单据编码
     */
    public String saveBill(D billDTO) {
        T bill = prepareAdjustBill(billDTO);
        mapBill(bill, billDTO);
        return save(bill).getBillCode();
    }


    /**
     * 提交调剂计划
     *
     * @param billDTO 前端dto
     * @return billCode 单据编码
     */
    public String submitBill(D billDTO) {
        T bill = prepareAdjustBill(billDTO);
        mapBill(bill, billDTO);
        return submit(bill).getBillCode();
    }

    /**
     * 审核不通过
     *
     * @param billCode        单据编码
     * @param auditPersonCode 审核人编码
     */
    public void auditBill(String billCode, String auditPersonCode, boolean isSuccess) {

        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        } else if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }

        T bill = billExtraService.findByBillCode(billCode);
        bill.setAuditPersonCode(auditPersonCode);
        audit(bill, isSuccess);
    }


    /**
     * 将单据动作更改为--打开
     *
     * @param billCode 单据编号
     * @return QueryOneAdjustDTO
     */
    public D openBill(String billCode, String operatorCode) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        }

        T bill = billExtraService.findByBillCode(billCode);

        // 如果单据是提交状态，则进行打开动作
        if (bill.getBillState().equals(BillStateEnum.SUBMITTED)) {
            bill.setOperatorCode(operatorCode);
            // 打开单据
            open(bill);
        }


        return commonFun(bill);
    }


    /**
     * 公共方法
     *
     * @param bill
     * @return
     */
    private D commonFun(T bill) {

        // 如果是根据原料拣货，则需要去查询一下总部计划单里面的数据
        if (BasicEnum.BY_MATERIAL.equals(bill.getBasicEnum())) {
            PlanBill planBill = planBillExtraService.findByBillCode(bill.getRootCode());
            Set<PlanBillDetail> billDetails = planBill.getBillDetails();
            return toMapOneDTO(bill, billDetails);
        } else {
            return toMapOneDTO(bill, null);
        }
    }


    /**
     * 初始化adjustBill
     *
     * @param billDTO 前端dto
     * @return AdjustBill 调剂计划实体
     */
    private T prepareAdjustBill(D billDTO) {

        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            return (T) new BillFactory().createBill(BillTypeEnum.ADJUST);
        }

        T bill = billExtraService.findByBillCode(billDTO.getBillCode());

        if (bill == null) {
            throw new DataException("432434", "没有找到该计划单");
        }

        return bill;
    }

    /**
     * 入库单查询
     *
     * @param conditionQuery 条件查询参数
     * @return 查询结果带分页信息
     */
    public Page<T> findInStorageBillByCondition(Q conditionQuery) {
        return findBillByCondition(conditionQuery, BillPurposeEnum.IN_STORAGE);
    }


    /**
     * 出库单查询
     *
     * @param conditionQuery 条件查询参数
     * @return 查询结果带分页信息
     */
    public Page<T> findOutStorageBillByCondition(Q conditionQuery) {
        return findBillByCondition(conditionQuery, BillPurposeEnum.OUT_STORAGE);
    }


    private Page<T> findBillByCondition(Q conditionQuery, BillPurposeEnum billPurposeEnum) {
        // SpringCloud调用查询用户编码
        List<String> userCodeList = sharedManager.findByLikeUserName(conditionQuery.getOperatorName());

        conditionQuery.setOperatorCodeList(userCodeList);
        conditionQuery.setPurposeEnum(billPurposeEnum);
        Page<T> adjustBillPage = billExtraService.findPageByCondition(conditionQuery);

        return adjustBillPage.map(source -> toMapConditionsDTO(source));
    }

    /**
     * dto 转换 bill - 条件查询 dto list
     *
     * @param source
     * @param <D>
     * @return
     */
    protected <D> D toMapConditionsDTO(T source) {
        BillDTO billDTO = new BillDTO();
        // 单据属性
        billDTO.setBillProperty(source.getBillProperty());
        // 出库状态
        //billDTO.setOutStatusCode(source.getOutStateEnum());
        // 提交状态
        billDTO.setSubmitState(source.getSubmitState());
        // 审核状态
        billDTO.setAuditState(source.getAuditState());
        // 单据状态
        billDTO.setBillState(source.getBillState());
        // 发起单号
        billDTO.setRootCode(source.getRootCode());
        // 来源单号
        billDTO.setSourceCode(source.getSourceCode());
        // 单据编码
        billDTO.setBillCode(source.getBillCode());
        // 录单时间
        //billDTO.setCreateTime(source.getCreateTime());
        // 出库时间
        billDTO.setOutWareHouseTime(source.getOutWareHouseTime());
        // 录单人
        billDTO.setOperatorName(sharedManager.findOneByUserCode(source.getOperatorCode()));
        // 审核人
        billDTO.setAuditPersonName(sharedManager.findOneByUserCode(source.getAuditPersonCode()));
        billDTO.setBasicEnum(source.getBasicEnum());
        // 出库站点
        Station outLocation = (Station) source.getOutLocation();
        if (outLocation != null) {
            // billDTO.setOutStationCode(outLocation.getStationCode());
        }
        // 入库站点
        Station inLocation = (Station) source.getInLocation();
        if (inLocation != null) {
            //billDTO.setInStationCode(inLocation.getStationCode());
        }
        // 配送数量
        //billDTO.setAdjustNumber(source.getTotalAmount());
        // 配送品种数
        //billDTO.setVarietyNumber(source.getTotalVarietyAmount());

        return (D) billDTO;
    }

    /**
     * dto - bill
     *
     * @param bill
     * @param billDTO
     */
    protected void mapBill(T bill, D billDTO) {
//        //操作人编码
//        bill.setOperatorCode(billDTO.getOperatorCode());
//        //审核人编码
//        bill.setAuditPersonCode(billDTO.getAuditPersonCode());
//        //设置单据作用
//        bill.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
//        //设置出库站点
//        Station outLocation = new Station(billDTO.getOutStationCode());
//        //设置出库库位
//        outLocation.setStorage(billDTO.getOutStorage());
//        //设置出库信息
//        bill.setOutLocation(outLocation);
//        //设置入站站点
//        Station inLocation = new Station(billDTO.getInStationCode());
//        inLocation.setStorage(billDTO.getInStorage());
//        bill.setInLocation(inLocation);
//        //设置源单号
//        if (!StringUtils.isEmpty(billDTO.getRootCode())) {
//            bill.setBillProperty(billDTO.getBillProperty());
//            bill.setRootCode(billDTO.getRootCode());
//            bill.setSourceCode(billDTO.getRootCode());
//        }
//        //设置计划备注
//        bill.setPlanMemo(billDTO.getPlanMemo());
//        //设置计划详情
//        bill.getBillDetails().clear();
//        bill.getBillDetails().addAll(mapDetails(billDTO));
//        //设置是按原料还是货物拣货
//        bill.setBasicEnum(billDTO.getBasicEnum());
//        //设置出库备注
//        bill.setOutStorageMemo(billDTO.getOutStorageMemo());
//        //设置所属站点
//        bill.setBelongStationCode(billDTO.getOutStationCode());
//        //设置调剂数量
//        //bill.setTotalAmount(sum(billDTO.getDetails(), on(AddAdjustBillDetailDTO.class).getShippedAmount()));
//        //设置调剂种类
//        bill.setTotalVarietyAmount(billDTO.getDetails().size());

    }

    /**
     * bill - dto 详细
     *
     * @param bill
     * @param billDetails
     * @return
     */
    protected D toMapOneDTO(T bill, Set<PlanBillDetail> billDetails) {
        return null;
    }

    /**
     * 设置调剂计划详情
     *
     * @param addAdjustBillDTO 前端dto
     * @return 调剂计划详情集合
     */
    private Set<AdjustBillDetail> mapDetails(D addAdjustBillDTO) {
//        Set<AdjustBillDetail> billDetails = new HashSet<>();
//        for (AddAdjustBillDetailDTO addAdjustBillDetailDTO : addAdjustBillDTO.getDetails()) {
//            AdjustBillDetail adjustBillDetail = new AdjustBillDetail();
//            //原料或货物编码
//            adjustBillDetail.setGoods(addAdjustBillDetailDTO.getRawMaterial());
//            //设置所属原料编码便于分类
//            adjustBillDetail.setBelongMaterialCode(addAdjustBillDetailDTO.getBelongMaterialCode());
//            //应拣数量
//            adjustBillDetail.setShippedAmount(addAdjustBillDetailDTO.getShippedAmount());
//            //实拣数量
//            adjustBillDetail.setActualAmount(addAdjustBillDetailDTO.getActualAmount());
//            //备注信息
//            billDetails.add(adjustBillDetail);
//        }
//        return billDetails;
        return null;
    }
}
