package cn.sisyphe.coffee.bill.application.transmit;


import cn.sisyphe.coffee.bill.domain.transmit.IWayBillService;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.domain.transmit.WayBillDetail;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
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

/**
 * 运货单
 */
@Service
public class WayBillManager {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    private IWayBillService iWayBillService;

    /**
     * @param editWayBillDTO
     * @return
     */
    public EditWayBillDTO createWayBillWithDTO(EditWayBillDTO editWayBillDTO) throws DataException {

        //参数检查
        this.checkParams(editWayBillDTO);
        ///
        WayBill wayBill = convertDtoToWayBill(editWayBillDTO);

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
        wayBill.setPlanArrivalTime(editWayBillDTO.getPlanArrivalTime());//到达时间
        wayBill.setDeliveryTime(editWayBillDTO.getDeliveryTime());//发货时间
        wayBill.setTotalWeight(editWayBillDTO.getTotalWeight());//总总量
        wayBill.setAmountOfPackages(editWayBillDTO.getAmountOfPackages());//运货件数
        wayBill.setMemo(editWayBillDTO.getMemo());//备注

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

            wayBillDetail.setTotalCount(item.getTotalCount());//总品种
            wayBillDetail.setTotalAmount(item.getTotalAmount());// 总数量
            wayBillDetail.setPackageCode(item.getPackageNumbers());//包号

            wayBillDetail.setInStationCode(item.getInStationCode());//入库站点
            wayBillDetail.setOutStationCode(item.getOutStationCode());//出库站点
            wayBillDetail.setOutStorageTime(item.getOutStorageTime());//出库时间

            wayBillDetail.setOperatorName(item.getOperatorName());//添加人

            //如果没有打包方式
            if (StringUtils.isEmpty(item.getPackageType())) {
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
        if (StringUtils.isEmpty(editWayBillDTO.getWayBillCode())) {
            throw new DataException("", "单据号为空");
        }

    }


    /**
     * 创建运货单
     *
     * @param wayBill
     * @throws DataException
     */
    public void createWayBill(WayBill wayBill) throws DataException {


        iWayBillService.createWayBill(wayBill);
        //
        System.out.println(wayBill);


    }

}
