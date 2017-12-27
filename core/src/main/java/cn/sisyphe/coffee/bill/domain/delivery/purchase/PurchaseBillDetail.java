package cn.sisyphe.coffee.bill.domain.delivery.purchase;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by heyong on 2017/12/21 11:55
 * Description: 进货单明细
 * @author heyong
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class PurchaseBillDetail extends BillDetail {

    /**
     * 进货价
     */
    private BigDecimal purchasingCost;

    public BigDecimal getPurchasingCost() {
        return purchasingCost;
    }

    public void setPurchasingCost(BigDecimal purchasingCost) {
        this.purchasingCost = purchasingCost;
    }

    @Override
    public String toString() {
        return "PurchaseBillDetail{" +
                "purchasingCost=" + purchasingCost +
                "} " + super.toString();
    }
}
