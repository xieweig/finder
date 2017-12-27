package cn.sisyphe.bill.core.domain.base.model.goods;

/**
 * Created by heyong on 2017/12/21 10:45
 * Description: 原料
 *
 * @author heyong
 */
public class RawMaterial extends AbstractGoods {

    /**
     * 原料代码
     */
    private String rawMaterialCode;
    /**
     * 原料名称
     */
    private String rawMaterialName;

    public RawMaterial(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public void setRawMaterialName(String rawMaterialName) {
        this.rawMaterialName = rawMaterialName;
    }

    /**
     * 代码
     *
     * @return
     */
    @Override
    public String code() {
        return rawMaterialCode;
    }

}
