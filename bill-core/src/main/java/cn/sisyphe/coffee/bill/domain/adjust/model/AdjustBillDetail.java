package cn.sisyphe.coffee.bill.domain.adjust.model;

import cn.sisyphe.coffee.bill.domain.base.model.AbstractBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.AbstractBillDetailDTO;
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
public class AdjustBillDetail extends AbstractBillDetail {

    /**
     * 所属原料编码
     */
    private String belongMaterialCode;


    @Override
    protected void unbuildExtend(AbstractBillDetailDTO abstractBillDetailDTO) {
        AdjustBillDetailDTO adjustBillDetailDTO = (AdjustBillDetailDTO) abstractBillDetailDTO;
        this.setBelongMaterialCode(adjustBillDetailDTO.getBelongMaterialCode());
    }

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
