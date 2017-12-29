package cn.sisyphe.coffee.bill.infrastructure.share.supplier;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ncmao
 * @Date 2017/12/29 9:55
 * @description
 */
@FeignClient(value = "COFFEE-BASEINFO-API", fallback = LocalSupplierCloudRepository.class)
public interface SupplierCloudRepository {

    @RequestMapping(path = "/api/v1/baseInfo/supplier/findBySupplierCode", method = RequestMethod.GET)
    ResponseResult findSupplierByCode(@RequestParam("supplierCode") String supplierCode);

    @RequestMapping(path = "/api/v1/baseInfo/supplier/findByLikeSupplierName", method = RequestMethod.GET)
    ResponseResult findByLikeSupplierName(@RequestParam("supplierName") String supplierCode);
}
