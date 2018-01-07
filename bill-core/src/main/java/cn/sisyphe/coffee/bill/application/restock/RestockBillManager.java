package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillQueryService;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillService;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillQueryService;
import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.QueryOneRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
@Service
public class RestockBillManager extends AbstractBillManager<RestockBill> {

    @Autowired
    private RestockBillRepository restockBillRepository;
    @Autowired
    private PlanBillRepository planBillRepository;

    public RestockBillManager(BillRepository<RestockBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    @Autowired
    private RestockBillQueryService restockBillQueryService;
    @Autowired
    private PlanBillQueryService planBillQueryService;
    
    
    /**
     * 保存进货单
     *
     * @param addRestockBillDTO
     */
    @Transactional
    public void saveBill(AddRestockBillDTO addRestockBillDTO) {
        verification(addRestockBillDTO);
        // 转换单据
        RestockBill restockBill = dtoToMapRestockBill(addRestockBillDTO);
        //若是计划转则保存PLANBILL的from
        if (addRestockBillDTO.getBillProperty()!= PropertyEnum.NOPLAN){
            System.err.print("按计划计划");
           PlanBill planBill = planBillRepository.findOneByBillCode(addRestockBillDTO.getFromBillCode());
           planBill.setReceiveBillCode(addRestockBillDTO.getBillCode());
           planBillRepository.save(planBill);
        }


        // 保存单据
        save(restockBill);
    }

    /**
     * 提交进货单
     *
     * @param addRestockBillDTO
     */
    public void submitBill(AddRestockBillDTO addRestockBillDTO) {
        // 转换单据
        RestockBill restockBill = dtoToMapRestockBill(addRestockBillDTO);

        submit(restockBill);
    }

    /**
     * 修改退库单--保存
     *
     * @param billDTO
     */
    public void updateBillToSave(AddRestockBillDTO billDTO) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(billDTO.getBillCode());
        restockBill.getBillDetails().clear();
        // 转换单据
        RestockBill mapBillAfter = dtoToMapRestockBillForEdit(billDTO, restockBill);

        save(mapBillAfter);
    }

    /**
     * 修改退库单--提交审核
     *
     * @param billDTO
     */
    public void updateBillToSubmit(AddRestockBillDTO billDTO) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(billDTO.getBillCode());
        restockBill.getBillDetails().clear();
        // 转换单据
        RestockBill mapBillAfter = dtoToMapRestockBillForEdit(billDTO, restockBill);

        submit(mapBillAfter);
    }
    /**
     * 打开退库单
     *
     * @param restockBillCode
     */
    public QueryOneRestockBillDTO openBill(String restockBillCode) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(restockBillCode);
        // 如果单据是打开状态或者是审核失败状态，则直接返回转换后的退库单据信息
        // 如果单据是提交状态，则进行打开动作
        if (restockBill.getBillState().equals(BillStateEnum.SUBMITTED)) {
            // 打开单据
            restockBill = open(restockBill);
            return mapOneToDTO(restockBill);
        } else {
            return mapOneToDTO(restockBill);
        }
    }

    /**
     * 审核退库单
     *
     * @param restockBillCode
     */
    public void auditBill(String restockBillCode, String auditPersonCode, boolean isSuccess) {
        RestockBill restockBill = restockBillQueryService.findByBillCode(restockBillCode);
        // 设置审核人编码
        restockBill.setAuditPersonCode(auditPersonCode);

        audit(restockBill, isSuccess);
    }

    /**
     * 单据出入库完成
     *
     * @param responseResult
     */
    public void doneBill(ResponseResult responseResult) {
        Map<String, Object> resultMap = responseResult.getResult();
        // 转换出单据信息
        RestockBill bill = responseResult.toClassObject(resultMap.get("bill"), RestockBill.class);
        // 根据单据编码查询数据库单据信息
        RestockBill restockBill = restockBillQueryService.findByBillCode(bill.getBillCode());
        // 设置入库时间
        restockBill.setInWareHouseTime(new Date());
        // 处理完成
        done(bill);
    }
    /**
     * 根据多条件查询退库单据信息
     *
     * @param conditionQueryRestockBill 查询条件
     * @return
     */
    public QueryRestockBillDTO findByConditions(ConditionQueryRestockBill conditionQueryRestockBill) {
        Page<RestockBill> restockBillPage = restockBillQueryService.findPageByCondition(conditionQueryRestockBill);

        QueryRestockBillDTO QueryRestockBillDTO = new QueryRestockBillDTO();
        // 转换
        List<RestockBillDTO> billDTOList = toMapDTO(restockBillPage.getContent());
        // 总数
        QueryRestockBillDTO.setTotalNumber(restockBillPage.getTotalElements());
        // 退库单据数据
        QueryRestockBillDTO.setContent(billDTOList);

        return QueryRestockBillDTO;
    }

    /**
     * 保存和提交操作需要用到的DTO转换
     *
     * @param addRestockBillDTO 前端传递的DTO参数信息
     * @return
     */
    private RestockBill dtoToMapRestockBill(AddRestockBillDTO addRestockBillDTO) {
        //通过工厂方法生成具体种类的单据
        BillFactory billFactory = new BillFactory();
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        // 设置单据的作用
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        // 设置单据类型
      //  restockBill.setBillType(BillTypeEnum.RESTOCK);
        //设置单据属性
        restockBill.setBillProperty(addRestockBillDTO.getBillProperty());
        // 单据编码生成器
        // TODO: 2017/12/29 单号生成器还没有实现
        //测试使用
        Random random = new Random();
        restockBill.setBillCode(random.nextInt(10000)+"0302");
        // 来源单号
        if (StringUtils.isEmpty(addRestockBillDTO.getFromBillCode())) {
            restockBill.setFromBillCode(addRestockBillDTO.getFromBillCode());
        }
        // 计划备注
        if (!StringUtils.isEmpty(addRestockBillDTO.getPlanMemo())) {
            restockBill.setPlanMemo(addRestockBillDTO.getPlanMemo());
        }
        // 出库备注
        if (!StringUtils.isEmpty(addRestockBillDTO.getOutMemo())) {
            restockBill.setOutMemo(addRestockBillDTO.getOutMemo());
        }
        // 操作人代码
        restockBill.setOperatorCode(addRestockBillDTO.getOperatorCode());
        // 归属站点
        /// TODO: 2018/1/6 前端没有数据

        restockBill.setInLocation(addRestockBillDTO.getInStation());
        restockBill.setOutLocation(addRestockBillDTO.getOutStation());
       /* restockBill.setBelongStationCode(addRestockBillDTO.getInStation().getStationCode());
        // 获取站点
        Station station = addRestockBillDTO.getInStation();
        // 获取库房
        Storage storage = addRestockBillDTO.getInStorage();
        // 组合站点和库房
        station.setStorage(storage);
        // 设置入库位置
        restockBill.setInLocation(station);
        // 设置出库位置
        storage = addRestockBillDTO.getOutStorage();
        station = addRestockBillDTO.getOutStation();
        station.setStorage(storage);
        restockBill.setOutLocation(station);
*/
        Set<RestockBillDetailDTO> detailDTOSet = addRestockBillDTO.getBillDetails();
        //退货数量
        int amount = 0;
        for (RestockBillDetailDTO detailDTO :
                detailDTOSet) {
            amount += detailDTO.getActualAmount();
        }
        restockBill.setAmount(amount);
        //退货品种数
        int variety = detailDTOSet.size();
        restockBill.setVariety(variety);
        //配送总价
        restockBill.setTotalPrice(addRestockBillDTO.getTotalPrice());
        //按货物还是按原料
        restockBill.setBasicEnum(addRestockBillDTO.getBasicEnum());
        // 转换单据明细信息
        Set<RestockBillDetail> detailSet = listDetailMapToSetDetail(detailDTOSet);
        // 设置单据明细信息
        restockBill.setBillDetails(detailSet);

        return restockBill;
    }

    /**
     * 保存、提交和修改操作需要用到的DTO转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    private Set<RestockBillDetail> listDetailMapToSetDetail(Set<RestockBillDetailDTO> billDetails) {

        Set<RestockBillDetail> detailSet = new HashSet<>();
        for (RestockBillDetailDTO detail : billDetails) {
            RestockBillDetail restockBillDetail = new RestockBillDetail();
            // 设置货物和原料信息
            RawMaterial rawMaterial = detail.getRawMaterial();
            restockBillDetail.setGoods(rawMaterial);
            //备注
            restockBillDetail.setMemo(detail.getMemo());
            //应捡数量
            restockBillDetail.setShippedAmount(detail.getShippedAmount());
            //实拣数量
            restockBillDetail.setActualAmount(detail.getActualAmount());

            detailSet.add(restockBillDetail);
        }
        return detailSet;
    }

    private QueryOneRestockBillDTO mapOneToDTO(RestockBill restockBill) {
        QueryOneRestockBillDTO billDTO = new QueryOneRestockBillDTO();
        // 备注
//        Station station = (Station) restockBill.getInLocation();
     /*   // 库位名称
        billDTO.setInStorageCode(station.getStorage().getStorageCode());

        station = (Station) restockBill.getOutLocation();
        // 库位名称
        billDTO.setInStorageCode(station.getStorage().getStorageCode());
*/
        // 转换进货单明细信息
        List<RestockBillDetailDTO> detailDTOList = setDetailMapToListMapDetail(restockBill.getBillDetails());

        // 进货单明细信息
        billDTO.setBillDetails(detailDTOList);
        System.err.println("查询到的进货单据信息：" + billDTO.toString());
        return billDTO;
    }

    /**
     * 前端查询单个单据明细信息将set转换为dto
     *
     * @param restockBillDetails
     * @return
     */
    private List<RestockBillDetailDTO> setDetailMapToListMapDetail(Set<RestockBillDetail> restockBillDetails) {

        List<RestockBillDetailDTO> detailDTOList = new ArrayList<>();
        for (RestockBillDetail detail : restockBillDetails) {
            RestockBillDetailDTO detailDTO = new RestockBillDetailDTO();
            // 设置货物和原料信息
            RawMaterial rawMaterial = (RawMaterial) detail.getGoods();
           /* detailDTO.setRawMaterial(rawMaterial);
            //设置数量
            detailDTO.setAmount(detail.getAmount());
            //备注
            detailDTO.se
*/
            detailDTOList.add(detailDTO);
        }
        return detailDTOList;
    }
    /**
     * 修改需要转换DTO
     *
     * @param editRestockBillDTO
     * @return
     */
    private RestockBill dtoToMapRestockBillForEdit(AddRestockBillDTO editRestockBillDTO, RestockBill restockBill) {

        // 备注
        // 操作人代码
        restockBill.setOperatorCode(editRestockBillDTO.getOperatorCode());
        // 归属站点
        restockBill.setBelongStationCode(editRestockBillDTO.getOutStation().getStationCode());
//        // 获取站点
//        Station station = editRestockBillDTO.getInStation();
//        // 获取库房
//        Storage storage = editRestockBillDTO.getOutStorage();
//        // 组合站点和库房
//        station.setStorage(storage);
//        //出库站点和库房
//        restockBill.setInLocation(station);
//
//        // 设置入库站点和库房
//        station = editRestockBillDTO.getInStation();
//        storage = editRestockBillDTO.getOutStorage();
//        station.setStorage(storage);
//        restockBill.setInLocation(station);
//
//        station = editRestockBillDTO.getOutStation();
//
//        restockBill.setOutLocation(station);
        // 获取站点 上面注释的下面4行先替代
        Station inStation = editRestockBillDTO.getInStation();

        restockBill.setInLocation(inStation);

        // 设置入库站点和库房
        Station outStation = editRestockBillDTO.getInStation();

        restockBill.setInLocation(outStation);

        // 转换单据明细信息
        Set<RestockBillDetail> detailSet = listDetailMapToSetDetail(editRestockBillDTO.getBillDetails());
        // 设置单据明细信息
        restockBill.setBillDetails(detailSet);

        return restockBill;
    }

    private List<RestockBillDTO> toMapDTO(List<RestockBill> restockBillList) {
        List<RestockBillDTO> restockBillDTOList = new ArrayList<>();
        for (RestockBill restockBill : restockBillList) {
            RestockBillDTO restockBillDTO = new RestockBillDTO();
            // 退库单号-主表
            restockBillDTO.setBillCode(restockBill.getBillCode());
            // 入库时间-主表
            restockBillDTO.setInWareHouseTime(restockBill.getInWareHouseTime());
            // 录单时间-主表
            restockBillDTO.setCreateTime(restockBill.getCreateTime());
            // 录单人-主表
            restockBillDTO.setOperatorCode(restockBill.getOperatorCode());
            // 审核人-主表
           /* restockBillDTO.setAuditPersonCode(restockBill.getAuditPersonCode());
            // 入库站点-主表
            Station station = (Station) restockBill.getInLocation();
            restockBillDTO.setInStationCode(station.getStationCode());
            // 入库库房-主表
            restockBillDTO.setInStorageCode(station.getStorage().getStorageCode());*/
            // 单据状态--主表
            toMapTwoState(restockBillDTO, restockBill.getBillState().name());
            // 备注--主表

            // 单据提交状态--主表
            if (restockBill.getSubmitState() != null) {
                restockBillDTO.setSubmitState(restockBill.getSubmitState().name());
            } else {
                restockBillDTO.setSubmitState("-");
            }
            // 单据审核状态--主表
            if (restockBill.getAuditState() != null) {
                restockBillDTO.setAuditState(restockBill.getAuditState().name());
            } else {
                restockBillDTO.setAuditState("-");
            }

            // 循环遍历明细信息，累加得到数据
            Set<RestockBillDetail> restockBillDetailSet = restockBill.getBillDetails();
            // 实收数量总计
            Integer amount = 0;
            // 数量差值总计
            Integer differenceNumber = 0;
            // 退库总价
            BigDecimal inTotalPrice = BigDecimal.ZERO;
            // 差价总和
            BigDecimal differencePrice = BigDecimal.ZERO;
           /* for (RestockBillDetail restockBillDetail : restockBillDetailSet) {
                // 累加实收数量
                amount += restockBillDetail.getAmount();
                // 累加数量差值
                differenceNumber += restockBillDetail.getDifferenceNumber();
                // 累加退库总价
                inTotalPrice = inTotalPrice.add(restockBillDetail.getUnitPrice().multiply(new BigDecimal(restockBillDetail.getAmount())));
                // 累加差价总和
                differencePrice = differencePrice.add(restockBillDetail.getDifferencePrice());
            }*/
            // 实收数量--明细表
            restockBillDTO.setAmount(amount);
            // 数量差值--明细表
            restockBillDTO.setDifferenceNumber(differenceNumber);
            // 总价差值--明细表
            restockBillDTO.setDifferencePrice(differencePrice);
            // 退库实洋
            restockBillDTO.setInTotalPrice(inTotalPrice);

            restockBillDTOList.add(restockBillDTO);
        }
        return restockBillDTOList;
    }

    /**
     * map状态
     *
     * @param billDTO
     * @param stateName
     * @return
     */
    private RestockBillDTO toMapTwoState(RestockBillDTO billDTO, String stateName) {

        switch (stateName) {

            case "SAVED":
                billDTO.setSubmitState("未提交");
                billDTO.setAuditState("未审核");
                break;
            case "SUBMITTED":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("未审核");
                break;
            case "OPEN":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("未审核");
                break;
            case "AUDIT_FAILURE":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核不通过");
                break;
            case "AUDIT_SUCCESS":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            case "OUT_STORAGING":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            case "IN_STORAGING":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            case "DONE":
                billDTO.setSubmitState("已提交");
                billDTO.setAuditState("审核通过");
                break;
            default:
                break;

        }

        return billDTO;
    }

    /**
     * 验证提交数据信息
     *
     * @param addRestockBillDTO
     */
    private void verification(AddRestockBillDTO addRestockBillDTO) {
        //来源单号
        if (addRestockBillDTO.getBillProperty() != PropertyEnum.NOPLAN) {
            if (StringUtils.isEmpty(addRestockBillDTO.getFromBillCode())) {
                throw new DataException("500", "来源单号为空");
            }
        }
        if (addRestockBillDTO.getBillProperty() == null) {
            throw new DataException("500", "单据属性为空");
        }
        if (addRestockBillDTO.getInStation() == null) {
            throw new DataException("500", "入库站点为空");
        }
        if (addRestockBillDTO.getOutStation() == null) {
            throw new DataException("500", "出库站点为空");
        }
        if (addRestockBillDTO.getBillDetails() == null) {
            throw new DataException("500", "货物详细为空");
        }
        if (StringUtils.isEmpty(addRestockBillDTO.getOperatorCode())) {
            throw new DataException("500", "操作人编码为空");
        }
        if (addRestockBillDTO.getOutMemo() == null) {
            addRestockBillDTO.setOutMemo("");
        }
        if (addRestockBillDTO.getPlanMemo() == null) {
            addRestockBillDTO.setPlanMemo("");
        }
        if (addRestockBillDTO.getBasicEnum() == null) {
            throw new DataException("500", "按货物按原料为空");
        }
        if (addRestockBillDTO.getTotalPrice() == null) {
            throw new DataException("500", "总价为空");
        }
    }
}
