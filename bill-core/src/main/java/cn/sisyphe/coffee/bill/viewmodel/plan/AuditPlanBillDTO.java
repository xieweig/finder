package cn.sisyphe.coffee.bill.viewmodel.plan;

/**
 * @author ncmao
 * @Date 2018/1/4 9:14
 * @description 审核总部计划DTO
 */
public class AuditPlanBillDTO {

    /**
     * 总部计划编号
      */
    private String billCode;


    /**
     * 审核意见
      */
    private String auditMemo;


    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    @Override
    public String toString() {
        return "AuditPlanBillDTO{" +
                "billCode='" + billCode + '\'' +
                ", auditMemo='" + auditMemo + '\'' +
                '}';
    }
}
