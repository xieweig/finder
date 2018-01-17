package cn.sisyphe.coffee.bill.domain.base.model;

import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.viewmodel.base.AbstractBillDetailDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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
     * 原料
     */
    private RawMaterial rawMaterial;

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }


    public void unbuild(AbstractBillDetailDTO abstractBillDetailDTO){
        this.setShippedAmount(abstractBillDetailDTO.getShippedAmount());
        this.setActualAmount(abstractBillDetailDTO.getActualAmount());


        unbuildExtend(abstractBillDetailDTO);
    }

    protected abstract void unbuildExtend(AbstractBillDetailDTO abstractBillDetailDTO);

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
