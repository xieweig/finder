package cn.sisyphe.coffee.bill.application.planbill;

import ch.lambdaj.group.Group;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
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
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillGoodsDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillLocationDTO;
import cn.sisyphe.framework.web.exception.DataException;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.QueryPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.QueryPlanDetailBillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ch.lambdaj.Lambda.by;
import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.on;

/**
 * @author ncmao
 * @Date 2017/12/27 11:36
 * @description
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
        PlanBill planBill;
        if (planBillDTO.getBillCode() != null) {
            //因为计划单编号是可以更改的，所以更新的时候，不能使用billCode查询
            planBill = planBillRepository.findOneByBillCode(planBillDTO.getBillCode());
        } else {
            validateBillCode(planBillDTO.getBillCode());
            planBill = (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);
        }
        map(planBill, planBillDTO);
        return save(planBill).getBillCode();
    }

    private void validateBillCode(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        if (planBill != null) {
            throw new DataException("123456", "计划编号已存在");
        }
    }

    /**
     * 提交计划单进行审核
     *
     * @param planBillDTO 前端传过来的DTO
     */
    public String submit(PlanBillDTO planBillDTO) {
        PlanBill planBill;
        if (planBillDTO.getBillCode() != null) {
            //因为计划单编号是可以更改的，所以更新的时候，不能使用billCode查询
            planBill = planBillRepository.findOneByBillCode(planBillDTO.getBillCode());
        } else {
            validateBillCode(planBillDTO.getBillCode());
            planBill = (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);
        }
        map(planBill, planBillDTO);
        return submit(planBill).getBillCode();
    }

    //查看总部计划，状态变更为审核中，两种情况，一种点击查看按钮，一种点击审核按钮
    public void open(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        open(planBill);
    }

    //审核不通过
    public void unPass(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        audit(planBill, false);

    }

    //审核通过，然后进行计划单切片
    public void pass(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        mapForSplit(planBill);
        audit(planBill, true);
        purpose(planBill);

    }

    private void mapForSplit(PlanBill planBill) {
        mapInLocation(planBill);
        mapOutLocation(planBill);
        setTransferLocation(planBill);
    }

    private void mapInLocation(PlanBill planBill) {
        if (planBill.getInLocation() instanceof Station) {
            planBill.setInLocation(stationRepository.findByStationCode(((Station) planBill.getInLocation()).getStationCode()));
            return;
        }
        if (planBill.getInLocation() instanceof Supplier) {
            planBill.setInLocation(supplierRepository.findBySupplierCode(((Supplier) planBill.getInLocation()).getSupplierCode()));
        }
    }

    private void mapOutLocation(PlanBill planBill) {
        if (planBill.getOutLocation() instanceof Station) {
            planBill.setOutLocation(stationRepository.findByStationCode(((Station) planBill.getOutLocation()).getStationCode()));
            return;
        }
        if (planBill.getInLocation() instanceof Supplier) {
            planBill.setOutLocation(supplierRepository.findBySupplierCode(((Supplier) planBill.getOutLocation()).getSupplierCode()));
        }
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
        if (BasicEnum.BY_CARGO.equals(basicEnum)) {
            return new Cargo(cargoCode);
        }
        return new RawMaterial(rawMaterialCode);
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
    private void setTransferLocation(PlanBill planBill) {
        for (PlanBillDetail planBillDetail : planBill.getBillDetails()) {
            if (planBillDetail.getOutLocation() instanceof Station && StationType.STORE.equals(((Station) planBillDetail.getOutLocation()).getStationType())
                    && planBillDetail.getOutLocation() instanceof Supplier) {
                //TODO 需要使用真实数据，等唐华玲写好接口之后,将中转的物流站点map上去
                Station wlzd001 = new Station("WLZD001");
                wlzd001.setStationType(StationType.LOGISTICS);
                planBillDetail.setTransferLocation(wlzd001);
            }
        }
    }

    /**
     * @param conditionQueryPlanBill
     * @return
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
    public List<QueryPlanDetailBillDTO> toMapDTO(List<PlanBill> planBillList) {

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
    public ResultPlanBillDTO findByBillCode(String billCode) throws DataException{
        return planBillToResultPlanBillDTO(planBillRepository.findOneByBillCode(billCode));
    }
    /**
     * 将 PlanBill 转为 ResultPlanBillDTO
     *
     * @param planBill
     * @return
     */
    private ResultPlanBillDTO planBillToResultPlanBillDTO(PlanBill planBill) throws DataException{
        ResultPlanBillDTO resultPlanBillDTO = new ResultPlanBillDTO();
        if(planBill==null){
            return resultPlanBillDTO;
        }
        resultPlanBillDTO.setBillCode(planBill.getBillCode());
        resultPlanBillDTO.setBillName(planBill.getBillName());
        resultPlanBillDTO.setBillType(planBill.getBillType());
        Set<ResultPlanBillGoodsDTO> resultPlanBillGoodsDTOSet = new HashSet<>();
        if(planBill.getBillDetails()!=null){
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
                    resultPlanBillLocationDTOSet.add(resultPlanBillLocationDTO);
                }
                resultPlanBillGoodsDTO.setResultPlanBillDetailDTOSet(resultPlanBillLocationDTOSet);
                resultPlanBillGoodsDTOSet.add(resultPlanBillGoodsDTO);
            }
        }
        resultPlanBillDTO.setPlanBillDetails(resultPlanBillGoodsDTOSet);
        return resultPlanBillDTO;
    }

    private void checkSaveParam() {

    }
}
