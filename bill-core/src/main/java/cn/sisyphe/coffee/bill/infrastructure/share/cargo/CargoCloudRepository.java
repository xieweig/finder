package cn.sisyphe.coffee.bill.infrastructure.share.cargo;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ncmao
 * @Date 2017/12/29 10:16
 * @description
 */
@FeignClient(value = "COFFEE-BASEINFO", fallback = LocalCargoCloudRepository.class)
public interface CargoCloudRepository {

    @RequestMapping(path = "/api/v1/baseInfo/cargo/findCargoCodeListByCargoName", method = RequestMethod.GET)
    ResponseResult findCargoCodesByCargoName(@RequestParam("cargoName") String cargoName);
}
