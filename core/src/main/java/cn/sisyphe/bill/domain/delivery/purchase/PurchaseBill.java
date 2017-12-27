package cn.sisyphe.bill.domain.delivery.purchase;

import cn.sisyphe.bill.domain.base.model.Bill;
import cn.sisyphe.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 进货单
 * @author heyong
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public final class PurchaseBill extends Bill<PurchaseBillDetail> {

    public PurchaseBill() {
        setBillType(BillTypeEnum.PURCHASE);
    }

}
