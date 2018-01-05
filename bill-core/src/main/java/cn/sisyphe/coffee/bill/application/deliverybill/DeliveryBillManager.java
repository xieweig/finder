package cn.sisyphe.coffee.bill.application.deliverybill;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillQueryService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.ConditionQueryDeliveryBill;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryPickingEditDTO;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.QueryDeliveryBillDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
     * 根据单号查询配送单
     *
     * @param billCode
     * @return
     */
    public DeliveryBillDTO findOneDeliveryBillByBillCode(String billCode) throws DataException {

        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("30001", "单据编码不能为空");
        }
        DeliveryBillDTO deliveryBillDTO = new DeliveryBillDTO();

        deliveryBillDTO = deliveryBillDTO.convertBillToDTO(deliveryBillQueryService.findOneByBillCode(billCode));
        return deliveryBillDTO;
    }


    /**
     * 配送单分页查询
     *
     * @param conditionQueryDeliveryBill
     * @return
     */
    public QueryDeliveryBillDTO findDeliveryBillsByCondition(ConditionQueryDeliveryBill conditionQueryDeliveryBill) {

        QueryDeliveryBillDTO queryDeliveryBillDTO = new QueryDeliveryBillDTO();

        //TODO: 2018/1/5 分页查询
        Page<DeliveryBill> deliveryBillPages = deliveryBillQueryService.findPageByCondition(conditionQueryDeliveryBill);
        // 转换
        List<DeliveryBillDTO> billDTOList = this.convertToQueryPageDTO(deliveryBillPages.getContent());
        // 总数
        queryDeliveryBillDTO.setTotalNumber(deliveryBillPages.getTotalElements());
        // 配送出库单据数据
        queryDeliveryBillDTO.setContent(billDTOList);
        return queryDeliveryBillDTO;
    }

    /**
     * 分页查询转换
     *
     * @param deliveryBills
     * @return
     */
    private List<DeliveryBillDTO> convertToQueryPageDTO(List<DeliveryBill> deliveryBills) {
        List<DeliveryBillDTO> deliveryBillDTOS = new ArrayList<>();
        for (DeliveryBill deliveryBill : deliveryBills) {

            //
            DeliveryBillDTO deliveryBillDTO = new DeliveryBillDTO();
            //在DTO子方法转换
            deliveryBillDTO = deliveryBillDTO.convertBillToDTO(deliveryBill);
            // 设置站点
            deliveryBillDTOS.add(deliveryBillDTO);//
        }
        return deliveryBillDTOS;
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
     * 保存拣货
     *
     * @param editDTO
     * @throws DataException
     */
    private void savePicking(DeliveryPickingEditDTO editDTO) throws DataException {
        // 参数检查
        this.checkSaveParam(editDTO);
        // DTO转换为单据
        DeliveryBill deliveryBill = editDTO.convertPickingDTOToBill(editDTO);

        // 保存单据
        this.save(deliveryBill);

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
        //其他判断
    }


}
