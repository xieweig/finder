package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货计划单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class ReturnedBill extends Bill<ReturnedBillDetail> {
    public static final String RETURN_OUT_STORAGE_PREFIX = "THCK";

    public ReturnedBill() {
        setBillCodePrefix(RETURN_OUT_STORAGE_PREFIX);
        setBillType(BillTypeEnum.RETURNED);
    }

}
