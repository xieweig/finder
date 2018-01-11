package cn.sisyphe.coffee.bill.application.plan;

import ch.lambdaj.group.Group;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDetailDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillStationDTO;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.plan.AuditPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillGoodsDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillLocationDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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
public class PlanBillManager extends AbstractBillManager<PlanBill> {

    @Autowired
    private PlanBillExtraService planBillExtraService;

    @Autowired
    private SharedManager sharedManager;

    @Autowired
    public PlanBillManager(BillRepository<PlanBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    /**
     * 创建计划单
     *
     * @param operatorCode
     * @param planBillDTO  计划单DTO
     * @param operatorCode
     * @return billcode
     */

    public String create(PlanBillDTO planBillDTO, String operatorCode) {
        PlanBill planBill = preparePlanBill(planBillDTO);
        planBill.setOperatorCode(operatorCode);
        map(planBill, planBillDTO);
        return save(planBill).getBillCode();
    }

    private PlanBill preparePlanBill(PlanBillDTO planBillDTO) {
        PlanBill planBill;
        if (!StringUtils.isEmpty(planBillDTO.getBillCode())) {
            //计划编码有由后端生成，如果前端传递回来的时候有code，就做更新操作
            planBill = planBillExtraService.findByBillCode(planBillDTO.getBillCode());
            if (planBill == null) {
                throw new DataException("xxxx", "没有找到该计划单");
            }
        } else {
            planBill = (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);
            planBill.setBillCode(generateBillCode());
        }
        return planBill;
    }

    //todo 测试
    private String generateBillCode() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 参数校验
     *
     * @param planBillDTO 前端传递过来的数据
     */

    private void validate(PlanBillDTO planBillDTO) {
        checkCreateParam(planBillDTO);
    }

    /**
     * 提交计划单进行审核
     *
     * @param planBillDTO  前端传过来的DTO
     * @param operatorCode
     */
    public String submit(PlanBillDTO planBillDTO, String operatorCode) {
        validate(planBillDTO);
        PlanBill planBill = preparePlanBill(planBillDTO);
        planBill.setOperatorCode(operatorCode);
        map(planBill, planBillDTO);
        return submit(planBill).getBillCode();
    }

    /**
     * 查看总部计划，状态变更为审核中，两种情况，一种点击查看按钮，一种点击审核按钮
     *
     * @param billCode     计划单编码
     * @param operatorCode
     * @return ResultPlanBillDTO
     */
    public ResultPlanBillDTO open(String billCode, String operatorCode) {
        PlanBill planBill = planBillExtraService.findByBillCode(billCode);
        if (BillStateEnum.SUBMITTED.equals(planBill.getBillState()) && !planBill.getOperatorCode().equals(operatorCode)) {
            open(planBill);
            return planBillToResultPlanBillDTO(planBill);
        }
        return planBillToResultPlanBillDTO(planBill);
    }

    /**
     * 审核不通过
     *
     * @param auditPlanBillDTO 计划单审核DTO
     */

    public void unPass(AuditPlanBillDTO auditPlanBillDTO, String operatorCode) {
        PlanBill planBill = planBillExtraService.findByBillCode(auditPlanBillDTO.getBillCode());
        planBill.setAuditMemo(auditPlanBillDTO.getAuditMemo());
        planBill.setAuditPersonCode(operatorCode);
        audit(planBill, false);

    }

    /**
     * 审核通过，然后进行计划单切片
     *
     * @param auditPlanBillDTO 计划单审核DTO
     * @param operatorCode
     */

    @Transactional(rollbackFor = RuntimeException.class)
    public void pass(AuditPlanBillDTO auditPlanBillDTO, String operatorCode) {
        PlanBill planBill = planBillExtraService.findByBillCode(auditPlanBillDTO.getBillCode());
        planBill.setAuditMemo(auditPlanBillDTO.getAuditMemo());
        planBill.setAuditPersonCode(operatorCode);
        mapForSplit(planBill);
        audit(planBill, true);

    }

    //因为数据库只保存了code，所以在做切片的时候要去查询基础资料，把站点类型拿过来
    private void mapForSplit(PlanBill planBill) {
        mapRealLocation(planBill);
    }

    /**
     * 组装切片站点信息
     *
     * @param planBill 计划单
     */

    private void mapRealLocation(PlanBill planBill) {
        for (PlanBillDetail planBillDetail : planBill.getBillDetails()) {
            planBillDetail.setInLocation(getRealLocation(planBillDetail.getInLocation()));
            planBillDetail.setOutLocation(getRealLocation(planBillDetail.getOutLocation()));
            planBillDetail.setTransferLocation(getTransferLocation(planBillDetail));
        }
    }

    private AbstractLocation getRealLocation(AbstractLocation abstractLocation) {
        if (abstractLocation instanceof Station) {
            return sharedManager.findStationByStationCode(abstractLocation.code());
        }
        if (abstractLocation instanceof Supplier) {
            return sharedManager.findSupplierBySupplierCode(abstractLocation.code());
        }
        throw new DataException("xxx", "站点转换错误");
    }

    //将前端传过来的数据进行转换
    private void map(PlanBill planBill, PlanBillDTO planBillDTO) {
        planBill.getBillDetails().clear();
        planBill.setSpecificBillType(planBillDTO.getBillType());
        planBill.setBillName(planBillDTO.getBillName());
        planBill.setPlanMemo(planBillDTO.getMemo());
        planBill.setHqBill(true);
        planBill.setBasicEnum(planBillDTO.getBasicEnum());
        for (PlanBillDetailDTO planBillDetailDTO : planBillDTO.getPlanBillDetailDTOS()) {
            for (PlanBillStationDTO planBillStationDTO : planBillDetailDTO.getPlanBillStationDTOS()) {
                PlanBillDetail planBillDetail = new PlanBillDetail();
                planBillDetail.setAmount(planBillStationDTO.getAmount());
                planBillDetail.setInLocation(getLocation(planBillStationDTO.getInStation()));
                planBillDetail.setOutLocation(getLocation(planBillStationDTO.getOutStation()));
                planBillDetail.setGoods(mapGoods(planBillDetailDTO.getRawMaterialCode(), planBillDetailDTO.getCargoCode(), planBill.getBasicEnum()));
                planBill.getBillDetails().add(planBillDetail);
            }
        }
        planBill.setBillPurpose(BillPurposeEnum.Plan);

    }

    //g根据前端传递过来的货物类型进行map，如果是按照货物分类则是cargo，其他这是原料
    private AbstractGoods mapGoods(String rawMaterialCode, String cargoCode, BasicEnum basicEnum) {
        RawMaterial rawMaterial = new RawMaterial(rawMaterialCode);
        if (!StringUtils.isEmpty(cargoCode)) {
            Cargo cargo = new Cargo(cargoCode);
            rawMaterial.setCargo(cargo);
        }
        return rawMaterial;
    }

    //如果前端传递过来类型是供应商，则new供应商对象
    private AbstractLocation getLocation(Station station) {
        if (StationType.SUPPLIER.equals(station.getStationType())) {
            Supplier supplier = new Supplier(station.getStationCode());
            supplier.setSupplierName(station.getStationName());
            return supplier;
        }
        return new Station(station.getStationCode());
    }

    private Station getTransferLocation(PlanBillDetail planBillDetail) {
        if (planBillDetail.getOutLocation() instanceof Station && StationType.STORE.equals(((Station) planBillDetail.getOutLocation()).getStationType())
                && planBillDetail.getInLocation() instanceof Supplier) {
            Station transferStation = new Station(sharedManager.findLogisticCodeByStationCode(planBillDetail.getOutLocation().code()));
            transferStation.setStationType(StationType.LOGISTICS);
            return transferStation;
        }
        return null;
    }

    /**
     * @param conditionQueryPlanBill 条件查询
     * @return QueryPlanBillDTO 前端页面展示
     * @throws DataException
     */
    public Page<ResultPlanBillDTO> findPageByCondition(ConditionQueryPlanBill conditionQueryPlanBill) throws DataException {
        //1 根据具体的运单号查询,只有唯一的显示，显示一条
        //2 根据配送出库查询可能会有多条
        //3 所有都是模糊匹配
        //所有的产品表中的数据

        //查询总部计划
        Page<PlanBill> planBillPage = planBillExtraService.findPageByCondition(conditionQueryPlanBill);
        return planBillPage.map(this::planBillToResultPlanBillDTO);

    }

    /**
     * 根据编号查询
     *
     * @param billCode
     * @return
     */
    public ResultPlanBillDTO findHqPlanBillByBillCode(String billCode) throws DataException {
        return planBillToResultPlanBillDTO(planBillExtraService.findByBillCode(billCode));
    }

    /**
     * 将总部计划PlanBill 转为 ResultPlanBillDTO
     *
     * @param planBill
     * @return
     */
    private ResultPlanBillDTO planBillToResultPlanBillDTO(PlanBill planBill) {
        ResultPlanBillDTO resultPlanBillDTO = new ResultPlanBillDTO();
        if (planBill == null) {
            return resultPlanBillDTO;
        }
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
                resultPlanBillLocationDTO.setAmount(planBillDetail.getAmount());
                resultPlanBillLocationDTOSet.add(resultPlanBillLocationDTO);
            }
            resultPlanBillGoodsDTO.setResultPlanBillDetailDTOSet(resultPlanBillLocationDTOSet);
            resultPlanBillGoodsDTOSet.add(resultPlanBillGoodsDTO);
        }
        resultPlanBillDTO.setPlanBillDetails(resultPlanBillGoodsDTOSet);
        return resultPlanBillDTO;
    }

    /**
     * 保存前的参数检查
     *
     * @param planBillDTO
     * @throws DataException
     */
    private void checkCreateParam(PlanBillDTO planBillDTO) throws DataException {
        if (StringUtils.isEmpty(planBillDTO.getBillName())) {
            throw new DataException("", "单据名称不能为空");
        }
        if (StringUtils.isEmpty(planBillDTO.getBillType())) {
            throw new DataException("", "单据类型不能为空");
        }
        if (StringUtils.isEmpty(planBillDTO.getMemo())) {
            throw new DataException("", "单据备注");
        }
        if (StringUtils.isEmpty(planBillDTO.getPlanBillDetailDTOS()) || planBillDTO.getPlanBillDetailDTOS().size() <= 0) {
            throw new DataException("", "单据明细不能为空");
        }
    }

    /**
     * 查询切分出来的子计划单
     *
     * @param billCode 计划单编码
     * @param billType
     * @return ChildPlanBillDTO
     */
    public ChildPlanBillDTO findChildPlanBillByBillCodeAndType(String billCode, BillTypeEnum billType) {
        if (billType != null) {
            PlanBill planBill = planBillExtraService.findByBillCodeAndType(billCode, billType);
            return mapChildPlanBillToDTO(planBill);
        }
        PlanBill planBill = planBillExtraService.findByBillCode(billCode);
        return mapChildPlanBillToDTO(planBill);
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
        childPlanBillDTO.setTotalAmount(sum(childPlanBill.getBillDetails(), on(BillDetail.class).getAmount()));
        childPlanBillDTO.setBillState(childPlanBill.getBillState());
        childPlanBillDTO.setSubmitState(childPlanBill.getSubmitState());
        childPlanBillDTO.setAuditState(childPlanBill.getAuditState());
        childPlanBillDTO.setProgress(childPlanBill.getProgress());
        childPlanBillDTO.setRootCode(childPlanBill.getRootCode());

        List<ChildPlanBillDetailDTO> childPlanBillDetailDTOS = new ArrayList<>();
        for (PlanBillDetail planBillDetail : childPlanBill.getBillDetails()) {
            ChildPlanBillDetailDTO childPlanBillDetailDTO = new ChildPlanBillDetailDTO();
            childPlanBillDetailDTO.setAmount(planBillDetail.getAmount());
            RawMaterial rawMaterial = new RawMaterial();
            if (planBillDetail.getGoods() instanceof Cargo) {
                rawMaterial.setCargo((Cargo) planBillDetail.getGoods());
            } else {
                rawMaterial = (RawMaterial) planBillDetail.getGoods();
            }
            childPlanBillDetailDTO.setRawMaterial(rawMaterial);
            childPlanBillDetailDTOS.add(childPlanBillDetailDTO);

        }
        childPlanBillDTO.setChildPlanBillDetails(childPlanBillDetailDTOS);
        return childPlanBillDTO;
    }

    public Page<ChildPlanBillDTO> findChildPlanBillByCondition(ConditionQueryPlanBill conditionQueryPlanBill) {
        Page<PlanBill> childPlanBill = planBillExtraService.findChildPlanBillBy(conditionQueryPlanBill);

        return childPlanBill.map(this::mapChildPlanBillToDTO);
    }

    public void updateChildProgress(String billCode, BigDecimal progress) {
        PlanBill planBill = planBillExtraService.findByBillCode(billCode);
        planBill.setProgress(progress);
        planBillExtraService.save(planBill);
    }


    /**
     * @param billCode
     * @param operationState
     */
    public void operationPickGood(String billCode, OperationStateEnum operationState) {
        PlanBill planBill = planBillExtraService.findByBillCode(billCode);
        if (planBill == null || OperationStateEnum.OPERATION.equals(planBill.getOperationState())) {
            return;
        }
        planBillExtraService.updateOperationStateByBill(planBill, operationState);
    }
}
