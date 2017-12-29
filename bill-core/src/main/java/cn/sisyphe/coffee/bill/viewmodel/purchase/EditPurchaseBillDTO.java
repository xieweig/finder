package cn.sisyphe.coffee.bill.viewmodel.purchase;

/**
 * Created by XiongJing on 2017/12/29.
 * remark：重新修改进货单DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class EditPurchaseBillDTO extends AddPurchaseBillDTO {

    /**
     * 单据编码
     */
    private String billCode;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Override
    public String toString() {
        return "EditPurchaseBillDTO{" +
                "billCode='" + billCode + '\'' +
                "} " + super.toString();
    }
}
