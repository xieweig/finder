package cn.sisyphe.coffee.bill.application.deliverybill;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillQueryService;
import cn.sisyphe.coffee.bill.domain.delivery.enums.PickingTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.*;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ScanFillBillDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/4.
 */
@Service
public class DeliveryBillManager extends AbstractBillManager<DeliveryBill> {


    //公共信息
    @Autowired
    private SharedManager sharedManager;

    @Autowired
    private DeliveryBillQueryService deliveryBillQueryService;


    @Autowired
    public DeliveryBillManager(BillRepository<DeliveryBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }


    /**
     * @param stationCode
     * @return
     */
    private Station findStation(String stationCode) {
        Station station = null;
        station = sharedManager.findStationByStationCode(stationCode);
        if (station == null) {
            return new Station(stationCode);
        }
        return station;
    }


    /**
     * 出库单打包汇总
     *
     * @param billCode
     * @return
     */
    public ScanFillBillDTO scanQueryBill(String billCode) {

        ScanFillBillDTO scanFillBillDTO = new ScanFillBillDTO();
        DeliveryBill deliveryBill = deliveryBillQueryService.findOneByBillCode(billCode);

        if (deliveryBill == null) {
            return null;
        }
        Set<DeliveryBillDetail> details = deliveryBill.getBillDetails();
        //
        int totalAmount = 0;
        int totalCount = 0;
        List<String> packNumbers = new ArrayList<>();
        for (DeliveryBillDetail detail : details) {
            totalCount += 1;// 总品种
            totalAmount += detail.getAmount();// 总数量
            //包号不重复则添加
            if (!packNumbers.contains(detail.getPackageCode())) {
                //添加包号
                packNumbers.add(detail.getPackageCode());
            }
        }
        //单据类型
        scanFillBillDTO.setBillType(BillTypeEnum.DELIVERY.name());
        //bill code
        scanFillBillDTO.setBillCode(deliveryBill.getBillCode());
        //录单人
        scanFillBillDTO.setOperatorCode(deliveryBill.getOperatorCode());
        //录单人姓名
        scanFillBillDTO.setOperatorName(deliveryBill.getOperatorName());
        //包号
        scanFillBillDTO.setPackNumbers(packNumbers);
        //总品种
        scanFillBillDTO.setTotalCount(totalCount);
        //总数量
        scanFillBillDTO.setTotalAmount(totalAmount);
        //findStation
        //出库时间
        scanFillBillDTO.setOutStockTime(deliveryBill.getOutStockTime());
        //入库站点
        if (deliveryBill.getInLocation() != null) {
            //
            Station station = this.findStation(deliveryBill.getInLocation().code());
            scanFillBillDTO.setInStationCode(station.getStationCode());
            scanFillBillDTO.setInStationName(station.getStationName());
        }
        //出库站点
        if (deliveryBill.getOutLocation() != null) {
            Station stationOut = this.findStation(deliveryBill.getInLocation().code());
            scanFillBillDTO.setOutStationCode(stationOut.getStationCode());
            scanFillBillDTO.setOutStationName(stationOut.getStationName());

        }
        //
        return scanFillBillDTO;
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
     * 按货物拣货，权限控制
     *
     * @param editDTO
     */
    public void pickingByCargo(DeliveryPickingEditDTO editDTO) throws DataException {

        if (editDTO.getPickingTypeEnum() == null) {
            throw new DataException("30005", "选择拣货方式");
        }
        if (PickingTypeEnum.PICKING_BY_CARGO.equals(editDTO.getPickingTypeEnum())) {
            this.savePicking(editDTO);
        }

    }

    /**
     * 按原料拣货，权限控制
     *
     * @param editDTO
     */
    public void pickingByRawMaterial(DeliveryPickingEditDTO editDTO) throws DataException {

        if (editDTO.getPickingTypeEnum() == null) {
            throw new DataException("30005", "选择拣货方式");
        }
        if (PickingTypeEnum.PICKING_BY_MATERIAL.equals(editDTO.getPickingTypeEnum())) {
            this.savePicking(editDTO);
        }

    }

    /**
     * 保存拣货
     *
     * @param editDTO
     * @throws DataException
     */
    private void savePicking(DeliveryPickingEditDTO editDTO) throws DataException {

        /**
         *
         *出库单的来源有两类：
         * 1总部计划切片而来 2"站点退库拣货"页面而来，
         * 这时 "来源单号 " 和 "出库单号" 一样，就是出库单号
         *
         */
        // 参数检查
        this.checkSaveParam(editDTO);
        // DTO转换为单据
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
        //参数检查
        this.checkSaveParam(editDTO);

        this.checkBeforeSubmit(editDTO);

        DeliveryBill deliveryBill = editDTO.convertPickingDTOToBill(editDTO);
        // 拣货提交生成出库单

        //计划编码有由后端生成，如果前端传递回来的时候有code，就做更新操作
        deliveryBill = deliveryBillQueryService.findOneByBillCode(editDTO.getBillCode());

        //
        submit(deliveryBill);

    }

    /**
     * 审核通过
     *
     * @param deliveryBillDTO
     */
    public void auditPass(AuditDeliveryBillDTO deliveryBillDTO) {

        //审核参数检查
        this.checkAuditParam(deliveryBillDTO);

        deliveryBillDTO.setAuditPass(true);
        //找到单据
        DeliveryBill deliveryBill = deliveryBillQueryService.findOneByBillCode(deliveryBillDTO.getBillCode());
        //意见
        deliveryBill.setAuditOpinion(deliveryBillDTO.getAuditOpinion());
        // 设置审核人code
        deliveryBill.setAuditPersonCode(deliveryBillDTO.getAuditOperatorCode());
        //审核
        audit(deliveryBill, deliveryBillDTO.getAuditPass());
    }


    /**
     * 审核不通过
     *
     * @param deliveryBillDTO
     */
    public void auditNotPass(AuditDeliveryBillDTO deliveryBillDTO) {
        //审核参数检查
        this.checkAuditParam(deliveryBillDTO);
        deliveryBillDTO.setAuditPass(false);
        //找到单据
        DeliveryBill deliveryBill = deliveryBillQueryService.findOneByBillCode(deliveryBillDTO.getBillCode());
        //意见
        deliveryBill.setAuditOpinion(deliveryBillDTO.getAuditOpinion());
        // 设置审核人code
        deliveryBill.setAuditPersonCode(deliveryBillDTO.getAuditOperatorCode());
        //审核
        audit(deliveryBill, deliveryBillDTO.getAuditPass());
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
        if (StringUtils.isEmpty(editDTO.getOperatorCode())) {
            throw new DataException("30002", "操作人编码不能为空");
        }
        if (editDTO.getInLocation() == null) {
            throw new DataException("30003", "操作入库站点不能为空");
        }
        if (editDTO.getOutLocation() == null) {
            throw new DataException("30004", "操作出库站点不能为空");
        }
        //其他判断
    }

    /**
     * 审核通过or不通过参数检查
     *
     * @param dto
     * @throws DataException
     */
    private void checkAuditParam(AuditDeliveryBillDTO dto) throws DataException {
        if (dto == null) {
            throw new DataException("30005", "审核条件为空");
        }
        if (StringUtils.isEmpty(dto.getBillCode())) {
            throw new DataException("30001", "单据编码不能为空");
        }
        if (StringUtils.isEmpty(dto.getAuditOperatorCode())) {
            throw new DataException("30002", "审核人编码不能为空");
        }
        if (StringUtils.isEmpty(dto.getAuditOpinion())) {
            throw new DataException("30007", "审核意见不能为空");
        }
    }

    /**
     * 提交前检查
     *
     * @param editDTO
     * @throws DataException
     */
    private void checkBeforeSubmit(DeliveryPickingEditDTO editDTO) throws DataException {
        if (editDTO.getBillDetails() == null) {
            throw new DataException("30005", "出库单明细为空，不能提交");
        }

    }


}
