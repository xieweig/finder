package cn.sisyphe.coffee.bill.application.returned;


import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillDetail;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillDetail;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillQueryService;
import cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.*;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货单协调层
 */
@Service
public class ReturnedBillManager extends AbstractBillManager<ReturnedBill> {
    @Autowired
    private ReturnedBillQueryService returnedBillQueryService;


    @Autowired
    public ReturnedBillManager(BillRepository<ReturnedBill> returnedBill, ApplicationEventPublisher applicationEventPublisher) {
        super(returnedBill, applicationEventPublisher);
    }

    /**
     * 保存退货单
     *
     * @param addReturnedBillDTO
     */
    public void saveBill(AddReturnedBillDTO addReturnedBillDTO) {
        // 转换单据
        ReturnedBill returnedBill = dtoToMapReturnedBill(addReturnedBillDTO);

        // 保存单据
        save(returnedBill);
    }

    /**
     * 保存和提交操作需要用到的DTO转换
     *
     * @param addReturnedBillDTO 前端传递的DTO参数信息
     * @return
     */
    private ReturnedBill dtoToMapReturnedBill(AddReturnedBillDTO addReturnedBillDTO) {
        //通过工厂方法生成具体种类的单据
        BillFactory billFactory = new BillFactory();
        ReturnedBill returnedBill = (ReturnedBill) billFactory.createBill(BillTypeEnum.RETURNED);
        // 设置单据的作用
        returnedBill.setBillPurpose(BillPurposeEnum.OutStorage);
        // 设置单据类型
        // returnedBill.setBillType(BillTypeEnum.RETURNED);
        //设置单据属性
        returnedBill.setBillProperty(addReturnedBillDTO.getBillProperty());
        // 单据编码生成器
        // TODO: 2017/12/29 单号生成器还没有实现
        //测试使用
        Random random = new Random();
        returnedBill.setBillCode(random.nextInt(10000) + "0302");
        // 来源单号
        if (StringUtils.isEmpty(addReturnedBillDTO.getSourceCode())) {
            returnedBill.setSourceCode(addReturnedBillDTO.getSourceCode());
        }
        // 计划备注
        if (!StringUtils.isEmpty(addReturnedBillDTO.getPlanMemo())) {
            returnedBill.setPlanMemo(addReturnedBillDTO.getPlanMemo());
        }
        // 出库备注
        if (!StringUtils.isEmpty(addReturnedBillDTO.getOutMemo())) {
            returnedBill.setOutMemo(addReturnedBillDTO.getOutMemo());
        }
        // 操作人代码
        returnedBill.setOperatorCode(addReturnedBillDTO.getOperatorCode());
        // 归属站点
        /// TODO: 2018/1/6 前端没有数据

        returnedBill.setInLocation(addReturnedBillDTO.getInStation());
        returnedBill.setOutLocation(addReturnedBillDTO.getOutStation());
       /* returnedBill.setBelongStationCode(addReturnedBillDTO.getInStation().getStationCode());
        // 获取站点
        Station station = addReturnedBillDTO.getInStation();
        // 获取库房
        Storage storage = addReturnedBillDTO.getInStorage();
        // 组合站点和库房
        station.setStorage(storage);
        // 设置入库位置
        returnedBill.setInLocation(station);
        // 设置出库位置
        storage = addReturnedBillDTO.getOutStorage();
        station = addReturnedBillDTO.getOutStation();
        station.setStorage(storage);
        returnedBill.setOutLocation(station);
*/
        Set<ReturnedBillDetailDTO> detailDTOSet = addReturnedBillDTO.getBillDetails();
        //退货数量
        int amount = 0;
        for (ReturnedBillDetailDTO detailDTO :
                detailDTOSet) {
            amount += detailDTO.getActualAmount();
        }
        returnedBill.setAmount(amount);
        //退货品种数
        int variety = detailDTOSet.size();
        returnedBill.setVariety(variety);
        //配送总价
        returnedBill.setTotalPrice(addReturnedBillDTO.getTotalPrice());
        //按货物还是按原料
        returnedBill.setBasicEnum(addReturnedBillDTO.getBasicEnum());
        // 转换单据明细信息
        Set<ReturnedBillDetail> detailSet = listDetailMapToSetDetail(detailDTOSet);
        // 设置单据明细信息
        returnedBill.setBillDetails(detailSet);

        return returnedBill;
    }

    /**
     * 保存、提交和修改操作需要用到的DTO转换单据明细信息
     *
     * @param billDetails
     * @return
     */
    private Set<ReturnedBillDetail> listDetailMapToSetDetail(Set<ReturnedBillDetailDTO> billDetails) {

        Set<ReturnedBillDetail> detailSet = new HashSet<>();
        for (ReturnedBillDetailDTO detail : billDetails) {
            ReturnedBillDetail returnedBillDetail = new ReturnedBillDetail();
            // 设置货物和原料信息
            RawMaterial rawMaterial = detail.getRawMaterial();
            returnedBillDetail.setGoods(rawMaterial);
            //备注
            returnedBillDetail.setMemo(detail.getMemo());
            //应捡数量
            returnedBillDetail.setShippedAmount(detail.getShippedAmount());
            //实拣数量
            returnedBillDetail.setActualAmount(detail.getActualAmount());

            detailSet.add(returnedBillDetail);
        }
        return detailSet;
    }

    /**
     * 提交进货单
     *
     * @param addReturnedBillDTO
     */
    public void submitBill(AddReturnedBillDTO addReturnedBillDTO) {
        // 转换单据
        ReturnedBill returnedBill = dtoToMapReturnedBill(addReturnedBillDTO);

        submit(returnedBill);
    }

    /**
     * 打开退货单
     *
     * @param returnedBillCode
     */
    public QueryOneReturnedBillDTO openBill(String returnedBillCode) {
        ReturnedBill returnedBill = returnedBillQueryService.findByBillCode(returnedBillCode);
        // 如果单据是打开状态或者是审核失败状态，则直接返回转换后的退货单据信息
        if (returnedBill.getBillState().equals(BillStateEnum.OPEN)
                || returnedBill.getBillState().equals(BillStateEnum.AUDIT_FAILURE)) {
            return mapOneToDTO(returnedBill);
        }

        // 打开单据
        returnedBill = open(returnedBill);

        return mapOneToDTO(returnedBill);
    }

    private QueryOneReturnedBillDTO mapOneToDTO(ReturnedBill returnedBill) {
        QueryOneReturnedBillDTO billDTO = new QueryOneReturnedBillDTO();
        // 备注
//        Station station = (Station) returnedBill.getInLocation();
     /*   // 库位名称
        billDTO.setInStorageCode(station.getStorage().getStorageCode());

        station = (Station) returnedBill.getOutLocation();
        // 库位名称
        billDTO.setInStorageCode(station.getStorage().getStorageCode());
*/
        // 转换进货单明细信息
        List<ReturnedBillDetailDTO> detailDTOList = setDetailMapToListMapDetail(returnedBill.getBillDetails());

        // 进货单明细信息
        billDTO.setBillDetails(detailDTOList);
        System.err.println("查询到的进货单据信息：" + billDTO.toString());
        return billDTO;
    }

    /**
     * 前端查询单个单据明细信息将set转换为dto
     *
     * @param returnedBillDetails
     * @return
     */
    private List<ReturnedBillDetailDTO> setDetailMapToListMapDetail(Set<ReturnedBillDetail> returnedBillDetails) {

        List<ReturnedBillDetailDTO> detailDTOList = new ArrayList<>();
        for (ReturnedBillDetail detail : returnedBillDetails) {
            ReturnedBillDetailDTO detailDTO = new ReturnedBillDetailDTO();
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
     * 审核退货单
     *
     * @param returnedBillCode
     */
    public void auditBill(String returnedBillCode, String auditPersonCode, boolean isSuccess) {
        ReturnedBill returnedBill = returnedBillQueryService.findByBillCode(returnedBillCode);
        // 设置审核人编码
        returnedBill.setAuditPersonCode(auditPersonCode);

        audit(returnedBill, isSuccess);
    }

    /**
     * 修改退货单--保存
     *
     * @param billDTO
     */
    public void updateBillToSave(AddReturnedBillDTO billDTO) {
        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            throw new DataException("404", "单据编码为空");
        }
        // 验证属性
        verification(billDTO);
        ReturnedBill returnedBill = returnedBillQueryService.findByBillCode(billDTO.getBillCode());
        if (billDTO.getBillDetails() != null && billDTO.getBillDetails().size() > 0) {
            returnedBill.getBillDetails().clear();
        }
        // 转换单据
        ReturnedBill mapBillAfter = dtoToMapReturnedBillForEdit(billDTO, returnedBill);
        save(mapBillAfter);
    }

    /**
     * 修改退货单--提交审核
     *
     * @param billDTO
     */
    public void updateBillToSubmit(AddReturnedBillDTO billDTO) {
        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            throw new DataException("404", "单据编码为空");
        }
        // 验证属性
        verification(billDTO);
        ReturnedBill returnedBill = returnedBillQueryService.findByBillCode(billDTO.getBillCode());
        if (billDTO.getBillDetails() != null && billDTO.getBillDetails().size() > 0) {
            returnedBill.getBillDetails().clear();
        }
        // 转换单据
        ReturnedBill mapBillAfter = dtoToMapReturnedBillForEdit(billDTO, returnedBill);

        submit(mapBillAfter);
    }

    /**
     * 修改需要转换DTO
     *
     * @param editReturnedBillDTO
     * @return
     */
    private ReturnedBill dtoToMapReturnedBillForEdit(AddReturnedBillDTO editReturnedBillDTO, ReturnedBill returnedBill) {

        // 设置单据的作用
        returnedBill.setBillPurpose(BillPurposeEnum.OutStorage);
        // 设置单据类型
        returnedBill.setBillType(BillTypeEnum.RETURNED);
        //设置单据属性
        returnedBill.setBillProperty(editReturnedBillDTO.getBillProperty());

        // 来源单号
        if (StringUtils.isEmpty(editReturnedBillDTO.getSourceCode())) {
            returnedBill.setSourceCode(editReturnedBillDTO.getSourceCode());
        }
        // 计划备注
        if (!StringUtils.isEmpty(editReturnedBillDTO.getPlanMemo())) {
            returnedBill.setPlanMemo(editReturnedBillDTO.getPlanMemo());
        }
        // 出库备注
        if (!StringUtils.isEmpty(editReturnedBillDTO.getOutMemo())) {
            returnedBill.setOutMemo(editReturnedBillDTO.getOutMemo());
        }
        // 操作人代码
        returnedBill.setOperatorCode(editReturnedBillDTO.getOperatorCode());
        // 归属站点
        /// TODO: 2018/1/6 前端没有数据

        returnedBill.setInLocation(editReturnedBillDTO.getInStation());
        returnedBill.setOutLocation(editReturnedBillDTO.getOutStation());
       /* returnedBill.setBelongStationCode(addReturnedBillDTO.getInStation().getStationCode());
        // 获取站点
        Station station = addReturnedBillDTO.getInStation();
        // 获取库房
        Storage storage = addReturnedBillDTO.getInStorage();
        // 组合站点和库房
        station.setStorage(storage);
        // 设置入库位置
        returnedBill.setInLocation(station);
        // 设置出库位置
        storage = addReturnedBillDTO.getOutStorage();
        station = addReturnedBillDTO.getOutStation();
        station.setStorage(storage);
        returnedBill.setOutLocation(station);
*/
        Set<ReturnedBillDetailDTO> detailDTOSet = editReturnedBillDTO.getBillDetails();
        //退货数量
        int amount = 0;
        for (ReturnedBillDetailDTO detailDTO :
                detailDTOSet) {
            amount += detailDTO.getActualAmount();
        }
        returnedBill.setAmount(amount);
        //退货品种数
        int variety = detailDTOSet.size();
        returnedBill.setVariety(variety);
        //配送总价
        returnedBill.setTotalPrice(editReturnedBillDTO.getTotalPrice());
        //按货物还是按原料
        returnedBill.setBasicEnum(editReturnedBillDTO.getBasicEnum());
        // 转换单据明细信息
        Set<ReturnedBillDetail> detailSet = listDetailMapToSetDetail(detailDTOSet);
        // 设置单据明细信息
        returnedBill.setBillDetails(detailSet);

        return returnedBill;
    }

    /**
     * 验证提交数据信息
     *
     * @param addReturnedBillDTO
     */
    private void verification(AddReturnedBillDTO addReturnedBillDTO) {
        //来源单号
        if (addReturnedBillDTO.getBillProperty() != PropertyEnum.NOPLAN) {
            if (StringUtils.isEmpty(addReturnedBillDTO.getSourceCode())) {
                throw new DataException("500", "来源单号为空");
            }
        }
        if (addReturnedBillDTO.getBillProperty() == null) {
            throw new DataException("500", "单据属性为空");
        }
        if (addReturnedBillDTO.getInStation() == null) {
            throw new DataException("500", "入库站点为空");
        }
        if (addReturnedBillDTO.getOutStation() == null) {
            throw new DataException("500", "出库站点为空");
        }
        if (addReturnedBillDTO.getBillDetails() == null) {
            throw new DataException("500", "货物详细为空");
        }
        if (StringUtils.isEmpty(addReturnedBillDTO.getOperatorCode())) {
            throw new DataException("500", "操作人编码为空");
        }
        if (addReturnedBillDTO.getOutMemo() == null) {
            addReturnedBillDTO.setOutMemo("");
        }
        if (addReturnedBillDTO.getPlanMemo() == null) {
            addReturnedBillDTO.setPlanMemo("");
        }
        if (addReturnedBillDTO.getBasicEnum() == null) {
            throw new DataException("500", "按货物按原料为空");
        }
        if (addReturnedBillDTO.getTotalPrice() == null) {
            throw new DataException("500", "总价为空");
        }
    }

    /**
     * 根据多条件查询退库单据信息
     *
     * @param conditionQueryReturnedBill 查询条件
     * @return
     */
    public QueryReturnedBillDTO findByConditions(ConditionQueryReturnedBill conditionQueryReturnedBill) {
        Page<ReturnedBill> returnedBillPage = returnedBillQueryService.findPageByCondition(conditionQueryReturnedBill);

        QueryReturnedBillDTO QueryReturnedBillDTO = new QueryReturnedBillDTO();
        // 转换
        List<ReturnedBillDTO> billDTOList = toMapDTO(returnedBillPage.getContent());
        // 总数
        QueryReturnedBillDTO.setTotalNumber(returnedBillPage.getTotalElements());
        // 退库单据数据
        QueryReturnedBillDTO.setContent(billDTOList);

        return QueryReturnedBillDTO;
    }

    private List<ReturnedBillDTO> toMapDTO(List<ReturnedBill> returnedBillList) {
        List<ReturnedBillDTO> returnedBillDTOList = new ArrayList<>();
        for (ReturnedBill returnedBill : returnedBillList) {
            ReturnedBillDTO returnedBillDTO = new ReturnedBillDTO();
            returnedBillDTO.setBillCode(returnedBill.getBillCode());
            returnedBillDTO.setAuditState(returnedBill.getAuditState());
            returnedBillDTO.setSubmitState(returnedBill.getSubmitState());
            returnedBillDTO.setAmount(returnedBill.getAmount());

            returnedBillDTO.setAuditMemo(returnedBill.getAuditMemo());

            // TODO: 2018/1/8 测试使用假数据
//            returnedBillDTO.setAuditPersonCode("");
//            returnedBillDTO.setOperatorName("");
//            returnedBillDTO.setOperatorCode(returnedBill.getOperatorCode());
//            returnedBillDTO.setAuditPersonCode(returnedBill.getAuditPersonCode());
            returnedBillDTO.setBasicEnum(returnedBill.getBasicEnum());
            returnedBillDTO.setBillDetails(billDetailsToReturnedBillDetailDTO(returnedBill.getBillDetails()));
            returnedBillDTO.setInWareHouseTime(returnedBill.getInWareHouseTime());
            returnedBillDTO.setBillProperty(returnedBill.getBillProperty());
            returnedBillDTO.setBillPurpose(returnedBill.getBillPurpose());
            returnedBillDTO.setBillState(returnedBill.getBillState());
            returnedBillDTO.setBillType(returnedBill.getBillType());
            returnedBillDTO.setInLocation(returnedBill.getInLocation());
            returnedBillDTO.setInOrOutState(returnedBill.getInOrOutState());
            returnedBillDTO.setOutLocation(returnedBill.getOutLocation());
            returnedBillDTO.setOutMemo(returnedBill.getOutMemo());
            returnedBillDTO.setPlanMemo(returnedBill.getPlanMemo());
            returnedBillDTO.setProgress(returnedBill.getProgress());
            returnedBillDTO.setRootCode(returnedBill.getRootCode());
            returnedBillDTO.setSourceCode(returnedBill.getSourceCode());
            returnedBillDTO.setTotalPrice(returnedBill.getTotalPrice());
            returnedBillDTO.setVariety(returnedBill.getVariety());
            returnedBillDTO.setCreateTime(returnedBill.getCreateTime());
            returnedBillDTOList.add(returnedBillDTO);
        }
        return returnedBillDTOList;
    }

    private Set<ReturnedBillDetailDTO> billDetailsToReturnedBillDetailDTO(Set<ReturnedBillDetail> returnedBillDetails){
        Set<ReturnedBillDetailDTO> returnedBillDetailDTOSet = new HashSet<>();
        for (ReturnedBillDetail returnedBillDetail : returnedBillDetails) {
            ReturnedBillDetailDTO returnedBillDetailDTO = new ReturnedBillDetailDTO();
            returnedBillDetailDTO.setRawMaterial((RawMaterial) returnedBillDetail.getGoods());
            returnedBillDetailDTO.setActualAmount(returnedBillDetail.getActualAmount());
            returnedBillDetailDTO.setMemo(returnedBillDetail.getMemo());
            returnedBillDetailDTO.setShippedAmount(returnedBillDetail.getShippedAmount());
            returnedBillDetailDTOSet.add(returnedBillDetailDTO);
        }
        return returnedBillDetailDTOSet;
    }

}
