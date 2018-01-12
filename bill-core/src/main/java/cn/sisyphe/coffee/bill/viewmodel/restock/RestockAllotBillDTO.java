package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * @author mayupeng
 * @Date 2018/01/12
 * @description 退库调拨单DTO
 */
public class RestockAllotBillDTO {
    /**
     * 单据编号
     */
    private String billCode;
    /**
     * 单据属性
     */
    private SourcePlanTypeEnum billProperty;
    /**
     * 出库站点
     */
    private AbstractLocation outLocation;
    /**
     * 入库站点（调出库位）
     */
    private AbstractLocation inLocationOutStorage;
    /**
     * 入库站点（入库库位）
     */
    private AbstractLocation inLocationInStorage;
    /**
     * 入库时间
     */
    private Date inWareHouseTime;
    /**
     * 退库调拨单明细
     */
    private List<RestockAllotDetailBillDTO> restockAllotDetailBillDTOList;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public SourcePlanTypeEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(SourcePlanTypeEnum billProperty) {
        this.billProperty = billProperty;
    }

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public AbstractLocation getInLocationOutStorage() {
        return inLocationOutStorage;
    }

    public void setInLocationOutStorage(AbstractLocation inLocationOutStorage) {
        this.inLocationOutStorage = inLocationOutStorage;
    }

    public AbstractLocation getInLocationInStorage() {
        return inLocationInStorage;
    }

    public void setInLocationInStorage(AbstractLocation inLocationInStorage) {
        this.inLocationInStorage = inLocationInStorage;
    }

    public Date getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public List<RestockAllotDetailBillDTO> getRestockAllotDetailBillDTOList() {
        return restockAllotDetailBillDTOList;
    }

    public void setRestockAllotDetailBillDTOList(List<RestockAllotDetailBillDTO> restockAllotDetailBillDTOList) {
        this.restockAllotDetailBillDTOList = restockAllotDetailBillDTOList;
    }
}
