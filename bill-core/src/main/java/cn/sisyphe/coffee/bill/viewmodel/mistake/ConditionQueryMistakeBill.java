package cn.sisyphe.coffee.bill.viewmodel.mistake;

import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

/**
 * @author Amy on 2018/1/18.
 * describe：
 */
public class ConditionQueryMistakeBill extends ConditionQueryBill {
    /**
     * 误差单号
     */
    private String mistakeBillCode;

    /**
     * 是否为误差单的查询
     */
    private boolean isMistakeQuery = false;

    public String getMistakeBillCode() {
        return mistakeBillCode;
    }

    public void setMistakeBillCode(String mistakeBillCode) {
        this.mistakeBillCode = mistakeBillCode;
    }

    public boolean isMistakeQuery() {
        return isMistakeQuery;
    }

    public void setMistakeQuery(boolean mistakeQuery) {
        isMistakeQuery = mistakeQuery;
    }
}
