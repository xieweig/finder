package cn.sisyphe.coffee.bill.application.purchase;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
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
import cn.sisyphe.coffee.bill.viewmodel.purchase.*;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    private SharedManager sharedManager;

    @Autowired
    public PurchaseBillManager(BillRepository<PurchaseBill> billRepository,
                               ApplicationEventPublisher applicationEventPublisher) {
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
        // 验证属性
        verification(addPurchaseBillDTO);
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
        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            throw new DataException("404", "单据编码为空");
        }
        // 验证属性
        verification(billDTO);
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(billDTO.getBillCode());
        if (billDTO.getBillDetails() != null && billDTO.getBillDetails().size() > 0) {
            purchaseBill.getBillDetails().clear();
        }
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
        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            throw new DataException("404", "单据编码为空");
        }
        // 验证属性
        verification(billDTO);
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(billDTO.getBillCode());
        if (billDTO.getBillDetails() != null && billDTO.getBillDetails().size() > 0) {
            purchaseBill.getBillDetails().clear();
        }
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
        if (StringUtils.isEmpty(purchaseBillCode)) {
            throw new DataException("404", "单据编码为空");
        }
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        // 如果单据是提交状态，则进行打开动作
        if (purchaseBill.getBillState().equals(BillStateEnum.SUBMITTED)) {
            // 打开单据
            purchaseBill = open(purchaseBill);
            return mapOneToDTO(purchaseBill);
        } else {
            return mapOneToDTO(purchaseBill);
        }
    }


    /**
     * 审核进货单
     *
     * @param purchaseBillCode
     */
    public void auditBill(String purchaseBillCode, String auditPersonCode, boolean isSuccess) {
        if (StringUtils.isEmpty(purchaseBillCode)) {
            throw new DataException("404", "单据编码为空");
        }
        if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }
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
        // 设置初始值
        purchaseBill.setBillState(BillStateEnum.SAVED);
        // 设置单据类型
        purchaseBill.setBillType(BillTypeEnum.PURCHASE);
        // 单据编码生成器
        // TODO: 2017/12/29 单号生成器还没有实现
        purchaseBill.setBillCode(String.valueOf(System.currentTimeMillis()));
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
        // 获取站点
        Station station = addPurchaseBillDTO.getStation();
        if (station != null) {
            // 归属站点
            purchaseBill.setBelongStationCode(station.getStationCode());
        }
        // 获取库房
        Storage storage = addPurchaseBillDTO.getStorage();
        if (storage != null) {
            // 组合站点和库房
            station.setStorage(storage);
            // 设置入库位置
            purchaseBill.setInLocation(station);
        }
        Supplier supplier = addPurchaseBillDTO.getSupplier();
        if (supplier != null) {
            // 设置出库位置
            purchaseBill.setOutLocation(supplier);
        }
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
        if (billDetails != null && billDetails.size() > 0) {
            for (BillDetailDTO detail : billDetails) {
                PurchaseBillDetail purchaseBillDetail = new PurchaseBillDetail();
                // 设置货物和原料信息
                RawMaterial rawMaterial = detail.getRawMaterial();
                if (rawMaterial != null) {
                    purchaseBillDetail.setGoods(rawMaterial);
                }
                if (!StringUtils.isEmpty(detail.getPackageCode())) {
                    // 包号
                    purchaseBillDetail.setPackageCode(detail.getPackageCode());
                }
                if (detail.getDateInProduced() != null) {
                    // 生产日期
                    purchaseBillDetail.setDateInProduced(detail.getDateInProduced());
                }
                if (detail.getUnitPrice() != null) {
                    // 单位进价
                    purchaseBillDetail.setUnitPrice(detail.getUnitPrice());
                }
                if (detail.getShippedNumber() != null) {
                    // 发货数量
                    purchaseBillDetail.setShippedNumber(detail.getShippedNumber());
                }
                if (detail.getAmount() != null) {
                    // 实收数量-最小单位数量
                    purchaseBillDetail.setAmount(detail.getAmount());
                }
                if (detail.getDifferenceNumber() != null) {
                    // 数量差值
                    purchaseBillDetail.setDifferenceNumber(detail.getDifferenceNumber());
                }
                if (detail.getDifferencePrice() != null) {
                    // 总价差值
                    purchaseBillDetail.setDifferencePrice(detail.getDifferencePrice());
                }
                detailSet.add(purchaseBillDetail);
            }
            return detailSet;
        } else {
            return detailSet;
        }

    }


    /**
     * 前端单个查询查询转换DTO
     *
     * @param purchaseBill 数据库查询出来的单据信息
     * @return
     */
    private QueryOnePurchaseBillDTO mapOneToDTO(PurchaseBill purchaseBill) {

        QueryOnePurchaseBillDTO billDTO = new QueryOnePurchaseBillDTO();
        // 单据编码
        billDTO.setBillCode(purchaseBill.getBillCode());
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
        if (supplier != null) {
            billDTO.setSupplierCode(supplier.getSupplierCode());
        }
        // 库位名称
        Station station = (Station) purchaseBill.getInLocation();
        if (station != null) {
            Storage storage = station.getStorage();
            if (storage != null) {
                billDTO.setInStorageCode(storage.getStorageCode());
            }
        }

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
        if (purchaseBillDetails != null && purchaseBillDetails.size() > 0) {
            for (PurchaseBillDetail detail : purchaseBillDetails) {
                BillDetailDTO detailDTO = new BillDetailDTO();
                // 设置货物和原料信息
                RawMaterial rawMaterial = (RawMaterial) detail.getGoods();
                if (rawMaterial != null) {
                    detailDTO.setRawMaterial(rawMaterial);
                }
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
        } else {
            return detailDTOList;
        }

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
        // 获取站点
        Station station = editPurchaseBillDTO.getStation();
        if (station != null) {
            // 归属站点
            purchaseBill.setBelongStationCode(station.getStationCode());
        }

        // 获取库房
        Storage storage = editPurchaseBillDTO.getStorage();
        if (storage != null) {
            // 组合站点和库房
            station.setStorage(storage);
            // 设置入库位置
            purchaseBill.setInLocation(station);
            // 设置出库位置
            purchaseBill.setOutLocation(storage);
        }
        // 转换单据明细信息
        Set<PurchaseBillDetail> detailSet = listDetailMapToSetDetail(editPurchaseBillDTO.getBillDetails());
        // 设置单据明细信息
        purchaseBill.getBillDetails().addAll(detailSet);

        return purchaseBill;
    }


    /**
     * 根据多条件查询进货单据信息
     *
     * @param conditionQueryPurchaseBill 查询条件
     * @return
     */
    public Page<PurchaseBillDTO> findByConditions(ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        // SpringCloud调用查询用户编码
        List<String> userCodeList = sharedManager.findByLikeUserName(conditionQueryPurchaseBill.getOperatorName());
        conditionQueryPurchaseBill.setOperatorCodeList(userCodeList);
        Page<PurchaseBill> purchaseBillPage = purchaseBillQueryService.findByConditions(conditionQueryPurchaseBill);
        return purchaseBillPage.map(source -> toMapDTO(source));
    }

    /**
     * 前端多条件查询转换DTO
     *
     * @param purchaseBill
     * @return
     */
    private PurchaseBillDTO toMapDTO(PurchaseBill purchaseBill) {

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
        if (station != null) {
            purchaseBillDTO.setInStationCode(station.getStationCode());
            // 入库库房-主表
            Storage storage = station.getStorage();
            if (storage != null) {
                purchaseBillDTO.setInStorageCode(storage.getStorageCode());
            }
        }
        // 供应商编码--主表
        Supplier supplier = (Supplier) purchaseBill.getOutLocation();
        if (supplier != null) {
            purchaseBillDTO.setSupplierCode(supplier.getSupplierCode());
        }
        // 单据提交状态--主表
        if (purchaseBill.getSubmitState() != null) {
            purchaseBillDTO.setSubmitState(purchaseBill.getSubmitState().name());
        } else {
            purchaseBillDTO.setSubmitState("-");
        }
        // 单据审核状态--主表
        if (purchaseBill.getAuditState() != null) {
            purchaseBillDTO.setAuditState(purchaseBill.getAuditState().name());
        } else {
            purchaseBillDTO.setAuditState("-");
        }
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
            if (purchaseBillDetail.getAmount() > 0) {
                // 累加实收数量
                amount += purchaseBillDetail.getAmount();
            }
            if (purchaseBillDetail.getDifferenceNumber() > 0) {
                // 累加数量差值
                differenceNumber += purchaseBillDetail.getDifferenceNumber();
            }
            if (purchaseBillDetail.getUnitPrice() != null && purchaseBillDetail.getAmount() > 0) {
                // 累加进货总价
                inTotalPrice = inTotalPrice.add(purchaseBillDetail.getUnitPrice().multiply(new BigDecimal(purchaseBillDetail.getAmount())));
            }
            if (purchaseBillDetail.getDifferencePrice() != null) {
                // 累加差价总和
                differencePrice = differencePrice.add(purchaseBillDetail.getDifferencePrice());
            }
        }
        // 实收数量--明细表
        purchaseBillDTO.setAmount(amount);
        // 数量差值--明细表
        purchaseBillDTO.setDifferenceNumber(differenceNumber);
        // 总价差值--明细表
        purchaseBillDTO.setDifferencePrice(differencePrice);
        // 进货实洋
        purchaseBillDTO.setInTotalPrice(inTotalPrice);

        return purchaseBillDTO;

    }

    /**
     * 验证提交数据信息
     *
     * @param addPurchaseBillDTO
     */
    private void verification(AddPurchaseBillDTO addPurchaseBillDTO) {
        if (StringUtils.isEmpty(addPurchaseBillDTO.getFreightCode())) {
            throw new DataException("500", "货运单号为空");
        }
        if (addPurchaseBillDTO.getShippedAmount() == null) {
            throw new DataException("500", "发货件数为空");
        }
        if (addPurchaseBillDTO.getActualAmount() == null) {
            throw new DataException("500", "实收件数为空");
        }
        if (StringUtils.isEmpty(addPurchaseBillDTO.getOperatorCode())) {
            throw new DataException("500", "操作人代码为空");
        }
        if (addPurchaseBillDTO.getStorage() == null) {
            throw new DataException("500", "库房为空");
        }
        if (addPurchaseBillDTO.getStation() == null) {
            throw new DataException("500", "站点为空");
        }
        if (addPurchaseBillDTO.getSupplier() == null) {
            throw new DataException("500", "供应商为空");
        }
        if (addPurchaseBillDTO.getBillDetails() == null) {
            throw new DataException("500", "进货单据明细为空");
        }
    }

}
