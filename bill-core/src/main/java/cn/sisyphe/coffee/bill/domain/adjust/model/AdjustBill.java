package cn.sisyphe.coffee.bill.domain.adjust.model;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单
 * version: 1.0
 *
 * @author XiongJing
 */
@Entity
@Table(name = "adjust_bill")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class AdjustBill extends Bill<AdjustBillDetail> {

    public AdjustBill() {
        setBillType(BillTypeEnum.ADJUST);
    }


    /**
     * 自主调剂
     */
    private Boolean self;

    /**
     * 是否是自主调剂
     */

    public boolean isSelfAdjust() {
        return StringUtils.isEmpty(getSourceCode());
    }


    public Boolean getSelf() {
        return self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }

    @Override
    public String billCodePrefix() {
        return "TJCK";
    }
}
