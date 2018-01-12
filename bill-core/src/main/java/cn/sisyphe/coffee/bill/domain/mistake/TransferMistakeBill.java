package cn.sisyphe.coffee.bill.domain.mistake;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Amy on 2018/1/12.
 * describe：流转误差单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class TransferMistakeBill extends Bill<TransferMistakeBillDetail> {
    public static final String TRANSFER_MISTAKE_IN_STORAGE_PREFIX = "TMCK";
    public TransferMistakeBill() {
        setBillCodePrefix(TRANSFER_MISTAKE_IN_STORAGE_PREFIX);
        setBillType(BillTypeEnum.TRANSFER_MISTAKE);
    }
}
