package cn.sisyphe.coffee.bill.domain.mistake.model;

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
public class MistakeBill extends Bill<MistakeBillDetail> {
    public MistakeBill() {
        setBillType(BillTypeEnum.TRANSFER_MISTAKE);
    }

    @Override
    public String billCodePrefix() {
        return "TMCK";
    }
}
