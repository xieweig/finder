package cn.sisyphe.coffee.bill.domain.base.model.location;

/**
 * Created by heyong on 2017/12/21 10:22
 * Description: 供应商
 *
 * @author heyong
 */
public class Supplier extends AbstractLocation {

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商地址
     */
    private String address;

    public Supplier(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 位置代码
     *
     * @return
     */
    @Override
    public String code() {
        return supplierCode;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
