package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class AllotBillDTO extends BillDTO<AllotBillDetailDTO> {

    public AllotBillDTO() {
        setBillType(BillTypeEnum.ALLOT);
    }

    private String allotMemo;

    /**
     * 入库单入库时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inStorageBillInStorageDate;

    /**
     * 入库单入库站点
     */
    private String inStorageBillInStationCode;

    /**
     * 入库单出库站点
     */
    private String inStorageBillOutStationCode;

    public String getAllotMemo() {
        return allotMemo;
    }

    public void setAllotMemo(String allotMemo) {
        this.allotMemo = allotMemo;
    }


    public Date getInStorageBillInStorageDate() {
        return inStorageBillInStorageDate;
    }

    public void setInStorageBillInStorageDate(Date inStorageBillInStorageDate) {
        this.inStorageBillInStorageDate = inStorageBillInStorageDate;
    }

    public String getInStorageBillInStationCode() {
        return inStorageBillInStationCode;
    }

    public void setInStorageBillInStationCode(String inStorageBillInStationCode) {
        this.inStorageBillInStationCode = inStorageBillInStationCode;
    }

    public String getInStorageBillOutStationCode() {
        return inStorageBillOutStationCode;
    }

    public void setInStorageBillOutStationCode(String inStorageBillOutStationCode) {
        this.inStorageBillOutStationCode = inStorageBillOutStationCode;
    }
}
