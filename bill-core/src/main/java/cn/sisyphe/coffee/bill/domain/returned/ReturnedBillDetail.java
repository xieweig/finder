package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author ncmao
 * @Date 2017/12/27 16:11
 * 退货计划详情
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class ReturnedBillDetail extends BillDetail {
    /**
     * 数量
     */
    private int amount;

    /**
     * 备注
     */
    private String memo;

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BillDetail that = (BillDetail) o;
        return Objects.equals(getBillDetailId(), that.getBillDetailId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getBillDetailId());
    }
}
