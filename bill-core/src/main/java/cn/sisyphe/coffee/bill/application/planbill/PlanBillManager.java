package cn.sisyphe.coffee.bill.application.planbill;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SubmitBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDetailDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillStationDTO;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.station.repo.StationRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo.SupplierRepository;
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
        billService.dispose(new SaveBehavior());
        billService.setBillRepository(planBillRepository);
        billService.save();


    }

    /**
     * 提交计划单进行审核
     *
     * @param planBillDTO 前端传过来的DTO
     */
    public void submit(PlanBillDTO planBillDTO) {
        PlanBill planBill = planBillRepository.findByBillCode(planBillDTO.getBillCode());
        map(planBill, planBillDTO);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.dispose(new SubmitBehavior());
        billService.setBillRepository(planBillRepository);
        billService.save();
    }

    //审核通过，然后进行计划单切片
    public void pass(PlanBillDTO planBillDTO) {
        PlanBill planBill = planBillRepository.findByBillCode(planBillDTO.getBillCode());
        setTransferLocation(planBill);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.dispose(new PurposeBehavior());
        billService.setBillRepository(planBillRepository);
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
            for (PlanBillStationDTO planBillStationDTODTO : planBillDetailDTO.getPlanBillStationDTOS()) {
                PlanBillDetail planBillDetail = new PlanBillDetail();
                planBillDetail.setAmount(planBillStationDTODTO.getAmount());
                planBillDetail.setInLocation(getLocation(planBillStationDTODTO.getInStation()));
                planBillDetail.setOutLocation(getLocation(planBillStationDTODTO.getOutStation()));
                //TODO map goods
                planBillDetails.add(planBillDetail);
            }

        }
        planBill.getBillDetails().addAll(planBillDetails);
        planBill.setBillPurpose(BillPurposeEnum.Plan);

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
