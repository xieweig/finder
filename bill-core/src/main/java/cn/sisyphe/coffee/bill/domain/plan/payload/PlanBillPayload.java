package cn.sisyphe.coffee.bill.domain.plan.payload;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.plan.strategy.AbstractCastableStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/27 11:55
 * @description
 */
public class PlanBillPayload {

    //总是PLAN
    private BillTypeEnum billType;
    //计划名称
    private String billName;
    //计划编码
    private String billCode;
    //备注
    private String memo;
    //入站站点
    private AbstractLocation inLocation;

    //出站站点
    private AbstractLocation outLocation;
    //中转站点
    private AbstractLocation transferLocation;

    //按货物还是原料
    private BasicEnum basicEnum;

    //原始计划编码
    private String parentBillCode;
    private List<PlanBillPayloadDetail> goodDetails = new ArrayList<>();
    //策略,根据不同的单据类型选择不同的策略
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

    public List<PlanBill> doCast() {
        return this.castableStrategy.cast(this);
    }

    public List<PlanBillPayloadDetail> getGoodDetails() {
        return goodDetails;
    }

    public void setGoodDetails(List<PlanBillPayloadDetail> goodDetails) {
        this.goodDetails = goodDetails;
    }

    public void addGoodsDetail(PlanBillPayloadDetail planBillPayloadDetail) {
        this.goodDetails.add(planBillPayloadDetail);
    }

    public AbstractLocation getTransferLocation() {
        return transferLocation;
    }

    public void setTransferLocation(AbstractLocation transferLocation) {
        this.transferLocation = transferLocation;
    }

    public String getParentBillCode() {
        return parentBillCode;
    }

    public void setParentBillCode(String parentBillCode) {
        this.parentBillCode = parentBillCode;
    }
}
