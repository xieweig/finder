package cn.sisyphe.coffee.bill.application.transmit;


import cn.sisyphe.coffee.bill.application.delivery.DeliveryBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.domain.transmit.WayBillDetail;
import cn.sisyphe.coffee.bill.domain.transmit.WayBillService;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import cn.sisyphe.coffee.bill.domain.transmit.enums.ReceivedStatusEnum;
import cn.sisyphe.coffee.bill.viewmodel.waybill.*;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 运货单
 */
@Service
public class WayBillManager {

    //公共信息
    @Autowired
    private SharedManager sharedManager;

    /**
     * 退库管理
     */
    @Autowired
    private RestockBillManager restockBillManager;

    /**
     * 运单管理
     */
    @Autowired
    private DeliveryBillManager deliveryBillManager;
    //事件
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    //运单服务类
    @Autowired
    private WayBillService iWayBillService;

    /**
     * 通过单据code 汇总包号，总数量，品种
     *
     * @param billCode
     * @return
     */
    public ScanFillBillDTO scanQueryBill(String billCode) {

//        现在在"运单跟踪"的出库单测试时使用以下临时规则：
//        单据类型+站点+时间+进程id+6位流水编码
//        配送出库单 ：PSCK+CKG001+20180110+P10+6位流水 -> PSCKCKG00120180110P10000001
//        调剂出库单 ：TJCK+CKG001+20180110+P10+6位流水  -> TJCKCKG00120180110P10000001
//        退库出库单 ：TKCK+CKG001+20180110+P10+6位流水 -> TKCKCKG00120180110P10000001
//        退货出库单 ：THCK+CKG001+20180110+P10+6位流水 -> THCKCKG00120180110P10000001

        // TODO: 2018/1/11  匹配新的规则
        if (StringUtils.isEmpty(billCode)) {
            return null;
        }
        //配送出库
        if (billCode.toUpperCase().startsWith("PSCK")) {

            return deliveryBillManager.scanQueryBill(billCode);
        }
        //调剂出库单
        if (billCode.toUpperCase().startsWith("TJCK")) {
            //
        }
        //退库出库单
        if (billCode.toUpperCase().startsWith("TKCK")) {
            //退库出库单
            return restockBillManager.findPackageInfoByBillCode(billCode);
        }
        //退货出库单
        if (billCode.toUpperCase().startsWith("THCK")) {
            //
        }
        return null;
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
        //code
        if (StringUtils.isEmpty(wayBill.getBillCode())) {//
            //
            wayBill.setBillCode(this.keyProducer("YD", editWayBillDTO));
        }
        //
        wayBill.setReceivedStatus(ReceivedStatusEnum.IS_NOT_RECEIVED);// 收货状态
        this.createWayBill(wayBill);
        //
        return editWayBillDTO;
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
        //
        queryWayBillDTO.setTotalNumber(wayBillPage.getTotalElements());//
        queryWayBillDTO.setContent(wayBillDTOList);
        return queryWayBillDTO;
    }


    /**
     * 确认收货
     *
     * @param billCode
     */
    public void confirmReceiptBill(String billCode) {

        // 修改运单状态
        iWayBillService.confirmReceiptBill(billCode);
    }

    /**
     * 条件查找单个运单
     *
     * @param billCode
     * @return
     */
    public EditWayBillDTO findOneWayBill(String billCode) {

        WayBill wayBill = null;
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("50001", "参数为空");
        }
        wayBill = iWayBillService.findOneBillByCode(billCode);
        //
        //转换DTO
        return billConvertTOEditWayBillDTO(wayBill);

    }

    /**
     * 单个查询的DTO转换
     *
     * @param wayBill
     * @return
     */
    private EditWayBillDTO billConvertTOEditWayBillDTO(WayBill wayBill) {
        if (wayBill == null) {
            return null;
        }

        EditWayBillDTO editWayBillDTO = new EditWayBillDTO();

        editWayBillDTO.setBillId(wayBill.getBillId());//id
        //bill code
        editWayBillDTO.setWayBillCode(wayBill.getBillCode());
        //物流公司名称
        editWayBillDTO.setLogisticsCompanyName(wayBill.getLogisticsCompanyName());

        editWayBillDTO.setInStationCode(wayBill.getInStationCode());
        editWayBillDTO.setInStationName(wayBill.getInStationName());// 入库站点名称

        editWayBillDTO.setOutStationCode(wayBill.getOutStationCode());
        editWayBillDTO.setOutStationName(wayBill.getOutStationName());// 出库站点名称

        editWayBillDTO.setDeliveryTime(wayBill.getDeliveryTime());//
        //计划到达时间
        editWayBillDTO.setPlanArrivalTime(wayBill.getPlanArrivalTime());
        //录单人code
        editWayBillDTO.setOperatorCode(wayBill.getOperatorCode());
        //录单人name
        String userName = this.findUserNameByCode(wayBill.getOperatorCode());
        //录单人name
        editWayBillDTO.setOperatorName((userName == null) ? "" : userName);

        //总重量
        editWayBillDTO.setTotalWeight(wayBill.getTotalWeight());
        //TODO: 2018/1/2 运货件数验证（手动填写+自动提取）
        // 如果自动提取没有提取到就手动填写
        editWayBillDTO.setAmountOfPackages(wayBill.getAmountOfPackages());//运货件数
        editWayBillDTO.setDestination(wayBill.getDestination());//目的地
        //
        editWayBillDTO.setMemo(wayBill.getMemo());// 备注
        //设置明细
        List<EditWayBillDetailDTO> editWayBillDetailDTOList = new ArrayList<>();
        for (WayBillDetail wayBillDetail : wayBill.getWayBillDetailSet()) {
            EditWayBillDetailDTO wayBillDetailDTO = new EditWayBillDetailDTO();

            //出库时间
            wayBillDetailDTO.setOutStorageTime(wayBillDetail.getOutStorageTime());
            // 配送单号
            wayBillDetailDTO.setOutStorageBillCode(wayBillDetail.getSourceCode());
            // id
            wayBillDetailDTO.setBillDetailId(wayBillDetail.getBillDetailId());
            // 数量
            wayBillDetailDTO.setTotalAmount(wayBillDetail.getTotalAmount());
            // 品种
            wayBillDetailDTO.setTotalCount(wayBillDetail.getTotalCount());
            //入库站点code
            wayBillDetailDTO.setInStationCode(wayBillDetail.getInStationCode());
            //出库站点code
            wayBillDetailDTO.setOutStationCode(wayBillDetail.getOutStationCode());
            //出入库站点名
            Station inStation = this.findStationByCode(wayBillDetail.getInStationCode());
            if (inStation != null) {
                wayBillDetailDTO.setInStationName(inStation.getStationName());
            }
            Station outStation = this.findStationByCode(wayBillDetail.getOutStationCode());
            if (outStation != null) {
                wayBillDetailDTO.setOutStationName(outStation.getStationName());
            }
            //明细的录单人,有可能没有code
            wayBillDetailDTO.setOperatorName(wayBillDetail.getOperatorName());

            if (wayBillDetail.getPackAgeTypeEnum() != null) {
                //
                wayBillDetailDTO.setPackageType(wayBillDetail.getPackAgeTypeEnum().name());//打包类型
            }
            //包号
            wayBillDetailDTO.setPackageNumbers(wayBillDetail.getPackageCode());
            wayBillDetailDTO.setSinglePacking(wayBillDetail.getSinglePacking());//是否单独打包
            editWayBillDetailDTOList.add(wayBillDetailDTO);// 设置dto
        }
        //
        editWayBillDTO.setEditWayBillDetailDTOList(editWayBillDetailDTOList);
        //
        return editWayBillDTO;
    }


    /**
     * 修改的DTO
     *
     * @param editWayBillDTO
     * @return
     * @throws DataException
     */
    private WayBill convertUpdateDTOtoWayBill(EditWayBillDTO editWayBillDTO) throws DataException {

        WayBill wayBill = new WayBill();
        //
        wayBill.setBillCode(editWayBillDTO.getWayBillCode());

        wayBill.setDestination(editWayBillDTO.getDestination());//目的地
        wayBill.setLogisticsCompanyName(editWayBillDTO.getLogisticsCompanyName());// 快递公司名称
        wayBill.setPlanArrivalTime(editWayBillDTO.getPlanArrivalTime());//到达时间
        wayBill.setDeliveryTime(editWayBillDTO.getDeliveryTime());//发货时间
        wayBill.setTotalWeight(editWayBillDTO.getTotalWeight());//总总量
        wayBill.setAmountOfPackages(editWayBillDTO.getAmountOfPackages());//运货件数
        wayBill.setMemo(editWayBillDTO.getMemo());//备注
        wayBill.setOperatorCode(editWayBillDTO.getOperatorCode());//user code

        String userName = this.findUserNameByCode(editWayBillDTO.getOperatorCode());
        wayBill.setOperatorName(userName);//录单人姓名

        wayBill.setInStationCode(editWayBillDTO.getInStationCode());//
        wayBill.setOutStationCode(editWayBillDTO.getOutStationCode());
        //出入库站点名

        Station inStation = this.findStationByCode(editWayBillDTO.getInStationCode());
        if (inStation != null) {
            wayBill.setInStationName(inStation.getStationName());
        }
        Station outStation = this.findStationByCode(editWayBillDTO.getOutStationCode());
        if (outStation != null) {
            wayBill.setOutStationName(outStation.getStationName());
        }
        //
        //添加明细
        wayBill.setWayBillDetailSet(this.addBillItem(editWayBillDTO));
        //
        return wayBill;
    }


    /**
     * 更具站点code查询站点信息
     *
     * @param stationCode
     * @return
     */
    private Station findStationByCode(String stationCode) {
        if (!StringUtils.isEmpty(stationCode)) {

            return sharedManager.findStationByStationCode(stationCode);
        }
        return new Station(stationCode);
    }

    /**
     * DTO转换
     *
     * @param editWayBillDTO
     * @return
     */

    private WayBill convertDtoToWayBill(EditWayBillDTO editWayBillDTO) throws DataException {
        WayBill wayBill = new WayBill();

        wayBill.setDestination(editWayBillDTO.getInStationCode());//目的地
        // wayBill.setDestination(editWayBillDTO.getDestination());//目的地
        wayBill.setLogisticsCompanyName(editWayBillDTO.getLogisticsCompanyName());// 快递公司名称
        wayBill.setPlanArrivalTime(editWayBillDTO.getPlanArrivalTime());//到达时间
        wayBill.setDeliveryTime(editWayBillDTO.getDeliveryTime());//发货时间
        wayBill.setTotalWeight(editWayBillDTO.getTotalWeight());//总总量
        wayBill.setAmountOfPackages(editWayBillDTO.getAmountOfPackages());//运货件数
        //
        wayBill.setOperatorCode(editWayBillDTO.getOperatorCode());//user code
        //
        String userName = this.findUserNameByCode(editWayBillDTO.getOperatorCode());
        wayBill.setOperatorName(userName);//录单人姓名

        wayBill.setReceivedStatus(ReceivedStatusEnum.IS_NOT_RECEIVED);//未收货
        wayBill.setInStationCode(editWayBillDTO.getInStationCode());//入库站点code
        wayBill.setOutStationCode(editWayBillDTO.getOutStationCode());//出库站点code
        //出入库站点名称
        Station inStation = this.findStationByCode(editWayBillDTO.getInStationCode());
        if (inStation != null) {
            wayBill.setInStationName(inStation.getStationName());
        }
        Station outStation = this.findStationByCode(editWayBillDTO.getOutStationCode());
        if (outStation != null) {
            wayBill.setOutStationName(outStation.getStationName());
        }
        wayBill.setMemo(editWayBillDTO.getMemo());//备注
        //添加明细
        wayBill.setWayBillDetailSet(this.addBillItem(editWayBillDTO));
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
            //
            //如果是供应商没有出库时间
            if (StringUtils.isEmpty(item.getOutStorageTime())) {
                throw new DataException("40006", "出库时间不能为空");
            }
            WayBillDetail wayBillDetail = new WayBillDetail();

            // TODO: 2018/1/8
            wayBillDetail.setSourceCode(item.getOutStorageBillCode());//出库单号
            wayBillDetail.setTotalCount(item.getTotalCount());//总品种
            wayBillDetail.setTotalAmount(item.getTotalAmount());// 总数量
            wayBillDetail.setPackageCode(item.getPackageNumbers());// 多个包号
            //
            wayBillDetail.setInStationCode(item.getInStationCode());//入库站点
            wayBillDetail.setOutStationCode(item.getOutStationCode());//出库站点
            wayBillDetail.setOutStorageTime(item.getOutStorageTime());//出库时间
            //
            String userName = this.findUserNameByCode(item.getOperatorCode());
            if (!StringUtils.isEmpty(userName)) {
                wayBillDetail.setOperatorName(userName);//来源单录单人
            }
            //供应商录单人手动输入的，系统没有
            if (!StringUtils.isEmpty(item.getOperatorName())) {
                wayBillDetail.setOperatorName(item.getOperatorName());//来源单录单人
            }
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
            throw new DataException("40001", "参数为空");
        }
        if (StringUtils.isEmpty(editWayBillDTO.getLogisticsCompanyName())) {
            throw new DataException("40002", "物流公司名称不能为空");
        }
//        if (editWayBillDTO.getDeliveryTime() == null) {
//            throw new DataException("40003", "发货时间不能为空");
//        }
        if (editWayBillDTO.getOutStationCode() == null) {
            throw new DataException("40004", "出库站点不能为空");
        }
        if (editWayBillDTO.getInStationCode() == null) {
            throw new DataException("40005", "入库站点不能为空");
        }
        if (editWayBillDTO.getAmountOfPackages() == null
                || editWayBillDTO.getAmountOfPackages() == 0) {
            throw new DataException("40006", "运货数量必须大于0");
        }
        if (StringUtils.isEmpty(editWayBillDTO.getOperatorCode())) {
            throw new DataException("40007", "操作人编码不能为空");
        }

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

        for (WayBill wayBill : wayBills) {
            ReturnWayBillDTO temp = new ReturnWayBillDTO();

            temp.setLogisticsCompanyName(wayBill.getLogisticsCompanyName());//公司名称

            temp.setWayBillCode(wayBill.getBillCode());//bill code

            //根据code 查询用户名称;
            String userName = this.findUserNameByCode(wayBill.getOperatorCode());
            // temp.setOperatorName(wayBill.getOperatorName());// 操作人姓名
            if (!StringUtils.isEmpty(userName)) {
                temp.setOperatorName(userName);// 操作人姓名
            } else {
                temp.setOperatorName("");
            }
            temp.setDeliveryTime(wayBill.getDeliveryTime());//发货时间
            temp.setCreateTime(wayBill.getCreateTime());//
            temp.setAmountOfPackages(wayBill.getAmountOfPackages());// 发货件数
            //
            temp.setReceivedStatus(wayBill.getReceivedStatus());//收货状态
            temp.setInStationCode(wayBill.getInStationCode());//入库站点
            temp.setOutStationCode(wayBill.getOutStationCode());//出库站点
            temp.setInStationName(wayBill.getInStationName());//入库站点名称
            temp.setOutStationName(wayBill.getOutStationName());// 出库站点名称
            //
            wayBillDTOList.add(temp);// 添加到集合
        }
        return wayBillDTOList;
    }


    /**
     * 通过用户code 查找用户姓名
     *
     * @param userCode
     * @return
     */
    private String findUserNameByCode(String userCode) {


        return sharedManager.findOneByUserCode(userCode);

    }

    /**
     * 临时单号
     *
     * @param prefix
     * @param dto
     * @return
     */
    private String keyProducer(String prefix, EditWayBillDTO dto) {
        String key = "";
        //  单据类型+站点+时间+进程id+6位流水编码
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);

        String name = ManagementFactory.getRuntimeMXBean().getName();
        //System.out.println(name);
        // get pid
        String pid = name.split("@")[0];

        //测试临时使用
        Random random = new Random();
        //配送单号
        String toStationCode = dto.getOutStationCode();///
        //目的站的code
        key = prefix + toStationCode + pid + dateString + random.nextInt(1000);
        return key;
    }

}
