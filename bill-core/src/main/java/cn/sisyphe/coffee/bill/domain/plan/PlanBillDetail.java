package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    @Column
    private Long inStationId;

    @Column
    private Long outStationId;


    @Column
    private String inStationName;

    @Column
    private String outStationName;

    public String getInStationName() {
        return inStationName;
    }

    public void setInStationName(String inStationName) {
        this.inStationName = inStationName;
    }

    public String getOutStationName() {
        return outStationName;
    }

    public void setOutStationName(String outStationName) {
        this.outStationName = outStationName;
    }

    public Long getInStationId() {
        return inStationId;
    }

    public void setInStationId(Long inStationId) {
        this.inStationId = inStationId;
    }

    public Long getOutStationId() {
        return outStationId;
    }

    public void setOutStationId(Long outStationId) {
        this.outStationId = outStationId;
    }

    public String getOutInStation() {
        return inStationName + outStationName;
    }
}
