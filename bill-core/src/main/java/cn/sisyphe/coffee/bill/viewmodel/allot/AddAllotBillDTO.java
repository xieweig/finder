package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;

import java.util.List;

/**
 * @author ncmao
 * @Date 2018/1/12 15:55
 * @description
 */
public class AddAllotBillDTO {

    /**
     * 按原料还是按货物
     */
    private BasicEnum basicEnum;

    /**
     * 入库站点和库房
     */
    private Station inStation;

    /**
     * 出库站点和库房
     */
    private Station outStation;

    /**
     * 入库单编号
     */
    private String inStorageBillCode;

    /**
     * 备注
     */
    private String memo;

    /**
     * 入库单类型，如果有
     */
    private BillTypeEnum inStorageBillType;

    /**
     * 调拨单详情
     */
    List<AllotBillDetailDTO> details;


    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<AllotBillDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<AllotBillDetailDTO> details) {
        this.details = details;
    }

    public String getInStorageBillCode() {
        return inStorageBillCode;
    }

    public void setInStorageBillCode(String inStorageBillCode) {
        this.inStorageBillCode = inStorageBillCode;
    }

    public BillTypeEnum getInStorageBillType() {
        return inStorageBillType;
    }

    public void setInStorageBillType(BillTypeEnum inStorageBillType) {
        this.inStorageBillType = inStorageBillType;
    }
}
