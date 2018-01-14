package cn.sisyphe.coffee.bill.application.allot;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBillDetail;
import cn.sisyphe.coffee.bill.domain.allot.AllotBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.allot.AddAllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 调拨单管理
 *
 * @author ncmao
 */
@Service
public class AllotBillManager extends AbstractBillManager<AllotBill> {

    @Autowired
    AllotBillExtraService allotBillExtraService;

    @Autowired
    SharedManager sharedManager;

    @Autowired
    private InStorageBillManager inStorageBillManager;

    @Autowired
    public AllotBillManager(BillRepository<AllotBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.ALLOT;
    }

    public Page<AllotBillDTO> findAllotBillByCondition(ConditionQueryAllotBill conditionQueryAllotBill, BillTypeEnum specificBillType) {
        conditionQueryAllotBill.setSpecificBillType(specificBillType);
//        conditionQueryAllotBill.setBillPurpose(billPurpose);
        Page<AllotBill> allotBills = allotBillExtraService.findPageByCondition(conditionQueryAllotBill);

        return allotBills.map(this::allotBillToAllotBillDTO);
    }

    private AllotBillDTO allotBillToAllotBillDTO(AllotBill allotBill) {
        AllotBillDTO allotBillDTO = new AllotBillDTO();
        allotBillDTO.setOperatorName(sharedManager.findOneByUserCode(allotBill.getOperatorCode()));
        allotBillDTO.setAuditMemo(allotBill.getAuditMemo());
        allotBillDTO.setAuditPersonName(sharedManager.findOneByUserCode(allotBill.getAuditPersonCode()));
        allotBillDTO.setAuditState(allotBill.getAuditState());
        allotBillDTO.setBasicEnum(allotBill.getBasicEnum());
        allotBillDTO.setBelongStationCode(allotBillDTO.getBelongStationCode());
        allotBillDTO.setBillCode(allotBill.getBillCode());
        allotBillDTO.setAuditState(allotBill.getAuditState());
//        allotBillDTO.setBillDetails(allotBill.getBillDetails());
        allotBillDTO.setBillProperty(allotBill.getBillProperty());
        allotBillDTO.setBillPurpose(allotBill.getBillPurpose());
        allotBillDTO.setBillState(allotBill.getBillState());
        allotBillDTO.setInLocation(allotBill.getInLocation());
        allotBillDTO.setInOrOutState(allotBill.getInOrOutState());
        allotBillDTO.setInWareHouseTime(allotBill.getInWareHouseTime());
        allotBillDTO.setOutLocation(allotBill.getOutLocation());
        allotBillDTO.setOutStateEnum(allotBill.getOutStateEnum());
        allotBillDTO.setOutStorageMemo(allotBill.getOutStorageMemo());
        allotBillDTO.setOutWareHouseTime(allotBill.getOutWareHouseTime());
        allotBillDTO.setPlanMemo(allotBill.getPlanMemo());
        allotBillDTO.setProgress(allotBillDTO.getProgress());
        allotBillDTO.setRootCode(allotBill.getRootCode());
        allotBillDTO.setSourceCode(allotBill.getSourceCode());
        allotBillDTO.setSpecificBillType(allotBill.getSpecificBillType());
        allotBillDTO.setSubmitState(allotBill.getSubmitState());
        allotBillDTO.setTotalAmount(allotBill.getTotalAmount());
        allotBillDTO.setTotalPrice(allotBill.getTotalPrice());
        allotBillDTO.setTotalVarietyAmount(allotBill.getTotalVarietyAmount());
        allotBillDTO.setBillDetails(billDetailToBillDetailDTO(allotBill.getBillDetails()));
        return allotBillDTO;
    }

    /**
     * DTO转调拨单
     *
     * @param allotBillDTO 生成调拨单DTO
     */

    public void createAllotBill(AddAllotBillDTO allotBillDTO) {

        AllotBill allotBill = mapAllotBill(allotBillDTO);
        //TODO 调用唐华玲的差错单生成接口生成差错单
        allotBill.setMistakeBill(new MistakeBill());
        purpose(allotBill);
        if (!StringUtils.isEmpty(allotBillDTO.getInStorageBillCode())){
            inStorageBillManager.committing(allotBillDTO.getInStorageBillCode(), allotBillDTO.getInStorageBillType());
        }
    }

    /**
     * map调拨单
     *
     * @param addAllotBillDTO 调拨单DTO
     * @return 调拨单
     */

    private AllotBill mapAllotBill(AddAllotBillDTO addAllotBillDTO) {
        AllotBill allotBill = (AllotBill) new BillFactory().createBill(BillTypeEnum.ALLOT);
        allotBill.setBillPurpose(BillPurposeEnum.moveStorage);
        allotBill.setBillState(BillStateEnum.AUDIT_SUCCESS);
        allotBill.setBelongStationCode(addAllotBillDTO.getInStation().code());
        allotBill.setInLocation(addAllotBillDTO.getInStation());
        allotBill.setOutLocation(addAllotBillDTO.getOutStation());
        allotBill.setAllotMemo(addAllotBillDTO.getMemo());
        allotBill.setSourceCode(addAllotBillDTO.getInStorageBillCode());
        allotBill.setBasicEnum(addAllotBillDTO.getBasicEnum());
        allotBill.setSpecificBillType(addAllotBillDTO.getInStorageBillType());
        allotBill.getBillDetails().clear();
        allotBill.getBillDetails().addAll(mapDetails(addAllotBillDTO));
        return allotBill;
    }

    /**
     * map调拨单详情
     *
     * @param addAllotBillDTO 调拨单详情
     * @return map出的调剂单details
     */
    private Set<AllotBillDetail> mapDetails(AddAllotBillDTO addAllotBillDTO) {
        Set<AllotBillDetail> details = new HashSet<>();
        for (AllotBillDetailDTO allotBillDetailDTO : addAllotBillDTO.getDetails()) {
            AllotBillDetail allotBillDetail = new AllotBillDetail();
            allotBillDetail.setGoods(allotBillDetailDTO.getRawMaterial());
            allotBillDetail.setShippedAmount(allotBillDetailDTO.getShippedAmount());
            allotBillDetail.setActualAmount(allotBillDetailDTO.getActualAmount());
            details.add(allotBillDetail);
        }
        return details;
    }

    private Set<AllotBillDetailDTO> billDetailToBillDetailDTO(Set<AllotBillDetail> billDetails) {
        Set<AllotBillDetailDTO> allotBillDetailDTOS = new HashSet<>();
        for (AllotBillDetail allotBillDetail : billDetails) {
            AllotBillDetailDTO allotBillDetailDTO = new AllotBillDetailDTO();
            allotBillDetailDTO.setActualAmount(allotBillDetail.getActualAmount());
            RawMaterial rawMaterial;
            if (allotBillDetail.getGoods() != null && allotBillDetail.getGoods() instanceof RawMaterial) {
                rawMaterial = (RawMaterial) allotBillDetail.getGoods();
                allotBillDetailDTO.setRawMaterial(rawMaterial);
            }
            allotBillDetailDTO.setShippedAmount(allotBillDetail.getShippedAmount());
            allotBillDetailDTOS.add(allotBillDetailDTO);
        }
        return allotBillDetailDTOS;
    }

    public AllotBillDTO findAllotBillByBillCode(String billCode) {
        AllotBill allotBill = allotBillExtraService.findOneByBillCode(billCode);
        AllotBillDTO allotBillDTO = allotBillToAllotBillDTO(allotBill);
        return allotBillDTO;
    }

    @Override
    public Bill findByBillCode(String billCode) {
        return allotBillExtraService.findOneByBillCode(billCode);
    }
}

