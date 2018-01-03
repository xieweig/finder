package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author ncmao
 * @Date 2017/12/27 16:11
 * 退库计划详情
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class RestockBillDetail extends BillDetail {


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BillDetail that = (BillDetail) o;
        return Objects.equals(getBillDetailId(), that.getBillDetailId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getBillDetailId());
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDetailsRemarks() {
        return detailsRemarks;
    }

    public void setDetailsRemarks(String detailsRemarks) {
        this.detailsRemarks = detailsRemarks;
    }

    /**
     *
     *notes :
     *  配送总价
     */
    private Integer totalPrice;
    /**
     *
     *notes :
     *  货物数量
     */
    @Column
    private Integer actualNumber;
    @Column
    private Integer expectedNumber;
    @Column(length = 500)
    private String detailsRemarks;

    public Integer getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Integer actualNumber) {
        this.actualNumber = actualNumber;
    }

    public Integer getExpectedNumber() {
        return expectedNumber;
    }

    public void setExpectedNumber(Integer expectedNumber) {
        this.expectedNumber = expectedNumber;
    }
}
