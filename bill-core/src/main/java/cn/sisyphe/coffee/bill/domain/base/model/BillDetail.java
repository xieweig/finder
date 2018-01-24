package cn.sisyphe.coffee.bill.domain.base.model;

import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Transient;

/**
 * 单据明细
 *
 * @author heyong
 */
@MappedSuperclass
public abstract class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billDetailId;

    /**
     * 物品
     */
    @Transient
    private AbstractGoods goods;

    /**
     * 包号
     */
    private String packageCode;

    /**
     * 数据库物品
     */
    @JsonIgnore
    private DbGoods dbGoods = new DbGoods();

    /**
     * 更新前, 在数据库操作中调用
     */
    public void update() {
        if (goods == null) {
            return;
        }

        dbGoods.setGoods(goods);
    }

    /**
     * 载入
     */
    @PostLoad
    @PostPersist
    public void load() {
        if (dbGoods == null) {
            return;
        }

        goods = dbGoods.getGoods();
    }

    /**
     * 应拣数量
     */
    private int shippedAmount;

    /**
     * 实拣数量
     */
    private int actualAmount;

    /**
     * 实拣总量（原料的）
     */
    private Integer actualTotalAmount;


//    public void unbuild(AbstractBillDetailDTO abstractBillDetailDTO){
//        this.setShippedAmount(abstractBillDetailDTO.getShippedAmount());
//        this.setActualAmount(abstractBillDetailDTO.getActualAmount());
//
//
//        unbuildExtend(abstractBillDetailDTO);
//    }
//
//    protected abstract void unbuildExtend(AbstractBillDetailDTO abstractBillDetailDTO);

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public AbstractGoods getGoods() {
        return goods;
    }

    public AbstractGoods alwaysCanGetGoods() {
        if (dbGoods == null) {
            return null;
        }

        return dbGoods.getGoods();
    }

    public void setGoods(AbstractGoods goods) {
        this.goods = goods;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public int getShippedAmount() {
        return shippedAmount;
    }

    public void setShippedAmount(int shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public DbGoods getDbGoods() {
        return dbGoods;
    }

    public void setDbGoods(DbGoods dbGoods) {
        this.dbGoods = dbGoods;
    }

    public Integer getActualTotalAmount() {
        return actualTotalAmount;
    }

    public void setActualTotalAmount(Integer actualTotalAmount) {
        this.actualTotalAmount = actualTotalAmount;
    }

    @Override
    public String toString() {
        return "AbstractBillDetail{" +
                "billDetailId=" + billDetailId +
                ", goods=" + goods +
                ", packageCode='" + packageCode + '\'' +
                ", dbGoods=" + dbGoods +
                ", shippedAmount=" + shippedAmount +
                ", actualAmount=" + actualAmount +
                '}';
    }
}
