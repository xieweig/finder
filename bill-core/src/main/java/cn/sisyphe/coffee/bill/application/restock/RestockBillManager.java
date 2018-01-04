package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillQueryService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.QueryOneRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
@Service
public class RestockBillManager extends AbstractBillManager<RestockBill> {

    public RestockBillManager(BillRepository<RestockBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    @Autowired
    private RestockBillQueryService restockBillQueryService;
    
    
    /**
     * 保存进货单
     *
     * @param addRestockBillDTO
     */
    public void saveBill(AddRestockBillDTO addRestockBillDTO) {
        // 转换单据
        RestockBill restockBill = dtoToMapRestockBill(addRestockBillDTO);

        // 保存单据
        save(restockBill);
    }


    /**
     * 提交进货单
     *
     * @param addRestockBillDTO
     */
    public void submitBill(AddRestockBillDTO addRestockBillDTO) {
        // 转换单据
        RestockBill restockBill = dtoToMapRestockBill(addRestockBillDTO);

        submit(restockBill);

    }



    /**
     * 修改退库单--保存
     *
     * @param billDTO
     */
    public void updateBillToSave(AddRestockBillDTO billDTO) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(billDTO.getBillCode());
        restockBill.getBillDetails().clear();
        // 转换单据
        RestockBill mapBillAfter = dtoToMapRestockBillForEdit(billDTO, restockBill);

        save(mapBillAfter);
    }

    /**
     * 修改退库单--提交审核
     *
     * @param billDTO
     */
    public void updateBillToSubmit(AddRestockBillDTO billDTO) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(billDTO.getBillCode());
        restockBill.getBillDetails().clear();
        // 转换单据
        RestockBill mapBillAfter = dtoToMapRestockBillForEdit(billDTO, restockBill);

        submit(mapBillAfter);
    }
    /**
     * 打开退库单
     *
     * @param restockBillCode
     */
    public QueryOneRestockBillDTO openBill(String restockBillCode) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(restockBillCode);
        // 如果单据是打开状态或者是审核失败状态，则直接返回转换后的退库单据信息
        if (restockBill.getBillState().equals(BillStateEnum.OPEN)
                || restockBill.getBillState().equals(BillStateEnum.AUDIT_FAILURE)) {
            return mapOneToDTO(restockBill);
        }

        // 打开单据
        restockBill = open(restockBill);

        return mapOneToDTO(restockBill);
    }

    /**
     * 审核退库单
     *
     * @param restockBillCode
     */
    public void auditBill(String restockBillCode, String auditPersonCode, boolean isSuccess) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(restockBillCode);
        // 设置审核人编码
        restockBill.setAuditPersonCode(auditPersonCode);

        audit(restockBill, isSuccess);
    }

    /**
     * 单据出入库完成
     *
     * @param responseResult
     */
    public void doneBill(ResponseResult responseResult) {
        Map<String, Object> resultMap = responseResult.getResult();
        // 转换出单据信息
        RestockBill bill = responseResult.toClassObject(resultMap.get("bill"), RestockBill.class);
        // 根据单据编码查询数据库单据信息
        RestockBill restockBill = restockBillQueryService.findByBillCode(bill.getBillCode());
        // 设置入库时间
        restockBill.setInWareHouseTime(new Date());
        // 处理完成
        done(bill);
    }
    /**
     * 根据多条件查询退库单据信息
     *
     * @param conditionQueryRestockBill 查询条件
     * @return
     */
    public QueryRestockBillDTO findByConditions(ConditionQueryRestockBill conditionQueryRestockBill) {
        Page<RestockBill> restockBillPage = restockBillQueryService.findPageByCondition(conditionQueryRestockBill);

        QueryRestockBillDTO QueryRestockBillDTO = new QueryRestockBillDTO();
        // 转换
        List<RestockBillDTO> billDTOList = toMapDTO(restockBillPage.getContent());
        // 总数
        QueryRestockBillDTO.setTotalNumber(restockBillPage.getTotalElements());
        // 退库单据数据
        QueryRestockBillDTO.setContent(billDTOList);

        return QueryRestockBillDTO;
    }

    /**
     * 保存和提交操作需要用到的DTO转换
     *
     * @param addRestockBillDTO 前端传递的DTO参数信息
     * @return
     */
    private RestockBill dtoToMapRestockBill(AddRestockBillDTO addRestockBillDTO) {
        //通过工厂方法生成具体种类的单据
        BillFactory billFactory = new BillFactory();
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        // 设置单据的作用
        restockBill.setBillPurpose(BillPurposeEnum.InStorage);
        // 设置单据类型
        restockBill.setBillType(BillTypeEnum.RESTOCK);
        // 单据编码生成器
        // TODO: 2017/12/29 单号生成器还没有实现
        restockBill.setBillCode("bill004");
        // 货运单号
        restockBill.setFreightCode(addRestockBillDTO.getFreightCode());
        // 发货件数
        restockBill.setShippedAmount(addRestockBillDTO.getShippedAmount());
        // 实收件数
        restockBill.setActualAmount(addRestockBillDTO.getActualAmount());
        // 备注
        restockBill.setMemo(addRestockBillDTO.getMemo());
        // 操作人代码
        restockBill.setOperatorCode(addRestockBillDTO.getOperatorCode());
        // 归属站点
        restockBill.setBelongStationCode(addRestockBillDTO.getStation().getStationCode());
        // 获取站点
        Station station = addRestockBillDTO.getStation();
        // 获取库房
        Storage storage = addRestockBillDTO.getStorage();
        // 组合站点和库房
        station.setStorage(storage);
        // 设置入库位置
        restockBill.setInLocation(station);
        // 设置出库位置
        restockBill.setOutLocation(addRestockBillDTO.getSupplier());
        // 转换单据明细信息
        Set<RestockBillDetail> detailSet = listDetailMapToSetDetail(addRestockBillDTO.getBillDetails());
        // 设置单据明细信息
        restockBill.setBillDetails(detailSet);

        return restockBill;
    }
    /**
     * 保存、提交和修改操作需要用到的DTO转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    private Set<RestockBillDetail> listDetailMapToSetDetail(List<BillDetailDTO> billDetails) {

        Set<RestockBillDetail> detailSet = new HashSet<>();
        for (BillDetailDTO detail : billDetails) {
            RestockBillDetail restockBillDetail = new RestockBillDetail();
            // 设置货物和原料信息
            RawMaterial rawMaterial = detail.getRawMaterial();
            restockBillDetail.setGoods(rawMaterial);
            // 包号
            restockBillDetail.setPackageCode(detail.getPackageCode());
            // 生产日期
            restockBillDetail.setDateInProduced(detail.getDateInProduced());
            // 单位进价
            restockBillDetail.setUnitPrice(detail.getUnitPrice());
            // 发货数量
            restockBillDetail.setShippedNumber(detail.getShippedNumber());
            // 实收数量-最小单位数量
            restockBillDetail.setAmount(detail.getAmount());
            // 数量差值
            restockBillDetail.setDifferenceNumber(detail.getDifferenceNumber());
            // 总价差值
            restockBillDetail.setDifferencePrice(detail.getDifferencePrice());
            detailSet.add(restockBillDetail);
        }
        return detailSet;
    }

    private QueryOneRestockBillDTO mapOneToDTO(RestockBill restockBill) {
        QueryOneRestockBillDTO billDTO = new QueryOneRestockBillDTO();
        // 备注
        billDTO.setMemo(restockBill.getMemo());
        // 运单号
        billDTO.setFreightCode(restockBill.getFreightCode());
        // 实收件数
        billDTO.setActualAmount(restockBill.getActualAmount());
        // 发货件数
        billDTO.setShippedAmount(restockBill.getShippedAmount());
        // 设置供应商名称
        Supplier supplier = (Supplier) restockBill.getOutLocation();
        billDTO.setSupplierCode(supplier.getSupplierCode());

        Station station = (Station) restockBill.getInLocation();
        // 库位名称
        billDTO.setInStorageCode(station.getStorage().getStorageCode());
        // 转换进货单明细信息
        List<cn.sisyphe.coffee.bill.viewmodel.restock.BillDetailDTO> detailDTOList = setDetailMapToListMapDetail(restockBill.getBillDetails());

        // 进货单明细信息
        billDTO.setBillDetails(detailDTOList);
        System.err.println("查询到的进货单据信息：" + billDTO.toString());
        return billDTO;
    }

    /**
     * 前端查询单个单据明细信息将set转换为dto
     *
     * @param restockBillDetails
     * @return
     */
    private List<BillDetailDTO> setDetailMapToListMapDetail(Set<RestockBillDetail> restockBillDetails) {

        List<BillDetailDTO> detailDTOList = new ArrayList<>();
        for (RestockBillDetail detail : restockBillDetails) {
            BillDetailDTO detailDTO = new BillDetailDTO();
            // 设置货物和原料信息
            RawMaterial rawMaterial = (RawMaterial) detail.getGoods();

            detailDTO.setRawMaterial(rawMaterial);
            // 实收数量-最小单位数量
            detailDTO.setAmount(detail.getAmount());
            // 包号
            detailDTO.setPackageCode(detail.getPackageCode());
            // 生产日期
            detailDTO.setDateInProduced(detail.getDateInProduced());
            // 单位进价
            detailDTO.setUnitPrice(detail.getUnitPrice());
            // 发货数量
            detailDTO.setShippedNumber(detail.getShippedNumber());
            // 数量差值
            detailDTO.setDifferenceNumber(detail.getDifferenceNumber());
            // 总价差值
            detailDTO.setDifferencePrice(detail.getDifferencePrice());
            detailDTOList.add(detailDTO);
        }
        return detailDTOList;
    }
    /**
     * 修改需要转换DTO
     *
     * @param editRestockBillDTO
     * @return
     */
    private RestockBill dtoToMapRestockBillForEdit(AddRestockBillDTO editRestockBillDTO, RestockBill restockBill) {

        // 货运单号
        restockBill.setFreightCode(editRestockBillDTO.getFreightCode());
        // 发货件数
        restockBill.setShippedAmount(editRestockBillDTO.getShippedAmount());
        // 实收件数
        restockBill.setActualAmount(editRestockBillDTO.getActualAmount());
        // 备注
        restockBill.setMemo(editRestockBillDTO.getMemo());
        // 操作人代码
        restockBill.setOperatorCode(editRestockBillDTO.getOperatorCode());
        // 归属站点
        restockBill.setBelongStationCode(editRestockBillDTO.getStation().getStationCode());
        // 获取站点
        Station station = editRestockBillDTO.getStation();
        // 获取库房
        Storage storage = editRestockBillDTO.getStorage();
        // 组合站点和库房
        station.setStorage(storage);
        // 设置入库位置
        restockBill.setInLocation(station);
        // 设置出库位置
        restockBill.setOutLocation(editRestockBillDTO.getStorage());
        // 转换单据明细信息
        Set<RestockBillDetail> detailSet = listDetailMapToSetDetail(editRestockBillDTO.getBillDetails());
        // 设置单据明细信息
        restockBill.setBillDetails(detailSet);

        return restockBill;
    }

    private List<RestockBillDTO> toMapDTO(List<RestockBill> restockBillList) {
        List<RestockBillDTO> restockBillDTOList = new ArrayList<>();
        for (RestockBill restockBill : restockBillList) {
            RestockBillDTO restockBillDTO = new RestockBillDTO();
            // 退库单号-主表
            restockBillDTO.setBillCode(restockBill.getBillCode());
            // 入库时间-主表
            restockBillDTO.setInWareHouseTime(restockBill.getInWareHouseTime());
            // 录单时间-主表
            restockBillDTO.setCreateTime(restockBill.getCreateTime());
            // 录单人-主表
            restockBillDTO.setOperatorCode(restockBill.getOperatorCode());
            // 审核人-主表
            restockBillDTO.setAuditPersonCode(restockBill.getAuditPersonCode());
            // 入库站点-主表
            Station station = (Station) restockBill.getInLocation();
            restockBillDTO.setInStationCode(station.getStationCode());
            // 入库库房-主表
            restockBillDTO.setInStorageCode(station.getStorage().getStorageCode());
            // 供应商名称--主表
            Supplier supplier = (Supplier) restockBill.getOutLocation();
            restockBillDTO.setSupplierCode(supplier.getSupplierName());
            // 单据状态--主表
            toMapTwoState(restockBillDTO, restockBill.getBillState().name());
            // 备注--主表
            restockBillDTO.setMemo(restockBill.getMemo());
            // 循环遍历明细信息，累加得到数据
            Set<RestockBillDetail> restockBillDetailSet = restockBill.getBillDetails();
            // 实收数量总计
            Integer amount = 0;
            // 数量差值总计
            Integer differenceNumber = 0;
            // 退库总价
            BigDecimal inTotalPrice = BigDecimal.ZERO;
            // 差价总和
            BigDecimal differencePrice = BigDecimal.ZERO;
            for (RestockBillDetail restockBillDetail : restockBillDetailSet) {
                // 累加实收数量
                amount += restockBillDetail.getAmount();
                // 累加数量差值
                differenceNumber += restockBillDetail.getDifferenceNumber();
                // 累加退库总价
                inTotalPrice = inTotalPrice.add(restockBillDetail.getUnitPrice().multiply(new BigDecimal(restockBillDetail.getAmount())));
                // 累加差价总和
                differencePrice = differencePrice.add(restockBillDetail.getDifferencePrice());
            }
            // 实收数量--明细表
            restockBillDTO.setAmount(amount);
            // 数量差值--明细表
            restockBillDTO.setDifferenceNumber(differenceNumber);
            // 总价差值--明细表
            restockBillDTO.setDifferencePrice(differencePrice);
            // 退库实洋
            restockBillDTO.setInTotalPrice(inTotalPrice);

            restockBillDTOList.add(restockBillDTO);
        }
        return restockBillDTOList;
    }

    /**
     * map状态
     *
     * @param billDTO
     * @param stateName
     * @return
     */
    private RestockBillDTO toMapTwoState(RestockBillDTO billDTO, String stateName) {

        switch (stateName) {

            case "SAVED":
                billDTO.setSubmitState("未提交");
                billDTO.setAuditState("未审核");
                break;
            case "SUBMITTED":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("未审核");
                break;
            case "OPEN":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("未审核");
                break;
            case "AUDIT_FAILURE":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核不通过");
                break;
            case "AUDIT_SUCCESS":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            case "OUT_STORAGING":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            case "IN_STORAGING":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            case "DONE":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            default:
                break;

        }

        return billDTO;
    }

}
