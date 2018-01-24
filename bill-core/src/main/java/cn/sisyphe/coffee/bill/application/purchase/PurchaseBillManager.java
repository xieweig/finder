package cn.sisyphe.coffee.bill.application.purchase;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillExtraService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDTO;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单协调层
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class PurchaseBillManager extends AbstractBillExtraManager<PurchaseBill, PurchaseBillDTO, ConditionQueryPurchaseBill> {

    @Autowired
    public PurchaseBillManager(BillRepository<PurchaseBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<PurchaseBill, ConditionQueryPurchaseBill> billExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager);
    }

    /**
     * 重写提交方法
     *
     * @param billDTO 前端dto
     * @return
     */
    @Override
    public PurchaseBillDTO submitBill(PurchaseBillDTO billDTO) {
        // 根据货运单号查询是否存在
        verificationFreightCode(billDTO);
        // 验证字段信息
        verificationPurchase(billDTO);
        return super.submitBill(billDTO);
    }

    /**
     * 重写保存方法
     *
     * @param billDTO 前端dto
     * @return
     */
    @Override
    public PurchaseBillDTO saveBill(PurchaseBillDTO billDTO) {
        // 根据货运单号查询是否存在
        verificationFreightCode(billDTO);
        return super.saveBill(billDTO);
    }

    /**
     * 重写DTO转换单据方法
     *
     * @param bill
     * @param billDTO
     * @return
     */
    @Override
    protected PurchaseBill dtoToBill(PurchaseBill bill, PurchaseBillDTO billDTO) {
        PurchaseBill purchaseBill = super.dtoToBill(bill, billDTO);
        Supplier supplier = billDTO.getSupplier();
        purchaseBill.setOutLocation(supplier);
        purchaseBill.setSourceCode(bill.getBillCode());
        return purchaseBill;
    }

    /**
     * 重写bill转换DTO
     *
     * @param bill
     * @return
     */
    @Override
    protected PurchaseBillDTO billToDto(PurchaseBill bill) {
        PurchaseBillDTO purchaseBillDTO = super.billToDto(bill);
        Supplier supplier = (Supplier) bill.getOutLocation();
        purchaseBillDTO.setSupplier(supplier);


        return purchaseBillDTO;
    }

    /**
     * 根据货运单号查询是否存在
     *
     * @param billDTO
     * @return
     */
    private void verificationFreightCode(PurchaseBillDTO billDTO) {
        // 根据货运单号查询是否存在
        PurchaseBill purchaseBill = ((PurchaseBillExtraService) getBillExtraService()).findByFreightCode(billDTO.getFreightCode());
        if (StringUtils.isEmpty(billDTO.getBillCode())) {
            if (purchaseBill != null) {
                throw new DataException("500", "货运单号重复！");
            }
        } else {
            if (purchaseBill != null && !purchaseBill.getBillCode().equals(billDTO.getBillCode())) {
                throw new DataException("500", "货运单号重复！");
            }
        }
    }

    /**
     * 提交验证字段方法
     *
     * @param billDTO
     */
    private void verificationPurchase(PurchaseBillDTO billDTO) {
        // 验证货运单号
        if (StringUtils.isEmpty(billDTO.getFreightCode())) {
            throw new DataException("500", "货运单号为空");
        }
        // 验证站点
        if (billDTO.getInLocation() == null) {
            throw new DataException("500", "站点为空");
        }
        // 验证库房
        if (billDTO.getInLocation() != null && billDTO.getInLocation().getStorage() == null) {
            throw new DataException("500", "库房为空");
        }
        // 验证发货件数
        if (billDTO.getShippedAmount() == null || billDTO.getShippedAmount() <= 0) {
            throw new DataException("500", "发货件数为空");
        }
        // 验证实收件数
        if (billDTO.getActualAmount() == null || billDTO.getActualAmount() <= 0) {
            throw new DataException("500", "实收件数为空");
        }
        // 验证供应商
        if (billDTO.getSupplier() == null) {
            throw new DataException("500", "供应商信息为空");
        }
        // 验证明细信息
        if (billDTO.getBillDetails() == null || billDTO.getBillDetails().size() <= 0) {
            throw new DataException("500", "明细信息为空");
        }


    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.PURCHASE;
    }
}
