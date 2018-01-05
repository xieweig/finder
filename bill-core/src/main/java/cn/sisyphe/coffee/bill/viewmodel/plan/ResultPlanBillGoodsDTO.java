package cn.sisyphe.coffee.bill.viewmodel.plan;

import java.util.Set;

/**
 * @author Amy 2018/1/3
 * 计划单返回实体
 */
public class ResultPlanBillGoodsDTO {
    /**
     * 数据库物品
     */
    private String goodsCode;
    private Set<ResultPlanBillLocationDTO> resultPlanBillDetailDTOSet;

    public Set<ResultPlanBillLocationDTO> getResultPlanBillDetailDTOSet() {
        return resultPlanBillDetailDTOSet;
    }

    public void setResultPlanBillDetailDTOSet(Set<ResultPlanBillLocationDTO> resultPlanBillDetailDTOSet) {
        this.resultPlanBillDetailDTOSet = resultPlanBillDetailDTOSet;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
}
