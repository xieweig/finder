package cn.sisyphe.coffee.bill.viewmodel.waybill;

import java.io.Serializable;
import java.util.List;

/**
 * 运单编辑(添加，修改明细DTO)
 */
public class EditWayBillDetailDTO implements Serializable {

    /**
     * 运单
     */
    private EditWayBillDTO editWayBillDTO;

    public EditWayBillDetailDTO(EditWayBillDTO editWayBillDTO) {
        this.editWayBillDTO = editWayBillDTO;

    }

    /**
     * 出库单号
     */
    private String outStorageBillCode;//

    /**
     * 录单人
     */
    private String operatorName;


    /**
     * 包号
     */
    private List<Long> packageNumbers;


    /**
     * 打包方式
     */
    private String packageType;

    /**
     * 品种数
     */

    private Integer totalCount;

    /**
     * 货物数
     */
    private Integer totalAmount;
    /**
     * 入库站点
     */
    private String inStationCode;

    /**
     * 出库站点
     */
    private String outStationCode;


    /**
     * 出库时间
     */
    private String outStorageTime;


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public EditWayBillDTO getEditWayBillDTO() {
        return editWayBillDTO;
    }

    public void setEditWayBillDTO(EditWayBillDTO editWayBillDTO) {
        this.editWayBillDTO = editWayBillDTO;
    }

    public List<Long> getPackageNumbers() {
        return packageNumbers;
    }

    public void setPackageNumbers(List<Long> packageNumbers) {
        this.packageNumbers = packageNumbers;
    }

    public String getOutStorageBillCode() {
        return outStorageBillCode;
    }

    public void setOutStorageBillCode(String outStorageBillCode) {
        this.outStorageBillCode = outStorageBillCode;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public String getOutStorageTime() {
        return outStorageTime;
    }

    public void setOutStorageTime(String outStorageTime) {
        this.outStorageTime = outStorageTime;
    }
}
