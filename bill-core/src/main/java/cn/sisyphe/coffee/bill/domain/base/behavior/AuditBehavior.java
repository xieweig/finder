package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.framework.web.exception.DataException;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：单据审核动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class AuditBehavior extends AbstractBillBehavior {

    /**
     * 执行动作
     */
    @Override
    public void doAction() {
        Bill bill = getBillService().getBill();
        if (bill != null) {
            // 操作是需要审核成功，并且单据状态是提交状态，才能赋予审核成功状态
            if ((Constant.AUDIT_SUCCESS_VALUE).equals(behaviorType)
                    && bill.getBillState().equals(BillStateEnum.SUBMITTED)) {
                bill.setBillState(BillStateEnum.AUDITSUCCESS);
            }
            // 操作是需要审核失败，并且单据状态是提交状态，才能赋予审核失败状态
            else if ((Constant.AUDIT_FAILURE_VALUE).equals(behaviorType)
                    && bill.getBillState().equals(BillStateEnum.SUBMITTED)) {
                bill.setBillState(BillStateEnum.AUDITFAILURE);
            } else {
                throw new DataException("20004", "当前状态不能审核");
            }
        } else {
            throw new DataException("20404", "单据为空");
        }
    }


    /**
     * 根据执行类型做相关操作
     *
     * @param billService
     * @param behaviorType
     */
    public AuditBehavior(AbstractBillService billService, String behaviorType) {
        super(billService);
        this.behaviorType = behaviorType;

    }

    /**
     * 执行具体操作类型
     */
    private String behaviorType;
}
