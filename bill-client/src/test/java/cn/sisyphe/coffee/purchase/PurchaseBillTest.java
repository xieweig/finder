package cn.sisyphe.coffee.purchase;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.AddPurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.BillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.EditPurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.QueryPurchaseBillDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单据单元测试
 * version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class PurchaseBillTest {

    @Autowired
    private PurchaseBillManager purchaseBillManager;

    /**
     * 测试保存单据
     */
    @Test
    public void save() {
        AddPurchaseBillDTO saveDto = new AddPurchaseBillDTO();
        // 货运单号
        saveDto.setFreightCode("hydh001");
        // 发货件数
        saveDto.setShippedAmount(10);
        // 实收件数
        saveDto.setActualAmount(8);
        // 备注
        saveDto.setMemo("测试保存单据");
        // 操作人代码
        saveDto.setOperatorCode("xj0001");
        // 库房
        Storage storage = new Storage("kf001");
        storage.setStorageName("测试库房");
        saveDto.setStorage(storage);

        // 供应商信息
        Supplier supplier = new Supplier("supplier001");
        supplier.setSupplierName("测试供应商");
        supplier.setAddress("重庆市");
        saveDto.setSupplier(supplier);

        // 站点
        Station station = new Station("CD36");
        station.setStationName("总部");
        station.setStationType(StationType.STORE);
        // 将库房设置到站点中去
        station.setStorage(storage);

        saveDto.setStation(station);

        List<BillDetailDTO> billDetails = new ArrayList<>();
        BillDetailDTO detailDTO = new BillDetailDTO();

        // 最小单位数量
        detailDTO.setAmount(1000);
        // 包号
        detailDTO.setPackageCode("package001");
        // 标准单位编码
        detailDTO.setStandardUnitCode("bzdwbm001");
        // 规格编码
        detailDTO.setMeasurementCode("ggbm001");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(10));
        // 实收数量
        detailDTO.setActualNumber(88);
        // 发货数量
        detailDTO.setShippedNumber(68);
        // 数量差值
        detailDTO.setDifferenceNumber(20);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(200));

        RawMaterial rawMaterial = new RawMaterial("ylbm001");
        rawMaterial.setRawMaterialName("原料001");
        Cargo cargo = new Cargo("cargo001");
        cargo.setCargoName("货物001");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);


        billDetails.add(detailDTO);
        saveDto.setBillDetails(billDetails);
        purchaseBillManager.saveBill(saveDto);
    }

    /**
     * 测试提交单据
     */
    @Test
    public void commit() {
        AddPurchaseBillDTO saveDto = new AddPurchaseBillDTO();
        // 货运单号
        saveDto.setFreightCode("hydh002");
        // 发货件数
        saveDto.setShippedAmount(5);
        // 实收件数
        saveDto.setActualAmount(5);
        // 备注
        saveDto.setMemo("测试保存单据2");
        // 操作人代码
        saveDto.setOperatorCode("xj0002");
        // 库房
        Storage storage = new Storage("kf002");
        storage.setStorageName("测试库房2");
        saveDto.setStorage(storage);

        // 供应商信息
        Supplier supplier = new Supplier("supplier002");
        supplier.setSupplierName("测试供应商2");
        supplier.setAddress("重庆市2");
        saveDto.setSupplier(supplier);

        // 站点
        Station station = new Station("CD37");
        station.setStationName("总部");
        station.setStationType(StationType.STORE);
        // 将库房设置到站点中去
        station.setStorage(storage);

        saveDto.setStation(station);

        List<BillDetailDTO> billDetails = new ArrayList<>();
        BillDetailDTO detailDTO = new BillDetailDTO();

        // 最小单位数量
        detailDTO.setAmount(2000);
        // 包号
        detailDTO.setPackageCode("package002");
        // 标准单位编码
        detailDTO.setStandardUnitCode("bzdwbm002");
        // 规格编码
        detailDTO.setMeasurementCode("ggbm002");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(1));
        // 实收数量
        detailDTO.setActualNumber(888);
        // 发货数量
        detailDTO.setShippedNumber(688);
        // 数量差值
        detailDTO.setDifferenceNumber(200);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(200));

        RawMaterial rawMaterial = new RawMaterial("ylbm002");
        rawMaterial.setRawMaterialName("原料002");
        Cargo cargo = new Cargo("cargo002");
        cargo.setCargoName("货物002");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);


        billDetails.add(detailDTO);
        saveDto.setBillDetails(billDetails);
        purchaseBillManager.submitBill(saveDto);
    }

    /**
     * 测试打开单据，状态改为审核中
     */
    @Test
    public void openBill() {
        String billCode = "bill002";
         purchaseBillManager.openBill(billCode);
    }
    /**
     * 修改单据--保存
     */
    @Test
    public void updatePurchaseBillToSave() {
        String billCode = "bill001";
        EditPurchaseBillDTO saveDto = new EditPurchaseBillDTO();
        saveDto.setBillCode(billCode);
        // 货运单号
        saveDto.setFreightCode("hydh001");
        // 发货件数
        saveDto.setShippedAmount(10);
        // 实收件数
        saveDto.setActualAmount(10);
        // 备注
        saveDto.setMemo("测试保存单据-保存");
        // 操作人代码
        saveDto.setOperatorCode("xj0001");
        // 库房
        Storage storage = new Storage("kf001");
        storage.setStorageName("测试库房");
        saveDto.setStorage(storage);

        // 供应商信息
        Supplier supplier = new Supplier("supplier001-saved");
        supplier.setSupplierName("测试供应商-saved");
        supplier.setAddress("重庆市-saved");
        saveDto.setSupplier(supplier);

        // 站点
        Station station = new Station("CD37-saved");
        station.setStationName("总部");
        station.setStorage(storage);
        saveDto.setStation(station);

        List<BillDetailDTO> billDetails = new ArrayList<>();
        BillDetailDTO detailDTO = new BillDetailDTO();

        // 最小单位数量
        detailDTO.setAmount(1000);
        // 包号
        detailDTO.setPackageCode("package001-saved");
        // 标准单位编码
        detailDTO.setStandardUnitCode("bzdwbm001");
        // 规格编码
        detailDTO.setMeasurementCode("ggbm001");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(10));
        // 实收数量
        detailDTO.setActualNumber(88);
        // 发货数量
        detailDTO.setShippedNumber(88);
        // 数量差值
        detailDTO.setDifferenceNumber(0);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(0));

        RawMaterial rawMaterial = new RawMaterial("ylbm001");
        rawMaterial.setRawMaterialName("原料001");
        Cargo cargo = new Cargo("cargo001");
        cargo.setCargoName("货物001");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);


        billDetails.add(detailDTO);
        saveDto.setBillDetails(billDetails);
        purchaseBillManager.updateBill(saveDto);
    }
    /**
     * 修改单据--提交审核
     */
    @Test
    public void updatePurchaseBillToSubmit() {
        String billCode = "bill001";
        EditPurchaseBillDTO saveDto = new EditPurchaseBillDTO();
        saveDto.setBillCode(billCode);
        // 货运单号
        saveDto.setFreightCode("hydh001");
        // 发货件数
        saveDto.setShippedAmount(10);
        // 实收件数
        saveDto.setActualAmount(10);
        // 备注
        saveDto.setMemo("测试保存单据-提交");
        // 操作人代码
        saveDto.setOperatorCode("xj0001");
        // 库房
        Storage storage = new Storage("kf001");
        storage.setStorageName("测试库房");
        saveDto.setStorage(storage);

        // 供应商信息
        Supplier supplier = new Supplier("supplier001-submit");
        supplier.setSupplierName("测试供应商-submit");
        supplier.setAddress("重庆市-submit");
        saveDto.setSupplier(supplier);

        // 站点
        Station station = new Station("CD37-submit");
        station.setStationName("总部");
        station.setStorage(storage);
        saveDto.setStation(station);

        List<BillDetailDTO> billDetails = new ArrayList<>();
        BillDetailDTO detailDTO = new BillDetailDTO();

        // 最小单位数量
        detailDTO.setAmount(1000);
        // 包号
        detailDTO.setPackageCode("package001-submit");
        // 标准单位编码
        detailDTO.setStandardUnitCode("bzdwbm001");
        // 规格编码
        detailDTO.setMeasurementCode("ggbm001");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(10));
        // 实收数量
        detailDTO.setActualNumber(88);
        // 发货数量
        detailDTO.setShippedNumber(88);
        // 数量差值
        detailDTO.setDifferenceNumber(0);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(0));

        RawMaterial rawMaterial = new RawMaterial("ylbm001");
        rawMaterial.setRawMaterialName("原料001");
        Cargo cargo = new Cargo("cargo001");
        cargo.setCargoName("货物001");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);


        billDetails.add(detailDTO);
        saveDto.setBillDetails(billDetails);
        purchaseBillManager.updateBill(saveDto);
    }
    /**
     * 测试审核失败单据
     */
    @Test
    public void auditFailureBill() {
        String billCode = "bill001";
        purchaseBillManager.auditFailureBill(billCode);
    }

    /**
     * 测试审核成功单据
     */
    @Test
    public void AuditSuccessBill() {
        String billCode = "bill001";
        purchaseBillManager.AuditSuccessBill(billCode);
    }
    /**
     * 测试多条件查询
     */
    @Test
    public void queryByConditions() {
        ConditionQueryPurchaseBill bill = new ConditionQueryPurchaseBill();
        bill.setPage(1);
        bill.setPageSize(1);
        bill.setBillCode("bill001");
        QueryPurchaseBillDTO dto = purchaseBillManager.findByConditions(bill);
        System.out.println(dto.toString());
    }
}
