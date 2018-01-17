package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.infrastructure.share.storage.TempStorage;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ApiOperation(value = "临时库房")
    @RequestMapping(path = "/queryStorageByStationCode", method = RequestMethod.GET)
    public ResponseResult queryStorageByStationCode(@RequestParam String stationCode) {
        ResponseResult responseResult = new ResponseResult();
        if (StringUtils.isEmpty(stationCode)) {
            DataException dataException = new DataException("404", "站点编码为空");
            responseResult.putException(dataException);
            return responseResult;
        }
        List<TempStorage> tempStorageList = new ArrayList<>();
        /**
         * 正常库01
         */
        TempStorage noramlStorageOne = new TempStorage();
        noramlStorageOne.setTempStorageId(1L);
        noramlStorageOne.setTempStorageCode("Noraml001");
        noramlStorageOne.setTempStorageName("正常库01");
        noramlStorageOne.setStorageType("NORAML");
        noramlStorageOne.setCreateTime(new Date());
        noramlStorageOne.setUpdateTime(new Date());
        noramlStorageOne.setVersion(0L);
        tempStorageList.add(noramlStorageOne);

        /**
         * 正常库02
         */
        TempStorage noramlStorageTwo = new TempStorage();
        noramlStorageTwo.setTempStorageId(2L);
        noramlStorageTwo.setTempStorageCode("Noraml002");
        noramlStorageTwo.setTempStorageName("正常库02");
        noramlStorageTwo.setStorageType("NORAML");
        noramlStorageTwo.setCreateTime(new Date());
        noramlStorageTwo.setUpdateTime(new Date());
        noramlStorageTwo.setVersion(0L);
        tempStorageList.add(noramlStorageTwo);

        /**
         * 仓储库01
         */
        TempStorage storage = new TempStorage();
        storage.setTempStorageId(3L);
        storage.setTempStorageCode("Storage001");
        storage.setTempStorageName("仓储库01");
        storage.setStorageType("STORAGE");
        storage.setCreateTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setVersion(0L);
        tempStorageList.add(storage);

        /**
         * 进货库01
         */
        TempStorage inStorage = new TempStorage();
        inStorage.setTempStorageId(4L);
        inStorage.setTempStorageCode("In001");
        inStorage.setTempStorageName("进货库01");
        inStorage.setStorageType("IN_STORAGE");
        inStorage.setCreateTime(new Date());
        inStorage.setUpdateTime(new Date());
        inStorage.setVersion(0L);
        tempStorageList.add(inStorage);

        /**
         * 退货库01
         */
        TempStorage outStorage = new TempStorage();
        outStorage.setTempStorageId(5L);
        outStorage.setTempStorageCode("Out001");
        outStorage.setTempStorageName("退货库01");
        inStorage.setStorageType("OUT_STORAGE");
        outStorage.setCreateTime(new Date());
        outStorage.setUpdateTime(new Date());
        outStorage.setVersion(0L);
        tempStorageList.add(outStorage);

        /**
         * 在途库01
         */
        TempStorage onStorage = new TempStorage();
        onStorage.setTempStorageId(6L);
        onStorage.setTempStorageCode("On001");
        onStorage.setTempStorageName("在途库01");
        inStorage.setStorageType("ON_STORAGE");
        onStorage.setCreateTime(new Date());
        onStorage.setUpdateTime(new Date());
        onStorage.setVersion(0L);
        tempStorageList.add(onStorage);

        /**
         * 预留库01
         */
        TempStorage reservedStorage = new TempStorage();
        reservedStorage.setTempStorageId(7L);
        reservedStorage.setTempStorageCode("Reserved001");
        reservedStorage.setTempStorageName("预留库01");
        inStorage.setStorageType("RESERVE_STORAGE");
        reservedStorage.setCreateTime(new Date());
        reservedStorage.setUpdateTime(new Date());
        reservedStorage.setVersion(0L);
        tempStorageList.add(reservedStorage);

        responseResult.put("content", tempStorageList);
        return responseResult;
    }

}
