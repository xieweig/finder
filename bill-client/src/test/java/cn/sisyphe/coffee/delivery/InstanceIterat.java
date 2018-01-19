package cn.sisyphe.coffee.delivery;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDetailDTO;
import cn.sisyphe.coffee.restock.InstanceIter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/18
 * @Description:
 */
public class InstanceIterat extends InstanceIter{
    //请先运行for plan 插入plan数据，之后替换下面，可能需要注释掉send event
    public static final String[] SOURCE_CODE_FOR_DELIVERY={"1516264416977","1516264417008","1516264416970"};
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public DeliveryBillDTO nextRandomAddDeliveryBillDTO(Integer detailsNumber){

        DeliveryBillDTO dto = new DeliveryBillDTO();

        //可能有误


        dto.setBillType(BillTypeEnum.DELIVERY);
        dto.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
        Integer sign = random.nextInt(10);


        if (sign>4){
            dto.setSpecificBillType(BillTypeEnum. DELIVERY);
            dto.setSourceCode(SOURCE_CODE_FOR_DELIVERY[random.nextInt(SOURCE_CODE_FOR_DELIVERY.length)].trim());
        }

        else{
            dto.setSpecificBillType(BillTypeEnum.NO_PLAN);
        }


        dto.setInLocation(this.nextRandomStation());
        dto.setOutLocation(this.nextRandomStation());
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);


        Set<DeliveryBillDetailDTO> details = new HashSet();
        for (int i = 0; i < detailsNumber; i++) {
            details.add(this.nextRandomDeliveryBillDetailDTO());
        }

        dto.setBillDetails(details);

        dto.setOutStorageMemo("出库库位备注："+random.nextInt(100));



        dto.setOperatorCode(OPERATIONCODE[random.nextInt(OPERATIONCODE.length)]);
        dto.setTotalAmount(random.nextInt(10));
        dto.setTotalVarietyAmount(random.nextInt(500)+100);

        return dto;
    }
    protected DeliveryBillDetailDTO nextRandomDeliveryBillDetailDTO(){
        DeliveryBillDetailDTO  DetailDTO = new DeliveryBillDetailDTO();

        Integer amount = random.nextInt(100)+20;
        DetailDTO.setActualAmount(amount);
        DetailDTO.setShippedAmount(amount+random.nextInt(10)-5);

        String codePair= detailList.get(random.nextInt(detailList.size()));
        String[] strings = codePair.split("=");
        RawMaterial rawMaterial = new RawMaterial(strings[0]);
        Cargo cargo = new Cargo(strings[1]);
        rawMaterial.setCargo(cargo);
        DetailDTO.setRawMaterial(rawMaterial);
        System.err.println(ToStringBuilder.reflectionToString(DetailDTO));
        return DetailDTO;
    }




}
