package cn.sisyphe.coffee.bill.application.planbill;

import ch.lambdaj.group.Group;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
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
import cn.sisyphe.coffee.bill.domain.plan.PlanBillQueryService;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDetailDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillStationDTO;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.station.repo.StationRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo.SupplierRepository;
import cn.sisyphe.coffee.bill.viewmodel.plan.AuditPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillGoodsDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillLocationDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.QueryPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.QueryPlanDetailBillDTO;
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

/**
 * 计划单据manager
 *
 * @author ncmao
 */
@Service
public class PlanBillManager extends AbstractBillManager<PlanBill> {

    @Autowired
    private PlanBillQueryService planBillQueryService;

    @Autowired
    private PlanBillRepository planBillRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    public PlanBillManager(BillRepository<PlanBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    /**
     * 创建计划单
     *
     * @param planBillDTO 计划单DTO
     * @return billcode
     */

    public String create(PlanBillDTO planBillDTO) {
        PlanBill planBill = preparePlanBill(planBillDTO);
        map(planBill, planBillDTO);
        return save(planBill).getBillCode();
    }

    private PlanBill preparePlanBill(PlanBillDTO planBillDTO) {
        PlanBill planBill;
        if (planBillDTO.getBillCode() != null) {
            //计划编码有由后端生成，如果前端传递回来的时候有code，就做更新操作
            planBill = planBillRepository.findOneByBillCode(planBillDTO.getBillCode());
            if (planBill == null) {
                throw new DataException("xxxx", "没有找到该计划单");
            }
            return planBill;
        }
        //TODO 为了测试
        planBillDTO.setBillCode(planBillDTO.getMemo());
        validate(planBillDTO);
        return (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);
    }

    private void validateBillCode(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        if (planBill != null) {
            throw new DataException("", "计划编号已存在");
        }
    }

    private void validate(PlanBillDTO planBillDTO) {
        validateBillCode(planBillDTO.getBillCode());
        checkCreateParam(planBillDTO);
    }

    /**
     * 提交计划单进行审核
     *
     * @param planBillDTO 前端传过来的DTO
     */
    public String submit(PlanBillDTO planBillDTO) {
        PlanBill planBill = preparePlanBill(planBillDTO);
        map(planBill, planBillDTO);
        return submit(planBill).getBillCode();
    }

    /**
     * 查看总部计划，状态变更为审核中，两种情况，一种点击查看按钮，一种点击审核按钮
     *
     * @param billCode 计划单编码
     * @return ResultPlanBillDTO
     */
    public ResultPlanBillDTO open(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        if (BillStateEnum.OPEN.equals(planBill.getBillState()) || BillStateEnum.AUDIT_FAILURE.equals(planBill.getBillState())) {
            return planBillToResultPlanBillDTO(planBill);
        }
        open(planBill);
        return planBillToResultPlanBillDTO(planBill);
    }

    /**
     * 审核不通过
     *
     * @param auditPlanBillDTO 计划单审核DTO
     */

    public void unPass(AuditPlanBillDTO auditPlanBillDTO) {
        PlanBill planBill = planBillRepository.findOneByBillCode(auditPlanBillDTO.getBillCode());
        planBill.setAuditMemo(auditPlanBillDTO.getAuditMemo());
        audit(planBill, false);

    }

    /**
     * 审核通过，然后进行计划单切片
     *
     * @param auditPlanBillDTO 计划单审核DTO
     */

    @Transactional(rollbackFor = RuntimeException.class)
    public void pass(AuditPlanBillDTO auditPlanBillDTO) {
        PlanBill planBill = planBillRepository.findOneByBillCode(auditPlanBillDTO.getBillCode());
        planBill.setAuditMemo(auditPlanBillDTO.getAuditMemo());
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
            return stationRepository.findByStationCode(abstractLocation.code());
        }
        if (abstractLocation instanceof Supplier) {
            return supplierRepository.findBySupplierCode(abstractLocation.code());
        }
        throw new DataException("xxx", "站点转换错误");
    }

    //将前端传过来的数据进行转换
    private void map(PlanBill planBill, PlanBillDTO planBillDTO) {
        planBill.getBillDetails().clear();
        planBill.setSpecificBillType(planBillDTO.getBillType());
        planBill.setBillName(planBillDTO.getBillName());
        planBill.setBillCode(planBillDTO.getBillCode());
        planBill.setMemo(planBillDTO.getMemo());
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

    //TODO 如果出站站点是门店，并且入战站点是供应商，则需要将中转物流站点map到tranferLocation上面去
    private Station getTransferLocation(PlanBillDetail planBillDetail) {
        if (planBillDetail.getOutLocation() instanceof Station && StationType.STORE.equals(((Station) planBillDetail.getOutLocation()).getStationType())
                && planBillDetail.getOutLocation() instanceof Supplier) {
            //TODO 需要使用真实数据，等唐华玲写好接口之后,将中转的物流站点map上去
            Station wlzd001 = new Station("WLZD001");
            wlzd001.setStationType(StationType.LOGISTICS);
            return wlzd001;
        }
        return null;
    }

    /**
     * @param conditionQueryPlanBill 条件查询
     * @return QueryPlanBillDTO 前端页面展示
     * @throws DataException
     */
    public QueryPlanBillDTO findPageByCondition(ConditionQueryPlanBill conditionQueryPlanBill) throws DataException {

        //1 根据具体的运单号查询,只有唯一的显示，显示一条
        //2 根据配送出库查询可能会有多条
        //3 所有都是模糊匹配
        //所有的产品表中的数据
        Page<PlanBill> planBillPage = planBillQueryService.findPageByCondition(conditionQueryPlanBill);
        QueryPlanBillDTO queryPlanBillDTO = new QueryPlanBillDTO();
        // 转换
        List<QueryPlanDetailBillDTO> billDTOList = toMapDTO(planBillPage.getContent());
        // 总数
        queryPlanBillDTO.setTotalNumber(planBillPage.getTotalElements());
        // 进货单据数据
        queryPlanBillDTO.setContent(billDTOList);

        return queryPlanBillDTO;
    }

    /**
     * 前端多条件查询转换DTO
     *
     * @param planBillList
     * @return
     */
    private List<QueryPlanDetailBillDTO> toMapDTO(List<PlanBill> planBillList) {

        List<QueryPlanDetailBillDTO> planBillDTOList = new ArrayList<>();
        int amount;
        for (PlanBill planBill : planBillList) {
            QueryPlanDetailBillDTO queryPlanDetailBillDTO = new QueryPlanDetailBillDTO();
            /**
             * progress-主表
             */
            queryPlanDetailBillDTO.setProgress(planBill.getProgress());
            /**
             * 计划站点号-主表
             */
            queryPlanDetailBillDTO.setStationPlanCode("bugaosuni001-buzhidao002");
            /**
             * 数量-从表
             */
            Set<PlanBillDetail> billDetails = planBill.getBillDetails();
            amount = 0;
            for (PlanBillDetail planBillDetail : billDetails) {
                amount += planBillDetail.getAmount();
            }
            queryPlanDetailBillDTO.setAmount(amount);
            /**
             * 货物详情-从表
             */
            queryPlanDetailBillDTO.setBillDetails(planBill.getBillDetails());
            /**
             * 规格品种--主表
             */
            queryPlanDetailBillDTO.setSpecies(billDetails.size());
            /**
             * 录单时间-主表
             */
            queryPlanDetailBillDTO.setCreateTime(planBill.getCreateTime());
            /**
             * 调入站点-主表
             */
            Station station = (Station) planBill.getInLocation();
            queryPlanDetailBillDTO.setInStation(station.getStationName());
            /**
             * 出库站点-主表
             */
            station = (Station) planBill.getOutLocation();
            queryPlanDetailBillDTO.setOutStation(station.getStationName());
            /**
             * 录单人-主表
             */
            queryPlanDetailBillDTO.setCreatorName(planBill.getCreatorName());
            /**
             * 备注-主表
             */
            queryPlanDetailBillDTO.setMemo(planBill.getMemo());

            planBillDTOList.add(queryPlanDetailBillDTO);
        }
        return planBillDTOList;
    }

    /**
     * 根据编号查询
     *
     * @param billCode
     * @return
     */
    public ResultPlanBillDTO findByBillCode(String billCode) throws DataException {
        return planBillToResultPlanBillDTO(planBillRepository.findOneByBillCode(billCode));
    }

    /**
     * 将 PlanBill 转为 ResultPlanBillDTO
     *
     * @param planBill
     * @return
     */
    private ResultPlanBillDTO planBillToResultPlanBillDTO(PlanBill planBill) throws DataException {
        ResultPlanBillDTO resultPlanBillDTO = new ResultPlanBillDTO();
        if (planBill == null) {
            return resultPlanBillDTO;
        }
        resultPlanBillDTO.setBillCode(planBill.getBillCode());
        resultPlanBillDTO.setBillName(planBill.getBillName());
        resultPlanBillDTO.setBillType(planBill.getSpecificBillType());
        Set<ResultPlanBillGoodsDTO> resultPlanBillGoodsDTOSet = new HashSet<>();
        if (planBill.getBillDetails() != null) {
            Group<PlanBillDetail> groupedPlanBillDetail = group(planBill.getBillDetails(), by(on(PlanBillDetail.class).getGoods().code()));
            for (String head : groupedPlanBillDetail.keySet()) {
                ResultPlanBillGoodsDTO resultPlanBillGoodsDTO = new ResultPlanBillGoodsDTO();
                List<PlanBillDetail> planBillDetails = groupedPlanBillDetail.find(head);
                PlanBillDetail firstPlanBillDetail = planBillDetails.get(0);
                resultPlanBillGoodsDTO.setGoods(firstPlanBillDetail.getGoods());
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
        //TODO 备注是否允许为空？ 字符长度是多少？
        if (StringUtils.isEmpty(planBillDTO.getMemo())) {
            throw new DataException("", "单据备注");
        }
        if (StringUtils.isEmpty(planBillDTO.getPlanBillDetailDTOS()) || planBillDTO.getPlanBillDetailDTOS().size() <= 0) {
            throw new DataException("", "单据明细不能为空");
        }
    }
}
