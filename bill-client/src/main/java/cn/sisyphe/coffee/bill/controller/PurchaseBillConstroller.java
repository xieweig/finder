package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.infrastructure.share.storage.TempStorage;
import cn.sisyphe.coffee.bill.viewmodel.purchase.AddPurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.QueryOnePurchaseBillDTO;
import cn.sisyphe.framework.auth.logic.annotation.ScopeAuth;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/bill/purchase")
@RestController
@Api(description = "进货入库单相关接口")
@CrossOrigin(origins = "*")
public class PurchaseBillConstroller {
    @Autowired
    private PurchaseBillManager purchaseBillManager;

    /**
     * 保存进货单
     *
     * @param addPurchaseBillDTO
     * @return
     */
    @ApiOperation(value = "保存进货单")
    @RequestMapping(path = "/savePurchaseBill", method = RequestMethod.POST)
    public ResponseResult savePurchaseBill(HttpServletRequest request, @RequestBody AddPurchaseBillDTO addPurchaseBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addPurchaseBillDTO.setOperatorCode(loginInfo.getOperatorCode());
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.saveBill(addPurchaseBillDTO);
        return responseResult;
    }

    /**
     * 提交进货单
     *
     * @param addPurchaseBillDTO
     * @return
     */
    @ApiOperation(value = "提交进货单")
    @RequestMapping(path = "/submitPurchaseBill", method = RequestMethod.POST)
    public ResponseResult submitPurchaseBill(HttpServletRequest request, @RequestBody AddPurchaseBillDTO addPurchaseBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addPurchaseBillDTO.setOperatorCode(loginInfo.getOperatorCode());
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.submitBill(addPurchaseBillDTO);
        return responseResult;
    }

    /**
     * 多条件查询进货单
     *
     * @param conditionQueryPurchaseBill
     * @return
     */
    @ApiOperation(value = "多条件查询进货单")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    @ScopeAuth(scopes = {"#conditionQueryPlanBill.outStationCodeArray", "#conditionQueryPurchaseBill.inStationCodeArray"},  token = "userCode")
    public ResponseResult findByConditions(@RequestBody ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        ResponseResult responseResult = new ResponseResult();

        Page<PurchaseBillDTO> purchaseBillPage = purchaseBillManager.findByConditions(conditionQueryPurchaseBill);
        responseResult.put("content", purchaseBillPage);
        return responseResult;
    }

    /**
     * 根据进货单编码查询进货单详细信息
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "根据进货单编码查询进货单详细信息")
    @RequestMapping(path = "/findByPurchaseBillCode", method = RequestMethod.GET)
    public ResponseResult findByPurchaseBillCode(@RequestParam(value = "purchaseBillCode") String purchaseBillCode) {
        ResponseResult responseResult = new ResponseResult();
        QueryOnePurchaseBillDTO billDTO = purchaseBillManager.queryOneByCode(purchaseBillCode);
        responseResult.put("purchaseBill", billDTO);
        return responseResult;
    }

    /**
     * 根据进货单编码查询进货单详细信息
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "根据进货单编码查询进货单详细信息")
    @RequestMapping(path = "/openPurchaseBill", method = RequestMethod.GET)
    public ResponseResult openPurchaseBill(@RequestParam(value = "purchaseBillCode") String purchaseBillCode) {
        ResponseResult responseResult = new ResponseResult();
        QueryOnePurchaseBillDTO billDTO = purchaseBillManager.openBill(purchaseBillCode);
        responseResult.put("purchaseBill", billDTO);
        return responseResult;
    }

    /**
     * 修改进货单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改进货单据信息--保存")
    @RequestMapping(path = "/updatePurchaseBillToSave", method = RequestMethod.POST)
    public ResponseResult updatePurchaseBillToSaved(HttpServletRequest request, @RequestBody AddPurchaseBillDTO billDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        billDTO.setOperatorCode(loginInfo.getOperatorCode());
        ResponseResult responseResult = new ResponseResult();
        try {
            purchaseBillManager.updateBillToSave(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 修改进货单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改进货单据信息--提交审核")
    @RequestMapping(path = "/updatePurchaseBillToSubmit", method = RequestMethod.POST)
    public ResponseResult updatePurchaseBillToSubmit(HttpServletRequest request, @RequestBody AddPurchaseBillDTO billDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        billDTO.setOperatorCode(loginInfo.getOperatorCode());
        ResponseResult responseResult = new ResponseResult();
        try {
            purchaseBillManager.updateBillToSubmit(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(HttpServletRequest request, @RequestParam(value = "purchaseBillCode") String purchaseBillCode) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.auditBill(purchaseBillCode, loginInfo.getOperatorCode(), false);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(HttpServletRequest request, @RequestParam(value = "purchaseBillCode") String purchaseBillCode) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.auditBill(purchaseBillCode, loginInfo.getOperatorCode(), true);
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
