package cn.sisyphe.coffee.bill.viewmodel.plan;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;

import java.util.Set;

/**
 * @author Amy 2018/1/3
 * 计划单返回实体
 */
public class ResultPlanBillGoodsDTO {
    /**
     * 数据库物品
     */
    private AbstractGoods goods;
    private Set<ResultPlanBillLocationDTO> resultPlanBillDetailDTOSet;

    public AbstractGoods getGoods() {
        return goods;
    }

    public void setGoods(AbstractGoods goods) {
        this.goods = goods;
    }

    public Set<ResultPlanBillLocationDTO> getResultPlanBillDetailDTOSet() {
        return resultPlanBillDetailDTOSet;
    }

    public void setResultPlanBillDetailDTOSet(Set<ResultPlanBillLocationDTO> resultPlanBillDetailDTOSet) {
        this.resultPlanBillDetailDTOSet = resultPlanBillDetailDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultPlanBillGoodsDTO)) return false;

        ResultPlanBillGoodsDTO that = (ResultPlanBillGoodsDTO) o;

        if (getGoods() != null ? !getGoods().equals(that.getGoods()) : that.getGoods() != null) return false;
        return getResultPlanBillDetailDTOSet() != null ? getResultPlanBillDetailDTOSet().equals(that.getResultPlanBillDetailDTOSet()) : that.getResultPlanBillDetailDTOSet() == null;
    }

    @Override
    public int hashCode() {
        int result = getGoods() != null ? getGoods().hashCode() : 0;
        result = 31 * result + (getResultPlanBillDetailDTOSet() != null ? getResultPlanBillDetailDTOSet().hashCode() : 0);
        return result;
    }
}
