package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AllotBillDTO extends BillDTO<AllotBillDetailDTO> {

    public AllotBillDTO() {
        setBillType(BillTypeEnum.ALLOT);
    }

    /**
     * 具体的单据类型
     */
    private BillTypeEnum specificBillType;

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }
}
