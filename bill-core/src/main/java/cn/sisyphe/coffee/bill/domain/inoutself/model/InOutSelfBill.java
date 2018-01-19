package cn.sisyphe.coffee.bill.domain.inoutself.model;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by heyong on 2018/1/19 10:13
 * Description: 其它出入库
 * @author heyong
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class InOutSelfBill extends Bill<InOutSelfBillDetail> {

    @Override
    public String billCodePrefix() {
        return "IOSB";
    }

    public InOutSelfBill() {
        setBillType(BillTypeEnum.IN_OUT_SELF_BILL);
    }
}
