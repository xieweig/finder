package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * dto与实体类互转
 *
 * @author bifenglin
 */
@Service
public class DTOManager {

    /**
     * @param bill
     * @param billDTO
     * @return
     */
    public Bill billDTOToBill(Bill bill, BillDTO billDTO) throws DataException {
        // 如果bill为空则实例化一个bill实例对象
        if (bill == null) {
            throw new DataException("432435", "单据实例对象为空，无法操作！");
        } else {

        }

        if (billDTO.getBillState() != null) {
            bill.setBillState(billDTO.getBillState());
        }
        bill.setAllotStatus(billDTO.getAllotStatus());
        bill.setAuditMemo(billDTO.getAuditMemo());
        bill.setAuditPersonCode(billDTO.getAuditPersonCode());
        bill.setAuditState(bill.getAuditState());
        bill.setBasicEnum(bill.getBasicEnum());
        bill.setBillCode(bill.getBillCode());
        //不确定需不需要
//        bill.setBillCodePrefix(bill.getBillCodePrefix());
//        bill.setBillDetails();
        bill.setBillProperty(billDTO.getBillProperty());
        bill.setBillType(billDTO.getBillType());
        bill.setInLocation(billDTO.getInLocation());
        bill.setInOrOutState(billDTO.getInOrOutState());
        bill.setInWareHouseTime(billDTO.getInWareHouseTime());
        //不确定
//        bill.setMistakeBill(billDTO.getMistakeBill());
        bill.setBillPurpose(billDTO.getBillPurpose());
        bill.setOperatorCode(billDTO.getOperatorCode());
        bill.setOutStateEnum(billDTO.getOutStateEnum());
        bill.setOutLocation(billDTO.getOutLocation());
        bill.setOutStorageMemo(billDTO.getOutStorageMemo());
        bill.setOutWareHouseTime(billDTO.getOutWareHouseTime());
        bill.setPlanMemo(billDTO.getPlanMemo());
        bill.setProgress(billDTO.getProgress());
        bill.setRootCode(billDTO.getRootCode());
        bill.setSourceCode(billDTO.getSourceCode());
        bill.setSubmitState(billDTO.getSubmitState());
        bill.setTotalAmount(billDTO.getTotalAmount());
        bill.setTotalPrice(billDTO.getTotalPrice());
        bill.setTotalVarietyAmount(billDTO.getTotalVarietyAmount());
        if (billDTO.getBillDetails() != null) {
            bill.setBillDetails(billDetailDTOToBillDetail(billDTO.getBillDetails(), bill.getBillType()));
        }
        return bill;
    }

    public Set<BillDetail> billDetailDTOToBillDetail(Set<BillDetailDTO> billDetailDTOSet, BillTypeEnum billType) {
        Set<BillDetail> billDetailSet = new HashSet<>();
        for (BillDetailDTO billDetailDTO : billDetailDTOSet) {
            BillDetail billDetail = new BillFactory().createBillDetail(billType);
            billDetail.setActualAmount(billDetailDTO.getActualAmount());
            if (billDetail.getGoods() instanceof RawMaterial) {
                billDetail.setGoods(billDetailDTO.getRawMaterial());
            }
            billDetail.setShippedAmount(billDetailDTO.getShippedAmount());
            billDetailSet.add(billDetail);
        }
        return billDetailSet;
    }
//
//    public BillDTO billToBillDTO(Bill bill) {
//        BillDTO billDTO = new BillDTO();
//        billDTO.setAllotStatus(bill.getAllotStatus());
//        billDTO.setAuditMemo(bill.getAuditMemo());
//        billDTO.setAuditPersonCode(bill.getAuditPersonCode());
//        billDTO.setAuditPersonName(sharedManager.findOneByUserCode(bill.getAuditPersonCode()));
//        billDTO.setAuditState(bill.getAuditState());
//        billDTO.setBasicEnum(bill.getBasicEnum());
//        billDTO.setBelongStationCode(bill.getBelongStationCode());
//        billDTO.setBillCode(bill.getBillCode());
////        billDTO.setBillCodePrefix();
//        billDTO.setBillProperty(bill.getBillProperty());
//        billDTO.setBillPurpose(bill.getBillPurpose());
//        billDTO.setBillState(bill.getBillState());
//        billDTO.setBillType(bill.getBillType());
//        billDTO.setInLocation(bill.getInLocation());
//        billDTO.setInOrOutState(bill.getInOrOutState());
//        billDTO.setInWareHouseTime(bill.getInWareHouseTime());
////        billDTO.setMistakeBill();
//        billDTO.setOperatorCode(bill.getOperatorCode());
//        billDTO.setOperatorName(sharedManager.findOneByUserCode(bill.getOperatorCode()));
//        billDTO.setOutLocation(bill.getOutLocation());
//        billDTO.setOutStateEnum(bill.getOutStateEnum());
//        billDTO.setOutStorageMemo(bill.getOutStorageMemo());
//        billDTO.setOutWareHouseTime(bill.getOutWareHouseTime());
//        billDTO.setPlanMemo(bill.getPlanMemo());
//        billDTO.setProgress(bill.getProgress());
//        billDTO.setRootCode(bill.getRootCode());
//        billDTO.setSourceCode(bill.getSourceCode());
//        billDTO.setSubmitState(bill.getSubmitState());
//        billDTO.setTotalAmount(bill.getTotalAmount());
//        billDTO.setTotalPrice(bill.getTotalPrice());
//        billDTO.setTotalVarietyAmount(bill.getTotalVarietyAmount());
//        billDTO.setBillDetails(billDetailToBillDetailDTO(bill.getBillDetails()));
//
//        return billDTO;
//    }
//
//    public Set<BillDetailDTO> billDetailToBillDetailDTO(Set<BillDetail> billDetails) {
//        Set<BillDetailDTO> billDetailDTOSet = new HashSet<BillDetailDTO>(16);
//        for (BillDetail billDetail : billDetails) {
//            BillDetailDTO billDetailDTO = new BillDetailDTO();
//            billDetailDTO.setShippedAmount(billDetail.getShippedAmount());
//            billDetailDTO.setRawMaterial((RawMaterial) billDetail.getGoods());
//            billDetailDTO.setActualAmount(billDetail.getActualAmount());
//            billDetailDTOSet.add(billDetailDTO);
//        }
//        return billDetailDTOSet;
//    }

}
