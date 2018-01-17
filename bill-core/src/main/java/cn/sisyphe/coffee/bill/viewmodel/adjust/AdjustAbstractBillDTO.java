package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.viewmodel.base.AbstractBillDTO;

import java.util.Set;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：多条件查询中的单条调剂单据信息
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustAbstractBillDTO extends AbstractBillDTO {


    private Set<AdjustBillDetailDTO> details;

    public Set<AdjustBillDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(Set<AdjustBillDetailDTO> details) {
        this.details = details;
    }
}
