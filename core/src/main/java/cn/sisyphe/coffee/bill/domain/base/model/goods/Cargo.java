package cn.sisyphe.coffee.bill.domain.base.model.goods;

/**
 * Created by heyong on 2017/12/21 10:44
 * Description: 货物
 * @author heyong
 */
public class Cargo extends AbstractGoods {

    /**
     * 货物代码
     */
    private String cargoCode;

    /**
     * 货物名称
     */
    private String cargoName;

    public Cargo(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    @Override
    public String code() {
        return cargoCode;
    }
}
