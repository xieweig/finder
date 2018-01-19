package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;

import java.math.BigDecimal;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class ReturnedBillDTO extends BillDTO<ReturnedBillDetailDTO> {

    public ReturnedBillDTO() {
        setBillType(BillTypeEnum.RETURNED);
    }

    /**
     * 供应商
     */
    private Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
