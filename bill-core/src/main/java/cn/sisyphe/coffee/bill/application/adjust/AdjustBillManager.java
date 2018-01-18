package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
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
public class AdjustBillManager extends AbstractBillExtraManager<AdjustBill, AdjustBillDTO, ConditionQueryAdjustBill> {


    @Autowired
    public AdjustBillManager(BillRepository<AdjustBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<AdjustBill, ConditionQueryAdjustBill> billExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager);
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

    @Override
    protected AdjustBill dtoToBill(AdjustBill bill, AdjustBillDTO billDTO) {
        mapBill(bill, billDTO);
        return bill;
    }

    /**
     * map数据
     *
     * @param adjustBill    调剂单信息
     * @param adjustBillDTO 调剂单DTO
     */
    private void mapBill(AdjustBill adjustBill, AdjustBillDTO adjustBillDTO) {
        //操作人编码
        adjustBill.setOperatorCode(adjustBillDTO.getOperatorCode());
        //审核人编码
        adjustBill.setAuditPersonCode(adjustBillDTO.getAuditPersonCode());
        //设置单据作用
        adjustBill.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
        //设置出库站点
        adjustBill.setOutLocation(adjustBillDTO.getOutLocation());
        //设置入站站点
        adjustBill.setInLocation(adjustBillDTO.getInLocation());
        //设置源单号
        if (!StringUtils.isEmpty(adjustBillDTO.getSourceCode())) {
            adjustBill.setRootCode(adjustBillDTO.getRootCode());
            adjustBill.setSourceCode(adjustBillDTO.getRootCode());
        }
        //设置计划备注
        adjustBill.setPlanMemo(adjustBillDTO.getPlanMemo());
        //设置计划详情
        adjustBill.getBillDetails().clear();
        adjustBill.getBillDetails().addAll(mapDetails(adjustBillDTO));
        //设置是按原料还是货物拣货
        adjustBill.setBasicEnum(adjustBillDTO.getBasicEnum());
        //设置出库备注
        adjustBill.setOutStorageMemo(adjustBillDTO.getOutStorageMemo());
        //设置所属站点
        adjustBill.setBelongStationCode(adjustBillDTO.getInLocation().code());
        //设置调剂数量
        adjustBill.setTotalAmount(sum(adjustBillDTO.getBillDetails(), on(AdjustBillDetailDTO.class).getShippedAmount()));
        //设置调剂种类
        adjustBill.setTotalVarietyAmount(adjustBillDTO.getBillDetails().size());

    }

    /**
     * 设置调剂计划详情
     *
     * @param adjustBillDTO 前端dto
     * @return 调剂计划详情集合
     */
    private Set<AdjustBillDetail> mapDetails(AdjustBillDTO adjustBillDTO) {
        Set<AdjustBillDetail> billDetails = new HashSet<>();
        for (AdjustBillDetailDTO adjustBillDetailDTO : adjustBillDTO.getBillDetails()) {
            AdjustBillDetail adjustBillDetail = new AdjustBillDetail();
            //原料或货物编码
            adjustBillDetail.setGoods(adjustBillDetailDTO.getRawMaterial());
            //设置所属原料编码便于分类
            adjustBillDetail.setBelongMaterialCode(adjustBillDetailDTO.getBelongMaterialCode());
            //应拣数量
            adjustBillDetail.setShippedAmount(adjustBillDetailDTO.getShippedAmount());
            //实拣数量
            adjustBillDetail.setActualAmount(adjustBillDetailDTO.getActualAmount());
            //备注信息
            billDetails.add(adjustBillDetail);
        }
        return billDetails;
    }
}
