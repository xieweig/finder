package cn.sisyphe.coffee.bill.application.transmit;


import cn.sisyphe.coffee.bill.domain.transmit.IWayBillService;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.domain.transmit.WayBillDetail;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.waybill.*;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
     * 根据多条件查询运单单据信息
     *
     * @param conditionQueryWayBill
     * @return
     * @throws DataException
     */
    public QueryWayBillDTO findPageByCondition(ConditionQueryWayBill conditionQueryWayBill) throws DataException {
        // TODO: 2018/1/2
        //1 根据具体的运单号查询,只有唯一的显示，显示一条
        //2 根据配送出库查询可能会有多条
        //3 所有都是模糊匹配
        //所有的产品表中的数据
        Page<WayBill> wayBillPage = iWayBillService.findPageByCondition(conditionQueryWayBill);

        QueryWayBillDTO queryWayBillDTO = new QueryWayBillDTO();
        // 转换
        List<ReturnWayBillDTO> wayBillDTOList = convertToDTO(wayBillPage.getContent());
        queryWayBillDTO.setTotalNumber(wayBillPage.getTotalElements());//

        wayBillPage.getNumber();
        queryWayBillDTO.setContent(wayBillDTOList);

        return queryWayBillDTO;
    }


    /**
     * 修改运单
     *
     * @param editWayBillDTO
     * @return
     * @throws DataException
     */

    public void updateWayBillWithDTO(EditWayBillDTO editWayBillDTO) throws DataException {

        //参数检查
        this.checkParams(editWayBillDTO);
        //修改转DTO
        WayBill wayBill = convertUpdateDTOtoWayBill(editWayBillDTO);
        //
        this.updateWayBill(wayBill);
    }


    /**
     * 添加运单
     *
     * @param editWayBillDTO
     * @return
     */
    public EditWayBillDTO createWayBillWithDTO(EditWayBillDTO editWayBillDTO) throws DataException {

        //参数检查
        this.checkParams(editWayBillDTO);
        //
        WayBill wayBill = convertDtoToWayBill(editWayBillDTO);
        // // TODO: 2017/12/29
        if (StringUtils.isEmpty(wayBill.getBillCode())) {//
            //test
            wayBill.setBillCode(UUID.randomUUID().toString());
        }
        this.createWayBill(wayBill);
        //
        return editWayBillDTO;
    }

    private WayBill convertUpdateDTOtoWayBill(EditWayBillDTO editWayBillDTO) throws DataException {

        WayBill wayBill = new WayBill();
        //bill code
        wayBill.setBillCode(editWayBillDTO.getWayBillCode());

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
        Set<WayBillDetail> wayBillDetails = addBillItem(editWayBillDTO, wayBill);//

//        wayBill.setWayBillDetailSetAddOrUpdate(wayBillDetails);// update
        wayBill.setWayBillDetailSet(this.addBillItem(editWayBillDTO, wayBill));
        return wayBill;
    }


    /**
     * DTO转换
     *
     * @param editWayBillDTO
     * @return
     */

    private WayBill convertDtoToWayBill(EditWayBillDTO editWayBillDTO) throws DataException {
        WayBill wayBill = new WayBill();

        wayBill.setDestination(editWayBillDTO.getDestination());//目的地
        wayBill.setLogisticsCompanyName(editWayBillDTO.getLogisticsCompanyName());// 快递公司名称
        wayBill.setPlanArrivalTime(editWayBillDTO.getPlanArrivalTime());//到达时间
        wayBill.setDeliveryTime(editWayBillDTO.getDeliveryTime());//发货时间
        wayBill.setTotalWeight(editWayBillDTO.getTotalWeight());//总总量
        wayBill.setAmountOfPackages(editWayBillDTO.getAmountOfPackages());//运货件数
        wayBill.setMemo(editWayBillDTO.getMemo());//备注
        wayBill.setOperatorName(editWayBillDTO.getOperatorName());//录单人姓名
        wayBill.setOperatorCode(editWayBillDTO.getOperatorCode());//user code

        wayBill.setOutStationCode(editWayBillDTO.getOutStationCode());//出库站点code
        //添加明细
        wayBill.setWayBillDetailSet(this.addBillItem(editWayBillDTO, wayBill));
        return wayBill;
    }

    /**
     * 拼接明细
     *
     * @param editWayBillDTO
     * @return
     */
    private Set<WayBillDetail> addBillItem(EditWayBillDTO editWayBillDTO, WayBill wayBill) {

        Set<WayBillDetail> billDetails = new HashSet<WayBillDetail>();
        //取DTO
        List<EditWayBillDetailDTO> editWayBillDetailDTOList = editWayBillDTO.getEditWayBillDetailDTOList();

        if (editWayBillDetailDTOList == null || editWayBillDetailDTOList.isEmpty()) {
            return billDetails;
        }
        for (EditWayBillDetailDTO item : editWayBillDetailDTOList) {
            //2运单明细
            WayBillDetail wayBillDetail = new WayBillDetail();

            wayBillDetail.setWayBill(wayBill);

            wayBillDetail.setSourceCode(item.getOutStorageBillCode());//出库单号
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
     * 修改方法
     *
     * @param wayBill
     * @throws DataException
     */
    public void updateWayBill(WayBill wayBill) throws DataException {

        WayBill wayBillTemp = iWayBillService.updateBill(wayBill);
        applicationEventPublisher.publishEvent(wayBillTemp);

    }

    /**
     * 创建运货单
     *
     * @param wayBill
     * @throws DataException
     */
    public void createWayBill(WayBill wayBill) throws DataException {

        iWayBillService.createBill(wayBill);

        applicationEventPublisher.publishEvent(wayBill);

    }


    /**
     * 转换为DTO
     *
     * @param
     * @return
     */
    private List<ReturnWayBillDTO> convertToDTO(List<WayBill> wayBills) {
        //
        List<ReturnWayBillDTO> wayBillDTOList = new ArrayList<>();
        ReturnWayBillDTO temp = new ReturnWayBillDTO();
        for (WayBill wayBill : wayBills) {
            temp.setLogisticsCompanyName(wayBill.getLogisticsCompanyName());//公司名称
            temp.setWayBillCode(wayBill.getBillCode());//bill code
            temp.setOperatorName(wayBill.getOperatorName());//
            temp.setWayBillCode(wayBill.getOperatorCode());
            temp.setDeliveryTime(wayBill.getDeliveryTime());//发货时间
            temp.setCreateTime(wayBill.getCreateTime());//
            temp.setAmountOfPackages(wayBill.getAmountOfPackages());// 发货件数
            //temp.setWayBillStatus(wayBill.getReceivedStatus().name());//收货状态
            temp.setInStationCode(wayBill.getInStationCode());//入库站点
            temp.setOutStationCode(wayBill.getOutStationCode());//出库站点
            wayBillDTOList.add(temp);// 添加到集合
        }
        return wayBillDTOList;
    }


}
