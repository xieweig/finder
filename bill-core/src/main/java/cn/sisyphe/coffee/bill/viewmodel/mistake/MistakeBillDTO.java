package cn.sisyphe.coffee.bill.viewmodel.mistake;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
/**
 * @author Amy on 2018/1/18.
 * describe：
 */
public class MistakeBillDTO extends BillDTO<MistakeBillDetailDTO> {

    public MistakeBillDTO() {
        setBillType(BillTypeEnum.MISTAKE);
    }

    /**
     * 备注信息
     */
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
