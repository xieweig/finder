package cn.sisyphe.coffee.bill.application.planbill;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.AuditBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.OpenBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SubmitBehavior;
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
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDetailDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillStationDTO;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.station.repo.StationRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo.SupplierRepository;
import cn.sisyphe.coffee.bill.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum.SAVED;

/**
 * @author ncmao
 * @Date 2017/12/27 11:36
 * @description
 */
@Service
public class PlanBillManager {

    @Autowired
    private PlanBillRepository planBillRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private StationRepository stationRepository;

    /**
     * 创建计划单
     *
     * @param planBillDTO 计划单DTO
     */

    public void create(PlanBillDTO planBillDTO) {
        PlanBill planBill = (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);

        map(planBill, planBillDTO);

        planBill.setBillState(SAVED);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new SaveBehavior());
        billService.save();

    }

    /**
     * 提交计划单进行审核
     *
     * @param planBillDTO 前端传过来的DTO
     */
    public void submit(PlanBillDTO planBillDTO) {
        PlanBill planBill = planBillRepository.findOneByBillCode(planBillDTO.getBillCode());
        map(planBill, planBillDTO);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new SubmitBehavior());
        billService.save();
    }

    //查看总部计划，状态变更为审核中，两种情况，一种点击查看按钮，一种点击审核按钮
    public void open(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new OpenBehavior());
        billService.save();
    }

    //审核不通过
    public void unPass(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new AuditBehavior(billService, Constant.AUDIT_FAILURE_VALUE));
        billService.save();
    }

    //审核通过，然后进行计划单切片
    public void pass(String billCode) {
        PlanBill planBill = planBillRepository.findOneByBillCode(billCode);
        setTransferLocation(planBill);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new PurposeBehavior());
        billService.dispose(new AuditBehavior(billService, Constant.AUDIT_SUCCESS_VALUE));
        billService.save();
    }

    //将前端传过来的数据进行
    private void map(PlanBill planBill, PlanBillDTO planBillDTO) {
        planBill.getBillDetails().clear();
        planBill.setSpecificBillType(planBillDTO.getBillType());
        planBill.setPlanName(planBillDTO.getBillName());
        planBill.setBillCode(planBillDTO.getBillCode());
        planBill.setMemo(planBillDTO.getMemo());
        planBill.setHqBill(true);
        planBill.setBasicEnum(planBillDTO.getBasicEnum());
        Set<PlanBillDetail> planBillDetails = new HashSet<>();
        for (PlanBillDetailDTO planBillDetailDTO : planBillDTO.getPlanBillDetailDTOS()) {
            for (PlanBillStationDTO planBillStationDTO : planBillDetailDTO.getPlanBillStationDTOS()) {
                PlanBillDetail planBillDetail = new PlanBillDetail();
                planBillDetail.setAmount(planBillStationDTO.getAmount());
                planBillDetail.setInLocation(getLocation(planBillStationDTO.getInStation()));
                planBillDetail.setOutLocation(getLocation(planBillStationDTO.getOutStation()));
                planBillDetail.setGoods(mapGoods(planBillDetailDTO.getRawMaterialCode(), planBillDetailDTO.getCargoCode(), planBill.getBasicEnum()));
                planBillDetails.add(planBillDetail);
            }

        }
        planBill.getBillDetails().addAll(planBillDetails);
        planBill.setBillPurpose(BillPurposeEnum.Plan);

    }

    private AbstractGoods mapGoods(String rawMaterialCode, String cargoCode, BasicEnum basicEnum) {
        if (BasicEnum.BY_CARGO.equals(basicEnum)) {
            return new Cargo(cargoCode);
        }
        return new RawMaterial(rawMaterialCode);
    }

    private AbstractLocation getLocation(Station station) {
        if (StationType.SUPPLIER.equals(station.getStationType())) {
            supplierRepository.findBySupplierCode(station.getStationCode());
            Supplier supplier = new Supplier(station.getStationCode());
            supplier.setSupplierName(station.getStationName());
            return supplier;
        }
        return stationRepository.findByStationCode(station.getStationCode());
    }

    //TODO 如果出站站点是门店，并且入战站点是供应商，则需要将中转物流站点map到tranferLocation上面去
    private void setTransferLocation(PlanBill planBill) {
        for (PlanBillDetail planBillDetail : planBill.getBillDetails()) {
            if (planBillDetail.getOutLocation() instanceof Station && StationType.STORE.equals(((Station) planBillDetail.getOutLocation()).getStationType())
                    && planBillDetail.getOutLocation() instanceof Supplier) {
                planBillDetail.setTransferLocation(supplierRepository.findBySupplierCode(planBillDetail.getOutLocation().code()));
            }
        }

    }
}
