
package cn.sisyphe.coffee.returned;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import cn.sisyphe.coffee.restock.InstanceIter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class InstanceIterator extends InstanceIter{

    public static final String[] SOURCE_CODE_FOR_RETURNED={"1516262901500","1516262901605","1516262901623"};
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public ReturnedBillDTO nextRandomAddReturnedBillDTO(Integer detailsNumber){

        ReturnedBillDTO dto = new ReturnedBillDTO();

        //可能有误


        dto.setBillType(BillTypeEnum.RETURNED);
        dto.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
        Integer sign = random.nextInt(10);
        dto.setRootCode(ROOT_CODES[random.nextInt(ROOT_CODES.length)]);

        if (sign>4){
            dto.setSpecificBillType(BillTypeEnum.RETURNED);
            dto.setSourceCode(SOURCE_CODE_FOR_RETURNED[random.nextInt(SOURCE_CODE_FOR_RETURNED.length)].trim());
        }

        else{
            dto.setSpecificBillType(BillTypeEnum.NOPLAN);
        }


        dto.setInLocation(this.nextRandomStation());
        dto.setOutLocation(this.nextRandomStation());
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);


        Set<ReturnedBillDetailDTO> details = new HashSet();
        for (int i = 0; i < detailsNumber; i++) {
            details.add(this.nextRandomReturnedBillDetailDTO());
        }

        dto.setBillDetails(details);

        dto.setOutStorageMemo("出库库位备注："+random.nextInt(100));



        dto.setOperatorCode(OPERATIONCODE[random.nextInt(OPERATIONCODE.length)]);
        dto.setTotalAmount(random.nextInt(10));
        dto.setTotalVarietyAmount(random.nextInt(500)+100);

        return dto;
    }
    protected ReturnedBillDetailDTO nextRandomReturnedBillDetailDTO(){
        ReturnedBillDetailDTO  DetailDTO = new ReturnedBillDetailDTO();

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