package cn.sisyphe.coffee.bill.application.returned;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.util.BillToDtoExtraProcessor;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTOFactory;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;


/**
 * @date: 2018/1/2
 * @description:
 * @author：xieweiguang
 */
@Service
public class ReturnedBillManager extends AbstractBillExtraManager<ReturnedBill, ReturnedBillDTO, ConditionQueryReturnedBill> {


    @Autowired
    public ReturnedBillManager(BillRepository<ReturnedBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<ReturnedBill, ConditionQueryReturnedBill> billExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager);
    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.RETURNED;
    }

    @Override
    protected ReturnedBill dtoToBill(ReturnedBill bill, ReturnedBillDTO billDTO) {
        ReturnedBill returnedBill = super.dtoToBill(bill, billDTO);
        returnedBill.setInLocation(billDTO.getSupplier());
        return returnedBill;
    }

    @Override
    protected ReturnedBillDTO billToDto(ReturnedBill bill) {
        ReturnedBillDTO returnedBillDTO = super.billToDto(bill);
       if (bill.getInLocation() instanceof Supplier){
           returnedBillDTO.setSupplier((Supplier) bill.getInLocation());
       }
        return returnedBillDTO;
    }
}
