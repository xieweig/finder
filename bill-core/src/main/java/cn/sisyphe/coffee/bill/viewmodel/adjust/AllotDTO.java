package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;

import java.util.Map;

/**
 * @Date 2018/1/11 17:07
 * @description
 */
public class AllotDTO {

    /**
     * 单据编号
      */
    private String billCode;

    /**
     * 调入库位
      */
    private Storage inStorage;

    /**
     * 货物编码和实际调入数量详情
      */
    private Map<String, Integer> details;


    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Storage getInStorage() {
        return inStorage;
    }

    public void setInStorage(Storage inStorage) {
        this.inStorage = inStorage;
    }

    public Map<String, Integer> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Integer> details) {
        this.details = details;
    }
}
