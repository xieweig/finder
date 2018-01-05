package cn.sisyphe.coffee.bill.viewmodel.plan.child;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ncmao
 * @Date 2018/1/5 10:11
 * @description
 */
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class ChildPlanBillDetailDTO {

    /**
     * 货物名称
     */
    private String cargoName;

    /**
     * 编码
     */
    private String goodsCode;


    /**
     * 应拣货物数量
     */
    private Integer amount;

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
}
