package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.BillDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class RequestMock {
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    @Resource
    private RestockBillManager restockBillManager;
    /**
     *
     *notes :
     *  随机生成 AddRestockBillDTO 的对象
     *
     */
    private AddRestockBillDTO Create(){
        AddRestockBillDTO dto = new AddRestockBillDTO();

        dto.setBillCode("0101"+random.nextInt(1000));
        dto.setActualAmount(random.nextInt(50));
        dto.setFreightCode("0901"+random.nextInt(200));
        dto.setShippedAmount(random.nextInt(10));
        dto.setMemo("Remarks: "+random.nextInt(20)+" ok!");
        dto.setOperatorCode("2200"+random.nextInt(100));

        Supplier supplier = new Supplier("1202"+random.nextInt(300));
        supplier.setSupplierName("江苏第"+random.nextInt(100)+"供应商");
        supplier.setAddress("浙江");
        dto.setSupplier(supplier);

        Station station = new Station("1302"+random.nextInt(10)+"02"+random.nextInt(10));
        station.setStationName("重庆"+random.nextInt(100)+"站");
        station.setStationType(StationType.STORE);
        dto.setStation(new Station());

        Storage storage = new Storage("00"+random.nextInt(8));
        storage.setStorageName("xx库");
        dto.setStorage(storage);

        List<BillDetailDTO> list = new ArrayList<>();
            BillDetailDTO billDetailDTO_1 = new BillDetailDTO();
            billDetailDTO_1.setAmount(random.nextInt(100));
            billDetailDTO_1.setDateInProduced(new Date());
            billDetailDTO_1.setDifferenceNumber(random.nextInt(30));
            billDetailDTO_1.setPackageCode("No>>."+random.nextInt(10));
            billDetailDTO_1.setShippedNumber(random.nextInt(20));
            list.add(billDetailDTO_1);
            BillDetailDTO billDetailDTO_2 = new BillDetailDTO();
            billDetailDTO_2.setAmount(random.nextInt(100));
            billDetailDTO_2.setDateInProduced(new Date());
            billDetailDTO_2.setDifferenceNumber(random.nextInt(30));
            billDetailDTO_2.setPackageCode("No>>."+random.nextInt(10));
            billDetailDTO_2.setShippedNumber(random.nextInt(20));
            list.add(billDetailDTO_2);

        dto.setBillDetails(list);
        return  dto;
    }
    @Test
    public void saveTest(){
        AddRestockBillDTO dto =this.Create();
        this.restockBillManager.saveBill(dto);

    }
    @Test
    public void update(){

    }
    @Test
    public void deleteTest(){



    }
    @Test
    public void queryTest(){

    }

}
