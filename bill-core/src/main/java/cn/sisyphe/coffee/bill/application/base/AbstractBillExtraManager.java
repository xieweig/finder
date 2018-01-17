package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by heyong on 2018/1/16 16:01
 * Description:
 *
 * @author heyong
 */
public abstract class AbstractBillExtraManager<T extends Bill, D extends BillDTO, Q extends ConditionQueryBill> extends AbstractBillManager<T> {

    private BillExtraService<T, Q> billExtraService;
    private SharedManager sharedManager;

    public BillExtraService<T, Q> getBillExtraService() {
        return billExtraService;
    }

    public void setBillExtraService(BillExtraService<T, Q> billExtraService) {
        this.billExtraService = billExtraService;
    }



    public SharedManager getSharedManager() {
        return sharedManager;
    }

    public void setSharedManager(SharedManager sharedManager) {
        this.sharedManager = sharedManager;
    }

    public AbstractBillExtraManager(BillRepository<T> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<T, Q> billExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher);
        this.billExtraService = billExtraService;
        this.sharedManager = sharedManager;
    }


    /**
     * 暂存调剂计划
     *
     * @param billDTO 前端dto
     * @return billCode 单据编码
     */
    public String saveBill(D billDTO) {
        T bill = prepareBill(billDTO);
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
        T bill = prepareBill(billDTO);
        dtoToBill(bill, billDTO);
        return submit(bill).getBillCode();
    }

    /**
     * 审核不通过
     *
     * @param billCode        单据编码
     * @param auditPersonCode 审核人编码
     */
    public T auditBill(String billCode, String auditPersonCode, boolean isSuccess) {

        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        } else if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }

        T bill = billExtraService.findByBillCode(billCode);
        bill.setAuditPersonCode(auditPersonCode);
        audit(bill, isSuccess);

        return bill;
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


    /**
     * 初始化bill
     *
     * @param billDTO 前端dto
     * @return AdjustBill 调剂计划实体
     */
    private T prepareBill(D billDTO) {

        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            return (T) new BillFactory().createBill(billDTO.getBillType());
        }

        T bill = billExtraService.findByBillCode(billDTO.getBillCode());

        if (bill == null) {
            throw new DataException("432434", "没有找到该计划单");
        }

        return bill;
    }





    /**
     * 多条件查询
     *
     * @param conditionQuery
     * @param billPurposeEnum
     * @return
     */
    public Page<D> findBillByCondition(Q conditionQuery, BillPurposeEnum billPurposeEnum) {

        if (!StringUtils.isEmpty(conditionQuery.getOperatorName())) {
            // SpringCloud调用查询用户编码
            List<String> userCodeList = sharedManager.findByLikeUserName(conditionQuery.getOperatorName());
            conditionQuery.setOperatorCodeList(userCodeList);
        }

        conditionQuery.setPurposeEnum(billPurposeEnum);
        Page<T> billPage = billExtraService.findPageByCondition(conditionQuery);

        return billPage.map(source -> billToListDto(source));
    }


    /**
     * 根据sourceCode查询单据
     * @param sourceCode
     * @return
     */
    public T findBySourceCode(String sourceCode) {
        return billExtraService.findBySourceCode(sourceCode);
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
        source.getBillDetails().clear();

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

        billDTO.getBillDetails().clear();
        BeanUtils.copyProperties(billDTO, bill, "createTime");
    }

    /**
     * bill - dto 详细
     *
     * @param bill
     * @return
     */
    protected D billToDto(T bill) {
        BillDTO billDTO = new BillDTO();
        bill.getBillDetails().clear();
        BeanUtils.copyProperties(bill, billDTO);

        return (D) billDTO;
    }


}
