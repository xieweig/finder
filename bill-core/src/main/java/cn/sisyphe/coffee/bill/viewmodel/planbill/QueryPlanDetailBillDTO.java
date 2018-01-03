package cn.sisyphe.coffee.bill.viewmodel.planbill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bifenglin
 */
public class QueryPlanDetailBillDTO implements Serializable{

    /**
     * 完成率
     */
    private BigDecimal progress;
    /**
     * 站点计划号
     */
    private String stationPlanCode;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 录单人
     */
    private String creatorName;

    /**
     * 调入站点
     */
    private String inStation;

    /**
     * 出库站点
     */
    private String outStation;

    /**
     * 数量
     */
    private int amount;

    /**
     * 规格品种
     */
    private int species;

    /**
     * 备注
     */
    private String memo;
    /**
     * 状态
     */
    private BillStateEnum billState;

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public String getStationPlanCode() {
        return stationPlanCode;
    }

    public void setStationPlanCode(String stationPlanCode) {
        this.stationPlanCode = stationPlanCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSpecies() {
        return species;
    }

    public void setSpecies(int species) {
        this.species = species;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}