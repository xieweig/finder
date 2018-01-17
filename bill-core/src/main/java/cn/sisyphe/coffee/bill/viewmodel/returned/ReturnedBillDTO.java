package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;

import java.math.BigDecimal;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class ReturnedBillDTO extends BillDTO<ReturnedBillDetailDTO> {

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
