package cn.sisyphe.coffee.bill.domain.plan.payload;

import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;

/**
 * @author ncmao
 * @Date 2017/12/28 14:35
 * @description
 */
public class PlanBillPayloadDetail {

    private Integer amount;


    private AbstractGoods goods;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public AbstractGoods getGoods() {
        return goods;
    }

    public void setGoods(AbstractGoods goods) {
        this.goods = goods;
    }
}
