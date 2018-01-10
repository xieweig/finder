package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单明细
 * version: 1.0
 *
 * @author XiongJing
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class AdjustBillDetail extends BillDetail {

    /**
     * 应拣数量
     */
    private Integer shippedAmount;

    /**
     * 实拣数量
     */
    private Integer actualAmount;
    /**
     * 备注
     */
    private String memo;


    /**
     * 所属原料编码
      */
    private String belongMaterialCode;

    public Integer getShippedAmount() {
        return shippedAmount;
    }

    public void setShippedAmount(Integer shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "AdjustBillDetail{" +
                "shippedAmount=" + shippedAmount +
                ", actualAmount=" + actualAmount +
                ", memo='" + memo + '\'' +
                "} " + super.toString();
    }

    public String getBelongMaterialCode() {
        return belongMaterialCode;
    }

    public void setBelongMaterialCode(String belongMaterialCode) {
        this.belongMaterialCode = belongMaterialCode;
    }
}
