package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Amy on 2018/1/4 09:17
 *         describe 公共数据
 */
@Api(description = "公用信息")
@RequestMapping("/api/bill/shared")
@RestController
@CrossOrigin(origins = "*")
public class SharedController {
    @Autowired
    private SharedManager sharedManager;

    @ApiOperation(value = "查询树结构的站点信息")
    @RequestMapping(path = "/findLogisticCodeByStationCode", method = RequestMethod.GET)
    public ResponseResult findLogisticCodeByStationCode(@RequestParam("stationCode") String stationCode) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("logisticCode", sharedManager.findLogisticCodeByStationCode(stationCode));
        return responseResult;
    }
}
