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
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
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
    private PlanBillExtraService planBillExtraService;

    @Autowired
    private AdjustBillExtraService adjustBillExtraService;

    @Autowired
    private SharedManager sharedManager;

    @Autowired
    public AdjustBillManager(BillRepository<AdjustBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }


    /**
     * 暂存调剂计划
     *
     * @param addAdjustBillDTO 前端dto
     * @return billcode
     */
    public String create(AddAdjustBillDTO addAdjustBillDTO) {
        AdjustBill adjustBill = prepareAdjustBill(addAdjustBillDTO);
        mapBill(adjustBill, addAdjustBillDTO);
        return save(adjustBill).getBillCode();
    }

    /**
     * 提交调剂计划
     *
     * @param addAdjustBillDTO 前端dto
     * @return billcode
     */
    public String submit(AddAdjustBillDTO addAdjustBillDTO) {
        AdjustBill adjustBill = prepareAdjustBill(addAdjustBillDTO);
        mapBill(adjustBill, addAdjustBillDTO);
        return submit(adjustBill).getBillCode();
    }

    private void mapBill(AdjustBill adjustBill, AddAdjustBillDTO addAdjustBillDTO) {
        //设置单据作用
        adjustBill.setBillPurpose(BillPurposeEnum.OutStorage);
        //设置出库站点
        Station outLocation = new Station(addAdjustBillDTO.getOutStationCode());
        //设置出库库位
        outLocation.setStorage(addAdjustBillDTO.getOutStorage());
        adjustBill.setOutLocation(outLocation);
        //设置入站站点
        adjustBill.setInLocation(new Station(addAdjustBillDTO.getInStationCode()));
        //设置源单号
        if (isFromPlanBill(addAdjustBillDTO)) {
            adjustBill.setPlanMemo(addAdjustBillDTO.getSourcePlanType().getDescription());
            adjustBill.setRootCode(addAdjustBillDTO.getRootCode());
            adjustBill.setSourceCode(addAdjustBillDTO.getRootCode());
        }

        //设置计划备注
        adjustBill.setPlanMemo(addAdjustBillDTO.getPlanMemo());
        //设置计划详情
        adjustBill.getBillDetails().clear();
        adjustBill.getBillDetails().addAll(mapDetails(addAdjustBillDTO));
        //设置是按原料还是货物拣货
        adjustBill.setBasicEnum(addAdjustBillDTO.getBasicEnum());

    }

    /**
     * 初始化adjustBill
     *
     * @param addAdjustBillDTO 前端dto
     * @return AdjustBill 调剂计划实体
     */
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

    /**
     * 设置调剂计划详情
     *
     * @param addAdjustBillDTO 前端dto
     * @return 调剂计划详情集合
     */
    private Set<AdjustBillDetail> mapDetails(AddAdjustBillDTO addAdjustBillDTO) {
        Set<AdjustBillDetail> billDetails = new HashSet<>();
        for (AdjustBillDetailDTO adjustBillDetailDTO : addAdjustBillDTO.getDetails()) {
            AdjustBillDetail adjustBillDetail = new AdjustBillDetail();
            //原料或货物编码
            adjustBillDetail.setGoods(adjustBillDetailDTO.getRawMaterial());
            //应拣数量
            adjustBillDetail.setShippedAmount(adjustBillDetailDTO.getShippedAmount());
            //实拣数量
            adjustBillDetail.setActualAmount(adjustBillDetailDTO.getActualAmount());
            //备注信息
            adjustBillDetail.setMemo(adjustBillDetailDTO.getMemo());
            billDetails.add(adjustBillDetail);
        }
        return billDetails;
    }

    //TODO
    private String generateBillCode() {
        return String.valueOf(System.currentTimeMillis());
    }

    private boolean isFromPlanBill(AddAdjustBillDTO addAdjustBillDTO) {

        return !StringUtils.isEmpty(addAdjustBillDTO.getRootCode());
    }
}
