package cn.sisyphe.coffee.bill.domain.plan.model;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.db.DbStation;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author ncmao
 * @Date 2017/12/25 15:39
 * @description
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class PlanBillDetail extends BillDetail {

    /**
     * 出库位置
     */
    @Transient
    private AbstractLocation outLocation;

    /**
     * 入库位置
     */
    @Transient
    private AbstractLocation inLocation;

    @Transient
    private AbstractLocation transferLocation;


    /**
     * 数据库位置储存
     */
    @JsonIgnore
    private DbStation dbStation = new DbStation();

    /**
     * 更新前
     */
    @Override
    public void update() {
        super.update();
        if (inLocation != null) {
            dbStation.setInLocation(inLocation);
        }

        if (outLocation != null) {
            dbStation.setOutLocation(outLocation);
        }
    }

    /**
     * 载入
     */
    @PostLoad
    @PostPersist
    public void planBillUpload() {
        if (dbStation == null) {
            return;
        }

        inLocation = dbStation.getInLocation();
        outLocation = dbStation.getOutLocation();
    }

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public AbstractLocation getInLocation() {
        return inLocation;
    }

    public void setInLocation(AbstractLocation inLocation) {
        this.inLocation = inLocation;
    }

    public DbStation getDbStation() {
        return dbStation;
    }

    public void setDbStation(DbStation dbStation) {
        this.dbStation = dbStation;
    }

    @JsonIgnore
    public String getInOutStationCode() {
        return inLocation.code() + outLocation.code();
    }

    public AbstractLocation getTransferLocation() {
        return transferLocation;
    }

    public void setTransferLocation(AbstractLocation transferLocation) {
        this.transferLocation = transferLocation;
    }

}
