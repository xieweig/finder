package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import java.io.Serializable;


/**
 * 审核DTO
 */
public class AuditDeliveryBillDTO implements Serializable {

    /**
     * 单据code
     */
    private String billCode;

    /**
     * 审核是否通过
     */
    private Boolean auditPass;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 审核人code
     */
    private String auditOperatorCode;


    public String getBillCode() {

        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Boolean getAuditPass() {
        return auditPass;
    }

    public void setAuditPass(Boolean auditPass) {
        this.auditPass = auditPass;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditOperatorCode() {
        return auditOperatorCode;
    }

    public void setAuditOperatorCode(String auditOperatorCode) {
        this.auditOperatorCode = auditOperatorCode;
    }

    @Override
    public String toString() {
        return "AuditDeliveryBillDTO{" +
                "billCode='" + billCode + '\'' +
                ", auditPass=" + auditPass +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", auditOperatorCode='" + auditOperatorCode + '\'' +
                '}';
    }
}
