package cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial.cloud;
import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author Amy
 */
@Component
public class LocalRawMaterialCloudRepository implements RawMaterialCloudRepository {


    @Override
    public ResponseResult findRawMaterialCodesByRawMaterialName(String rawMaterialName) {
        return new ResponseResult();
    }
}
