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
     * 所属原料编码
     */
    private String belongMaterialCode;

    @Override
    public String toString() {
        return "AdjustBillDetail{" +
                "belongMaterialCode='" + belongMaterialCode + '\'' +
                '}';
    }

    public String getBelongMaterialCode() {
        return belongMaterialCode;
    }

    public void setBelongMaterialCode(String belongMaterialCode) {
        this.belongMaterialCode = belongMaterialCode;
    }
}
