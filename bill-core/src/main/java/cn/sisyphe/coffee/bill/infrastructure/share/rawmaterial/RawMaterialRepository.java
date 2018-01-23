package cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial;

import java.util.List;

/**
 * @author Amy
 */
public interface RawMaterialRepository {

    /**
     * 通过原料名称模糊查询原料编码集合
     *
     * @param cargoName
     * @return
     */
    List<String> findRawMaterialCodesByRawMaterialName(String cargoName);
}
