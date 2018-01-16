package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillMaterialDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.adjust.QueryOneAdjustDTO;
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
public class AdjustBillManager extends AbstractBillExtraManager<AdjustBill, ConditionQueryAdjustBill> {

    @Autowired
    private PlanBillExtraService planBillExtraService;
    @Autowired
    private AdjustBillExtraService adjustBillExtraService;
    @Autowired
    private SharedManager sharedManager;

    @Autowired
    public AdjustBillManager(BillRepository<AdjustBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<AdjustBill, ConditionQueryAdjustBill> billExtraService, PlanBillExtraService planBillExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, planBillExtraService, sharedManager);
    }


    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.ADJUST;
    }




    /**
     * 根据sourceCode查询单据
     *
     * @param sourceCode
     * @return
     */
    public AdjustBill findAdjustBillBySourceCode(String sourceCode) {
        return adjustBillExtraService.findBySourceCode(sourceCode);
    }

    /**
     * map数据
     *
     * @param adjustBill       调拨单信息
     * @param addAdjustBillDTO
     */
    private void mapBill(AdjustBill adjustBill, AddAdjustBillDTO addAdjustBillDTO) {
        //操作人编码
        adjustBill.setOperatorCode(addAdjustBillDTO.getOperatorCode());
        //审核人编码
        adjustBill.setAuditPersonCode(addAdjustBillDTO.getAuditPersonCode());
        //设置单据作用
        adjustBill.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
        //设置出库站点
        Station outLocation = new Station(addAdjustBillDTO.getOutStationCode());
        //设置出库库位
        outLocation.setStorage(addAdjustBillDTO.getOutStorage());
        //设置出库信息
        adjustBill.setOutLocation(outLocation);
        //设置入站站点
        Station inLocation = new Station(addAdjustBillDTO.getInStationCode());
        inLocation.setStorage(addAdjustBillDTO.getInStorage());
        adjustBill.setInLocation(inLocation);
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


//    /**
//     * 查询单个mapDTO
//     *
//     * @param adjustBill
//     * @param billDetails
//     * @return
//     */
//    @Override
//    private QueryOneAdjustDTO toMapOneDTO(AdjustBill adjustBill, Set<PlanBillDetail> billDetails) {
//        QueryOneAdjustDTO queryOneAdjustDTO = new QueryOneAdjustDTO();
//        // 单据编号
//        queryOneAdjustDTO.setBillCode(adjustBill.getBillCode());
//        // 录单时间
//        queryOneAdjustDTO.setCreateTime(adjustBill.getCreateTime());
//        // 出/入库时间
//        queryOneAdjustDTO.setInOrOutWareHouseTime(adjustBill.getOutWareHouseTime());
//        // 录单人
//        queryOneAdjustDTO.setOperatorName(sharedManager.findOneByUserCode(adjustBill.getOperatorCode()));
//        // 审核人
//        queryOneAdjustDTO.setAuditorName(sharedManager.findOneByUserCode(adjustBill.getAuditPersonCode()));
//        // 出库站点
//        Station outLocation = (Station) adjustBill.getOutLocation();
//        if (outLocation != null) {
//            queryOneAdjustDTO.setOutStationCode(outLocation.getStationCode());
//            Storage storage = outLocation.getStorage();
//            if (storage != null) {
//                // 出库库位
//                queryOneAdjustDTO.setOutStorageCode(storage.getStorageCode());
//            }
//        }
//        // 入库站点
//        Station inLocation = (Station) adjustBill.getInLocation();
//        if (inLocation != null) {
//            queryOneAdjustDTO.setInStationCode(inLocation.getStationCode());
//        }
//        // 单据属性
//        queryOneAdjustDTO.setBillTypeStr(adjustBill.getBillProperty());
//        // 出库状态
//        queryOneAdjustDTO.setOutStateEnum(adjustBill.getOutStateEnum());
//        // 提交状态
//        queryOneAdjustDTO.setSubmitState(adjustBill.getSubmitState());
//        // 审核状态
//        queryOneAdjustDTO.setAuditState(adjustBill.getAuditState());
//        // 调剂数量
//        queryOneAdjustDTO.setAdjustNumber(adjustBill.getTotalAmount());
//        // 调剂品种数
//        queryOneAdjustDTO.setVarietyNumber(adjustBill.getTotalVarietyAmount());
//        // 计划备注
//        queryOneAdjustDTO.setPlanMemo(adjustBill.getPlanMemo());
//        // 出库备注
//        queryOneAdjustDTO.setOutStorageMemo(adjustBill.getOutStorageMemo());
//        // 审核意见
//        queryOneAdjustDTO.setAuditMemo(adjustBill.getAuditMemo());
//        // 设置原料拣货或者货物拣货
//        queryOneAdjustDTO.setBasicEnum(adjustBill.getBasicEnum());
//        // 调剂货物计划详情
//        List<AdjustBillDetailDTO> detailDTOS = new ArrayList<>();
//        Set<AdjustBillDetail> details = adjustBill.getBillDetails();
//        if (details != null) {
//            for (AdjustBillDetail adjustBillDetail : details) {
//                AdjustBillDetailDTO adjustBillDetailDTO = new AdjustBillDetailDTO();
//                adjustBillDetailDTO.setActualAmount(adjustBillDetail.getActualAmount());
//                adjustBillDetailDTO.setShippedAmount(adjustBillDetail.getShippedAmount());
//                adjustBillDetailDTO.setRawMaterial((RawMaterial) adjustBillDetail.getGoods());
//                detailDTOS.add(adjustBillDetailDTO);
//            }
//            queryOneAdjustDTO.setDetails(detailDTOS);
//        }
//        // 调剂原料计划详情
//        List<AdjustBillMaterialDetailDTO> detailDTOs = new ArrayList<>();
//        if (billDetails != null) {
//            for (PlanBillDetail planBillDetail : billDetails) {
//                AdjustBillMaterialDetailDTO dto = new AdjustBillMaterialDetailDTO();
//                // 应拣数量
//                dto.setShippedAmount(planBillDetail.getShippedAmount());
//                // 原料信息
//                RawMaterial rawMaterial = (RawMaterial) planBillDetail.getGoods();
//                dto.setRawMaterial(rawMaterial);
//            }
//            queryOneAdjustDTO.setMaterialDetails(detailDTOs);
//        }
//        return queryOneAdjustDTO;
//    }

    /**
     * 前端多条件查询转换DTO
     *
     * @param adjustBill
     * @return
     */
    @Override
    protected AdjustBillDTO toMapConditionsDTO(AdjustBill adjustBill) {
        AdjustBillDTO adjustBillDTO = new AdjustBillDTO();
        // 单据属性
        adjustBillDTO.setBillProperty(adjustBill.getBillProperty());
        // 出库状态
        adjustBillDTO.setOutStatusCode(adjustBill.getOutStateEnum());
        // 提交状态
        adjustBillDTO.setSubmitState(adjustBill.getSubmitState());
        // 审核状态
        adjustBillDTO.setAuditState(adjustBill.getAuditState());
        // 单据状态
        adjustBillDTO.setBillState(adjustBill.getBillState());
        // 发起单号
        adjustBillDTO.setRootCode(adjustBill.getRootCode());
        // 来源单号
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
