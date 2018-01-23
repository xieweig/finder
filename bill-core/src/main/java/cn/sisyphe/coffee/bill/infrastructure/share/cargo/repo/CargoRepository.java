package cn.sisyphe.coffee.bill.infrastructure.share.cargo.repo;

import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/29 10:21
 * @description
 */
public interface CargoRepository {

    /**
     * 通过货物名称模糊查询货物编码
     *
     * @param cargoName 货物名称
     * @return 货物编码列表
     */
    List<String> findCargoCodesByCargoName(String cargoName);
}
