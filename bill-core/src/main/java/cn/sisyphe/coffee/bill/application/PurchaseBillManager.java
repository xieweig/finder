package cn.sisyphe.coffee.bill.application;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.*;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillDetail;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillQueryService;
import cn.sisyphe.coffee.bill.infrastructure.purchase.PurchaseBillRepository;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.*;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单协调层
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class PurchaseBillManager {

    @Autowired
    private PurchaseBillQueryService purchaseBillQueryService;
    @Autowired
    private PurchaseBillRepository purchaseBillRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    BillServiceFactory serviceFactory = new BillServiceFactory();

    BillFactory billFactory = new BillFactory();

    /**
     * 保存进货单
     *
     * @param addPurchaseBillDTO
     */
    public void saveBill(AddPurchaseBillDTO addPurchaseBillDTO) {
        // 转换单据
        PurchaseBill purchaseBill = toMapPurchaseBill(addPurchaseBillDTO);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        // 动作调用
        purchaseBillService.dispose(new SaveBehavior());
        // 设置数据库仓库
        purchaseBillService.setBillRepository(purchaseBillRepository);
        // 保存数据到数据库中
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 提交进货单
     *
     * @param addPurchaseBillDTO
     */
    public void submitBill(AddPurchaseBillDTO addPurchaseBillDTO) {
        // 转换单据
        PurchaseBill purchaseBill = toMapPurchaseBill(addPurchaseBillDTO);
        // 生成单据服务
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        // 动作调用
        purchaseBillService.dispose(new SubmitBehavior());
        // 设置数据库仓库
        purchaseBillService.setBillRepository(purchaseBillRepository);
        // 保存数据到数据库中
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);

    }

    /**
     * 转换
     *
     * @param addPurchaseBillDTO
     * @return
     */
    public PurchaseBill toMapPurchaseBill(AddPurchaseBillDTO addPurchaseBillDTO) {
        /**
         * 通过工厂方法生成具体种类的单据
         */
        PurchaseBill purchaseBill = (PurchaseBill) billFactory.createBill(BillTypeEnum.PURCHASE);
        /**
         * 设置单据的作用
         */
        purchaseBill.setBillPurpose(BillPurposeEnum.InStorage);
        /**
         * 单据编码生成器
         */
        purchaseBill.setBillCode(new Date().toString());
        /**
         * 货运单号
         */
        purchaseBill.setFreightCode(addPurchaseBillDTO.getFreightCode());
        /**
         * 发货件数
         */
        purchaseBill.setShippedAmount(addPurchaseBillDTO.getShippedAmount());
        /**
         * 实收件数
         */
        purchaseBill.setActualAmount(addPurchaseBillDTO.getActualAmount());
        /**
         * 备注
         */
        purchaseBill.setMemo(addPurchaseBillDTO.getMemo());
        /**
         * 操作人代码
         */
        purchaseBill.setOperatorCode(addPurchaseBillDTO.getOperatorCode());
        /**
         * 归属站点
         */
        purchaseBill.setBelongStationCode(addPurchaseBillDTO.getBelongStationCode());
        /**
         * 获取站点
         */
        Station station = addPurchaseBillDTO.getStation();
        /**
         * 获取库房
         */
        Storage storage = addPurchaseBillDTO.getStorage();
        /**
         * 组合站点和库房
         */
        station.setStorage(storage);
        /**
         * 设置入库位置
         */
        purchaseBill.setInLocation(station);
        /**
         * 设置出库位置
         */
        purchaseBill.setOutLocation(addPurchaseBillDTO.getStorage());
        /**
         * 转换单据明细信息
         */
        Set<PurchaseBillDetail> detailSet = toMapPurchaseBillDetail(addPurchaseBillDTO.getBillDetails());
        /**
         * 设置单据明细信息
         */
        purchaseBill.setBillDetails(detailSet);

        return purchaseBill;
    }

    /**
     * 转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    public Set<PurchaseBillDetail> toMapPurchaseBillDetail(List<BillDetailDTO> billDetails) {

        Set<PurchaseBillDetail> detailSet = new HashSet<>();
        for (BillDetailDTO detail : billDetails) {
            PurchaseBillDetail purchaseBillDetail = new PurchaseBillDetail();
            /**
             * 设置货物和原料信息
             */
            RawMaterial rawMaterial = detail.getRawMaterial();
            purchaseBillDetail.setGoods(rawMaterial);
            /**
             * 最小单位数量
             */
            purchaseBillDetail.setAmount(detail.getAmount());
            /**
             * 包号
             */
            purchaseBillDetail.setPackageCode(detail.getPackageCode());
            /**
             * 标准单位编码
             */
            purchaseBillDetail.setStandardUnitCode(detail.getStandardUnitCode());
            /**
             * 规格编码
             */
            purchaseBillDetail.setMeasurementCode(detail.getMeasurementCode());
            /**
             * 生产日期
             */
            purchaseBillDetail.setDateInProduced(detail.getDateInProduced());
            /**
             * 单位进价
             */
            purchaseBillDetail.setUnitPrice(detail.getUnitPrice());
            /**
             * 实收数量
             */
            purchaseBillDetail.setActualNumber(detail.getActualNumber());
            /**
             * 发货数量
             */
            purchaseBillDetail.setShippedNumber(detail.getShippedNumber());
            /**
             * 数量差值
             */
            purchaseBillDetail.setDifferenceNumber(detail.getDifferenceNumber());
            /**
             * 总价差值
             */
            purchaseBillDetail.setDifferencePrice(detail.getDifferencePrice());
            detailSet.add(purchaseBillDetail);
        }
        return detailSet;
    }

    /**
     * 将set转换为dto
     *
     * @param purchaseBillDetails
     * @return
     */
    public List<BillDetailDTO> toMapDetail(Set<PurchaseBillDetail> purchaseBillDetails) {

        List<BillDetailDTO> detailDTOList = new ArrayList<>();
        for (PurchaseBillDetail detail : purchaseBillDetails) {
            BillDetailDTO detailDTO = new BillDetailDTO();
            /**
             * 设置货物和原料信息
             */
            RawMaterial rawMaterial = (RawMaterial) detail.getGoods();

            detailDTO.setRawMaterial(rawMaterial);
            /**
             * 最小单位数量
             */
            detailDTO.setAmount(detail.getAmount());
            /**
             * 包号
             */
            detailDTO.setPackageCode(detail.getPackageCode());
            /**
             * 标准单位编码
             */
            detailDTO.setStandardUnitCode(detail.getStandardUnitCode());
            /**
             * 规格编码
             */
            detailDTO.setMeasurementCode(detail.getMeasurementCode());
            /**
             * 生产日期
             */
            detailDTO.setDateInProduced(detail.getDateInProduced());
            /**
             * 单位进价
             */
            detailDTO.setUnitPrice(detail.getUnitPrice());
            /**
             * 实收数量
             */
            detailDTO.setActualNumber(detail.getActualNumber());
            /**
             * 发货数量
             */
            detailDTO.setShippedNumber(detail.getShippedNumber());
            /**
             * 数量差值
             */
            detailDTO.setDifferenceNumber(detail.getDifferenceNumber());
            /**
             * 总价差值
             */
            detailDTO.setDifferencePrice(detail.getDifferencePrice());
            detailDTOList.add(detailDTO);
        }
        return detailDTOList;
    }


    /**
     * 打开进货单
     *
     * @param purchaseBillCode
     */
    public QueryOnePurchaseBillDTO openBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        // 如果单据是打开状态，则直接返回转换后的进货单据信息
        if (purchaseBill.getBillState().equals(BillStateEnum.OPEN)) {
            return toMapOneDTO(purchaseBill);
        }
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new OpenBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        QueryOnePurchaseBillDTO billDTO = toMapOneDTO(purchaseBill);
        return billDTO;
    }


    /**
     * 编辑进货单
     *
     * @param billDTO
     */
    public void auditBill(EditPurchaseBillDTO billDTO) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(billDTO.getBillCode());
        //
        // TODO: 2017/12/29 暂未完成，判断单据，是否能够被修改
        if (purchaseBill.getBillState().equals(BillStateEnum.OPEN)) {
            throw new DataException("", "");
        }
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
    }


    /**
     * 审核失败进货单
     *
     * @param purchaseBillCode
     */
    public void auditFailureBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);

        purchaseBillService.dispose(new AuditBehavior(purchaseBillService, Constant.AUDIT_FAILURE_VALUE));
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 审核成功进货单
     *
     * @param purchaseBillCode
     */
    public void AuditSuccessBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new AuditBehavior(purchaseBillService, Constant.AUDIT_SUCCESS_VALUE));
        purchaseBillService.dispose(new PurposeBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 单据出入库完成
     *
     * @param responseResult
     */
    public void DoneBill(ResponseResult responseResult) {
        Map<String, Object> resultMap = responseResult.getResult();
        PurchaseBill bill = responseResult.toClassObject(resultMap.get("bill"), PurchaseBill.class);
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(bill.getBillCode());
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new PurposeBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 根据多条件查询进货单据信息
     *
     * @param conditionQueryPurchaseBill 查询条件
     * @return
     */
    public QueryPurchaseBillDTO findByConditions(ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        Page<PurchaseBill> purchaseBillPage = purchaseBillQueryService.findByConditions(conditionQueryPurchaseBill);

        QueryPurchaseBillDTO queryPurchaseBillDTO = new QueryPurchaseBillDTO();
        // 转换
        List<PurchaseBillDTO> billDTOList = toMapDTO(purchaseBillPage.getContent());
        // 总数
        queryPurchaseBillDTO.setTotalNumber(purchaseBillPage.getTotalElements());
        // 进货单据数据
        queryPurchaseBillDTO.setContent(billDTOList);

        return queryPurchaseBillDTO;
    }

    /**
     * 前端多条件查询转换DTO
     *
     * @param purchaseBillList
     * @return
     */
    public List<PurchaseBillDTO> toMapDTO(List<PurchaseBill> purchaseBillList) {

        List<PurchaseBillDTO> purchaseBillDTOList = new ArrayList<>();
        for (PurchaseBill purchaseBill : purchaseBillList) {
            PurchaseBillDTO purchaseBillDTO = new PurchaseBillDTO();
            /**
             * 进货单号-主表
             */
            purchaseBillDTO.setBillCode(purchaseBill.getBillCode());
            /**
             * 入库时间-主表
             */
            purchaseBillDTO.setInWareHouseTime(purchaseBill.getInWareHouseTime().toString());
            /**
             * 录单时间-主表
             */
            purchaseBillDTO.setCreateTime(purchaseBill.getCreateTime().toString());
            /**
             * 录单人-主表
             */
            purchaseBillDTO.setOperatorCode(purchaseBill.getOperatorCode());
            /**
             * 审核人-主表
             */
            purchaseBillDTO.setAuditPersonCode(purchaseBill.getAuditPersonCode());
            /**
             * 入库站点-主表
             */
            Station station = (Station) purchaseBill.getInLocation();
            purchaseBillDTO.setInStationName(station.getStationName());
            /**
             * 入库库房-主表
             */
            purchaseBillDTO.setInStorageName(station.getStorage().getStorageName());
            /**
             * 供应商名称--主表
             */
            Supplier supplier = (Supplier) purchaseBill.getOutLocation();
            purchaseBillDTO.setSupplierCode(supplier.getSupplierName());
            /**
             * 单据状态--主表
             */
            purchaseBillDTO.setBillState(purchaseBill.getBillState().name());
            /**
             * 备注--主表
             */
            purchaseBillDTO.setMemo(purchaseBill.getMemo());

            // 循环遍历明细信息，累加得到数据
            Set<PurchaseBillDetail> purchaseBillDetailSet = purchaseBill.getBillDetails();
            // 实收数量总计
            Integer actualNumber = 0;
            // 数量差值总计
            Integer differenceNumber = 0;
            // 进货总价
            BigDecimal inTotalPrice = BigDecimal.ZERO;
            // 差价总和
            BigDecimal differencePrice = BigDecimal.ZERO;
            for (PurchaseBillDetail purchaseBillDetail : purchaseBillDetailSet) {
                // 累加实收数量
                actualNumber += purchaseBillDetail.getActualNumber();
                // 累加数量差值
                differenceNumber += purchaseBillDetail.getDifferenceNumber();
                // 累加进货总价
                inTotalPrice = inTotalPrice.add(purchaseBillDetail.getUnitPrice().multiply(new BigDecimal(purchaseBillDetail.getActualNumber())));
                // 累加差价总和
                differencePrice = differencePrice.add(purchaseBillDetail.getDifferencePrice());
            }
            /**
             * 实收数量--明细表
             */
            purchaseBillDTO.setActualNumber(actualNumber);
            /**
             * 数量差值--明细表
             */
            purchaseBillDTO.setDifferenceNumber(differenceNumber);
            /**
             * 总价差值--明细表
             */
            purchaseBillDTO.setDifferencePrice(differencePrice);
            /**
             * 进货实洋
             */
            purchaseBillDTO.setInTotalPrice(inTotalPrice);

            purchaseBillDTOList.add(purchaseBillDTO);
        }

        return purchaseBillDTOList;
    }


    /**
     * 前端单个查询查询转换DTO
     *
     * @param purchaseBill 数据库查询出来的单据信息
     * @return
     */
    public QueryOnePurchaseBillDTO toMapOneDTO(PurchaseBill purchaseBill) {

        QueryOnePurchaseBillDTO billDTO = new QueryOnePurchaseBillDTO();
        // 备注
        billDTO.setMemo(purchaseBill.getMemo());
        // 运单号
        billDTO.setFreightCode(purchaseBill.getFreightCode());
        // 实收件数
        billDTO.setActualAmount(purchaseBill.getActualAmount());
        // 发货件数
        billDTO.setShippedAmount(purchaseBill.getShippedAmount());
        // 设置供应商名称
        Supplier supplier = (Supplier) purchaseBill.getOutLocation();
        billDTO.setSupplierName(supplier.getSupplierName());

        // 转换进货单明细信息
        List<BillDetailDTO> detailDTOList = toMapDetail(purchaseBill.getBillDetails());

        // 进货单明细信息
        billDTO.setBillDetails(detailDTOList);

        return billDTO;
    }

    /**
     * 前端传递站点信息
     *
     * @param station
     * @return
     */
    // TODO: 2017/12/29 备用
    private AbstractLocation getLocation(Station station) {
        if (StationType.SUPPLIER.equals(station.getStationType())) {
            Supplier supplier = new Supplier(station.getStationCode());
            supplier.setSupplierName(station.getStationName());
            return supplier;
        }
        return station;
    }

}
