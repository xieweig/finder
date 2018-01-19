package cn.sisyphe.coffee.bill.application.mistake;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.allot.AllotBillExtraService;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBillDetail;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.storage.TempStorage;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static cn.sisyphe.coffee.bill.util.Constant.STORAGE_NAME_WCK;
import static cn.sisyphe.coffee.bill.util.Constant.STORAGE_TYPE_WCK;

/**
 * @author Amy on 2018/1/18.
 *         describe：
 */
@Service
public class MistakeBillManager extends AbstractBillExtraManager<MistakeBill, MistakeBillDTO, ConditionQueryMistakeBill> {

    @Autowired
    public MistakeBillManager(BillRepository<MistakeBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<MistakeBill, ConditionQueryMistakeBill> billExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager);
    }

    @Autowired
    private AllotBillExtraService allotBillExtraService;

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.MISTAKE;
    }

    /**
     * 出入库完成后的回调
     *
     * @param mistakeBillCode
     */
    public void callbackMistakeBill(String mistakeBillCode) throws DataException {
        MistakeBill mistakeBill = getBillExtraService().findByBillCode(mistakeBillCode);
        if (mistakeBill == null) {
            throw new DataException("60010", "单据不存在");
        }
        if (!BillStateEnum.SUBMITTED.equals(mistakeBill.getBillState())) {
            throw new DataException("60005", "单据当前状态不可修改为出入库成功");
        }
        mistakeBill.setBillState(BillStateEnum.IN_STORAGING);
        done(mistakeBill);
    }


    /**
     * 调拨单生成后回写编号到误差单中
     *
     * @param billCode
     * @param allotBillCode
     */
    public void callBackToAddAllotBillCode(String billCode, String allotBillCode) {
        AllotBill allotBill = allotBillExtraService.findByBillCode(allotBillCode);
        MistakeBill mistakeBill = getBillExtraService().findByBillCode(billCode);
        MistakeBill allotBillMistake = getBillExtraService().findBySourceCode(allotBillCode);
        if (allotBill == null) {
            throw new DataException("60011", "该调拨单不存在");
        }
        if (mistakeBill == null) {
            throw new DataException("60010", "该流转误差单不存在");
        }
        if (allotBillMistake != null) {
            throw new DataException("60008", "该调拨单已存在流转误差单");
        }
        if (StringUtils.isEmpty(mistakeBill.getSourceCode())) {
            mistakeBill.setSourceCode(allotBillCode);
            save(mistakeBill);
        }
    }

    public MistakeBill submitByAllotBill(AllotBill allotBill) throws DataException {
        //检查参数
        checkSubmitParam(allotBill);
        Set<MistakeBillDetail> mistakeBillDetailSet = obtainMistakeBillDetail(allotBill.getBillDetails());
        if (mistakeBillDetailSet.size() <= 0) {
            return null;
        }
        // 将DTO 转换为 单据
        MistakeBill mistakeBill = allotBillToMistakeBill(allotBill);
        mistakeBill.getBillDetails().addAll(mistakeBillDetailSet);
        return submit(mistakeBill);
    }

    private void checkSubmitParam(AllotBill allotBill) {
        if (allotBill == null) {
            throw new DataException("60006", "调拨单信息不能为空");
        }
        if (allotBill.getBillDetails() == null || allotBill.getBillDetails().size() <= 0) {
            throw new DataException("60009", "调拨单明细不能为空");
        }
    }

    /**
     * 根据调拨单获取误差单主信息
     *
     * @param allotBill
     * @return
     */
    private MistakeBill allotBillToMistakeBill(AllotBill allotBill) {
        MistakeBill mistakeBill = new MistakeBill();
        //单据作用
        mistakeBill.setBillPurpose(BillPurposeEnum.IN_STORAGE);
        //单据类型
        mistakeBill.setBillType(BillTypeEnum.MISTAKE);
        //创建时间
        mistakeBill.setCreateTime(new Date());
        //归属站点
        mistakeBill.setBelongStationCode(allotBill.getBelongStationCode());
        //操作人编号
        mistakeBill.setOperatorCode(allotBill.getOperatorCode());
        //出库位置信息
        mistakeBill.setOutLocation(allotBill.getOutLocation());

        //获取调拨单的入库位置信息
        Station allotBillInLocation = (Station) allotBill.getInLocation();
        Station inStation = new Station();
        Storage inStorage = new Storage();
        //查询出该站点的误差库位信息
        TempStorage tempStorage = new TempStorage(STORAGE_NAME_WCK, STORAGE_TYPE_WCK);
        //设置库位信息
        inStorage.setStorageCode(tempStorage.getTempStorageCode());
        inStorage.setStorageName(tempStorage.getTempStorageName());
        //设置入站点信息
        inStation.setStationCode(allotBillInLocation.getStationCode());
        inStation.setStationName(allotBillInLocation.getStationName());
        inStation.setStationType(allotBillInLocation.getStationType());
        inStation.setStorage(inStorage);

        mistakeBill.setInLocation(inStation);
        mistakeBill.setSourceCode(allotBill.getBillCode());
        mistakeBill.setRootCode(allotBill.getRootCode());

        mistakeBill.setSpecificBillType(allotBill.getSpecificBillType());
        return mistakeBill;
    }

    /**
     * 根据调拨单获取误差单明细
     *
     * @param billDetails
     * @return
     * @throws DataException
     */
    private Set<MistakeBillDetail> obtainMistakeBillDetail(Set<AllotBillDetail> billDetails) throws DataException {
        Set<MistakeBillDetail> mistakeBillDetailSet = new HashSet<>();
        if (billDetails.size() <= 0) {
            throw new DataException("60009", "调拨单明细不能为空！");
        }
        for (AllotBillDetail allotBillDetail : billDetails) {
            if (allotBillDetail.getShippedAmount() != allotBillDetail.getActualAmount()) {
                MistakeBillDetail mistakeBillDetail = new MistakeBillDetail();
                mistakeBillDetail.setActualAmount(allotBillDetail.getActualAmount());
                mistakeBillDetail.setShippedAmount(allotBillDetail.getShippedAmount());
                mistakeBillDetail.setGoods(allotBillDetail.getGoods());
                mistakeBillDetailSet.add(mistakeBillDetail);
            }
        }
        return mistakeBillDetailSet;
    }

    /**
     * 过滤无误差的明细（用于流转误差单的详情查询）
     *
     * @param responseResult
     * @return
     */
    public ResponseResult allotBillFilter(ResponseResult responseResult) {
        ResponseResult returnResponseResult = new ResponseResult();
        if (responseResult != null && responseResult.getResult() != null && responseResult.getResult().get("bill") != null) {
            AllotBillDTO allotBillDTO = JSON.parseObject(JSON.toJSONString(responseResult.getResult().get("bill"))).toJavaObject(AllotBillDTO.class);
            Set<AllotBillDetailDTO> allotBillDetailDTOSet = new HashSet<>();
            for (AllotBillDetailDTO allotBillDetailDTO : allotBillDTO.getBillDetails()) {
                if (!allotBillDetailDTO.getActualAmount().equals(allotBillDetailDTO.getShippedAmount())) {
                    allotBillDetailDTOSet.add(allotBillDetailDTO);
                }
            }
            allotBillDTO.getBillDetails().clear();
            allotBillDTO.getBillDetails().addAll(allotBillDetailDTOSet);
            returnResponseResult.put("bill", allotBillDTO);
        } else {
            returnResponseResult.put("bill", new AllotBillDTO());
        }
        return returnResponseResult;
    }
}