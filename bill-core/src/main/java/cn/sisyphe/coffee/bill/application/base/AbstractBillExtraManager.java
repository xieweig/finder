package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by heyong on 2018/1/16 16:01
 * Description:
 *
 * @author heyong
 */
public abstract class AbstractBillExtraManager<T extends Bill, D extends BillDTO, Q extends ConditionQueryBill> extends AbstractBillManager<T> {

    private BillExtraService<T, Q> billExtraService;
    private PlanBillExtraService planBillExtraService;
    private SharedManager sharedManager;

    public BillExtraService<T, Q> getBillExtraService() {
        return billExtraService;
    }

    public void setBillExtraService(BillExtraService<T, Q> billExtraService) {
        this.billExtraService = billExtraService;
    }

    public PlanBillExtraService getPlanBillExtraService() {
        return planBillExtraService;
    }

    public void setPlanBillExtraService(PlanBillExtraService planBillExtraService) {
        this.planBillExtraService = planBillExtraService;
    }

    public SharedManager getSharedManager() {
        return sharedManager;
    }

    public void setSharedManager(SharedManager sharedManager) {
        this.sharedManager = sharedManager;
    }

    public AbstractBillExtraManager(BillRepository<T> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<T, Q> billExtraService, PlanBillExtraService planBillExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher);
        this.billExtraService = billExtraService;
        this.planBillExtraService = planBillExtraService;
        this.sharedManager = sharedManager;
    }


    /**
     * 暂存调剂计划
     *
     * @param billDTO 前端dto
     * @return billCode 单据编码
     */
    public String saveBill(D billDTO) {
        T bill = prepareAdjustBill(billDTO);
        dtoToBill(bill, billDTO);
        return save(bill).getBillCode();
    }


    /**
     * 提交调剂计划
     *
     * @param billDTO 前端dto
     * @return billCode 单据编码
     */
    public String submitBill(D billDTO) {
        T bill = prepareAdjustBill(billDTO);
        dtoToBill(bill, billDTO);
        return submit(bill).getBillCode();
    }

    /**
     * 审核不通过
     *
     * @param billCode        单据编码
     * @param auditPersonCode 审核人编码
     */
    public void auditBill(String billCode, String auditPersonCode, boolean isSuccess) {

        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        } else if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }

        T bill = billExtraService.findByBillCode(billCode);
        bill.setAuditPersonCode(auditPersonCode);
        audit(bill, isSuccess);
    }


    /**
     * 将单据动作更改为--打开
     *
     * @param billCode 单据编号
     * @return QueryOneAdjustDTO
     */
    public D openBill(String billCode, String operatorCode) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        }

        T bill = billExtraService.findByBillCode(billCode);

        // 如果单据是提交状态，则进行打开动作
        if (bill.getBillState().equals(BillStateEnum.SUBMITTED)) {
            bill.setOperatorCode(operatorCode);
            // 打开单据
            open(bill);
        }


        return billToDto(bill);
    }


//    /**
//     * 公共方法
//     *
//     * @param bill
//     * @return
//     */
//    private D commonFun(T bill) {
//
//        // 如果是根据原料拣货，则需要去查询一下总部计划单里面的数据
//        if (BasicEnum.BY_MATERIAL.equals(bill.getBasicEnum())) {
//            PlanBill planBill = planBillExtraService.findByBillCode(bill.getRootCode());
//            Set<PlanBillDetail> billDetails = planBill.getBillDetails();
//            return billToDto(bill, billDetails);
//        } else {
//            return billToDto(bill, null);
//        }
//    }


    /**
     * 初始化adjustBill
     *
     * @param billDTO 前端dto
     * @return AdjustBill 调剂计划实体
     */
    private T prepareAdjustBill(D billDTO) {

        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            return (T) new BillFactory().createBill(BillTypeEnum.ADJUST);
        }

        T bill = billExtraService.findByBillCode(billDTO.getBillCode());

        if (bill == null) {
            throw new DataException("432434", "没有找到该计划单");
        }

        return bill;
    }

    /**
     * 入库单查询
     *
     * @param conditionQuery 条件查询参数
     * @return 查询结果带分页信息
     */
    public Page<D> findInStorageBillByCondition(Q conditionQuery) {
        return findBillByCondition(conditionQuery, BillPurposeEnum.IN_STORAGE);
    }


    /**
     * 出库单查询
     *
     * @param conditionQuery 条件查询参数
     * @return 查询结果带分页信息
     */
    public Page<D> findOutStorageBillByCondition(Q conditionQuery) {
        return findBillByCondition(conditionQuery, BillPurposeEnum.OUT_STORAGE);
    }


    private Page<D> findBillByCondition(Q conditionQuery, BillPurposeEnum billPurposeEnum) {
        // SpringCloud调用查询用户编码
        List<String> userCodeList = sharedManager.findByLikeUserName(conditionQuery.getOperatorName());

        conditionQuery.setOperatorCodeList(userCodeList);
        conditionQuery.setPurposeEnum(billPurposeEnum);
        Page<T> adjustBillPage = billExtraService.findPageByCondition(conditionQuery);

        return adjustBillPage.map(source -> billToListDto(source));
    }

    /**
     * dto 转换 bill - 条件查询 dto list
     *
     * @param source
     * @return
     */
    protected D billToListDto(T source) {
        BillDTO billDTO = new BillDTO();
        // 清空明细
        source.setBillDetails(null);

        BeanUtils.copyProperties(source, billDTO);


        return (D) billDTO;
    }

    /**
     * dto - bill
     *
     * @param bill
     * @param billDTO
     */
    protected void dtoToBill(T bill, D billDTO) {

        BeanUtils.copyProperties(billDTO, bill);
    }

    /**
     * bill - dto 详细
     *
     * @param bill
     * @return
     */
    protected D billToDto(T bill) {
        BillDTO billDTO = new BillDTO();

        BeanUtils.copyProperties(bill, billDTO);

        return (D) billDTO;
    }


}
