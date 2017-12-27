package cn.sisyphe.coffee.bill.domain.base.model;

import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;

import javax.persistence.*;

/**
 * 单据明细
 *
 * @author heyong
 */
@MappedSuperclass
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billDetailId;

    /**
     * 物品
     */
    @Transient
    private AbstractGoods goods;

    /**
     * 最小单位数量
     */
    private int amount;

    /**
     * 包号
     */
    private String packageCode;

    /**
     * 数据库物品
     */
    private DbGoods dbGoods = new DbGoods();

    /**
     * 更新前
     */
    @PrePersist
    @PreUpdate
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

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public AbstractGoods getGoods() {
        return goods;
    }

    public void setGoods(AbstractGoods goods) {
        this.goods = goods;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "billDetailId=" + billDetailId +
                ", goods=" + goods +
                ", amount=" + amount +
                ", packageCode='" + packageCode + '\'' +
                ", dbGoods=" + dbGoods +
                '}';
    }
}
