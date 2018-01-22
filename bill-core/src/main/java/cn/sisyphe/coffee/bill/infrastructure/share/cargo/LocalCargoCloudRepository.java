package cn.sisyphe.coffee.bill.infrastructure.share.cargo;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author ncmao
 * @Date 2017/12/29 10:18
 * @description
 */

@Component
public class LocalCargoCloudRepository implements CargoCloudRepository {


    @Override
    public ResponseResult findCargoCodesByCargoName(String cargoName) {
        return new ResponseResult();
    }
}
