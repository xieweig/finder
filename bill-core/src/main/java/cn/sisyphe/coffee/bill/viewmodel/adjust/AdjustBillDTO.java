package cn.sisyphe.coffee.bill.viewmodel.adjust;


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


    public List<ChildPlanBillDetailDTO> childPlanBillDetailDTOS;


    public List<ChildPlanBillDetailDTO> getChildPlanBillDetailDTOS() {
        return childPlanBillDetailDTOS;
    }

    public void setChildPlanBillDetailDTOS(List<ChildPlanBillDetailDTO> childPlanBillDetailDTOS) {
        this.childPlanBillDetailDTOS = childPlanBillDetailDTOS;
    }
}
