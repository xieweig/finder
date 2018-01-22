package cn.sisyphe.coffee.bill.viewmodel.adjust;


import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDetailDTO;

import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：多条件查询中的单条调剂单据信息
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustBillDTO extends BillDTO<AdjustBillDetailDTO> {

    /**
     * 自主调剂
     */
    private Boolean self;

    private List<ChildPlanBillDetailDTO> childPlanBillDetailDTOS;

    public AdjustBillDTO() {
        setBillType(BillTypeEnum.ADJUST);
    }


    public Boolean getSelf() {
        return self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }

    public List<ChildPlanBillDetailDTO> getChildPlanBillDetailDTOS() {
        return childPlanBillDetailDTOS;
    }

    public void setChildPlanBillDetailDTOS(List<ChildPlanBillDetailDTO> childPlanBillDetailDTOS) {
        this.childPlanBillDetailDTOS = childPlanBillDetailDTOS;
    }
}
