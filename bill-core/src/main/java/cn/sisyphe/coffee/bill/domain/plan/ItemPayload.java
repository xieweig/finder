package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;

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
    private Station inStation;
    private Station outStation;
    private BasicEnum basicEnum;

    private List<AbstractGoods> goods = new ArrayList<>();


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

    public Station getInStation() {
        return inStation;
    }

    public void setInStation(Station inStation) {
        this.inStation = inStation;
    }

    public Station getOutStation() {
        return outStation;
    }

    public void setOutStation(Station outStation) {
        this.outStation = outStation;
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
}
