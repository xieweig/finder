package cn.sisyphe.coffee.bill.application.deliverybill;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillQueryService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryPickingEditDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2018/1/4.
 */
@Service
public class DeliveryBillManager extends AbstractBillManager<DeliveryBill> {

    @Autowired
    private DeliveryBillQueryService deliveryBillQueryService;


    @Autowired
    public DeliveryBillManager(BillRepository<DeliveryBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    /**
     * 根据单号查看单据
     *
     * @param billCode
     * @return
     */
    public DeliveryBillDTO findOneByBillCode(String billCode) {

        DeliveryBillDTO deliveryBillDTO = new DeliveryBillDTO();
        DeliveryBill deliveryBill = deliveryBillQueryService.findOneByBillCode(billCode);
        return deliveryBillDTO.convertBillToDTO(deliveryBill);
    }


    /**
     * 按货物拣货
     *
     * @param editDTO
     */
    public void pickingByCargo(DeliveryPickingEditDTO editDTO) throws DataException {

        // TODO: 2018/1/4
        this.savePicking(editDTO);
    }

    /**
     * 按原料拣货
     *
     * @param editDTO
     */
    public void pickingByRawMaterial(DeliveryPickingEditDTO editDTO) throws DataException {
        // TODO: 2018/1/4

        this.savePicking(editDTO);
    }


    /**
     * @param editDTO
     * @throws DataException
     */
    private void savePicking(DeliveryPickingEditDTO editDTO) throws DataException {
        // 参数检查
        this.checkSaveParam(editDTO);
        // 转换单据
        DeliveryBill deliveryBill = editDTO.convertPickingDTOToBill(editDTO);

        // 保存单据
        save(deliveryBill);

    }

    /**
     * 拣货提交
     *
     * @param editDTO
     */
    public void submitWhenDone(DeliveryPickingEditDTO editDTO) throws DataException {


    }


    //如果前端传递过来类型是供应商，则new供应商对象
    private AbstractLocation getLocation(Station station) {
        if (StationType.SUPPLIER.equals(station.getStationType())) {
            Supplier supplier = new Supplier(station.getStationCode());
            supplier.setSupplierName(station.getStationName());
            return supplier;
        }
        return new Station(station.getStationCode());
    }

    /**
     * 保存前的参数检查
     *
     * @param editDTO
     * @throws DataException
     */
    private void checkSaveParam(DeliveryPickingEditDTO editDTO) throws DataException {

        if (StringUtils.isEmpty(editDTO.getBillCode())) {
            throw new DataException("30001", "单据编码不能为空");
        }
        //
        if (StringUtils.isEmpty(editDTO.getOperatorCode())) {
            throw new DataException("30002", "操作人编码不能为空");
        }

        if (!editDTO.getBillDetails().isEmpty()) {
            //明细检查
            // TODO: 2018/1/4
        }

    }
}
