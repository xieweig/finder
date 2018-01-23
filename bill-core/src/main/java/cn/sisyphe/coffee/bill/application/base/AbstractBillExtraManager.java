package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.util.BillToDtoExtraProcessor;
import cn.sisyphe.coffee.bill.util.DtoToBillExtraProcessor;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTOFactory;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.framework.web.exception.DataException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by heyong on 2018/1/16 16:01
 * Description:
 *
 * @author heyong
 */
public abstract class AbstractBillExtraManager<T extends Bill, D extends BillDTO, Q extends ConditionQueryBill> extends AbstractBillManager<T> {

    private BillExtraService<T, Q> billExtraService;
    protected SharedManager sharedManager;

    protected BillExtraService<T, Q> getBillExtraService() {
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
     * 暂存计划
     *
     * @param billDTO 前端dto
     * @return billCode 单据编码
     */
    public D saveBill(D billDTO) {
        T bill = prepareBill(billDTO.getBillCode());
        bill = dtoToBill(bill, billDTO);
        save(bill);

        return billToDto(bill);
    }


    /**
     * 提交计划
     *
     * @param billDTO 前端dto
     * @return billCode 单据编码
     */
    public D submitBill(D billDTO) {
        T bill = prepareBill(billDTO.getBillCode());
        bill = dtoToBill(bill, billDTO);
        submit(bill);

        return billToDto(bill);
    }

    /**
     * 审核不通过
     *
     * @param billCode        单据编码
     * @param auditPersonCode 审核人编码
     */
    public D auditBill(String billCode, String auditPersonCode, String auditMemo, boolean isSuccess) {

        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("404", "单据编码为空");
        } else if (StringUtils.isEmpty(auditPersonCode)) {
            throw new DataException("404", "审核人编码为空");
        }

        T bill = billExtraService.findByBillCode(billCode);
        bill.setAuditPersonCode(auditPersonCode);
        bill.setAuditMemo(auditMemo);
        audit(bill, isSuccess);

        return billToDto(bill);
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
     * @param billCode 前端dto
     * @return AdjustBill 调剂计划实体
     */
    protected T prepareBill(String billCode) {

        if (StringUtils.isEmpty(billCode)) {
            return (T) new BillFactory().createBill(billType());
        }

        T bill = billExtraService.findByBillCode(billCode);

        if (bill == null) {
            throw new DataException("432434", "没有找到该计划单");
        }

        return bill;
    }


    /**
     * 查询单据
     *
     * @param billCode
     * @return
     */
    public D findBillDtoByBillCode(String billCode) {

        T bill = billExtraService.findByBillCode(billCode);
        if(bill == null){
            return null;
        }
        return billToDto(bill);
    }

    /**
     * 根据sourceCode查询单据
     *
     * @param sourceCode
     * @return
     */
    public D findBillDtoBySourceCode(String sourceCode) {
        T bill = billExtraService.findBySourceCode(sourceCode);
        return billToDto(bill);
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
     * bill 转换 dto - 条件查询 dto list
     *
     * @param bill
     * @return
     */
    protected D billToListDto(T bill) {
        // 清空明细
//        bill.getBillDetails().clear();

        return billToDto(bill);
    }

    /**
     * dto - bill
     *
     * @param bill
     * @param billDTO
     */
    protected T dtoToBill(T bill, D billDTO) {
        T newBill = JSON.parseObject(JSON.toJSONString(billDTO), (Class<T>) bill.getClass(), new DtoToBillExtraProcessor());

        // 重复提交
        if (bill.getBillId() != null){
            newBill.setBillId(bill.getBillId());
            newBill.setVersion(bill.getVersion());
        }

        // 解决 inLocation / outLocation 问题
        BeanUtils.copyProperties(billDTO, newBill, "createTime", "billDetails");
        return newBill;
    }

    /**
     * bill - dto 详细
     *
     * @param bill
     * @return
     */
    protected D billToDto(T bill) {
        D billDto = JSON.parseObject(JSON.toJSONString(bill), (Type) new BillDTOFactory().createBillDTO(bill.getBillType()).getClass(), new BillToDtoExtraProcessor());
        if (!StringUtils.isEmpty(bill.getOperatorCode())){
            billDto.setOperatorName(sharedManager.findOneByUserCode(bill.getOperatorCode()));
        }
        if (!StringUtils.isEmpty(bill.getAuditPersonCode())) {
            billDto.setAuditPersonName(sharedManager.findOneByUserCode(bill.getAuditPersonCode()));

        }
        return billDto;
    }


}
