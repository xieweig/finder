package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author bifenglin
 */
public class RestockBillDetailDTO {

    /**
     * 应拣数量
     */
    private int shippedAmount;

    /**
     * 实拣数量
     */
    private int actualAmount;

    /**
     * 原料
     */
    private RawMaterial rawMaterial;

    public int getShippedAmount() {
        return shippedAmount;
    }

    public void setShippedAmount(int shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

}
