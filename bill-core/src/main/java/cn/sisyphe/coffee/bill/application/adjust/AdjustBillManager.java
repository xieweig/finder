package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDetailDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务
 * version: 1.0
 *
 * @author XiongJing
 */

@Service
public class AdjustBillManager extends AbstractBillManager<AdjustBill> {


    @Autowired
    private AdjustBillExtraService adjustBillExtraService;

    @Autowired
    private SharedManager sharedManager;

    @Autowired
    public AdjustBillManager(BillRepository<AdjustBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }


    public String create(AddAdjustBillDTO addAdjustBillDTO) {
        AdjustBill adjustBill = prepareAdjustBill(addAdjustBillDTO);
        mapBill(adjustBill, addAdjustBillDTO);
        return save(adjustBill).getBillCode();
    }

    private void mapBill(AdjustBill adjustBill, AddAdjustBillDTO addAdjustBillDTO) {
        adjustBill.setBillCode(generateBillCode());
        adjustBill.setBillPurpose(BillPurposeEnum.OutStorage);
        adjustBill.setOutLocation(new Station(addAdjustBillDTO.getOutStationCode()));
        adjustBill.setInLocation(new Station(addAdjustBillDTO.getInStationCode()));
        if (isFromPlanBill(addAdjustBillDTO)) {
            adjustBill.setPlanMemo("调剂计划生成");
            adjustBill.setRootCode(addAdjustBillDTO.getRootCode());
            adjustBill.setSourceCode(addAdjustBillDTO.getRootCode());
        }
        adjustBill.setPlanMemo(addAdjustBillDTO.getPlanMemo());
        adjustBill.setBillDetails(mapDetails(addAdjustBillDTO));
        adjustBill.setBasicEnum(addAdjustBillDTO.getBasicEnum());

    }

    private AdjustBill prepareAdjustBill(AddAdjustBillDTO addAdjustBillDTO) {
        AdjustBill adjustBill;
        if (!StringUtils.isEmpty(addAdjustBillDTO.getBillCode())) {
            //计划编码有由后端生成，如果前端传递回来的时候有code，就做更新操作
            adjustBill = adjustBillExtraService.findByBillCode(addAdjustBillDTO.getBillCode());
            if (adjustBill == null) {
                throw new DataException("xxxx", "没有找到该计划单");
            }
        } else {
            adjustBill = (AdjustBill) new BillFactory().createBill(BillTypeEnum.ADJUST);
            adjustBill.setBillCode(generateBillCode());
        }
        return adjustBill;
    }

    private Set<AdjustBillDetail> mapDetails(AddAdjustBillDTO addAdjustBillDTO) {
        Set<AdjustBillDetail> billDetails = new HashSet<>();
        for (AdjustBillDetailDTO adjustBillDetailDTO : addAdjustBillDTO.getDetails()) {
            AdjustBillDetail adjustBillDetail = new AdjustBillDetail();
            adjustBillDetail.setGoods(adjustBillDetailDTO.getRawMaterial());
            adjustBillDetail.setShippedAmount(adjustBillDetailDTO.getShippedAmount());
            adjustBillDetail.setActualAmount(adjustBillDetailDTO.getActualAmount());
            adjustBillDetail.setMemo(adjustBillDetailDTO.getMemo());
            billDetails.add(adjustBillDetail);
        }
        return billDetails;
    }

    private String generateBillCode() {
        return String.valueOf(System.currentTimeMillis());
    }

    private boolean isFromPlanBill(AddAdjustBillDTO addAdjustBillDTO) {

        return !StringUtils.isEmpty(addAdjustBillDTO.getRootCode());
    }
}
