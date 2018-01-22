package cn.sisyphe.coffee.bill.domain.base.model.db;


import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

/**
 * Created by heyong on 2017/12/26 22:08
 * Description: 数据库物品位置，用于数据库存储
 *
 * @author heyong
 * @Embeddable 将所有属性值自动加入引用实体中
 */
@Embeddable
public class DbGoods {

    /**
     * 货物代码
     */
    private String cargoCode;

    /**
     * 原料代码
     */
    private String rawMaterialCode;


    /**
     * 设置物品
     *
     * @param goods
     */
    public void setGoods(AbstractGoods goods) {

        if (goods instanceof Cargo) {
            cargoCode = goods.code();
        } else if (goods instanceof RawMaterial) {
            rawMaterialCode = goods.code();

            if (((RawMaterial) goods).getCargo() != null) {
                cargoCode = ((RawMaterial) goods).getCargo().getCargoCode();
            }
        }
    }

    /**
     * 获取物品
     *
     * @return
     */
    public AbstractGoods getGoods() {
        if (!StringUtils.isEmpty(rawMaterialCode)) {
            RawMaterial rawMaterial = new RawMaterial(rawMaterialCode);

            if (!StringUtils.isEmpty(cargoCode)) {
                rawMaterial.setCargo(new Cargo(cargoCode));
            }

            return rawMaterial;
        } else if (!StringUtils.isEmpty(cargoCode)) {
            return new Cargo(cargoCode);
        }


        return null;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    @Override
    public String toString() {
        return "DbGoods{" +
                "cargoCode='" + cargoCode + '\'' +
                ", rawMaterialCode='" + rawMaterialCode + '\'' +
                '}';
    }
}
