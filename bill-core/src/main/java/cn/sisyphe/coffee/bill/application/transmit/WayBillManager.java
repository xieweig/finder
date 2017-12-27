package cn.sisyphe.coffee.bill.application.transmit;


import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.stereotype.Service;

/**
 * 运货单
 */
@Service
public class WayBillManager {


    /**
     * @param editWayBillDTO
     * @return
     */
    public EditWayBillDTO createWayBill(EditWayBillDTO editWayBillDTO) throws DataException {


        EditWayBillDTO editWayBillTemp = new EditWayBillDTO();


        return editWayBillTemp;
    }

}
