/*
package cn.sisyphe.coffee.returned;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author mayupeng
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class ReturnedBillTest {
    @Autowired
    private ReturnedBillManager returnedBillManager;

    */
/**
     * 测试保存单据
     *//*

    @Test
    public void save() {
        AddReturnedBillDTO saveDto = new AddReturnedBillDTO();
        // 备注
        saveDto.setMemo("测试保存单据4");
        // 操作人代码
        saveDto.setOperatorCode("xj0004");
        // 库房
        Storage storage = new Storage("kf004");
        storage.setStorageName("测试库房4");
        saveDto.setStorage(storage);

        // 供应商信息
        Supplier supplier = new Supplier("supplier004");
        supplier.setSupplierName("测试供应商4");
        supplier.setAddress("重庆市4");

        // 站点
        Station station = new Station("CD39");
        station.setStationName("总部4");
        station.setStationType(StationType.STORE);
        // 将库房设置到站点中去
        station.setStorage(storage);

        saveDto.setStation(station);

        List<ReturnedBillDetailDTO> billDetails = new ArrayList<>();

        ReturnedBillDetailDTO detailDTO = new ReturnedBillDetailDTO();

        // 最小单位数量
        detailDTO.setAmount(300);
        */
/*//*
/ 包号
        detailDTO.setPackageCode("package004");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(10));
        // 发货数量
        detailDTO.setShippedNumber(300);
        // 数量差值
        detailDTO.setDifferenceNumber(0);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(0));
*//*

        RawMaterial rawMaterial = new RawMaterial("ylbm004");
        rawMaterial.setRawMaterialName("原料004");
        Cargo cargo = new Cargo("cargo004");
        cargo.setCargoName("货物004");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);

        ReturnedBillDetailDTO detailDTO2 = new ReturnedBillDetailDTO();

        // 最小单位数量
        detailDTO2.setAmount(200);
        // 包号
        */
/*detailDTO2.setPackageCode("package005");
        // 生产日期
        detailDTO2.setDateInProduced(new Date());
        // 单位进价
        detailDTO2.setUnitPrice(new BigDecimal(10));
        // 发货数量
        detailDTO2.setShippedNumber(300);
        // 数量差值
        detailDTO2.setDifferenceNumber(100);
        // 总价差值
        detailDTO2.setDifferencePrice(new BigDecimal(1000));*//*


        RawMaterial rawMaterial2 = new RawMaterial("ylbm005");
        rawMaterial2.setRawMaterialName("原料005");
        Cargo cargo2 = new Cargo("cargo005");
        cargo2.setCargoName("货物005");
        rawMaterial2.setCargo(cargo2);
        detailDTO2.setRawMaterial(rawMaterial2);

        billDetails.add(detailDTO);
        billDetails.add(detailDTO2);

        saveDto.setBillDetails(billDetails);
        returnedBillManager.saveBill(saveDto);
    }

    */
/**
     * 测试提交单据
     *//*

    @Test
    public void submit() {
        AddReturnedBillDTO saveDto = new AddReturnedBillDTO();
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

        // 站点
        Station station = new Station("CD37");
        station.setStationName("总部");
        station.setStationType(StationType.STORE);
        // 将库房设置到站点中去
        station.setStorage(storage);

        saveDto.setStation(station);

        List<ReturnedBillDetailDTO> billDetails = new ArrayList<>();
        ReturnedBillDetailDTO detailDTO = new ReturnedBillDetailDTO();

        // 最小单位数量
        detailDTO.setAmount(2000);
        // 包号
        */
/*detailDTO.setPackageCode("package002");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(1));
        // 发货数量
        detailDTO.setShippedNumber(688);
        // 数量差值
        detailDTO.setDifferenceNumber(200);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(200));
*//*

        RawMaterial rawMaterial = new RawMaterial("ylbm002");
        rawMaterial.setRawMaterialName("原料002");
        Cargo cargo = new Cargo("cargo002");
        cargo.setCargoName("货物002");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);


        billDetails.add(detailDTO);
        saveDto.setBillDetails(billDetails);
        returnedBillManager.submitBill(saveDto);
    }
    */
/**
     * 测试打开单据，状态改为审核中
     *//*

    @Test
    public void openBill() {
        String billCode = "bill004";
        returnedBillManager.openBill(billCode);
    }
    */
/**
     * 修改单据--保存
     *//*

    @Test
    public void updateReturnedBillToSave() {
        String billCode = "bill004";
        AddReturnedBillDTO saveDto = new AddReturnedBillDTO();
        saveDto.setBillCode(billCode);
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

        // 站点
        Station station = new Station("CD37-saved");
        station.setStationName("总部");
        station.setStorage(storage);
        saveDto.setStation(station);

        List<ReturnedBillDetailDTO> billDetails = new ArrayList<>();
        ReturnedBillDetailDTO detailDTO = new ReturnedBillDetailDTO();

        // 实收数量
        detailDTO.setAmount(88);
        // 包号
        */
/*detailDTO.setPackageCode("package001-saved");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(10));
        // 发货数量
        detailDTO.setShippedNumber(88);
        // 数量差值
        detailDTO.setDifferenceNumber(0);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(0));*//*


        RawMaterial rawMaterial = new RawMaterial("ylbm001");
        rawMaterial.setRawMaterialName("原料001");
        Cargo cargo = new Cargo("cargo001");
        cargo.setCargoName("货物001");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);


        billDetails.add(detailDTO);
        saveDto.setBillDetails(billDetails);
        returnedBillManager.updateBillToSave(saveDto);
    }
    */
/**
     * 修改单据--提交审核
     *//*

    @Test
    public void updateReturnedBillToSubmit() {
        String billCode = "bill004";
        AddReturnedBillDTO saveDto = new AddReturnedBillDTO();
        saveDto.setBillCode(billCode);
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

        // 站点
        Station station = new Station("CD37-submit");
        station.setStationName("总部");
        station.setStorage(storage);
        saveDto.setStation(station);

        List<ReturnedBillDetailDTO> billDetails = new ArrayList<>();
        ReturnedBillDetailDTO detailDTO = new ReturnedBillDetailDTO();

        // 最小单位数量
        detailDTO.setAmount(88);
        // 包号
       */
/* detailDTO.setPackageCode("package001-submit");
        // 生产日期
        detailDTO.setDateInProduced(new Date());
        // 单位进价
        detailDTO.setUnitPrice(new BigDecimal(10));
        // 发货数量
        detailDTO.setShippedNumber(88);
        // 数量差值
        detailDTO.setDifferenceNumber(0);
        // 总价差值
        detailDTO.setDifferencePrice(new BigDecimal(0));*//*


        RawMaterial rawMaterial = new RawMaterial("ylbm001");
        rawMaterial.setRawMaterialName("原料001");
        Cargo cargo = new Cargo("cargo001");
        cargo.setCargoName("货物001");
        rawMaterial.setCargo(cargo);
        detailDTO.setRawMaterial(rawMaterial);


        billDetails.add(detailDTO);
        saveDto.setBillDetails(billDetails);
        returnedBillManager.updateBillToSubmit(saveDto);
    }
    */
/**
     * 测试审核失败单据
     *//*

    @Test
    public void auditFailureBill() {
        String billCode = "bill004";
        String auditPersonCode = "admin001";
        returnedBillManager.auditBill(billCode,auditPersonCode,false);
    }

    */
/**
     * 测试审核成功单据
     *//*

    @Test
    public void AuditSuccessBill() {
        String billCode = "bill004";
        String auditPersonCode = "admin003 ";
        returnedBillManager.auditBill(billCode,auditPersonCode,true);
    }
}
*/
