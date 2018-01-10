package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.util.BillCodeManager;
import cn.sisyphe.coffee.bill.viewmodel.adjust.*;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.sum;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务
 * version: 1.0
 *
 * @author XiongJing
 */

@Service
public class AdjustBillManager extends AbstractBillManager<AdjustBill> {

    public static final String ADJUST_OUT_STORAGE_PREFIX = "TJCKD";
    @Autowired
    private PlanBillExtraService planBillExtraService;
    @Autowired
    private AdjustBillExtraService adjustBillExtraService;
    @Autowired
    private SharedManager sharedManager;

    @Autowired
    public AdjustBillManager(BillRepository<AdjustBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    /**
     * 暂存调剂计划
     *
     * @param addAdjustBillDTO 前端dto
     * @return billCode 单据编码
     */
    public String create(AddAdjustBillDTO addAdjustBillDTO) {
        AdjustBill adjustBill = prepareAdjustBill(addAdjustBillDTO);
        mapBill(adjustBill, addAdjustBillDTO);
        return save(adjustBill).getBillCode();
    }

    /**
     * 提交调剂计划
     *
     * @param addAdjustBillDTO 前端dto
     * @return billCode 单据编码
     */
    public String submit(AddAdjustBillDTO addAdjustBillDTO) {
        AdjustBill adjustBill = prepareAdjustBill(addAdjustBillDTO);
        mapBill(adjustBill, addAdjustBillDTO);
        return submit(adjustBill).getBillCode();
    }

    /**
     * 审核不通过
     *
     * @param billCode        单据编码
     * @param auditPersonCode 审核人编码
     */
    public void audit(String billCode, String auditPersonCode, boolean isSuccess) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        }
        if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }
        AdjustBill adjustBill = adjustBillExtraService.findByBillCode(billCode);
        adjustBill.setAuditPersonCode(auditPersonCode);
        audit(adjustBill, isSuccess);
    }

    /**
     * 根据多条件查询调拨单据信息
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return 分页信息
     */
    public Page<AdjustBillDTO> findByConditions(ConditionQueryAdjustBill conditionQueryAdjustBill) {
        // SpringCloud调用查询用户编码
        List<String> userCodeList = sharedManager.findByLikeUserName(conditionQueryAdjustBill.getOperatorName());
        conditionQueryAdjustBill.setOperatorCodeList(userCodeList);
        Page<AdjustBill> adjustBillPage = adjustBillExtraService.findByConditions(conditionQueryAdjustBill);
        return adjustBillPage.map(source -> toMapConditionsDTO(source));
    }

    /**
     * 调剂出库单单个查询
     *
     * @param billCode 单据编号
     * @return QueryOneAdjustDTO
     */
    public QueryOneAdjustDTO findByBillCode(String billCode) {
        AdjustBill adjustBill = adjustBillExtraService.findByBillCode(billCode);
        // 如果单据是提交状态，则进行打开动作
        if (adjustBill.getBillState().equals(BillStateEnum.SUBMITTED)) {
            // 打开单据
            open(adjustBill);
        }
        // 如果是根据原料拣货，则需要去查询一下总部计划单里面的数据
        if (BasicEnum.BY_MATERIAL.equals(adjustBill.getBasicEnum())) {
            PlanBill planBill = planBillExtraService.findByBillCode(adjustBill.getRootCode());
            Set<PlanBillDetail> billDetails = planBill.getBillDetails();
            return toMapOneDTO(adjustBill, billDetails);
        } else {
            return toMapOneDTO(adjustBill, null);
        }
    }

    /**
     * map数据
     *
     * @param adjustBill       调拨单信息
     * @param addAdjustBillDTO
     */
    private void mapBill(AdjustBill adjustBill, AddAdjustBillDTO addAdjustBillDTO) {
        //设置单据作用
        adjustBill.setBillPurpose(BillPurposeEnum.OutStorage);
        //设置出库站点
        Station outLocation = new Station(addAdjustBillDTO.getOutStationCode());
        //设置出库库位
        outLocation.setStorage(addAdjustBillDTO.getOutStorage());
        adjustBill.setOutLocation(outLocation);
        //设置入站站点
        adjustBill.setInLocation(new Station(addAdjustBillDTO.getInStationCode()));
        //设置源单号
        if (isFromPlanBill(addAdjustBillDTO)) {
            adjustBill.setBillProperty(addAdjustBillDTO.getBillProperty());
            adjustBill.setRootCode(addAdjustBillDTO.getRootCode());
            adjustBill.setSourceCode(addAdjustBillDTO.getRootCode());
        }

        //设置计划备注
        adjustBill.setPlanMemo(addAdjustBillDTO.getPlanMemo());
        //设置计划详情
        adjustBill.getBillDetails().clear();
        adjustBill.getBillDetails().addAll(mapDetails(addAdjustBillDTO));
        //设置是按原料还是货物拣货
        adjustBill.setBasicEnum(addAdjustBillDTO.getBasicEnum());
        //设置出库备注
        adjustBill.setOutStorageMemo(addAdjustBillDTO.getOutStorageMemo());
        //设置所属站点
        adjustBill.setBelongStationCode(addAdjustBillDTO.getOutStationCode());
        //设置调剂数量
        adjustBill.setTotalAmount(sum(addAdjustBillDTO.getDetails(), on(AddAdjustBillDetailDTO.class).getShippedAmount()));
        //设置调剂种类
        adjustBill.setTotalVarietyAmount(addAdjustBillDTO.getDetails().size());

    }

    /**
     * 初始化adjustBill
     *
     * @param addAdjustBillDTO 前端dto
     * @return AdjustBill 调剂计划实体
     */
    private AdjustBill prepareAdjustBill(AddAdjustBillDTO addAdjustBillDTO) {
        AdjustBill adjustBill;
        if (!StringUtils.isEmpty(addAdjustBillDTO.getBillCode())) {
            //计划编码有由后端生成，如果前端传递回来的时候有code，就做更新操作
            adjustBill = adjustBillExtraService.findByBillCode(addAdjustBillDTO.getBillCode());
            if (adjustBill == null) {
                throw new DataException("432434", "没有找到该计划单");
            }
        } else {
            adjustBill = (AdjustBill) new BillFactory().createBill(BillTypeEnum.ADJUST);
            // 设置单据编码
            adjustBill.setBillCode(BillCodeManager.getBillCodeFun(ADJUST_OUT_STORAGE_PREFIX, adjustBill.getBelongStationCode()));
        }
        return adjustBill;
    }

    /**
     * 设置调剂计划详情
     *
     * @param addAdjustBillDTO 前端dto
     * @return 调剂计划详情集合
     */
    private Set<AdjustBillDetail> mapDetails(AddAdjustBillDTO addAdjustBillDTO) {
        Set<AdjustBillDetail> billDetails = new HashSet<>();
        for (AddAdjustBillDetailDTO addAdjustBillDetailDTO : addAdjustBillDTO.getDetails()) {
            AdjustBillDetail adjustBillDetail = new AdjustBillDetail();
            //原料或货物编码
            adjustBillDetail.setGoods(addAdjustBillDetailDTO.getRawMaterial());
            //设置所属原料编码便于分类
            adjustBillDetail.setBelongMaterialCode(addAdjustBillDetailDTO.getBelongMaterialCode());
            //应拣数量
            adjustBillDetail.setShippedAmount(addAdjustBillDetailDTO.getShippedAmount());
            //实拣数量
            adjustBillDetail.setActualAmount(addAdjustBillDetailDTO.getActualAmount());
            //备注信息
            billDetails.add(adjustBillDetail);
        }
        return billDetails;
    }

    /**
     * 是否是从总部计划单中来的
     *
     * @param addAdjustBillDTO
     * @return
     */
    private boolean isFromPlanBill(AddAdjustBillDTO addAdjustBillDTO) {

        return !StringUtils.isEmpty(addAdjustBillDTO.getRootCode());
    }


    /**
     * 查询单个mapDTO
     *
     * @param adjustBill
     * @param billDetails
     * @return
     */
    private QueryOneAdjustDTO toMapOneDTO(AdjustBill adjustBill, Set<PlanBillDetail> billDetails) {
        QueryOneAdjustDTO queryOneAdjustDTO = new QueryOneAdjustDTO();
        // 单据编号
        queryOneAdjustDTO.setBillCode(adjustBill.getBillCode());
        // 录单时间
        queryOneAdjustDTO.setCreateTime(adjustBill.getCreateTime());
        // 出库时间
        queryOneAdjustDTO.setOutWareHouseTime(adjustBill.getOutWareHouseTime());
        // 录单人
        queryOneAdjustDTO.setOperatorName(sharedManager.findOneByUserCode(adjustBill.getOperatorCode()));
        // 审核人
        queryOneAdjustDTO.setAuditorName(sharedManager.findOneByUserCode(adjustBill.getAuditPersonCode()));
        // 出库站点
        Station outLocation = (Station) adjustBill.getOutLocation();
        if (outLocation != null) {
            queryOneAdjustDTO.setOutStationCode(outLocation.getStationCode());
            Storage storage = outLocation.getStorage();
            if (storage != null) {
                // 出库库位
                queryOneAdjustDTO.setOutStorageCode(storage.getStorageCode());
            }
        }
        // 入库站点
        Station inLocation = (Station) adjustBill.getInLocation();
        if (inLocation != null) {
            queryOneAdjustDTO.setInStationCode(inLocation.getStationCode());
        }
        // 单据属性
        queryOneAdjustDTO.setBillTypeStr(adjustBill.getBillProperty());
        // 出库状态
        queryOneAdjustDTO.setOutStateEnum(adjustBill.getOutStateEnum());
        // 提交状态
        queryOneAdjustDTO.setSubmitState(adjustBill.getSubmitState());
        // 审核状态
        queryOneAdjustDTO.setAuditState(adjustBill.getAuditState());
        // 调剂数量
        queryOneAdjustDTO.setAdjustNumber(adjustBill.getTotalAmount());
        // 调剂品种数
        queryOneAdjustDTO.setVarietyNumber(adjustBill.getTotalVarietyAmount());
        // 计划备注
        queryOneAdjustDTO.setPlanMemo(adjustBill.getPlanMemo());
        // 出库备注
        queryOneAdjustDTO.setOutStorageMemo(adjustBill.getOutStorageMemo());
        // 审核意见
        queryOneAdjustDTO.setAuditMemo(adjustBill.getAuditMemo());
        // 设置原料拣货或者货物拣货
        queryOneAdjustDTO.setBasicEnum(adjustBill.getBasicEnum());
        // 调剂货物计划详情
        List<AdjustBillDetailDTO> detailDTOS = new ArrayList<>();
        Set<AdjustBillDetail> details = adjustBill.getBillDetails();
        if (details != null) {
            for (AdjustBillDetail adjustBillDetail : details) {
                AdjustBillDetailDTO adjustBillDetailDTO = new AdjustBillDetailDTO();
                adjustBillDetailDTO.setActualAmount(adjustBillDetail.getActualAmount());
                adjustBillDetailDTO.setShippedAmount(adjustBillDetail.getShippedAmount());
                adjustBillDetailDTO.setRawMaterial((RawMaterial) adjustBillDetail.getGoods());
                detailDTOS.add(adjustBillDetailDTO);
            }
            queryOneAdjustDTO.setDetails(detailDTOS);
        }
        // 调剂原料计划详情
        List<AdjustBillMaterialDetailDTO> detailDTOs = new ArrayList<>();
        if (billDetails != null) {
            for (PlanBillDetail planBillDetail : billDetails) {
                AdjustBillMaterialDetailDTO dto = new AdjustBillMaterialDetailDTO();
                // 应拣数量
                dto.setShippedAmount(planBillDetail.getAmount());
                // 原料信息
                RawMaterial rawMaterial = (RawMaterial) planBillDetail.getGoods();
                dto.setRawMaterial(rawMaterial);
            }
            queryOneAdjustDTO.setMaterialDetails(detailDTOs);
        }
        return queryOneAdjustDTO;
    }

    /**
     * 前端多条件查询转换DTO
     *
     * @param adjustBill
     * @return
     */
    private AdjustBillDTO toMapConditionsDTO(AdjustBill adjustBill) {
        AdjustBillDTO adjustBillDTO = new AdjustBillDTO();
        // 单据属性
        adjustBillDTO.setBillProperty(adjustBill.getBillProperty());
        // 出库状态
        adjustBillDTO.setOutStatusCode(adjustBill.getOutStateEnum());
        // 提交状态
        adjustBillDTO.setSubmitState(adjustBill.getSubmitState().name());
        // 审核状态
        adjustBillDTO.setAuditState(adjustBill.getAuditState().name());
        //单据状态
        adjustBillDTO.setBillState(adjustBill.getBillState());
        // 发起单号
        adjustBillDTO.setRootCode(adjustBill.getRootCode());
        //来源单号
        adjustBillDTO.setSourceCode(adjustBill.getSourceCode());
        // 单据编码
        adjustBillDTO.setBillCode(adjustBill.getBillCode());
        // 录单时间
        adjustBillDTO.setCreateTime(adjustBill.getCreateTime());
        // 出库时间
        adjustBillDTO.setOutWareHouseTime(adjustBill.getOutWareHouseTime());
        // 录单人
        adjustBillDTO.setOperatorName(sharedManager.findOneByUserCode(adjustBill.getOperatorCode()));
        // 审核人
        adjustBillDTO.setAuditorName(sharedManager.findOneByUserCode(adjustBill.getAuditPersonCode()));
        adjustBillDTO.setBasicEnum(adjustBill.getBasicEnum());
        // 出库站点
        Station outLocation = (Station) adjustBill.getOutLocation();
        if (outLocation != null) {
            adjustBillDTO.setOutStationCode(outLocation.getStationCode());
        }
        // 入库站点
        Station inLocation = (Station) adjustBill.getInLocation();
        if (inLocation != null) {
            adjustBillDTO.setInStationCode(inLocation.getStationCode());
        }
        // 配送数量
        adjustBillDTO.setAdjustNumber(adjustBill.getTotalAmount());
        // 配送品种数
        adjustBillDTO.setVarietyNumber(adjustBill.getTotalVarietyAmount());
        return adjustBillDTO;

    }
}
