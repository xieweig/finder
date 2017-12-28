package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.plan.strategy.AbstractCastableStrategy;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/27 11:55
 * @description
 */
public class ItemPayload {

    private BillTypeEnum billType;
    private String billName;
    private String billCode;
    private String memo;
    private AbstractLocation inLocation;
    private AbstractLocation outLocation;
    private BasicEnum basicEnum;
    private List<AbstractGoods> goods = new ArrayList<>();

    private AbstractCastableStrategy castableStrategy;


    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public AbstractLocation getInLocation() {
        return inLocation;
    }

    public void setInLocation(AbstractLocation inLocation) {
        this.inLocation = inLocation;
    }

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public List<AbstractGoods> getGoods() {
        return goods;
    }

    public void setGoods(List<AbstractGoods> goods) {
        this.goods = goods;
    }

    public void addGood(AbstractGoods good) {
        this.goods.add(good);
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public AbstractCastableStrategy getCastableStrategy() {
        return castableStrategy;
    }

    public void setCastableStrategy(AbstractCastableStrategy castableStrategy) {
        this.castableStrategy = castableStrategy;
    }

    public void doCast(BillRepository billRepository) {
        this.castableStrategy.cast(this, billRepository);
    }
}
