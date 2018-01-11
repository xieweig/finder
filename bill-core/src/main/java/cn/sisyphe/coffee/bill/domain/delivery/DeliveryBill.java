package cn.sisyphe.coffee.bill.domain.delivery;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yichuan on 2017/12/19 17:33
 * Description: 配送单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class DeliveryBill extends Bill<DeliveryBillDetail> {
    public static final String DELIVERY_OUT_STORAGE_PREFIX = "PSCK";
    public DeliveryBill() {
        setBillCodePrefix(DELIVERY_OUT_STORAGE_PREFIX);
        setBillType(BillTypeEnum.DELIVERY);
    }


    /**
     * 操作人姓名
     */
    @Column
    private String operatorName;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
