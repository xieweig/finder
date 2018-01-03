package cn.sisyphe.coffee.bill.application.purchase;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillDetail;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillQueryService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.*;
import cn.sisyphe.framework.web.ResponseResult;
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
public class PurchaseBillManager extends AbstractBillManager<PurchaseBill> {

    @Autowired
    private PurchaseBillQueryService purchaseBillQueryService;


    @Autowired
    public PurchaseBillManager(BillRepository<PurchaseBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }


    /**
     * 保存进货单
     *
     * @param addPurchaseBillDTO
     */
    public void saveBill(AddPurchaseBillDTO addPurchaseBillDTO) {
        // 转换单据
        PurchaseBill purchaseBill = dtoToMapPurchaseBill(addPurchaseBillDTO);

        // 保存单据
        save(purchaseBill);
    }

    /**
     * 提交进货单
     *
     * @param addPurchaseBillDTO
     */
    public void submitBill(AddPurchaseBillDTO addPurchaseBillDTO) {
        // 转换单据
        PurchaseBill purchaseBill = dtoToMapPurchaseBill(addPurchaseBillDTO);

        submit(purchaseBill);

    }

    /**
     * 修改进货单--保存
     *
     * @param billDTO
     */
    public void updateBillToSave(AddPurchaseBillDTO billDTO) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(billDTO.getBillCode());
        purchaseBill.getBillDetails().clear();
        // 转换单据
        PurchaseBill mapBillAfter = dtoToMapPurchaseBillForEdit(billDTO, purchaseBill);

        save(mapBillAfter);
    }

    /**
     * 修改进货单--提交审核
     *
     * @param billDTO
     */
    public void updateBillToSubmit(AddPurchaseBillDTO billDTO) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(billDTO.getBillCode());
        purchaseBill.getBillDetails().clear();
        // 转换单据
        PurchaseBill mapBillAfter = dtoToMapPurchaseBillForEdit(billDTO, purchaseBill);

        submit(mapBillAfter);
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
            return mapOneToDTO(purchaseBill);
        }

        // 打开单据
        purchaseBill = open(purchaseBill);

        return mapOneToDTO(purchaseBill);
    }


    /**
     * 审核进货单
     *
     * @param purchaseBillCode
     */
    public void auditBill(String purchaseBillCode, String auditPersonCode, boolean isSuccess) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        // 设置审核人编码
        purchaseBill.setAuditPersonCode(auditPersonCode);

        audit(purchaseBill, isSuccess);
    }

    /**
     * 单据出入库完成
     *
     * @param responseResult
     */
    public void doneBill(ResponseResult responseResult) {
        Map<String, Object> resultMap = responseResult.getResult();
        // 转换出单据信息
        PurchaseBill bill = responseResult.toClassObject(resultMap.get("bill"), PurchaseBill.class);
        // 根据单据编码查询数据库单据信息
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(bill.getBillCode());
        // 设置入库时间
        purchaseBill.setInWareHouseTime(new Date());
        // 处理完成
        done(bill);
    }


    /**
     * 保存和提交操作需要用到的DTO转换
     *
     * @param addPurchaseBillDTO 前端传递的DTO参数信息
     * @return
     */
    private PurchaseBill dtoToMapPurchaseBill(AddPurchaseBillDTO addPurchaseBillDTO) {
        //通过工厂方法生成具体种类的单据
        BillFactory billFactory = new BillFactory();
        PurchaseBill purchaseBill = (PurchaseBill) billFactory.createBill(BillTypeEnum.PURCHASE);
        // 设置单据的作用
        purchaseBill.setBillPurpose(BillPurposeEnum.InStorage);
        // 设置单据类型
        purchaseBill.setBillType(BillTypeEnum.PURCHASE);
        // 单据编码生成器
        // TODO: 2017/12/29 单号生成器还没有实现
        purchaseBill.setBillCode("bill003");
        // 货运单号
        purchaseBill.setFreightCode(addPurchaseBillDTO.getFreightCode());
        // 发货件数
        purchaseBill.setShippedAmount(addPurchaseBillDTO.getShippedAmount());
        // 实收件数
        purchaseBill.setActualAmount(addPurchaseBillDTO.getActualAmount());
        // 备注
        purchaseBill.setMemo(addPurchaseBillDTO.getMemo());
        // 操作人代码
        purchaseBill.setOperatorCode(addPurchaseBillDTO.getOperatorCode());
        // 归属站点
        purchaseBill.setBelongStationCode(addPurchaseBillDTO.getStation().getStationCode());
        // 获取站点
        Station station = addPurchaseBillDTO.getStation();
        // 获取库房
        Storage storage = addPurchaseBillDTO.getStorage();
        // 组合站点和库房
        station.setStorage(storage);
        // 设置入库位置
        purchaseBill.setInLocation(station);
        // 设置出库位置
        purchaseBill.setOutLocation(addPurchaseBillDTO.getSupplier());
        // 转换单据明细信息
        Set<PurchaseBillDetail> detailSet = listDetailMapToSetDetail(addPurchaseBillDTO.getBillDetails());
        // 设置单据明细信息
        purchaseBill.setBillDetails(detailSet);

        return purchaseBill;
    }


    /**
     * 保存、提交和修改操作需要用到的DTO转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    private Set<PurchaseBillDetail> listDetailMapToSetDetail(List<BillDetailDTO> billDetails) {

        Set<PurchaseBillDetail> detailSet = new HashSet<>();
        for (BillDetailDTO detail : billDetails) {
            PurchaseBillDetail purchaseBillDetail = new PurchaseBillDetail();
            // 设置货物和原料信息
            RawMaterial rawMaterial = detail.getRawMaterial();
            purchaseBillDetail.setGoods(rawMaterial);
            // 包号
            purchaseBillDetail.setPackageCode(detail.getPackageCode());
            // 生产日期
            purchaseBillDetail.setDateInProduced(detail.getDateInProduced());
            // 单位进价
            purchaseBillDetail.setUnitPrice(detail.getUnitPrice());
            // 发货数量
            purchaseBillDetail.setShippedNumber(detail.getShippedNumber());
            // 实收数量-最小单位数量
            purchaseBillDetail.setAmount(detail.getAmount());
            // 数量差值
            purchaseBillDetail.setDifferenceNumber(detail.getDifferenceNumber());
            // 总价差值
            purchaseBillDetail.setDifferencePrice(detail.getDifferencePrice());
            detailSet.add(purchaseBillDetail);
        }
        return detailSet;
    }


    /**
     * 前端单个查询查询转换DTO
     *
     * @param purchaseBill 数据库查询出来的单据信息
     * @return
     */
    private QueryOnePurchaseBillDTO mapOneToDTO(PurchaseBill purchaseBill) {

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
        billDTO.setSupplierCode(supplier.getSupplierCode());

        Station station = (Station) purchaseBill.getInLocation();
        // 库位名称
        billDTO.setInStorageCode(station.getStorage().getStorageCode());
        // 转换进货单明细信息
        List<BillDetailDTO> detailDTOList = setDetailMapToListMapDetail(purchaseBill.getBillDetails());

        // 进货单明细信息
        billDTO.setBillDetails(detailDTOList);
        System.err.println("查询到的进货单据信息：" + billDTO.toString());
        return billDTO;
    }

    /**
     * 前端查询单个单据明细信息将set转换为dto
     *
     * @param purchaseBillDetails
     * @return
     */
    private List<BillDetailDTO> setDetailMapToListMapDetail(Set<PurchaseBillDetail> purchaseBillDetails) {

        List<BillDetailDTO> detailDTOList = new ArrayList<>();
        for (PurchaseBillDetail detail : purchaseBillDetails) {
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
     * @param editPurchaseBillDTO
     * @return
     */
    private PurchaseBill dtoToMapPurchaseBillForEdit(AddPurchaseBillDTO editPurchaseBillDTO, PurchaseBill purchaseBill) {

        // 货运单号
        purchaseBill.setFreightCode(editPurchaseBillDTO.getFreightCode());
        // 发货件数
        purchaseBill.setShippedAmount(editPurchaseBillDTO.getShippedAmount());
        // 实收件数
        purchaseBill.setActualAmount(editPurchaseBillDTO.getActualAmount());
        // 备注
        purchaseBill.setMemo(editPurchaseBillDTO.getMemo());
        // 操作人代码
        purchaseBill.setOperatorCode(editPurchaseBillDTO.getOperatorCode());
        // 归属站点
        purchaseBill.setBelongStationCode(editPurchaseBillDTO.getStation().getStationCode());
        // 获取站点
        Station station = editPurchaseBillDTO.getStation();
        // 获取库房
        Storage storage = editPurchaseBillDTO.getStorage();
        // 组合站点和库房
        station.setStorage(storage);
        // 设置入库位置
        purchaseBill.setInLocation(station);
        // 设置出库位置
        purchaseBill.setOutLocation(editPurchaseBillDTO.getStorage());
        // 转换单据明细信息
        Set<PurchaseBillDetail> detailSet = listDetailMapToSetDetail(editPurchaseBillDTO.getBillDetails());
        // 设置单据明细信息
        purchaseBill.setBillDetails(detailSet);

        return purchaseBill;
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
    private List<PurchaseBillDTO> toMapDTO(List<PurchaseBill> purchaseBillList) {

        List<PurchaseBillDTO> purchaseBillDTOList = new ArrayList<>();
        for (PurchaseBill purchaseBill : purchaseBillList) {
            PurchaseBillDTO purchaseBillDTO = new PurchaseBillDTO();
            // 进货单号-主表
            purchaseBillDTO.setBillCode(purchaseBill.getBillCode());
            // 入库时间-主表
            purchaseBillDTO.setInWareHouseTime(purchaseBill.getInWareHouseTime());
            // 录单时间-主表
            purchaseBillDTO.setCreateTime(purchaseBill.getCreateTime());
            // 录单人-主表
            purchaseBillDTO.setOperatorCode(purchaseBill.getOperatorCode());
            // 审核人-主表
            purchaseBillDTO.setAuditPersonCode(purchaseBill.getAuditPersonCode());
            // 入库站点-主表
            Station station = (Station) purchaseBill.getInLocation();
            purchaseBillDTO.setInStationCode(station.getStationCode());
            // 入库库房-主表
            purchaseBillDTO.setInStorageCode(station.getStorage().getStorageCode());
            // 供应商名称--主表
            Supplier supplier = (Supplier) purchaseBill.getOutLocation();
            purchaseBillDTO.setSupplierCode(supplier.getSupplierName());
            // 单据状态--主表
            purchaseBillDTO.setBillState(purchaseBill.getBillState().name());
            // 备注--主表
            purchaseBillDTO.setMemo(purchaseBill.getMemo());

            // 循环遍历明细信息，累加得到数据
            Set<PurchaseBillDetail> purchaseBillDetailSet = purchaseBill.getBillDetails();
            // 实收数量总计
            Integer amount = 0;
            // 数量差值总计
            Integer differenceNumber = 0;
            // 进货总价
            BigDecimal inTotalPrice = BigDecimal.ZERO;
            // 差价总和
            BigDecimal differencePrice = BigDecimal.ZERO;
            for (PurchaseBillDetail purchaseBillDetail : purchaseBillDetailSet) {
                // 累加实收数量
                amount += purchaseBillDetail.getAmount();
                // 累加数量差值
                differenceNumber += purchaseBillDetail.getDifferenceNumber();
                // 累加进货总价
                inTotalPrice = inTotalPrice.add(purchaseBillDetail.getUnitPrice().multiply(new BigDecimal(purchaseBillDetail.getAmount())));
                // 累加差价总和
                differencePrice = differencePrice.add(purchaseBillDetail.getDifferencePrice());
            }
            // 实收数量--明细表
            purchaseBillDTO.setAmount(amount);
            // 数量差值--明细表
            purchaseBillDTO.setDifferenceNumber(differenceNumber);
            // 总价差值--明细表
            purchaseBillDTO.setDifferencePrice(differencePrice);
            // 进货实洋
            purchaseBillDTO.setInTotalPrice(inTotalPrice);

            purchaseBillDTOList.add(purchaseBillDTO);
        }
        return purchaseBillDTOList;
    }
}
