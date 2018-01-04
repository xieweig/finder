package cn.sisyphe.coffee.bill.viewmodel.plan;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;

/**
 * @author Amy 2018/1/3
 * 计划单返回实体
 */
public class ResultPlanBillLocationDTO {
    /**
     * 出库位置
     */
    private AbstractLocation outLocation;
    /**
     * 入库位置
     */
    private AbstractLocation inLocation;

    /**
     * 实收最小单位数量
     */
    private int amount;

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public AbstractLocation getInLocation() {
        return inLocation;
    }

    public void setInLocation(AbstractLocation inLocation) {
        this.inLocation = inLocation;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultPlanBillLocationDTO)) return false;

        ResultPlanBillLocationDTO that = (ResultPlanBillLocationDTO) o;

        if (getAmount() != that.getAmount()) return false;
        if (getOutLocation() != null ? !getOutLocation().equals(that.getOutLocation()) : that.getOutLocation() != null)
            return false;
        return getInLocation() != null ? getInLocation().equals(that.getInLocation()) : that.getInLocation() == null;
    }

    @Override
    public int hashCode() {
        int result = getOutLocation() != null ? getOutLocation().hashCode() : 0;
        result = 31 * result + (getInLocation() != null ? getInLocation().hashCode() : 0);
        result = 31 * result + getAmount();
        return result;
    }
}
