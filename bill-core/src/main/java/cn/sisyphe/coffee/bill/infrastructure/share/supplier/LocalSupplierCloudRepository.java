package cn.sisyphe.coffee.bill.infrastructure.share.supplier;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author ncmao
 * @Date 2017/12/29 9:59
 * @description
 */
@Component
public class LocalSupplierCloudRepository implements SupplierCloudRepository {
    @Override
    public ResponseResult findSupplierByCode(String supplierCode) {
        return new ResponseResult();
    }
}
