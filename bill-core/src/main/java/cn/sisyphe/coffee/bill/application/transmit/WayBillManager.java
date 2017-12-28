package cn.sisyphe.coffee.bill.application.transmit;


import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.WayBillSaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.domain.transmit.WayBillDetail;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDetailDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 运货单
 */
@Service
public class WayBillManager {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    private WayBillRepository wayBillRepository;


    /**
     * @param editWayBillDTO
     * @return
     */
    public EditWayBillDTO createWayBillWithDTO(EditWayBillDTO editWayBillDTO) throws DataException {

        //参数检查
        this.checkParams(editWayBillDTO);
        ///
        WayBill wayBill = convertDtoToWayBill(editWayBillDTO);

        if (StringUtils.isEmpty(wayBill.getBillCode())) {//
            //test
            wayBill.setBillCode(UUID.randomUUID().toString());
        }
        this.createWayBill(wayBill);

        return editWayBillDTO;
    }

    /**
     * DTO转换
     *
     * @param editWayBillDTO
     * @return
     */

    private WayBill convertDtoToWayBill(EditWayBillDTO editWayBillDTO) throws DataException {
        WayBill wayBill = new WayBill();

        wayBill.setBillId(editWayBillDTO.getBillId());
        //
        wayBill.setBillCode(editWayBillDTO.getWayBillCode());
        //
        wayBill.setDestination(editWayBillDTO.getDestination());//目的地
        wayBill.setLogisticsCompanyName(editWayBillDTO.getLogisticsCompanyName());// 快递公司名称
        wayBill.setPlanArrivalTime(editWayBillDTO.getPlanArrivalTime());//到达时间
        wayBill.setDeliveryTime(editWayBillDTO.getDeliveryTime());//发货时间
        wayBill.setTotalWeight(editWayBillDTO.getTotalWeight());//总总量
        wayBill.setAmountOfPackages(editWayBillDTO.getAmountOfPackages());//运货件数
        wayBill.setMemo(editWayBillDTO.getMemo());//备注
        wayBill.setOperatorName(editWayBillDTO.getOperatorName());//录单人姓名
        wayBill.setOperatorCode(editWayBillDTO.getOperatorCode());//user code
        //添加明细
        wayBill.setBillDetails(addBillItem(editWayBillDTO));
        return wayBill;
    }

    /**
     * 拼接明细
     *
     * @param editWayBillDTO
     * @return
     */
    private Set<WayBillDetail> addBillItem(EditWayBillDTO editWayBillDTO) {

        Set<WayBillDetail> billDetails = new HashSet<WayBillDetail>();
        //取DTO
        List<EditWayBillDetailDTO> editWayBillDetailDTOList = editWayBillDTO.getEditWayBillDetailDTOList();

        if (editWayBillDetailDTOList == null || editWayBillDetailDTOList.isEmpty()) {
            return billDetails;
        }
        for (EditWayBillDetailDTO item : editWayBillDetailDTOList) {
            //2运单明细
            WayBillDetail wayBillDetail = new WayBillDetail();

            wayBillDetail.setOutStorageBillCode(item.getOutStorageBillCode());//出库单号

            wayBillDetail.setTotalCount(item.getTotalCount());//总品种
            wayBillDetail.setTotalAmount(item.getTotalAmount());// 总数量
            wayBillDetail.setPackageCode(item.getPackageNumbers());//包号

            wayBillDetail.setInStationCode(item.getInStationCode());//入库站点
            wayBillDetail.setOutStationCode(item.getOutStationCode());//出库站点
            wayBillDetail.setOutStorageTime(item.getOutStorageTime());//出库时间
            wayBillDetail.setOperatorName(item.getOperatorName());//添加人

            //如果没有打包方式
            if (!StringUtils.isEmpty(item.getPackageType())) {
                wayBillDetail.setPackAgeTypeEnum(PackAgeTypeEnum.DEFAULT);
            }
            billDetails.add(wayBillDetail);
        }
        return billDetails;
    }


    /**
     * @param editWayBillDTO
     * @throws DataException
     */
    private void checkParams(EditWayBillDTO editWayBillDTO) throws DataException {
        if (editWayBillDTO == null) {
            throw new DataException("", "参数为空");
        }
        if (StringUtils.isEmpty(editWayBillDTO.getLogisticsCompanyName())) {
            throw new DataException("", "物流公司名称不能为空");
        }
        if (editWayBillDTO.getDeliveryTime() == null) {
            throw new DataException("", "发货时间不能为空");
        }
        if (StringUtils.isEmpty(editWayBillDTO.getDestination())) {
            throw new DataException("", "目的地不能为空");
        }
        if (editWayBillDTO.getAmountOfPackages() == null || editWayBillDTO.getAmountOfPackages() == 0) {
            throw new DataException("", "运货数量必须大于0");
        }
    }


    /**
     * 创建运货单
     *
     * @param wayBill
     * @throws DataException
     */
    public void createWayBill(WayBill wayBill) throws DataException {

        //工厂
        new BillFactory().createBill(BillTypeEnum.TRANSMIT);
        // 单据用途
        wayBill.setBillPurpose(BillPurposeEnum.OutStorage);


        BillServiceFactory serviceFactory = new BillServiceFactory();
        AbstractBillService billService = serviceFactory.createBillService(wayBill);
        // 4.处理保存动作
        wayBill.setBillState(BillStateEnum.SAVED);// 单据状态
        wayBill.setBillState(BillStateEnum.DONE);// 单据状态

        // 自定义保存动作
        billService.dispose(new WayBillSaveBehavior());
        //状态

        // 5.设置数据仓库和保存单据
        billService.setBillRepository(wayBillRepository);
        billService.save();
        billService.afterDispose();///
        applicationEventPublisher.publishEvent(wayBill);

    }

}
