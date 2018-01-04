package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/1/4.
 */
public class DeliveryBillDetailDTO implements Serializable {


    /**
     * 调入站点
     */
    private String inStation;

    /**
     * 出库站点
     */
    private String outStation;


    /**
     * 完成度
     */
    private BigDecimal progress;

    /**
     * 备注
     */
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public String getInStation() {

        return inStation;
    }

    public void setInStation(String inStation) {
        this.inStation = inStation;
    }

    public String getOutStation() {
        return outStation;
    }

    public void setOutStation(String outStation) {
        this.outStation = outStation;
    }

    @Override
    public String toString() {
        return "DeliveryBillDetailDTO{" +
                "inStation='" + inStation + '\'' +
                ", outStation='" + outStation + '\'' +
                ", progress=" + progress +
                ", memo='" + memo + '\'' +
                '}';
    }
}