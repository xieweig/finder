package cn.sisyphe.coffee.bill.application.plan;

import ch.lambdaj.group.Group;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillStationDTO;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.plan.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.plan.PlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.PlanBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillGoodsDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillLocationDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDetailDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ch.lambdaj.Lambda.by;
import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.sum;

/**
 * 计划单据manager
 *
 * @author ncmao
 */
@Service
public class PlanBillManager extends AbstractBillExtraManager<PlanBill, PlanBillDTO, ConditionQueryPlanBill> {


    @Autowired
    public PlanBillManager(BillRepository<PlanBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<PlanBill, ConditionQueryPlanBill> billExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager);
    }

    /**
     * 单据类型
     *
     * @return billType
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.PLAN;
    }

    /**
     * 查询子计划单
     *
     * @param billCode     单号
     * @param billTypeEnum 单据类型
     * @return 子计划单据
     */
    public ChildPlanBillDTO findChildPlanBillByBillCode(String billCode, BillTypeEnum billTypeEnum) {
        PlanBill planBill = ((PlanBillExtraService) getBillExtraService()).findByBillCodeAndSpecificBillType(billCode, billTypeEnum);
        return mapChildPlanBillToDTO(planBill);
    }

    /**
     * 多条件查询子计划查询
     *
     * @param conditionQueryPlanBill 计划条件查询
     * @return 查询出来的子计划单据
     */

    public Page<ChildPlanBillDTO> findChildPlanBillByCondition(ConditionQueryPlanBill conditionQueryPlanBill, BillTypeEnum specificBillType) {
        conditionQueryPlanBill.setHqBill(false);
        if (!StringUtils.isEmpty(conditionQueryPlanBill.getOperatorName())) {
            // SpringCloud调用查询用户编码
            List<String> userCodeList = getSharedManager().findByLikeUserName(conditionQueryPlanBill.getOperatorName());
            conditionQueryPlanBill.setOperatorCodeList(userCodeList);
        }

        //将specificType转为List
        List<BillTypeEnum> specificBillTypes = new ArrayList<>();
        specificBillTypes.add(specificBillType);
        conditionQueryPlanBill.setSpecificBillType(specificBillTypes);
        Page<PlanBill> billPage = getBillExtraService().findPageByCondition(conditionQueryPlanBill);

        return billPage.map(planBill -> mapChildPlanBillToDTO(planBill));
    }

    @Override
    protected PlanBill dtoToBill(PlanBill bill, PlanBillDTO billDTO) {
        map(bill, billDTO);
        return bill;
    }

    /**
     * 审核通过否
     *
     * @param billCode        单据编码
     * @param auditPersonCode 审核人编码
     * @param isSuccess       是否审核成功
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public PlanBillDTO auditBill(String billCode, String auditPersonCode, String auditMemo, boolean isSuccess) {
        PlanBill planBill = (PlanBill) findOneByBillCode(billCode);
        planBill.setAuditPersonCode(auditPersonCode);
        planBill.setAuditMemo(auditMemo);
        if (isSuccess) {
            mapForSplit(planBill);
        }

        audit(planBill, isSuccess);
        return super.billToDto(planBill);
    }

    /**
     * 将前端传过来的数据进行转换
     *
     * @param planBill    计划单
     * @param planBillDTO 前端DTO
     */
    private void map(PlanBill planBill, PlanBillDTO planBillDTO) {
        planBill.getBillDetails().clear();
        planBill.setSpecificBillType(planBillDTO.getSpecificBillType());
        planBill.setBillName(planBillDTO.getBillName());
        planBill.setPlanMemo(planBillDTO.getPlanMemo());
        planBill.setHqBill(true);
        planBill.setBillType(BillTypeEnum.PLAN);
        planBill.setBasicEnum(planBillDTO.getBasicEnum());
        planBill.setBelongStationCode(planBill.getBelongStationCode());
        for (PlanBillDetailDTO planBillDetailDTO : planBillDTO.getBillDetails()) {
            for (PlanBillStationDTO planBillStationDTO : planBillDetailDTO.getPlanBillStationDTOS()) {
                PlanBillDetail planBillDetail = new PlanBillDetail();
                planBillDetail.setShippedAmount(planBillStationDTO.getAmount());
                planBillDetail.setInLocation(getLocation(planBillStationDTO.getInStation()));
                planBillDetail.setOutLocation(getLocation(planBillStationDTO.getOutStation()));
                planBillDetail.setGoods(planBillDetailDTO.getRawMaterial());
                planBill.getBillDetails().add(planBillDetail);
            }
        }
        planBill.setBillPurpose(BillPurposeEnum.PLAN);

    }

    /**
     * 如果前端传递过来类型是供应商，则new供应商对象
     *
     * @param station 前端站点
     * @return 供应商或站点
     */
    private AbstractLocation getLocation(Station station) {
        if (StationType.SUPPLIER.equals(station.getStationType())) {
            Supplier supplier = new Supplier(station.getStationCode());
            supplier.setSupplierName(station.getStationName());
            return supplier;
        }
        return new Station(station.getStationCode());
    }

    /**
     * 查询站点信息
     *
     * @param planBill 计划单
     */
    private void mapForSplit(PlanBill planBill) {
        for (PlanBillDetail planBillDetail : planBill.getBillDetails()) {
            planBillDetail.setInLocation(getRealLocation(planBillDetail.getInLocation()));
            planBillDetail.setOutLocation(getRealLocation(planBillDetail.getOutLocation()));
            planBillDetail.setTransferLocation(getTransferLocation(planBillDetail));
        }
    }

    /**
     * 获取真实站点
     *
     * @param abstractLocation 数据库站点station
     * @return cloud查询出来的站点
     */
    private AbstractLocation getRealLocation(AbstractLocation abstractLocation) {
        if (abstractLocation instanceof Station) {
            return getSharedManager().findStationByStationCode(abstractLocation.code());
        }
        if (abstractLocation instanceof Supplier) {
            return getSharedManager().findSupplierBySupplierCode(abstractLocation.code());
        }
        throw new DataException("xxx", "站点转换错误");
    }

    /**
     * 获取中转的物流站点
     *
     * @param planBillDetail
     * @return
     */
    private Station getTransferLocation(PlanBillDetail planBillDetail) {
        if (planBillDetail.getOutLocation() instanceof Station && StationType.STORE.equals(((Station) planBillDetail.getOutLocation()).getStationType())
                && planBillDetail.getInLocation() instanceof Supplier) {
            Station transferStation = new Station(getSharedManager().findLogisticCodeByStationCode(planBillDetail.getOutLocation().code()));
            transferStation.setStationType(StationType.LOGISTICS);
            return transferStation;
        }
        return null;
    }

    /**
     * 拣货操作
     *
     * @param billCode
     * @param operationState
     */
    public void operationPickGood(String billCode, OperationStateEnum operationState) {
        PlanBill planBill = getBillExtraService().findByBillCode(billCode);

        if (planBill == null || OperationStateEnum.OPERATION.equals(planBill.getOperationState())) {
            return;
        }

        if (getBillExtraService() instanceof PlanBillExtraService) {
            ((PlanBillExtraService) getBillExtraService()).updateOperationStateByBill(planBill, operationState);
        }
    }

    /**
     * 根据编号查询
     *
     * @param billCode
     * @return
     */
    public ResultPlanBillDTO findHqPlanBillByBillCode(String billCode) throws DataException {
        PlanBill planBill = getBillExtraService().findByBillCode(billCode);
        return planBillToResultPlanBillDTO(planBill);
    }

    /**
     * 根据条件查询
     *
     * @param conditionQueryPlanBill 条件查询参数
     * @return
     */
    public Page<ResultPlanBillDTO> findHqPlanBillByConditions(ConditionQueryPlanBill conditionQueryPlanBill) throws DataException {
        conditionQueryPlanBill.setHqBill(true);
        //通过前端查询出来的cargoname，模糊查询货物编码
        if (!StringUtils.isEmpty(conditionQueryPlanBill.getCargoName())) {
            conditionQueryPlanBill.setCargoCodes(sharedManager.findCargoCodesByCargoName(conditionQueryPlanBill.getCargoName()));
        }
        //将操作人名字转成操作人编号
        if (!StringUtils.isEmpty(conditionQueryPlanBill.getOperatorName())) {
            conditionQueryPlanBill.setOperatorCodeList(sharedManager.findByLikeUserName(conditionQueryPlanBill.getOperatorName()));
        }
        Page<PlanBill> planBills = getBillExtraService().findPageByCondition(conditionQueryPlanBill);
        return planBills.map(planBill -> planBillToResultPlanBillDTO(planBill));
    }


    /**
     * 转换成子计划单DTO
     *
     * @param childPlanBill 子计划单
     * @return 子计划单DTO
     */
    private ChildPlanBillDTO mapChildPlanBillToDTO(PlanBill childPlanBill) {
        ChildPlanBillDTO childPlanBillDTO = new ChildPlanBillDTO();
        //拣货状态
        childPlanBillDTO.setOperationState(childPlanBill.getOperationState());

        childPlanBillDTO.setBillCode(childPlanBill.getBillCode());
        childPlanBillDTO.setMemo(childPlanBill.getPlanMemo());
        childPlanBillDTO.setBillType(childPlanBill.getSpecificBillType());
        childPlanBillDTO.setCreateTime(childPlanBill.getCreateTime());
        /*        childPlanBillDTO.setReceiveBillCode(childPlanBill.getReceiveBillCode());*/
        childPlanBillDTO.setOutStationCode(childPlanBill.getOutLocation().code());
        childPlanBillDTO.setInStationCode(childPlanBill.getInLocation().code());
        childPlanBillDTO.setBasicEnum(childPlanBill.getBasicEnum());
        //通过springCloud设置operatorName
        String userName = sharedManager.findOneByUserCode(childPlanBill.getOperatorCode());
        childPlanBillDTO.setOperatorName(userName);
        childPlanBillDTO.setTypeAmount(childPlanBill.getBillDetails().size());
        childPlanBillDTO.setTotalAmount(sum(childPlanBill.getBillDetails(), on(BillDetail.class).getShippedAmount()));
        childPlanBillDTO.setBillState(childPlanBill.getBillState());
        childPlanBillDTO.setSubmitState(childPlanBill.getSubmitState());
        childPlanBillDTO.setAuditState(childPlanBill.getAuditState());
        childPlanBillDTO.setRootCode(childPlanBill.getRootCode());

        childPlanBillDTO.setChildPlanBillDetails(mapChildPlanBillDetails(childPlanBill));
        return childPlanBillDTO;
    }

    private List<ChildPlanBillDetailDTO> mapChildPlanBillDetails(PlanBill childPlanBill) {
        List<ChildPlanBillDetailDTO> childPlanBillDetailDTOS = new ArrayList<>();
        for (PlanBillDetail planBillDetail : childPlanBill.getBillDetails()) {
            ChildPlanBillDetailDTO childPlanBillDetailDTO = new ChildPlanBillDetailDTO();
            childPlanBillDetailDTO.setAmount(planBillDetail.getShippedAmount());
            RawMaterial rawMaterial = new RawMaterial();
            if (planBillDetail.getGoods() instanceof Cargo) {
                rawMaterial.setCargo((Cargo) planBillDetail.getGoods());
            } else {
                rawMaterial = (RawMaterial) planBillDetail.getGoods();
            }
            childPlanBillDetailDTO.setRawMaterial(rawMaterial);
            childPlanBillDetailDTOS.add(childPlanBillDetailDTO);

        }
        return childPlanBillDetailDTOS;
    }


    /**
     * 将总部计划PlanBill 转为 ResultPlanBillDTO
     *
     * @param planBill
     * @return
     */
    private ResultPlanBillDTO planBillToResultPlanBillDTO(PlanBill planBill) {
        if (planBill == null) {
            return null;
        }
        ResultPlanBillDTO resultPlanBillDTO = new ResultPlanBillDTO();
        resultPlanBillDTO.setBillCode(planBill.getBillCode());
        resultPlanBillDTO.setBillName(planBill.getBillName());
        resultPlanBillDTO.setBillType(planBill.getSpecificBillType());
        resultPlanBillDTO.setBasicEnum(planBill.getBasicEnum());
        resultPlanBillDTO.setAuditMemo(planBill.getAuditMemo());
        resultPlanBillDTO.setCreateTime(planBill.getCreateTime());
        resultPlanBillDTO.setBillSubmitState(planBill.getSubmitState());
        resultPlanBillDTO.setAuditState(planBill.getAuditState());
        resultPlanBillDTO.setBillState(planBill.getBillState());
        resultPlanBillDTO.setOperatorName(planBill.getOperatorCode());
        resultPlanBillDTO.setAuditorName(planBill.getAuditPersonCode());
        resultPlanBillDTO.setMemo(planBill.getPlanMemo());
        resultPlanBillDTO.setAuditMemo(planBill.getAuditMemo());
        Set<ResultPlanBillGoodsDTO> resultPlanBillGoodsDTOSet = new HashSet<>();
        if (planBill.getBillDetails() == null) {
            resultPlanBillDTO.setPlanBillDetails(resultPlanBillGoodsDTOSet);
            return resultPlanBillDTO;
        }

        Group<PlanBillDetail> groupedPlanBillDetail = group(planBill.getBillDetails(), by(on(PlanBillDetail.class).getGoods().code()));
        for (String head : groupedPlanBillDetail.keySet()) {
            ResultPlanBillGoodsDTO resultPlanBillGoodsDTO = new ResultPlanBillGoodsDTO();
            List<PlanBillDetail> planBillDetails = groupedPlanBillDetail.find(head);
            PlanBillDetail firstPlanBillDetail = planBillDetails.get(0);
            if (firstPlanBillDetail.getGoods() != null && !"".equals(firstPlanBillDetail.getGoods().code())) {
                resultPlanBillGoodsDTO.setGoodsCode(firstPlanBillDetail.getGoods().code());
            }
            Set<ResultPlanBillLocationDTO> resultPlanBillLocationDTOSet = new HashSet<>();
            for (PlanBillDetail planBillDetail : planBillDetails) {
                ResultPlanBillLocationDTO resultPlanBillLocationDTO = new ResultPlanBillLocationDTO();
                resultPlanBillLocationDTO.setOutLocation(planBillDetail.getOutLocation());
                resultPlanBillLocationDTO.setInLocation(planBillDetail.getInLocation());
                resultPlanBillLocationDTO.setAmount(planBillDetail.getShippedAmount());
                resultPlanBillLocationDTOSet.add(resultPlanBillLocationDTO);
            }
            resultPlanBillGoodsDTO.setResultPlanBillDetailDTOSet(resultPlanBillLocationDTOSet);
            resultPlanBillGoodsDTOSet.add(resultPlanBillGoodsDTO);
        }
        resultPlanBillDTO.setPlanBillDetails(resultPlanBillGoodsDTOSet);
        return resultPlanBillDTO;
    }
}
