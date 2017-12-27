package cn.sisyphe.coffee.bill.domain.headquarters;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * @author ncmao
 * @Date 2017/12/25 15:39
 * @description
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class HeadQuarterBillDetail extends BillDetail {

    @Column
    private Long inStationId;

    @Column
    private Long outStationId;


    @Column
    private String inStationName;

    @Column
    private String outStationName;

    //不做级联操作，创建的时候先创建货物明细在手动建立关系
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_detail_id")
    private RawMaterialDetail rawMaterialDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_detail_id")
    private CargoDetail cargoDetail;

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

    public RawMaterialDetail getRawMaterialDetail() {
        return rawMaterialDetail;
    }

    public void setRawMaterialDetail(RawMaterialDetail rawMaterialDetail) {
        this.rawMaterialDetail = rawMaterialDetail;
    }

    public CargoDetail getCargoDetail() {
        return cargoDetail;
    }

    public void setCargoDetail(CargoDetail cargoDetail) {
        this.cargoDetail = cargoDetail;
    }
}
