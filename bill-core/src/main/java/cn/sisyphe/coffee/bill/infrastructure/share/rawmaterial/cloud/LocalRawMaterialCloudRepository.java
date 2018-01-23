package cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial.cloud;
import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author ncmao
 * @Date 2017/12/29 10:18
 * @description
 */

@Component
public class LocalRawMaterialCloudRepository implements RawMaterialCloudRepository {


    @Override
    public ResponseResult findRawMaterialCodesByRawMaterialName(String rawMaterialName) {
        return new ResponseResult();
    }
}
