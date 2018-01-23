package cn.sisyphe.coffee.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDetailDTO;
import cn.sisyphe.coffee.restock.InstanceIter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/12
 * @Description: 调拨单的信息实例dto 或entity 的生成 辅助测试
 */
public class InstanceFactory extends InstanceIter {

    protected List<String> sourceRestockList = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public AllotBillDTO nextRandomAddAllotBillDTO(Integer detailsNumber){

        AllotBillDTO dto = new AllotBillDTO();

        //可能有误
        dto.setBillPurpose(BillPurposeEnum.MOVE_STORAGE);


        dto.setSpecificBillType(BillTypeEnum.RESTOCK);
   //     dto.setSourceCode(SOURCE_Allot_CODE[random.nextInt(SOURCE_Allot_CODE.length)].trim());


        dto.setAllotStatus(BillAllotStatusEnum.values()[random.nextInt(BillAllotStatusEnum.values().length)]);
        dto.setInLocation(this.nextRandomStation());
        dto.setOutLocation(this.nextRandomStation());
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);


        Set<AllotBillDetailDTO> details = new HashSet();
        for (int i = 0; i < detailsNumber; i++) {
            details.add(this.nextRandomAllotBillDetailDTO());
        }

        dto.setBillDetails(details);

        dto.setOutStorageMemo("出库库位备注："+random.nextInt(100));



        dto.setOperatorCode(OPERATIONCODE[random.nextInt(OPERATIONCODE.length)]);
        dto.setTotalAmount(random.nextInt(10));
        dto.setTotalVarietyAmount(random.nextInt(500)+100);

        return dto;
    }
    protected AllotBillDetailDTO nextRandomAllotBillDetailDTO(){
        AllotBillDetailDTO DetailDTO = new AllotBillDetailDTO();

        Integer amount = random.nextInt(100)+20;
        DetailDTO.setActualAmount(amount);
        DetailDTO.setShippedAmount(amount+random.nextInt(10)-5);

        String codePair= detailList.get(random.nextInt(detailList.size()));
        String[] strings = codePair.split("=");
        RawMaterial rawMaterial = new RawMaterial(strings[0]);
        Cargo cargo = new Cargo(strings[1]);
        rawMaterial.setCargo(cargo);
        DetailDTO.setRawMaterial(rawMaterial);

        return DetailDTO;
    }


}
