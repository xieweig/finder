package cn.sisyphe.coffee.bill.viewmodel.mistake;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

import java.util.Set;

/**
 * @author Amy on 2018/1/18.
 *         describe：
 */
public class ConditionQueryMistakeBill extends ConditionQueryBill {
    /**
     * 目标类型集合
     */
    private Set<BasicEnum> targetEnumSet;
    /**
     * 目标编号
     */
    private String targetCode;
    /**
     * 目标名称
     */
    private String targetName;
    /**
     * 出库站点
     */
    private Set<String> outStorageCodeSet;
    /**
     * 入库站点
     */
    private Set<String> inStorageCodeSet;


    public Set<BasicEnum> getTargetEnumSet() {
        return targetEnumSet;
    }

    public void setTargetEnumSet(Set<BasicEnum> targetEnumSet) {
        this.targetEnumSet = targetEnumSet;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Set<String> getOutStorageCodeSet() {
        return outStorageCodeSet;
    }

    public void setOutStorageCodeSet(Set<String> outStorageCodeSet) {
        this.outStorageCodeSet = outStorageCodeSet;
    }

    public Set<String> getInStorageCodeSet() {
        return inStorageCodeSet;
    }

    public void setInStorageCodeSet(Set<String> inStorageCodeSet) {
        this.inStorageCodeSet = inStorageCodeSet;
    }

    public boolean obtainTarget(Set<BasicEnum> targetEnumSet) {
        if (targetEnumSet == null || targetEnumSet.size()<=0) {
            return true;
        } else if (targetEnumSet.size() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
