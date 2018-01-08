package cn.sisyphe.coffee.bill.domain.plan.dto;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;

import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/27 14:54
 * @description
 */
public class PlanBillDTO {
    private String billCode;

    private String billName;

    private BillTypeEnum billType;

    private String memo;

    private BasicEnum basicEnum;

    private List<PlanBillDetailDTO> planBillDetailDTOS;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<PlanBillDetailDTO> getPlanBillDetailDTOS() {
        return planBillDetailDTOS;
    }

    public void setPlanBillDetailDTOS(List<PlanBillDetailDTO> planBillDetailDTOS) {
        this.planBillDetailDTOS = planBillDetailDTOS;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }


}
