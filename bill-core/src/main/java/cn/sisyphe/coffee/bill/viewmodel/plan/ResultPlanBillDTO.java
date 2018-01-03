package cn.sisyphe.coffee.bill.viewmodel.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import java.util.Set;

/**
 * @author Amy 2018/1/3
 *         计划单返回实体
 */
public class ResultPlanBillDTO {
    /**
     * 单据名称
     */
    private String billName;
    /**
     * 计划单编号
     */
    private String billCode;
    /**
     * 计划单类型
     */
    private BillTypeEnum billType;

    /**
     * 单据明细
     */
    private Set<ResultPlanBillGoodsDTO> planBillDetails;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public Set<ResultPlanBillGoodsDTO> getPlanBillDetails() {
        return planBillDetails;
    }

    public void setPlanBillDetails(Set<ResultPlanBillGoodsDTO> planBillDetails) {
        this.planBillDetails = planBillDetails;
    }

    @Override
    public String toString() {
        return "ResultPlanBillDTO{" +
                "billName='" + billName + '\'' +
                ", billCode='" + billCode + '\'' +
                ", billType=" + billType +
                ", planBillDetails=" + planBillDetails +
                '}';
    }
}
