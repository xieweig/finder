package cn.sisyphe.coffee.bill.viewmodel.base;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 单据明细DTO基类
 * 后续所有用到单据明细的dto都必须继承自本类
 * 本类不能直接被实例化，避免在使用中错误，本类实现基础的单据明细信息
 * 需要为单据明细增加数据时必须继承本类来实现，在使用中必须使用子类来逻辑操作
 * @author bifenglin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDetailDTO {
    /**
     * 应拣数量
     */
    private Integer shippedAmount;

    /**
     * 实拣数量
     */
    private Integer actualAmount;

    /**
     * 原料
     */
    private RawMaterial rawMaterial;

    public Integer getShippedAmount() {
        return shippedAmount;
    }

    public void setShippedAmount(Integer shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }
}
